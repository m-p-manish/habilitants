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

import com.sun.web.security.WebPrincipal;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

//import javax.faces.FactoryFinder;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
//import javax.faces.context.FacesContextFactory;
//import javax.faces.lifecycle.Lifecycle;
//import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.ServletResponse;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.josso.agent.Constants;
import org.josso.agent.LocalSession;
import org.josso.agent.Lookup;
import org.josso.agent.SSOAgentRequest;
import org.josso.agent.SSOPartnerAppConfig;
import org.josso.agent.SingleSignOnEntry;
import org.josso.agent.http.HttpSSOAgent;
import org.josso.agent.http.WebAccessControlUtil;
import org.josso.gl2.agent.CatalinaSSOAgentRequest;

//import org.apache.catalina.Context;
import org.apache.catalina.core.ApplicationContextFacade;
import org.apache.catalina.Session;
import org.apache.coyote.tomcat5.CoyoteRequest;
import org.apache.coyote.tomcat5.CoyoteRequestFacade;

import org.josso.agent.http.HttpSSOAgentRequest;
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
public class GenericServletSSOAgentFilter implements Filter {

    public static final String KEY_JOSSO_SAVED_REQUEST = "org.josso.servlet.agent.savedRequest";

    private static final String KEY_SESSION_MAP = "org.josso.servlet.agent.sessionMap";

    // Used for the auth-type string.
    public static final String WEBAUTH_PROGRAMMATIC="PROGRAMMATIC";
    /**
     * One agent instance for all applications.
     */
    private FacesSSOAgent _agent;
    private int debug = 1;
    private ServletContext sCtx = null;


    public GenericServletSSOAgentFilter() {

    }

    public void init(FilterConfig filterConfig) throws ServletException {
        // Validate and update our current component state
        System.out.println("** Initialisation du filtre josso sur servlet (debut)");
        ServletContext ctx = filterConfig.getServletContext();
        sCtx = ctx;
        ctx.setAttribute(KEY_SESSION_MAP, new HashMap());

        if (_agent == null) {

            try {

                Lookup lookup = Lookup.getInstance();
                //O n'initialise pas on suppose que c'est déjà fait !!!
                lookup.add("josso-agent2-config.xml"); // For spring compatibility ...

                // We need at least an abstract SSO Agent
                _agent = (FacesSSOAgent) lookup.lookupSSOAgent("josso-agent2-config.xml");
                //on ne fait pas le start on suppose que c'est déjà fait par SSOAgentValve !!!
                /*if (debug==1)
                _agent.setDebug(1);*/
                _agent.start();
                _agent.setDebug(1);
                _agent.setsCtx(ctx);
            } catch (Exception e) {
                throw new ServletException("Error starting SSO Agent : " + e.getMessage(), e);
            }


        }

        System.out.println("** Initialisation du filtre josso sur servlet (fin)");
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        /*FacesContext inst = getFacesContext(request, response);
        HttpServletRequest hreq = (HttpServletRequest) inst.getExternalContext().getRequest();*/
        HttpServletRequest hreq = (HttpServletRequest) request;

        HttpServletResponse hres = (HttpServletResponse) response;
        _agent.setRep(response);
        _agent.setReq(request);
        try {
            FacesContext inst = _agent.getLeFacesContext(request, response);
            Boolean bFaces = false;
            if (inst != null) {
                bFaces = true;
                ExternalContext externalContext = inst.getExternalContext();
                if (externalContext != null) {
                    HttpSession session = (HttpSession) externalContext.getSession(false);
                    if (session != null) {
                        System.out.println("_-*-_On fonctionne dans Faces session=" + session.toString());
                    } else {
                        System.err.println("_-*-_On fonctionne dans Faces sans session !");
                    }
                } else {
                    System.err.println("_-*-_On fonctionne dans Faces sans externalContext !");
                }
            } else {
                System.err.println("_-*-_On fonctionne sans Faces !");
            }
        } catch (Exception e) {
            System.err.println("_-*-_On fonctionne sans Faces mais avec erreur="+e.toString());
        }
        if (debug==1)
            System.out.println("Processing : " + hreq.getContextPath());

        try {
            // ------------------------------------------------------------------
            // Check with the agent if this context should be processed.
            // ------------------------------------------------------------------
            String contextPath = hreq.getContextPath();
            String vhost = hreq.getServerName();

            // In catalina, the empty context is considered the root context
            if ("".equals(contextPath))
                contextPath = "/";

            if (!_agent.isPartnerApp(vhost, contextPath)) {
                filterChain.doFilter(hreq, hres);
                if (debug==1)
                    System.out.println("Context is not a josso partner app : " + hreq.getContextPath());

                return;
            }else{
                System.out.println("Context IS a josso partner app =" + hreq.getContextPath());
            }

            // ------------------------------------------------------------------
            // Check some basic HTTP handling
            // ------------------------------------------------------------------
            // P3P Header for IE 6+ compatibility when embedding JOSSO in a IFRAME
            SSOPartnerAppConfig cfg = _agent.getPartnerAppConfig(vhost, contextPath);
            if (cfg.isSendP3PHeader() && !hres.isCommitted()) {
                hres.setHeader("P3P", cfg.getP3PHeaderValue());
            }

            // Get our session ...
            HttpSession session = hreq.getSession(true);
            
            // ------------------------------------------------------------------
            // Check if the partner application required the login form
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Checking if its a josso_login_request for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOLoginUri()) || 
            		hreq.getRequestURI().endsWith(_agent.getJOSSOUserLoginUri())) {

                if (debug==1)
                    System.out.println("josso_login_request received for uri '" + hreq.getRequestURI() + "'");

                //save referer url in case the user clicked on Login from some public resource (page)
                //so agent can redirect the user back to that page after successful login
                if (hreq.getRequestURI().endsWith(_agent.getJOSSOUserLoginUri())) {
                	saveLoginBackToURL(hreq, session, true);
                } else {
                	saveLoginBackToURL(hreq, session, false);
                }
                
                String loginUrl = _agent.buildLoginUrl(hreq);

                if (debug==1)
                    System.out.println("Redirecting to login url '" + loginUrl + "'");

                //set non cache headers
                _agent.prepareNonCacheResponse(hres);
                hres.sendRedirect(hres.encodeRedirectURL(loginUrl));

                return;

            }

            // ------------------------------------------------------------------
            // Check if the partner application required a logout
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Checking if its a josso_logout request for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOLogoutUri())) {

                if (debug==1)
                    System.out.println("josso_logout request received for uri '" + hreq.getRequestURI() + "'");

                String logoutUrl = _agent.buildLogoutUrl(hreq, cfg);

                if (debug==1)
                    System.out.println("Redirecting to logout url '" + logoutUrl + "'");

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

            // ------------------------------------------------------------------
            // Check for the single sign on cookie
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Checking for SSO cookie");
            Cookie cookie = null;
            Cookie cookies[] = hreq.getCookies();
            if (cookies == null)
                cookies = new Cookie[0];
            for (int i = 0; i < cookies.length; i++) {
                if (org.josso.gateway.Constants.JOSSO_SINGLE_SIGN_ON_COOKIE.equals(cookies[i].getName())) {
                    cookie = cookies[i];
                    break;
                }
            }
            
            String jossoSessionId = (cookie == null) ? null : cookie.getValue();
            GenericServletLocalSession localSession = new GenericServletLocalSession(session);
            
            // ------------------------------------------------------------------
            // Check if the partner application submitted custom login form
            // ------------------------------------------------------------------
            
            if (debug==1){
                System.out.println("Checking if its a josso_authentication for '" + hreq.getRequestURI() + "'");
            }
            if (hreq.getRequestURI().endsWith(_agent.getJOSSOAuthenticationUri())) {

            	if (debug==1){
                    System.out.println("josso_authentication received for uri '" + hreq.getRequestURI() + "'");
            	}
            	
            	CatalinaSSOAgentRequest customAuthRequest = new CatalinaSSOAgentRequest(
                		SSOAgentRequest.ACTION_CUSTOM_AUTHENTICATION, jossoSessionId, localSession);//(CatalinaSSOAgentRequest) doMakeSSOAgentRequest( SSOAgentRequest.ACTION_CUSTOM_AUTHENTICATION, jossoSessionId, localSession, null, hreq, hres);
                
                _agent.processRequest(customAuthRequest);
                
                return;
            }

            if (cookie == null || cookie.getValue().equals("-")) {
            	
            	// ------------------------------------------------------------------
                // Trigger LOGIN OPTIONAL if required
                // ------------------------------------------------------------------

            	 if (debug==1)
            		 System.out.println("SSO cookie is not present, verifying optional login process ");

                // We have no cookie, remember me is enabled and a security check without assertion was received ...
                // This means that the user could not be identified ... go back to the original resource
                if (hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                    hreq.getParameter("josso_assertion_id") == null) {

                    if (debug==1)
                             System.out.println(_agent.getJOSSOSecurityCheckUri() + " received without assertion.  Login Optional Process failed");

                    String requestURI = getSavedRequestURL(session);
                    if (debug==1)
                             System.out.println("retour à l'envoyeur ="+requestURI);
                    _agent.prepareNonCacheResponse(hres);
                    hres.sendRedirect(hres.encodeRedirectURL(requestURI));
                    return;

                }
                
            	// This is a standard anonymous request!
                if (!hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri())) {

                    if (!_agent.isResourceIgnored(cfg, hreq) && 
                    		_agent.isAutomaticLoginRequired(hreq)) {

                        if (debug==1)
                        	System.out.println("SSO cookie is not present, attempting automatic login");

                        // Save current request, so we can co back to it later ...
                        saveRequestURL(hreq, session);
                        String loginUrl = _agent.buildLoginOptionalUrl(hreq);

                        if (debug==1)
                        	System.out.println("Redirecting to login url '" + loginUrl + "'");
                        
                        //set non cache headers
                        _agent.prepareNonCacheResponse(hres);
                        hres.sendRedirect(hres.encodeRedirectURL(loginUrl));
                        return;
                    } else {
                    	if (debug==1)
                    		System.out.println("SSO cookie is not present, but login optional process is not required");
                    }
                }
                
                if (debug==1)
                	System.out.println("SSO cookie is not present, checking for outbound relaying");

                if (!(hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                    hreq.getParameter("josso_assertion_id") != null)) {
                    System.out.println("SSO cookie not present and relaying was not requested, skipping");
                    filterChain.doFilter(hreq, hres);
                    return;
                }

            }

            // ------------------------------------------------------------------
            // Check if this URI is subject to SSO protection
            // ------------------------------------------------------------------
            if (_agent.isResourceIgnored(cfg, hreq)) {
                filterChain.doFilter(hreq, hres);
                return;
            }

            // This URI should be protected by SSO, go on ...
            if (debug==1)
                System.out.println("Session is: " + session);
            
            // ------------------------------------------------------------------
            // Invoke the SSO Agent
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Executing agent...");

            // ------------------------------------------------------------------
            // Check if a user has been authenitcated and should be checked by the agent.
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Checking if its a josso_security_check for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                hreq.getParameter("josso_assertion_id") != null) {

                if (debug==1)
                    System.out.println("josso_security_check received for uri '" + hreq.getRequestURI() + "' assertion id '" +
                            hreq.getParameter("josso_assertion_id")
                    );

                String assertionId = hreq.getParameter(Constants.JOSSO_ASSERTION_ID_PARAMETER);

                HttpSSOAgentRequest relayRequest;

                if (debug==1)
                    System.out.println("Outbound relaying requested for assertion id [" + assertionId + "]");

                relayRequest = new HttpSSOAgentRequest(
                        SSOAgentRequest.ACTION_RELAY, null, localSession, assertionId);//(CatalinaSSOAgentRequest) doMakeSSOAgentRequest( SSOAgentRequest.ACTION_RELAY, null, localSession, assertionId, hreq, hres);

               relayRequest.setRequest(hreq);
               relayRequest.setResponse(hres);
                /*Context c = null;
                try{
                c = (org.apache.catalina.Context) sCtx;
                }catch(Exception err){
                System.out.println("Erreur sur extraction du context ="+err.toString());
                }
                if(c==null){
                System.out.println("Arrive pas à extraire le context depuis FacesContext");
                }else{
                relayRequest.setContext(sCtx);
                }*/
               SingleSignOnEntry entry = _agent.processRequest(relayRequest);
                if (entry == null) {
                    // This is wrong! We should have an entry here!
                    System.err.println("Outbound relaying failed for assertion id [" + assertionId + "], no Principal found.");
                    // Throw an exception and let the container send the INERNAL SERVER ERROR
                    throw new ServletException("No Principal found. Verify your SSO Agent Configuration!");
                }

                if (debug==1)
                    System.out.println("Outbound relaying succesfull for assertion id [" + assertionId + "]");

                if (debug==1)
                    System.out.println("Assertion id [" + assertionId + "] mapped to SSO session id [" + entry.ssoId + "]");

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
	                        System.out.println("No saved request found, using : '" + requestURI + "'");
	                }
                }

                clearSavedRequestURLs(session);
               	_agent.clearAutomaticLoginReferer(hreq);
               	_agent.prepareNonCacheResponse(hres);
                //on essaie une autre méthode pour associer le principal au flux http pour ne plus avoir la redirection
                //sur la page de login
                System.out.println("Classe du principal obtenu="+entry.getClass().toString());
                if(!attachePrincipal(hreq, (com.sun.enterprise.deployment.PrincipalImpl)entry.principal, hres)){
                    System.out.println("Marche pas mon truc !");
                }else{
                    System.out.println("Essayer d'ajouter le principal au flux http="+entry.principal.toString());
                }
               	
               	// Check if we have a post login resource :
                String postAuthURI = cfg.getPostAuthenticationResource();
                if (postAuthURI != null) {
                    String postAuthURL = _agent.buildPostAuthUrl(hres, requestURI, postAuthURI);
                    if (debug==1)
                        System.out.println("Redirecting to post-auth-resource '" + postAuthURL  + "'");
                    hres.sendRedirect(postAuthURL);
                } else {
                	if (debug==1)
                         System.out.println("Redirecting to original '" + requestURI + "'");
                    hres.sendRedirect(hres.encodeRedirectURL(requestURI));
                }
               	
                return;
            }


            SSOAgentRequest r = new CatalinaSSOAgentRequest(
                    SSOAgentRequest.ACTION_ESTABLISH_SECURITY_CONTEXT, jossoSessionId, localSession);//doMakeSSOAgentRequest(SSOAgentRequest.ACTION_ESTABLISH_SECURITY_CONTEXT, jossoSessionId, localSession, null, hreq, hres);
            SingleSignOnEntry entry = _agent.processRequest(r);

            if (debug==1)
                System.out.println("Executed agent.");

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
                System.out.println("Process request for '" + hreq.getRequestURI() + "'");

            if (entry != null) {
                if (debug==1)
                    System.out.println("Principal '" + entry.principal +
                        "' has already been authenticated");
                // TODO : Not supported
                // (request).setAuthType(entry.authType);
                // (request).setUserPrincipal(entry.principal);
            } else {
            	System.out.println("No Valid SSO Session, attempt an optional login?");
                // This is a standard anonymous request!

            	if (cookie != null) {
                	// cookie is not valid
                	cookie = _agent.newJossoCookie(hreq.getContextPath(), "-");
                	hres.addCookie(cookie);
                }
            	
            	if (cookie != null || (getSavedRequestURL(session) == null && _agent.isAutomaticLoginRequired(hreq))) {

                    if (debug==1)
                    	System.out.println("SSO Session is not valid, attempting automatic login");

                    // Save current request, so we can co back to it later ...
                    saveRequestURL(hreq, session);
                    String loginUrl = _agent.buildLoginOptionalUrl(hreq);

                    if (debug==1)
                    	System.out.println("Redirecting to login url '" + loginUrl + "'");
                    
                    //set non cache headers
                    _agent.prepareNonCacheResponse(hres);
                    hres.sendRedirect(hres.encodeRedirectURL(loginUrl));
                    return;
                } else {
                    if (debug==1)
                    	System.out.println("SSO cookie is not present, but login optional process is not required");
                }

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
        } finally {
            if (debug==1)
                System.out.println("Processed : " + hreq.getContextPath());
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
    /*protected SSOAgentRequest doMakeSSOAgentRequest(int action, String sessionId, LocalSession session, String assertionId,
    HttpServletRequest hreq, HttpServletResponse hres) {
    CatalinaSSOAgentRequest r = new CatalinaSSOAgentRequest(action, sessionId, session, assertionId);
    r.setRequest(hreq);
    r.setResponse(hres);

    return r;

    }*/
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

     private Boolean attachePrincipal(HttpServletRequest request, com.sun.enterprise.deployment.PrincipalImpl principal, HttpServletResponse response)
    {
        // Need real request object not facade

        CoyoteRequest req = getUnwrappedCoyoteRequest(request);
        if (req == null) {
            return Boolean.valueOf(false);
        }
        if (principal == null) {
            return Boolean.valueOf(false);
        }

        req.setUserPrincipal(principal);
        req.setAuthType(WEBAUTH_PROGRAMMATIC);
        /*HttpServletRequest req2 = null;
        try{
        FacesContext inst = getFacesContext(request, response);
        req2 = (HttpServletRequest) inst.getExternalContext().getRequest();
        }catch(Exception err){
        System.err.println("Erreur sur obtention du flux HTTP "+err.toString());
        return Boolean.valueOf(false);
        }
        CoyoteRequest req3 = getUnwrappedCoyoteRequest(req2);
        if (req3 == null) {
        System.err.println("flux HTTP vide");
        return Boolean.valueOf(false);
        }
        req3.setUserPrincipal(principal);
        req3.setAuthType(WEBAUTH_PROGRAMMATIC);*/

        // Try to retrieve a Session object (not the facade); if it exists
        // store the principal there as well. This will allow web container
        // authorization to work in subsequent requests in this session.
/*Session realSession = getSession(req);
        if (realSession != null) {
        realSession.setPrincipal((WebPrincipal)principal);
        realSession.setAuthType(WEBAUTH_PROGRAMMATIC);
        System.out.println("Programmatic login set principal in session principal="+principal.toString());
        } else {
        System.err.println("Programmatic login: No session available.");
        return false;
        }*/
        return Boolean.valueOf(true);
    }
   /**
     * Return the unwrapped <code>CoyoteRequest</code> object.
     */
    private CoyoteRequest getUnwrappedCoyoteRequest(HttpServletRequest request){
        CoyoteRequest req = null;
        ServletRequest servletRequest = request;
        try{

            ServletRequest prevRequest = null;
            while (servletRequest != prevRequest
                    && servletRequest instanceof ServletRequestWrapper) {
                prevRequest = servletRequest;
                servletRequest =
                    ((ServletRequestWrapper)servletRequest).getRequest();
	    }

	    if (servletRequest instanceof CoyoteRequestFacade) {
		req = ((CoyoteRequestFacade)servletRequest).getUnwrappedCoyoteRequest();
	    }

        } catch (Exception ex){
            System.err.println("Programmatic login failed to get request"+ex.toString());
        }
        return req;
    }
}
