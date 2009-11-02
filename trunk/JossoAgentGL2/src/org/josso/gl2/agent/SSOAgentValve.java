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

package org.josso.gl2.agent;

import java.io.IOException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Globals;
import org.apache.catalina.HttpRequest;
import org.apache.catalina.HttpResponse;
import org.apache.catalina.Lifecycle;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.LifecycleListener;
import org.apache.catalina.Logger;
import org.apache.catalina.Manager;
import org.apache.catalina.Realm;
import org.apache.catalina.Request;
import org.apache.catalina.Response;
import org.apache.catalina.Session;
import org.apache.catalina.SessionEvent;
import org.apache.catalina.SessionListener;
import org.apache.catalina.Valve;
//import org.apache.catalina.ValveContext;
import org.apache.catalina.authenticator.SavedRequest;
import org.apache.catalina.deploy.SecurityConstraint;
import org.apache.catalina.util.LifecycleSupport;
import org.apache.catalina.valves.ValveBase;
import org.josso.agent.Constants;
import org.josso.agent.LocalSession;
import org.josso.agent.Lookup;
import org.josso.agent.SSOAgentRequest;
import org.josso.agent.SSOPartnerAppConfig;
import org.josso.agent.SingleSignOnEntry;

//import org.apache.coyote.Request;
import org.apache.coyote.tomcat5.CoyoteRequest;
import org.apache.coyote.tomcat5.CoyoteRequestFacade;
import com.sun.web.security.WebPrincipal;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;







/**
 * Boucle de traitement JOSSO pour servlet Catalina
 * @author spopoff@rocketmail.com <a href="mailto:gbrigand@josso.org">Gianluca Brigandi</a>
 * @version 0.2
 */
public class SSOAgentValve extends ValveBase
        implements Lifecycle, SessionListener {

    /**
     * The debugging detail level for this component.
     */
    protected int debug = 1;


    /**
     * Descriptive information about this Valve implementation.
     */
    protected static String info =
            "org.apache.catalina.authenticator.SingleSignOn";


    /**
     * The lifecycle event support for this component.
     */
    protected LifecycleSupport lifecycle = new LifecycleSupport(this);

    /**
     * Component started flag.
     */
    protected boolean started = false;
    private FacesSSOAgent _agent;

    /**
     * Catalina Session to Local Session Map.
     */
    Map _sessionMap = Collections.synchronizedMap(new HashMap());

    // Used for the auth-type string.
    public static final String WEBAUTH_PROGRAMMATIC="PROGRAMMATIC";
    // ------------------------------------------------------------- Properties


    /**
     * Return the debugging detail level.
     */
    public int getDebug() {

        return (this.debug);

    }


    /**
     * Set the debugging detail level.
     *
     * @param debug The new debugging detail level
     */
    @Override
    public void setDebug(int debug) {

        this.debug = debug;

    }

    // ------------------------------------------------------ SessionListener Methods

    public void sessionEvent(SessionEvent event) {


        // obtain the local session for the catalina session, and notify the listeners for it.
        LocalSession localSession = (LocalSession) _sessionMap.get(event.getSession());

        if (event.getType().equals(Session.SESSION_DESTROYED_EVENT))
            localSession.expire();
    }

    // ------------------------------------------------------ Lifecycle Methods


    /**
     * Add a lifecycle event listener to this component.
     *
     * @param listener The listener to add
     */
    public void addLifecycleListener(LifecycleListener listener) {

        lifecycle.addLifecycleListener(listener);

    }


    /**
     * Get the lifecycle listeners associated with this lifecycle. If this
     * Lifecycle has no listeners registered, a zero-length array is returned.
     */
    public LifecycleListener[] findLifecycleListeners() {

        return lifecycle.findLifecycleListeners();

    }


    /**
     * Remove a lifecycle event listener from this component.
     *
     * @param listener The listener to remove
     */
    public void removeLifecycleListener(LifecycleListener listener) {

        lifecycle.removeLifecycleListener(listener);

    }

    /**
     * Prepare for the beginning of active use of the public methods of this
     * component.  This method should be called after <code>configure()</code>,
     * and before any of the public methods of the component are utilized.
     *
     * @throws LifecycleException if this component detects a fatal error
     *                            that prevents this component from being used
     */
    @Override
    public void start() throws LifecycleException {

        // Validate and update our current component state
        if (started)
            throw new LifecycleException
                    ("Agent already started");
        lifecycle.fireLifecycleEvent(START_EVENT, null);
        started = true;

        try {
            Lookup lookup = Lookup.getInstance();
            lookup.add("josso-agent2-config.xml");
            _agent = (FacesSSOAgent) lookup.lookupSSOAgent("josso-agent2-config.xml");
            _agent.setDebug(debug);
            _agent.setCatalinaContainer(container);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace(System.err);
            throw new LifecycleException("Error starting SSO Agent : " + e.getMessage());
        }
        _agent.start();
        debug = 1;
        if (debug >= 1)
            log("Started");
    }

    /**
     * Gracefully terminate the active use of the public methods of this
     * component.  This method should be the last one called on a given
     * instance of this component.
     *
     * @throws LifecycleException if this component detects a fatal error
     *                            that needs to be reported
     */
    @Override
    public void stop() throws LifecycleException {

        // Validate and update our current component state
        if (!started)
            throw new LifecycleException
                    ("Agent not started");
        lifecycle.fireLifecycleEvent(STOP_EVENT, null);
        started = false;
        if(_agent!=null)
            _agent.stop();

        if (debug >= 1)
            log("Stopped");

    }



    // ---------------------------------------------------------- Valve Methods


    /**
     * Return descriptive information about this Valve implementation.
     */
    public String getInfo() {
        return (info);
    }

    /**
     * Perform single-sign-on support processing for this request.
     *
     * @param request  The servlet request we are processing
     * @param response The servlet response we are creating
     * @throws IOException      if an input/output error occurs
     * @throws ServletException if a servlet error occurs
     */
    public int invoke(Request request, Response response) throws IOException, ServletException {

        // If this is not an HTTP request and response, just pass them on
        int ret = 0;
        if (!(request instanceof HttpRequest) ||
                !(response instanceof HttpResponse)) {
            //context.invokeNext(request, response);
            return Valve.INVOKE_NEXT;
        }

        HttpServletRequest hreq =
                (HttpServletRequest) request.getRequest();
        HttpServletResponse hres =
                (HttpServletResponse) response.getResponse();

        if (debug >= 1)
            System.out.println("***Processing : " + hreq.getContextPath() + " ["+hreq.getRequestURL()+"] path="+hreq.getPathInfo());

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
                //context.invokeNext(request, response);
                //return Valve.INVOKE_NEXT;

                if (debug >= 1)
                    log("Context is not a josso partner app : " + hreq.getContextPath());

                return Valve.INVOKE_NEXT;

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
            Session session = getSession(((HttpRequest) request), true);
            
            // ------------------------------------------------------------------
            // Check if the partner application required the login form
            // ------------------------------------------------------------------
            if (debug >= 1)
                log("Checking if its a josso_login_request for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOLoginUri()) || 
            		hreq.getRequestURI().endsWith(_agent.getJOSSOUserLoginUri())) {

                if (debug >= 1)
                    log("josso_login_request received for uri '" + hreq.getRequestURI() + "'");

                //save referer url in case the user clicked on Login from some public resource (page)
                //so agent can redirect the user back to that page after successful login
                if (hreq.getRequestURI().endsWith(_agent.getJOSSOUserLoginUri())) {
                	saveLoginBackToURL(hreq, session, true);
                } else {
                	saveLoginBackToURL(hreq, session, false);
                }
                
                String loginUrl = _agent.buildLoginUrl(hreq);

                if (debug >= 1)
                    log("Redirecting to login url '" + loginUrl + "'");

                //set non cache headers
                _agent.prepareNonCacheResponse(hres);
                hres.sendRedirect(hres.encodeRedirectURL(loginUrl));

                return Valve.END_PIPELINE;

            }

            // ------------------------------------------------------------------
            // Check if the partner application required a logout
            // ------------------------------------------------------------------
            if (debug >= 1)
                log("Checking if its a josso_logout request for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOLogoutUri())) {

                if (debug >= 1)
                    log("josso_logout request received for uri '" + hreq.getRequestURI() + "'");

                String logoutUrl = _agent.buildLogoutUrl(hreq, cfg);

                if (debug >= 1)
                    log("Redirecting to logout url '" + logoutUrl + "'");

                // Clear previous COOKIE ...
                Cookie ssoCookie = _agent.newJossoCookie(hreq.getContextPath(), "-");
                hres.addCookie(ssoCookie);

                //set non cache headers
                _agent.prepareNonCacheResponse(hres);
                hres.sendRedirect(hres.encodeRedirectURL(logoutUrl));

                return Valve.END_PIPELINE;

            }

            // ------------------------------------------------------------------
            // Check for the single sign on cookie
            // ------------------------------------------------------------------
            if (debug >= 1)
                log("Checking for SSO cookie");
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
            if (debug >= 1)
                log("Session is: " + session);
            LocalSession localSession = new CatalinaLocalSession(session);
            
            // ------------------------------------------------------------------
            // Check if the partner application submitted custom login form
            // ------------------------------------------------------------------
            
            if (debug >= 1){
                log("Checking if its a josso_authentication for '" + hreq.getRequestURI() + "'");
            }
            if (hreq.getRequestURI().endsWith(_agent.getJOSSOAuthenticationUri())) {

                if (debug >= 1)
                    log("josso_authentication received for uri '" + hreq.getRequestURI() + "'");

                CatalinaSSOAgentRequest customAuthRequest = new CatalinaSSOAgentRequest(
                		SSOAgentRequest.ACTION_CUSTOM_AUTHENTICATION, jossoSessionId, localSession);
                
                customAuthRequest.setRequest(hreq);
                customAuthRequest.setResponse(hres);
                customAuthRequest.setContext(request.getContext());
                
                _agent.processRequest(customAuthRequest);
                
                return Valve.END_PIPELINE;
            }            

            if (cookie == null || cookie.getValue().equals("-")) {

                // ------------------------------------------------------------------
                // Trigger LOGIN OPTIONAL if required
                // ------------------------------------------------------------------

                if (debug >= 1)
                    log("SSO cookie is not present, verifying optional login process ");

                // We have no cookie, remember me is enabled and a security check without assertion was received ...
                // This means that the user could not be identified ... go back to the original resource
                if (hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                    hreq.getParameter("josso_assertion_id") == null) {

                    if (debug >= 1)
                        log(_agent.getJOSSOSecurityCheckUri() + " received without assertion.  Login Optional Process failed");

                    String requestURI = this.getSavedRequestURL(session);
                    _agent.prepareNonCacheResponse(hres);
                    hres.sendRedirect(hres.encodeRedirectURL(requestURI));
                    return Valve.END_PIPELINE;

                }

                // This is a standard anonymous request!
                if (!hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri())) {

                    // If saved request is NOT null, we're in the middle of another process ...
                    if (!isResourceIgnored(cfg, request) &&
                            _agent.isAutomaticLoginRequired(hreq)) {

                        if (debug >= 1)
                            log("SSO cookie is not present, attempting automatic login");

                        // Save current request, so we can co back to it later ...
                        saveRequest((HttpRequest) request, session);
                        String loginUrl = _agent.buildLoginOptionalUrl(hreq);

                        if (debug >= 1)
                            log("Redirecting to login url '" + loginUrl + "'");

               			//set non cache headers
                		_agent.prepareNonCacheResponse(hres);
                        hres.sendRedirect(hres.encodeRedirectURL(loginUrl));
                        return Valve.END_PIPELINE;
                    } else {
                        if (debug >= 1)
                            log("SSO cookie is not present, but login optional process is not required");
                    }
                }

                if (debug >= 1)
                    log("SSO cookie is not present, checking for outbound relaying");

                if (!(hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                    hreq.getParameter("josso_assertion_id") != null)) {
                    log("SSO cookie not present and relaying was not requested, skipping");
                    //context.invokeNext(request, response);
                    return Valve.INVOKE_NEXT;
                }

            }


            // ------------------------------------------------------------------
            // Check if this URI is subject to SSO protection
            // ------------------------------------------------------------------

            if (isResourceIgnored(cfg, request)) {
                //context.invokeNext(request, response);
                return Valve.INVOKE_NEXT;
            }

            // This URI should be protected by SSO, go on ...
            if (debug >= 1)
                log("Session is: " + session);
            
            // ------------------------------------------------------------------
            // Invoke the SSO Agent
            // ------------------------------------------------------------------
            if (debug >= 1)
                log("Executing agent...");

            _agent.setCatalinaContainer(request.getContext());

            // ------------------------------------------------------------------
            // Check if a user has been authenticated and should be checked by the agent.
            // ------------------------------------------------------------------
            if (debug >= 1)
                log("Checking if its a josso_security_check for '" + hreq.getRequestURI() + "'");

            if (hreq.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                hreq.getParameter("josso_assertion_id") != null) {

                if (debug >= 1)
                    log("josso_security_check received for uri '" + hreq.getRequestURI() + "' assertion id '" +
                            hreq.getParameter("josso_assertion_id")
                    );

                String assertionId = hreq.getParameter(Constants.JOSSO_ASSERTION_ID_PARAMETER);

                CatalinaSSOAgentRequest relayRequest;

                if (debug >= 1)
                    System.out.println("Outbound relaying requested for assertion id [" + assertionId + "]");

                relayRequest = new CatalinaSSOAgentRequest(
                        SSOAgentRequest.ACTION_RELAY, null, localSession, assertionId
                    );

                relayRequest.setRequest(hreq);
                relayRequest.setResponse(hres);
                relayRequest.setContext(request.getContext());

                SingleSignOnEntry entry = _agent.processRequest(relayRequest);
                if (entry == null) {
                    // This is wrong! We should have an entry here!
                    if (debug >= 1)
                        System.out.println("Outbound relaying failed for assertion id [" + assertionId + "], no Principal found.");
                    // Throw an exception, we will handle it below !
                    throw new RuntimeException("Outbound relaying failed. No Principal found. Verify your SSO Agent Configuration!");
                }

                if (debug >= 1)
                    System.out.println("Outbound relaying succesfull for assertion id [" + assertionId + "]");

                if (debug >= 1)
                    System.out.println("Assertion id [" + assertionId + "] mapped to SSO session id [" + entry.ssoId + "]");

                // The cookie is valid to for the partner application only ... in the future each partner app may
                // store a different auth. token (SSO SESSION) value
                cookie = _agent.newJossoCookie(hreq.getContextPath(), entry.ssoId);
                hres.addCookie(cookie);

                //Redirect user to the saved splash resource (in case of auth request) or to request URI otherwise
                String requestURI = getSavedSplashResource(session.getSession());
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
	
	                    if (debug >= 1)
	                        System.out.println("No saved request found, using : '" + requestURI + "'");
	                }
	            }
                
                clearSavedRequestURLs(session);
               	_agent.clearAutomaticLoginReferer(hreq);
                _agent.prepareNonCacheResponse(hres);
                //on essaie une autre méthode pour associer le principal au flux http pour ne plus avoir la redirection
                //sur la page de login
                if(!attachePrincipal(hreq, (WebPrincipal)entry.principal)){
                    System.out.println("Marche pas mon truc !");
                }

                // Check if we have a post login resource :
                String postAuthURI = cfg.getPostAuthenticationResource();
                if (postAuthURI != null) {
                    String postAuthURL = _agent.buildPostAuthUrl(hres, requestURI, postAuthURI);
                    if (debug >= 1)
                        System.out.println("Redirecting to post-auth-resource '" + postAuthURL  + "'");
                    hres.sendRedirect(postAuthURL);
                } else {
                    if (debug >= 1)
                        System.out.println("Redirecting to original '" + requestURI + "'");
                    hres.sendRedirect(hres.encodeRedirectURL(requestURI));
                }

                return Valve.END_PIPELINE;
            }


            CatalinaSSOAgentRequest r;

            System.out.println("Creating Security Context for Session [" + session + "]");
            r = new CatalinaSSOAgentRequest(
                    SSOAgentRequest.ACTION_ESTABLISH_SECURITY_CONTEXT, jossoSessionId, localSession
            );

            r.setRequest(hreq);
            r.setResponse(hres);
            r.setContext(request.getContext());

            SingleSignOnEntry entry = _agent.processRequest(r);

            if (debug >= 1)
                System.out.println("Executed agent.");

            if (_sessionMap.get(localSession.getWrapped()) == null) {
                // the local session is new so, make the valve listen for its events so that it can
                // map them to local session events.
                session.addSessionListener(this);
                _sessionMap.put(session, localSession);
            }

            // ------------------------------------------------------------------
            // Has a valid user already been authenticated?
            // ------------------------------------------------------------------
            if (debug >= 1)
                System.out.println("Process request for '" + hreq.getRequestURI() + "'");

            if (entry != null) {
                if (debug >= 1)
                    System.out.println("Principal '" + entry.principal +
                        "' has already been authenticated");

                ((HttpRequest) request).setAuthType(entry.authType);
                ((HttpRequest) request).setUserPrincipal(entry.principal);

            } else {
                System.out.println("No Valid SSO Session, attempt an optional login?");
                // This is a standard anonymous request!

                if (cookie != null) {
                	// cookie is not valid
                	cookie = _agent.newJossoCookie(hreq.getContextPath(), "-");
                	hres.addCookie(cookie);
                }
                
                if (cookie != null || (getSavedRequestURL(session) == null && _agent.isAutomaticLoginRequired(hreq))) {

                    if (debug >= 1)
                        System.out.println("SSO Session is not valid, attempting automatic login");

                    // Save current request, so we can co back to it later ...
                    saveRequest((HttpRequest) request, session);
                    String loginUrl = _agent.buildLoginOptionalUrl(hreq);

                    if (debug >= 1)
                        System.out.println("Redirecting to login url '" + loginUrl + "'");

                	//set non cache headers
                	_agent.prepareNonCacheResponse(hres);
                    hres.sendRedirect(hres.encodeRedirectURL(loginUrl));
                    return Valve.END_PIPELINE;
                } else {
                    if (debug >= 1)
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
            //context.invokeNext(request, response);
            ret = Valve.INVOKE_NEXT;
        } catch (Throwable t) {
            //  This is a 'hack' : Because this valve exectues before the ErrorReportingValve, we need to preapare
            // some stuff and invoke the next valve in the chain always ...

            // Store this error, it will be checked by the ErrorReportingValve
            hreq.setAttribute(Globals.EXCEPTION_ATTR, t);

            // Mark this response as error!
            response.setError();

            // Let the next valves work on this
            //context.invokeNext(request, response);
            ret = Valve.INVOKE_NEXT;

        } finally {
            if (debug >= 1)
                System.out.println("Processed : " + hreq.getContextPath() + " ["+hreq.getRequestURL()+"]");
        }
        return ret;

    }

    // --------------------------------------------------------- Public Methods


    /**
     * Return a String rendering of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("SingleSignOn[");
        // Sometimes the container is not present when this method is invoked ...
        sb.append(container != null ? container.getName() : "");
        sb.append("]");
        return (sb.toString());

    }


    // -------------------------------------------------------- Package Methods




    // ------------------------------------------------------ Protected Methods

    /**
     * Return the internal Session that is associated with this HttpRequest,
     * or <code>null</code> if there is no such Session.
     *
     * @param request The HttpRequest we are processing
     */
    protected Session getSession(HttpRequest request) {

        return (getSession(request, false));

    }

    /**
     * Return the internal Session that is associated with this HttpRequest,
     * possibly creating a new one if necessary, or <code>null</code> if
     * there is no such session and we did not create one.
     *
     * @param request The HttpRequest we are processing
     * @param create  Should we create a session if needed?
     */
    protected Session getSession(HttpRequest request, boolean create) {

        HttpServletRequest hreq =
                (HttpServletRequest) request.getRequest();
        HttpSession hses = hreq.getSession(create);
        if (debug >= 1)
            log("getSession() : hses " + hses);

        if (hses == null)
            return (null);
        // Get catalina Context from request ...
        Manager manager = request.getContext().getManager();

        if (debug >= 1)
            log("getSession() : manager is " + manager);
        if (manager == null)
            return (null);
        else {
            try {
                return (manager.findSession(hses.getId()));
            } catch (IOException e) {
                return (null);
            }
        }

    }

    /**
     * Log a message on the Logger associated with our Container (if any).
     *
     * @param message Message to be logged
     */
    protected void log(String message) {

        Logger logger = container.getLogger();
        if (logger != null)
            logger.log(this.toString() + ": " + message);
        else
            System.out.println(this.toString() + ": " + message);

    }


    /**
     * Log a message on the Logger associated with our Container (if any).
     *
     * @param message   Message to be logged
     * @param throwable Associated exception
     */
    protected void log(String message, Throwable throwable) {

        Logger logger = container.getLogger();
        if (logger != null)
            logger.log(this.toString() + ": " + message, throwable);
        else {
            System.out.println(this.toString() + ": " + message);
            throwable.printStackTrace(System.out);
        }

    }


    /**
     * Return the request URI (with the corresponding query string, if any)
     * from the saved request so that we can redirect to it.
     *
     * @param session Our current session
     */
    private String getSavedRequestURL(Session session) {

        SavedRequest saved =
                (SavedRequest) session.getNote(org.apache.catalina.authenticator.Constants.FORM_REQUEST_NOTE);
        if (saved == null)
            return (null);
        StringBuffer sb = new StringBuffer(saved.getRequestURI());
        if (saved.getQueryString() != null) {
            sb.append('?');
            sb.append(saved.getQueryString());
        }
        return (sb.toString());

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
     * Save the original request information into our session.
     *
     * @param request The request to be saved
     * @param session The session to contain the saved information
     * @throws IOException
     */
    private void saveRequest(HttpRequest request, Session session) {

        // Create and populate a SavedRequest object for this request
        HttpServletRequest hreq = (HttpServletRequest) request.getRequest();
        SavedRequest saved = new SavedRequest();
        Cookie cookies[] = hreq.getCookies();
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++)
                saved.addCookie(cookies[i]);
        }
        Enumeration names = hreq.getHeaderNames();
        while (names.hasMoreElements()) {
            String name = (String) names.nextElement();
            Enumeration values = hreq.getHeaders(name);
            while (values.hasMoreElements()) {
                String value = (String) values.nextElement();
                saved.addHeader(name, value);
            }
        }
        Enumeration locales = hreq.getLocales();
        while (locales.hasMoreElements()) {
            Locale locale = (Locale) locales.nextElement();
            saved.addLocale(locale);
        }
        Map parameters = hreq.getParameterMap();
        Iterator paramNames = parameters.keySet().iterator();
        while (paramNames.hasNext()) {
            String paramName = (String) paramNames.next();
            String paramValues[] = (String[]) parameters.get(paramName);
            saved.addParameter(paramName, paramValues);
        }
        saved.setMethod(hreq.getMethod());
        saved.setQueryString(hreq.getQueryString());
        saved.setRequestURI(hreq.getRequestURI());

        // Stash the SavedRequest in our session for later use
        session.setNote(org.apache.catalina.authenticator.Constants.FORM_REQUEST_NOTE, saved);

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
    protected void saveLoginBackToURL(HttpServletRequest request, Session session, boolean overrideSavedResource) {
    	String referer = request.getHeader("referer");
        //saved request will exist only if user requested some protected resource
    	SavedRequest saved =
            (SavedRequest) session.getNote(org.apache.catalina.authenticator.Constants.FORM_REQUEST_NOTE);
        if ((saved == null || overrideSavedResource) && referer != null && !referer.equals("")) {
            saved = new SavedRequest();

            int p = referer.indexOf("?");

            String uri = p >= 0 ? referer.substring(0, p) : referer;
            String queryStr = p >= 0 ? referer.substring(p) : null;
        	saved.setRequestURI(uri);
            saved.setQueryString(queryStr);

            session.setNote(org.apache.catalina.authenticator.Constants.FORM_REQUEST_NOTE, saved);
        }
    }
    
    /**
     * Remove saved request URLs from session 
     * to avoid mixing up resources from previous operations 
     * (logins, logouts) with the current one.
     * 
     * @param session Our current session
     */
    protected void clearSavedRequestURLs(Session session) {
    	session.removeNote(org.apache.catalina.authenticator.Constants.FORM_REQUEST_NOTE);
    	session.getSession().removeAttribute(Constants.JOSSO_SPLASH_RESOURCE_PARAMETER);
    }

    protected boolean isResourceIgnored(SSOPartnerAppConfig cfg, Request request) {
        // There are some web-resources to ignore.
        String[] ignoredWebResources = cfg.getIgnoredWebRources();

        if (debug >= 1)
            log("Found [" +  (ignoredWebResources!= null ? ignoredWebResources.length+"" : "no") + "] ignored web resources ");

        if (ignoredWebResources != null && ignoredWebResources.length > 0) {

            Realm realm = request.getContext().getRealm();
            SecurityConstraint [] constraints
                = realm.findSecurityConstraints((HttpRequest) request, request.getContext());

            if ((constraints != null)) {

                for (int i = 0; i < ignoredWebResources.length; i++) {

                    // For each ignored web resource, find a correspondig web resource collection.
                    String ignoredWebResource = ignoredWebResources[i];
                    for (int j = 0; j < constraints.length; j++) {

                        // Find a matching web resource collection for each ignored web resources
                        SecurityConstraint constraint = constraints[j];
                        if (constraint.findCollection(ignoredWebResource) != null) {

                            // We should ignore this URI, it's not subject to SSO protection.
                            if (debug >= 1)
                                log("Not subject to SSO protection :  web-resource-name:" + ignoredWebResource);

                            return true;
                        }
                    }
                }
            }
        }

        return false;

    }
    private Boolean attachePrincipal(HttpServletRequest request, WebPrincipal principal)
    {
        // Need real request object not facade

        CoyoteRequest req = getUnwrappedCoyoteRequest(request);
        if (req == null) {
            return Boolean.valueOf(false);
        }

        req.setUserPrincipal(principal);
        req.setAuthType(WEBAUTH_PROGRAMMATIC);

        // Try to retrieve a Session object (not the facade); if it exists
        // store the principal there as well. This will allow web container
        // authorization to work in subsequent requests in this session.

        Session realSession = getSession(req);
        if (realSession != null) {
            realSession.setPrincipal((WebPrincipal)principal);
            realSession.setAuthType(WEBAUTH_PROGRAMMATIC);
            System.out.println("Programmatic login set principal in session principal="+principal.toString());
        } else {
            System.err.println("Programmatic login: No session available.");
            return false;
        }

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
            System.err.println("Programmatic login faiied to get request"+ex.toString());
        }
        return req;
    }


}
