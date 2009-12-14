/*
 * JOSSO: Java Open Single Sign-On
 *
 * Copyright 2004-2009, Atricore, Inc.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */

package org.josso.servlet.agent;

import com.sun.web.security.WebProgrammaticLogin;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Container;
import org.apache.catalina.Session;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.josso.agent.Constants;
import org.josso.agent.LocalSession;
import org.josso.agent.Lookup;
import org.josso.agent.SSOAgentRequest;
import org.josso.agent.SSOPartnerAppConfig;
import org.josso.agent.SingleSignOnEntry;
//import org.josso.agent.http.HttpSSOAgent;
import org.josso.agent.http.WebAccessControlUtil;
import org.josso.gl2.agent.CatalinaLocalSession;
import org.josso.gl2.agent.FacesSSOAgent;

/**
 * JOSSO Servlet Filter for Generic SSO Agent, this replaces the Valve in tomcat or other container specific components.
 * The fillter will handle web logic to authenticate, login and logout users.
 *
 * Date: Nov 27, 2007
 * Time: 9:28:53 AM
 *
 * @author <a href="mailto:sgonzalez@josso.org">Sebastian Gonzalez Oyuela</a>
 */
public class JossoFilter implements Filter {

    public static final String KEY_JOSSO_SAVED_REQUEST = "org.josso.servlet.agent.savedRequest";

    private static final String KEY_SESSION_MAP = "org.josso.servlet.agent.sessionMap";

    /**
     * One agent instance for all applications.
     */
    private FacesSSOAgent _agent;
    private Container container = null;
    private String jossoSessionId = null;
    private String theOriginal = null;
    private Cookie cookie = null;
    private LocalSession localSession = null;
    private Session session = null;
    private String assertionId = null;
    private int iBoucle = 0;
    private int debug = 0;
    private final String COOKIE_LOGIN = "JOSSO_SESSION_LOGIN";
    private ServletContext ctx = null;

    /**
     * Logger
     */
    private static final Log log = LogFactory.getLog(JossoFilter.class);

    public JossoFilter() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // Validate and update our current component state
        ctx = filterConfig.getServletContext();
        String nom = filterConfig.getInitParameter("leNom");
        logg("Initialisation du filtre pour="+nom+" contextName="+ctx.getContextPath());
        ctx.setAttribute(KEY_SESSION_MAP, new HashMap());
        if (_agent == null) {

            try {

                Lookup lookup = Lookup.getInstance();
                lookup.init("josso-agent2-config.xml", ctx.getContextPath()); // For spring compatibility ...

                // We need at least an abstract SSO Agent
                _agent = (FacesSSOAgent) lookup.lookupSSOAgent();
                if (debug==1)
                    _agent.setDebug(1);
                _agent.start();
            } catch (Exception e) {
                throw new ServletException("Error starting SSO Agent : " + e.getMessage(), e);
            }


        }


    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest hreq =
                (HttpServletRequest) request;

        HttpServletResponse hres =
                (HttpServletResponse) response;
        debug = 1;
        if (debug==1)
            log.debug("Processing : " + hreq.getContextPath());

        try {
            // ------------------------------------------------------------------
            // Check with the agent if this context should be processed.
            // ------------------------------------------------------------------
            String contextPath = hreq.getContextPath();
            String vhost = hreq.getServerName();
            //_agent.setCatalinaContainer(container);
            // In catalina, the empty context is considered the root context
            if ("".equals(contextPath))
                contextPath = "/";

             // T1 si l'appli n'est pas partenaire alors pas de SSO on continue
            if (!_agent.isPartnerApp(vhost, contextPath)) {
                filterChain.doFilter(hreq, hres);
                    logg("T1 Context is not a josso partner app : " + hreq.getContextPath());
                    hres.sendError(hres.SC_UNAUTHORIZED, "vérifier config agent ajouter le contexte");
                return;
              }else{
                  logg("T1 Context IS a josso partner app =" + hreq.getContextPath());
              }

            //T2
            // ------------------------------------------------------------------
            // Check some basic HTTP handling
            // ------------------------------------------------------------------
            // P3P Header for IE 6+ compatibility when embedding JOSSO in a IFRAME
            SSOPartnerAppConfig cfg = _agent.getPartnerAppConfig(vhost, contextPath);
            if (cfg.isSendP3PHeader() && !hres.isCommitted()) {
                hres.setHeader("P3P", cfg.getP3PHeaderValue());
            }

            //T9
            // ------------------------------------------------------------------
            // Check if this URI is subject to SSO protection
            // ------------------------------------------------------------------
            if (_agent.isResourceIgnored(cfg, hreq)) {
                logg("T9 ressource non ssoisé (accès libre)");
                filterChain.doFilter(hreq, hres);
                return;
            }

            // Get our session ...
            HttpSession session = hreq.getSession(true);
            
            testCookieSession(hreq);
            //TA1 pas de cookie et on trouve la page de login attendue dans la déclaration du contexte agent
            logg("TA1 uri="+hreq.getRequestURI()+" se termine par "+cfg.getLoginPage()+" rep="+hreq.getRequestURI().endsWith(cfg.getLoginPage())+" test cookie="+testCookie2Session(hreq, session.getId()));
            if (!testCookie2Session(hreq, session.getId()) && hreq.getRequestURI().endsWith(cfg.getLoginPage())) {
                 logg("TA1 on demande l'authentification locale on switche vers Josso");
                 Cookie gato = newJossoCookie2(hreq.getContextPath(), session.getId(), COOKIE_LOGIN);
                 hres.addCookie(gato);
                 String loginUrl = _agent.buildLoginUrl(hreq);
                 hres.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                 //response.setHeader("Location", jeVeux);
                 hres.sendRedirect(loginUrl);
                 return;
            }
            //T3 on revient après authentification réussie et pour finalisation
            if(_agent.isSSOIDloged(jossoSessionId)){
                iBoucle++;
                logg("T3 Info retour authentifié pour " + jossoSessionId+" faire retour vers "+theOriginal);
                SSOAgentRequest r = doMakeSSOAgentRequest(SSOAgentRequest.ACTION_ESTABLISH_SECURITY_CONTEXT, jossoSessionId, localSession, null, hreq, hres);
                SingleSignOnEntry entry = _agent.processRequest(r);

                if (debug==1)
                    log.debug("Executed agent.");

                // Get session map for this servlet context.
                Map sessionMap = (Map) hreq.getSession().getServletContext().getAttribute(KEY_SESSION_MAP);
                if (sessionMap.get(localSession.getWrapped()) == null) {
                    // the local session is new so, make the valve listen for its events so that it can
                    // map them to local session events.
                    // Not supported : session.addSessionListener(this);
                    sessionMap.put(session, localSession);
                }

                // ------------------------------------------------------------------
                // Has a valid user already been authenticated?
                // ------------------------------------------------------------------
                if (debug==1)
                    log.debug("Process request for '" + hreq.getRequestURI() + "'");

                if (entry != null) {
                    if (debug==1)
                        log.debug("Principal '" + entry.principal +
                            "' has already been authenticated");
                    // TODO : Not supported
                    // (request).setAuthType(entry.authType);
                    // (request).setUserPrincipal(entry.principal);
                } else {
                    log.info("No Valid SSO Session, attempt an optional login?");
                    // This is a standard anonymous request!

                    if (cookie != null) {
                            // cookie is not valid
                            cookie = _agent.newJossoCookie(hreq.getContextPath(), "-");
                            hres.addCookie(cookie);
                    }

                    if (cookie != null || (getSavedRequestURL(session) == null && _agent.isAutomaticLoginRequired(hreq))) {

                        if (debug==1)
                            log.debug("SSO Session is not valid, attempting automatic login");

                        // Save current request, so we can co back to it later ...
                        saveRequestURL(hreq, session);
                        String loginUrl = _agent.buildLoginOptionalUrl(hreq);

                        if (debug==1)
                            log.debug("Redirecting to login url '" + loginUrl + "'");

                        //set non cache headers
                        _agent.prepareNonCacheResponse(hres);
                        hres.sendRedirect(hres.encodeRedirectURL(loginUrl));
                        return;
                    } else {
                        if (debug==1)
                            log.debug("SSO cookie is not present, but login optional process is not required");
                    }

                }
                try {
                    logg("Avant sur webProgrammaticLogin -------------"+iBoucle);

                    if(!WebProgrammaticLogin.login(jossoSessionId, assertionId, "jossoRealm", hreq, hres)){
                                logg("Erreur sur webProgrammaticLogin");
                    }else{
                                 logg("Réussite sur webProgrammaticLogin");
                    }
                    logg("Après sur webProgrammaticLogin-------------"+iBoucle);
               }catch (Exception err){
                    logg("SSOAgentValve Erreur2 finalisation contexte securité", err);
                    throw new ServletException(err);
                }

                // propagate the login and logout URLs to
                // partner applications.
                hreq.setAttribute("org.josso.agent.gateway-login-url", _agent.getGatewayLoginUrl() );
                hreq.setAttribute("org.josso.agent.gateway-logout-url", _agent.getGatewayLogoutUrl() );
                hreq.setAttribute("org.josso.agent.ssoSessionid", jossoSessionId);

                // ------------------------------------------------------------------
                // Invoke the next Valve in our pipeline
                // ------------------------------------------------------------------
                filterChain.doFilter(hreq, hres);
            }


            //T4
            // ------------------------------------------------------------------
            // Check if the partner application required the login form
            // ------------------------------------------------------------------
            if (debug==1)
                log.debug("T4 Checking if its a josso_login_request for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOLoginUri()) || 
            		hreq.getRequestURI().endsWith(_agent.getJOSSOUserLoginUri())) {

                if (debug==1)
                    log.debug("T4 josso_login_request received for uri '" + hreq.getRequestURI() + "'");

                //save referer url in case the user clicked on Login from some public resource (page)
                //so agent can redirect the user back to that page after successful login
                if (hreq.getRequestURI().endsWith(_agent.getJOSSOUserLoginUri())) {
                	saveLoginBackToURL(hreq, session, true);
                } else {
                	saveLoginBackToURL(hreq, session, false);
                }
                
                String loginUrl = _agent.buildLoginUrl(hreq);

                if (debug==1)
                    log.debug("T4 Redirecting to login url '" + loginUrl + "'");

                //set non cache headers
                _agent.prepareNonCacheResponse(hres);
                hres.sendRedirect(hres.encodeRedirectURL(loginUrl));

                return;

            }

            //T5
            // ------------------------------------------------------------------
            // Check if the partner application required a logout
            // ------------------------------------------------------------------
            if (debug==1)
                log.debug("T5 Checking if its a josso_logout request for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOLogoutUri())) {

                if (debug==1)
                    log.debug("T5 josso_logout request received for uri '" + hreq.getRequestURI() + "'");

                String logoutUrl = _agent.buildLogoutUrl(hreq, cfg);

                if (debug==1)
                    log.debug("T5 Redirecting to logout url '" + logoutUrl + "'");

                // Clear previous COOKIE ...
                Cookie ssoCookie = _agent.newJossoCookie(hreq.getContextPath(), "-");
                hres.addCookie(ssoCookie);
                
                // invalidate session (unbind josso security context)
                session.invalidate();
                
                //set non cache headers
                _agent.prepareNonCacheResponse(hres);
                hres.sendRedirect(hres.encodeRedirectURL(logoutUrl));

                return;

            }

            //T6
            testCookieSession(hreq);
            //T7
            // ------------------------------------------------------------------
            // Check if the partner application submitted custom login form
            // ------------------------------------------------------------------
            
            if (debug==1){
                log.debug("T7 Checking if its a josso_authentication for '" + hreq.getRequestURI() + "'");
            }
            if (hreq.getRequestURI().endsWith(_agent.getJOSSOAuthenticationUri())) {

            	if (debug==1){
                    log.debug("T7 josso_authentication received for uri '" + hreq.getRequestURI() + "'");
            	}
            	
            	GenericServletSSOAgentRequest customAuthRequest = (GenericServletSSOAgentRequest) doMakeSSOAgentRequest( SSOAgentRequest.ACTION_CUSTOM_AUTHENTICATION, jossoSessionId, localSession, null, hreq, hres);
                
                _agent.processRequest(customAuthRequest);
                
                return;
            }
            //T8
            // si pas de cookie de session SSO
            if (cookie == null || cookie.getValue().equals("-")) {
            	
            	// ------------------------------------------------------------------
                // Trigger LOGIN OPTIONAL if required
                // ------------------------------------------------------------------

            	 if (debug==1)
            		 log.debug("T8 SSO cookie is not present, verifying optional login process ");

                // We have no cookie, remember me is enabled and a security check without assertion was received ...
                // This means that the user could not be identified ... go back to the original resource
                if (hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                    hreq.getParameter("josso_assertion_id") == null) {

                	 if (debug==1)
                		 log.debug("T8-1 "+_agent.getJOSSOSecurityCheckUri() + " received without assertion.  Login Optional Process failed");

                    String requestURI = getSavedRequestURL(session);
                    _agent.prepareNonCacheResponse(hres);
                    hres.sendRedirect(hres.encodeRedirectURL(requestURI));
                    return;

                }
                
            	// This is a standard anonymous request!
                if (!hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri())) {

                    if (!_agent.isResourceIgnored(cfg, hreq) && 
                    		_agent.isAutomaticLoginRequired(hreq)) {

                        if (debug==1)
                        	log.debug("T8-2 SSO cookie is not present, attempting automatic login");

                        // Save current request, so we can co back to it later ...
                        saveRequestURL(hreq, session);
                        String loginUrl = _agent.buildLoginOptionalUrl(hreq);

                        if (debug==1)
                        	log.debug("T8-2 Redirecting to login url '" + loginUrl + "'");
                        
                        //set non cache headers
                        _agent.prepareNonCacheResponse(hres);
                        hres.sendRedirect(hres.encodeRedirectURL(loginUrl));
                        return;
                    } else {
                    	if (debug==1)
                    		log.debug("T8-2 SSO cookie is not present, but login optional process is not required");
                    }
                }
                
                if (debug==1)
                	log.debug("T8-3 SSO cookie is not present, checking for outbound relaying");

                if (!(hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                    hreq.getParameter("josso_assertion_id") != null)) {
                    log.debug("T8-3 SSO cookie not present and relaying was not requested, skipping");
                    filterChain.doFilter(hreq, hres);
                    return;
                }

            }

            // This URI should be protected by SSO, go on ...
            if (debug==1)
                log.debug("Session is: " + session);
            
            // ------------------------------------------------------------------
            // Invoke the SSO Agent
            // ------------------------------------------------------------------
            if (debug==1)
                log.debug("Executing agent...");
            //T10  /josso_security_check
            // ------------------------------------------------------------------
            // Check if a user has been authenitcated and should be checked by the agent.
            // ------------------------------------------------------------------
            if (debug==1)
                log.debug("T10 Checking if its a josso_security_check for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                hreq.getParameter("josso_assertion_id") != null) {

                if (debug==1)
                    log.debug("T10 josso_security_check received for uri '" + hreq.getRequestURI() + "' assertion id '" +
                            hreq.getParameter("josso_assertion_id")
                    );

                assertionId = hreq.getParameter(Constants.JOSSO_ASSERTION_ID_PARAMETER);

                GenericServletSSOAgentRequest relayRequest;

                if (debug==1)
                    log.debug("T10 Outbound relaying requested for assertion id [" + assertionId + "]");

                relayRequest = (GenericServletSSOAgentRequest) doMakeSSOAgentRequest( SSOAgentRequest.ACTION_RELAY, null, localSession, assertionId, hreq, hres);

                SingleSignOnEntry entry = _agent.processRequest(relayRequest);
                if (entry == null) {
                    // This is wrong! We should have an entry here!
                    log.error("T10-1 Outbound relaying failed for assertion id [" + assertionId + "], no Principal found.");
                    // Throw an exception and let the container send the INERNAL SERVER ERROR
                    throw new ServletException("No Principal found. Verify your SSO Agent Configuration!");
                }

                if (debug==1)
                    log.debug("T10-2 Outbound relaying succesfull for assertion id [" + assertionId + "]");

                if (debug==1)
                    log.debug("T10-2 Assertion id [" + assertionId + "] mapped to SSO session id [" + entry.ssoId + "]");

                // The cookie is valid to for the partner application only ... in the future each partner app may
                // store a different auth. token (SSO SESSION) value
                cookie = _agent.newJossoCookie(hreq.getContextPath(), entry.ssoId);
                hres.addCookie(cookie);

                // Redirect the user to the original request URI (which will cause
                // the original request to be restored)
                String requestURI = getSavedSplashResource(session);
                if(requestURI == null) {
                	requestURI = getSavedRequestURL(session);
	                if (requestURI == null) {
	
	                	if (cfg.getDefaultResource() != null) {
                            requestURI = cfg.getDefaultResource();
                        } else {
    		                // If no saved request is found, redirect to the partner app root :
	    	                requestURI = hreq.getRequestURI().substring(
		                        0, (hreq.getRequestURI().length() - _agent.getJOSSOSecurityCheckUri().length()));
                        }
	                	
	                    // If we're behind a reverse proxy, we have to alter the URL ... this was not necessary on tomcat 5.0 ?!
	                    String singlePointOfAccess = _agent.getSinglePointOfAccess();
	                    if (singlePointOfAccess != null) {
	                        requestURI = singlePointOfAccess + requestURI;
	                    } else {
	                        String reverseProxyHost = hreq.getHeader(org.josso.gateway.Constants.JOSSO_REVERSE_PROXY_HEADER);
	                        if (reverseProxyHost != null) {
	                            requestURI = reverseProxyHost + requestURI;
	                        }
	                    }
	
	                    if (debug==1)
	                        log.debug("T10 No saved request found, using : '" + requestURI + "'");
	                }
                }

                clearSavedRequestURLs(session);
               	_agent.clearAutomaticLoginReferer(hreq);
               	_agent.prepareNonCacheResponse(hres);
               	
               	// Check if we have a post login resource :
                String postAuthURI = cfg.getPostAuthenticationResource();
                if (postAuthURI != null) {
                    String postAuthURL = _agent.buildPostAuthUrl(hres, requestURI, postAuthURI);
                    if (debug==1)
                        log.debug("T10 Redirecting to post-auth-resource '" + postAuthURL  + "'");
                    hres.sendRedirect(postAuthURL);
                } else {
                	if (debug==1)
                         log.debug("T10 Redirecting to original '" + requestURI + "'");
                    hres.sendRedirect(hres.encodeRedirectURL(requestURI));
                }
               	_agent.addEntrySSOIDsuccessed(entry.ssoId, entry.getPrincipal().getName());
                return;
            }


        } finally {
            if (debug==1)
                log.debug("Processed : " + hreq.getContextPath());
        }
    }

    public void destroy() {
        // Validate and update our current component state
        if (_agent != null) {
            _agent.stop();
            _agent = null;
        }


    }
    
    /**
     * Return the splash resource from session so that we can redirect the user to it
     * if (s)he was logged in using custom form
     * @param session Our current session
     */
    private String getSavedSplashResource(HttpSession session){
    	return  (String)session.getAttribute(Constants.JOSSO_SPLASH_RESOURCE_PARAMETER);
    }    


    /**
     * Return the request URI (with the corresponding query string, if any)
     * from the saved request so that we can redirect to it.
     *
     * @param session Our current session
     */
    private String getSavedRequestURL(HttpSession session) {

        return  (String) session.getAttribute(WebAccessControlUtil.KEY_JOSSO_SAVED_REQUEST_URI);

    }

    /**
     * Creates a new request
     */
    protected SSOAgentRequest doMakeSSOAgentRequest(int action, String sessionId, LocalSession session, String assertionId,
                                                    HttpServletRequest hreq, HttpServletResponse hres) {
        GenericServletSSOAgentRequest r = new GenericServletSSOAgentRequest(action, sessionId, session, assertionId);
        r.setRequest(hreq);
        r.setResponse(hres);

        return r;

    }

    /**
     * Saves the original request URL into our session.
     *
     * @param request The request to be saved
     * @param session The session to contain the saved information
     * @throws IOException
     */
    private void saveRequestURL(HttpServletRequest hreq, HttpSession session) {
    	StringBuffer sb = new StringBuffer(hreq.getRequestURI());
        if (hreq.getQueryString() != null) {
            sb.append('?');
            sb.append(hreq.getQueryString());
        }
        session.setAttribute(WebAccessControlUtil.KEY_JOSSO_SAVED_REQUEST_URI, sb.toString());
    }

    /**
     * Save referer URI into our session for later use.
     * 
     * This method is used so agent can know from which
     * public resource (page) user requested login.
     * 
     * @param request http request
     * @param session current session
     * @param overrideSavedResource true if saved resource should be overridden, false otherwise
     */
    protected void saveLoginBackToURL(HttpServletRequest request, HttpSession session, boolean overrideSavedResource) {
    	String referer = request.getHeader("referer");
    	if ((getSavedRequestURL(session) == null || overrideSavedResource) && referer != null && !referer.equals("")) {
    		session.setAttribute(WebAccessControlUtil.KEY_JOSSO_SAVED_REQUEST_URI, referer);
        }
    }
    
    /**
     * Remove saved request URLs from session 
     * to avoid mixing up resources from previous operations 
     * (logins, logouts) with the current one.
     * 
     * @param session Our current session
     */
    protected void clearSavedRequestURLs(HttpSession session) {
    	session.removeAttribute(WebAccessControlUtil.KEY_JOSSO_SAVED_REQUEST_URI);
    	session.removeAttribute(Constants.JOSSO_SPLASH_RESOURCE_PARAMETER);
    }

    /**
     * Log a message on the Logger associated with our Container (if any).
     *
     * @param message Message to be logged
     */
    protected void logg(String message) {
        log.info(this.toString() + ": " + message);
    }


    /**
     * Log a message on the Logger associated with our Container (if any).
     *
     * @param message   Message to be logged
     * @param throwable Associated exception
     */
    protected void logg(String message, Throwable throwable) {
        log.error(this.toString() + ": " + message, throwable);
    }
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("JossoFilter[");
        // Sometimes the container is not present when this method is invoked ...
        sb.append(ctx != null ? ctx.getContextPath() : "");
        sb.append("]");
        return (sb.toString());

    }
    private void testCookieSession(HttpServletRequest req){
        // ------------------------------------------------------------------
        // Check for the single sign on cookie
        // ------------------------------------------------------------------
        if (debug==1)
            log.debug("Checking for SSO cookie");
        cookie = null;
        Cookie cookies[] = req.getCookies();
        if (cookies == null)
            cookies = new Cookie[0];
        for (int i = 0; i < cookies.length; i++) {
            if (org.josso.gateway.Constants.JOSSO_SINGLE_SIGN_ON_COOKIE.equals(cookies[i].getName())) {
                cookie = cookies[i];
                break;
            }
        }

        jossoSessionId = (cookie == null) ? null : cookie.getValue();
        localSession = new CatalinaLocalSession(session);

    }
     private Boolean testCookie2Session(HttpServletRequest req, String sessionID){
        // ------------------------------------------------------------------
        // Check for the single sign on cookie
        // ------------------------------------------------------------------
        Boolean ret = false;
        Cookie cookies[] = req.getCookies();
        if (cookies == null)
            cookies = new Cookie[0];
        for (int i = 0; i < cookies.length; i++) {
            if (COOKIE_LOGIN.equals(cookies[i].getName()) && sessionID.equals(cookies[i].getValue())) {
                ret = true;
                break;
            }
        }

        return ret;
    }
    /**
     * This creates a new JOSSO Cookie for the given path and value.
     *
     * @param path  the path associated with the cookie, normaly the partner application context.
     * @param value the SSO Session ID
     * @param  type le type du cookie
     * @return
     */
    private Cookie newJossoCookie2(String path, String value, String type) {

        // Some browsers don't like cookies without paths. This is useful for partner applications configured in the root context
        if (path == null || "".equals(path))
            path = "/";

        Cookie ssoCookie = new Cookie(type, value);
        ssoCookie.setMaxAge(-1);
        ssoCookie.setPath(path);

        // TODO : Check domain / secure ?
        //ssoCookie.setDomain(cfg.getSessionTokenScope());
        //ssoCookie.setSecure(true);

        return ssoCookie;
    }
}
