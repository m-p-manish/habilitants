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

package org.josso.agent.http;

//import org.josso.agent.http.FrontChannelParametersBuilder;
//import org.josso.agent.http.HttpSSOAgentRequest;
//import org.josso.agent.http.SecurityContextExporterFilter;
import org.josso.agent.AbstractSSOAgent;
import org.josso.agent.SSOAgentRequest;
import org.josso.agent.SSOPartnerAppConfig;
import org.josso.agent.Constants;
import org.josso.gateway.SSONameValuePair;
import org.josso.gateway.identity.SSORole;
import org.josso.gateway.identity.SSOUser;
import org.josso.gateway.identity.exceptions.SSOIdentityException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpUtils;
import javax.servlet.http.Cookie;
import java.security.Principal;
import java.util.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;

import javax.faces.FactoryFinder;
//import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.context.FacesContextFactory;
import javax.faces.lifecycle.Lifecycle;
import javax.faces.lifecycle.LifecycleFactory;
import javax.servlet.ServletContext;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;


/**
 * @author <a href="mailto:sgonzalez@atricore.org">Sebastian Gonzalez Oyuela</a>
 * @version $Rev: 608 $ $Date: 2008-08-21 12:35:13 -0300 (Thu, 21 Aug 2008) $
 */
public abstract class HttpSSOAgent extends AbstractSSOAgent {

    private static final String JOSSO_LOGIN_URI = "/josso_login/";

    private static final String JOSSO_USER_LOGIN_URI = "/josso_user_login/";
    
    private static final String JOSSO_SECURITY_CHECK_URI = "/josso_security_check";

    private static final String JOSSO_LOGOUT_URI = "/josso_logout/";
    
    private static final String JOSSO_AUTHENTICATION_URI = "/josso_authentication/";

    private List<FrontChannelParametersBuilder> _builders = new ArrayList<FrontChannelParametersBuilder>();

    private ServletContext sCtx = null;

    private FacesContext leCtx = null;

    public void setsCtx(ServletContext sCtx) {
        this.sCtx = sCtx;
    }

    public FacesContext getLeFacesContext(ServletRequest request, ServletResponse response){
        leCtx = getFacesContext(request, response);
        return leCtx;
    }


    /**
     * By default we do not require to authenticate all requests.
     */
    protected boolean isAuthenticationAlwaysRequired() {
        return false;
    }

    protected void propagateSecurityContext(SSOAgentRequest request, Principal principal) {
        HttpSSOAgentRequest servletSSOAgentRequest = (HttpSSOAgentRequest) request;
        SSOPartnerAppConfig partnerAppConfig;

        String contextPath = servletSSOAgentRequest.getRequest().getContextPath();

        // In catalina, the empty context is considered the root context
        if ("".equals(contextPath))
            contextPath = "/";

        partnerAppConfig = getPartnerAppConfig(servletSSOAgentRequest.getRequest().getServerName(),
                contextPath
        );

        if (partnerAppConfig.getSecurityContextPropagationConfig() == null) {
            // No security propagation configuration found, ignore this.
            return;
        }

        String binding = partnerAppConfig.getSecurityContextPropagationConfig().getBinding();
        String userPlaceHolder = partnerAppConfig.getSecurityContextPropagationConfig().getUserPlaceHolder();
        String rolesPlaceHolder = partnerAppConfig.getSecurityContextPropagationConfig().getRolesPlaceHolder();
        String propertiesPlaceholder = partnerAppConfig.getSecurityContextPropagationConfig().getPropertiesPlaceHolder();
        String user = principal.getName();

        if (binding != null && userPlaceHolder != null && rolesPlaceHolder != null) {
            SSORole[] roleSets;

            try {
                roleSets = im.findRolesBySSOSessionId(servletSSOAgentRequest.getSessionId());
            } catch (SSOIdentityException e) {
                if (debug > 0)
                    log("Error fetching roles for SSO Session [" + servletSSOAgentRequest.getSessionId() + "]" +
                        " on attempting to propagate security context, aborting");

                return;
            }

            HttpServletRequest hreq = servletSSOAgentRequest.getRequest();

            if (binding.equalsIgnoreCase("HTTP_HEADERS")) {

                HashMap headers = new HashMap();
                List users = new ArrayList();
                users.add(user);
                headers.put(userPlaceHolder, users);

                if (debug > 0)
                    log("Propagated user [" + user + "] onto HTTP Header [" + userPlaceHolder + "]");

                List roles = new ArrayList();
                for (int i = 0; i < roleSets.length; i++) {
                    SSORole roleSet = roleSets[i];

                    roles.add(roleSet.getName());

                    if (debug > 0)
                        log("Propagated role [" + roleSet.getName() + "] onto HTTP_HEADERS based security context");
                }
                headers.put(rolesPlaceHolder, roles);

                hreq.setAttribute(SecurityContextExporterFilter.SECURITY_CONTEXT_BINDING,
                    SecurityContextExporterFilter.HTTP_HEADERS_BINDING);

                hreq.setAttribute(SecurityContextExporterFilter.SECURITY_CONTEXT_CONTENT, headers);

            } else if (binding.equalsIgnoreCase("HREQ_ATTRS")) {

                HashMap attrs = new HashMap();
                attrs.put(userPlaceHolder, user);

                for (int i = 0; i < roleSets.length; i++) {
                    SSORole roleSet = roleSets[i];
                    attrs.put(rolesPlaceHolder + "_" + i, roleSet.getName());

                    if (debug > 0)
                        log("Propagated role [" + roleSet.getName() + "] onto HREQ_ATTRS based security context");
                }

                SSOUser usr = (SSOUser) principal;
                if (usr.getProperties() != null) {
                    Properties props = new Properties();
                    for (int i = 0 ; i < usr.getProperties().length ; i++) {
                        attrs.put(propertiesPlaceholder + "_" +  usr.getProperties()[i].getName(),
                                usr.getProperties()[i].getValue());

                        if (debug > 0)
                            log("Propagated role [" + usr.getProperties()[i].getName() + "=" +
                                usr.getProperties()[i].getValue() +"] onto HREQ_ATTRS based security context");
                    }
                }

                hreq.setAttribute(SecurityContextExporterFilter.SECURITY_CONTEXT_CONTENT, attrs);

                hreq.setAttribute(SecurityContextExporterFilter.SECURITY_CONTEXT_BINDING,
                    SecurityContextExporterFilter.HTTP_REQ_ATTRS_BINDING);

            }
        }
    }

    public boolean isAutomaticLoginRequired(HttpServletRequest hreq) {
        // TODO : This should be supported by a component, for now we apply some rules on the referer:
        try {

            // TODO : This is not the best way to avoid loops when no referer is present, the flag should expire and
            // should not be attached to the SSO Session

            // The first time we access a partner application, we should attempt an automatic login.
            Boolean autoLoginExecuted = (Boolean) hreq.getSession().getAttribute("JOSSO_AUTOMATIC_LOGIN_EXECUTED");
            // If no referer host is found but we did not executed auto login yet, give it a try.
            if (autoLoginExecuted == null || !autoLoginExecuted) {
                if (debug > 0)
                    log("No referer found and automatic login was never executed.  Require Autologin!");

                hreq.getSession().setAttribute("JOSSO_AUTOMATIC_LOGIN_EXECUTED", Boolean.TRUE);
                return true;
            }

            String referer = hreq.getHeader("referer");
            // If we have a referer host that differs from our we require an autologinSSs
            if (referer != null && !"".equals(referer)) {

                String oldReferer = (String) hreq.getSession().getAttribute("JOSSO_AUTOMATIC_LOGIN_REFERER");
                if (oldReferer != null && oldReferer.equals(referer)) {
                    if (debug > 0)
                        log("Referer already processed " + referer);

                    // cleanup so we give this referer a chance in the future!
                    hreq.getSession().removeAttribute("JOSSO_AUTOMATIC_LOGIN_REFERER");
                    return false;
                }

                StringBuffer mySelf = hreq.getRequestURL();
                java.net.URL myUrl = new java.net.URL(mySelf.toString());

                // This should build the base url of the java application
                String myUrlStr = myUrl.getProtocol() + "://" + myUrl.getHost() + ((myUrl.getPort() > 0 && myUrl.getPort() != 80 && myUrl.getPort() != 443) ? ":" + myUrl.getPort() : "") + hreq.getContextPath() + "/";

                if (debug > 0)
                    log("Processing referer " + referer + " for host " + myUrlStr);

                if (!referer.startsWith(myUrlStr)) {

                    if (debug > 0)
                        log("Referer found differs from current host.  Require Autologin!");

                    // Store referer for future reference!
                    hreq.getSession().setAttribute("JOSSO_AUTOMATIC_LOGIN_REFERER", referer);
                    return true;
                }
            } else {
            	String oldReferer = (String) hreq.getSession().getAttribute("JOSSO_AUTOMATIC_LOGIN_REFERER");
                if (oldReferer != null && oldReferer.equals("NO_REFERER")) {
                    if (debug > 0)
                        log("Referer already processed " + referer);
                    hreq.getSession().removeAttribute("JOSSO_AUTOMATIC_LOGIN_REFERER");
                    return false;
                } else {
                	hreq.getSession().setAttribute("JOSSO_AUTOMATIC_LOGIN_REFERER", "NO_REFERER");
                	return true;
                }
            }

        } catch (MalformedURLException e) {
            this.log("Error creating Referer URL : "+ e.getMessage(), e);
        } catch (Exception e) {
            this.log("Cannot verify request for automatic login : " + e.getMessage(), e);
        }

        if (debug > 0)
            log("Do not Require Autologin!");

        return false;
    }

    public void clearAutomaticLoginReferer(HttpServletRequest hreq) {
    	hreq.getSession().removeAttribute("JOSSO_AUTOMATIC_LOGIN_REFERER");
    }
    
    /**
     * This method builds a logout URL based on a HttpServletRequest.  The url contains all necessary parameters
     * required by the front-channel part of the SSO protocol.
     *
     * @deprecated
     *
     * @return
     */
    public String buildLogoutUrl(HttpServletRequest hreq) {
        return buildLogoutUrl(hreq, "/");
    }

    /**
     * This method builds a logout URL based on a HttpServletRequest.  The url contains all necessary parameters
     * required by the front-channel part of the SSO protocol.
     *
     * @return
     */
    public String buildLogoutUrl(HttpServletRequest hreq, SSOPartnerAppConfig cfg) {
        return buildLogoutUrl(hreq, cfg.getDefaultResource() != null ? cfg.getDefaultResource() :  "/");
    }


    /**
     * This method builds a logout URL based on a HttpServletRequest.  The url contains all necessary parameters
     * required by the front-channel part of the SSO protocol.
     *
     * @return
     */
    public String buildLogoutUrl(HttpServletRequest hreq, String backToPath) {

        String backto = buildBackToURL(hreq, backToPath);

        String logoutUrl = getGatewayLogoutUrl() + (backto != null ? "?josso_back_to=" + backto : "");

        logoutUrl += buildLogoutUrlParams(hreq);

        return logoutUrl;
    }
    

    /**
     * This method builds a login URL based on a HttpServletRequest.  The url contains all necessary parameters
     * required by the front-channel part of the SSO protocol.
     */
    public String buildLoginUrl(HttpServletRequest hreq) {
        String loginUrl = getGatewayLoginUrl();

        String backto = buildBackToURL(hreq, getJOSSOSecurityCheckUri());

        loginUrl = loginUrl + "?josso_back_to=" + backto;

        // Add login URL parameters
        loginUrl += buildLoginUrlParams(hreq);

        return loginUrl;
    }

    /**
     * This method builds a login URL based on a HttpServletRequest.  The url contains all necessary parameters
     * required by the front-channel part of the SSO protocol.
     */
    public String buildLoginOptionalUrl(HttpServletRequest hreq) {
        String loginUrl = getGatewayLoginUrl();

        String backto = buildBackToURL(hreq, getJOSSOSecurityCheckUri());

        loginUrl = loginUrl + "?josso_cmd=login_optional&josso_back_to=" + backto;

        // Add login URL parameters
        loginUrl += buildLoginUrlParams(hreq);

        return loginUrl;
    }


    /**
     * This method builds the back_to URL value pointing to the given URI.
     * <p/>
     * The determines the host used to build the back_to URL in the following order :
     * <p/>
     * First, checks the singlePointOfAccess agent's configuration property.
     * Then checks the reverse-proxy-host HTTP header value from the request.
     * Finally uses current host name.
     */
    public String buildBackToURL(HttpServletRequest hreq, String uri) {
        String backto = null;

        // Build the back to url.
        String contextPath = hreq.getContextPath();

        // This is the root context
        if (contextPath == null || "".equals(contextPath))
            contextPath = "/";

        String reverseProxyHost = hreq.getHeader(org.josso.gateway.Constants.JOSSO_REVERSE_PROXY_HEADER);
        String singlePointOfAccess = getSinglePointOfAccess();

        if (singlePointOfAccess != null) {
            // Using single-point of access configuration.
            if (debug >= 1)
                log("josso_back_to option : singlePointOfAccess: " + singlePointOfAccess);
            backto = singlePointOfAccess + contextPath + uri;

        } else if (reverseProxyHost != null) {
            // Using reverse proxy host header.
            if (debug >= 1)
                log("josso_back_to option : reverse-proxy-host: " + reverseProxyHost);
            backto = reverseProxyHost + contextPath + uri;

        } else {
            // Using default host
            StringBuffer mySelf = HttpUtils.getRequestURL(hreq);

            try {
                java.net.URL url = new java.net.URL(mySelf.toString());
                backto = url.getProtocol() + "://" + url.getHost() + ((url.getPort() > 0) ? ":" + url.getPort() : "");
            } catch (java.net.MalformedURLException e) {
                throw new RuntimeException(e);
            }

            backto += (contextPath.endsWith("/") ? contextPath.substring(0, contextPath.length() - 1) : contextPath) + uri;

        }

        if (debug >= 1)
            log("Using josso_back_to : " + backto);

        return backto;
    }

    public String buildPostAuthUrl(HttpServletResponse hres, String requestURI, String postAuthURI) {
        // TODO : Is there a better way to do this ?
        String encURL = requestURI.replaceAll("&", "%26").replaceAll("\\?", "%3F");

        return hres.encodeRedirectURL(postAuthURI +  "?josso_original_resource=" + hres.encodeURL(encURL));

    }

    /**
     * This creates a new JOSSO Cookie for the given path and value.
     *
     * @param path  the path associated with the cookie, normaly the partner application context.
     * @param value the SSO Session ID
     * @return
     */
    public Cookie newJossoCookie(String path, String value) {

        // Some browsers don't like cookies without paths. This is useful for partner applications configured in the root context
        if (path == null || "".equals(path))
            path = "/";

        Cookie ssoCookie = new Cookie(org.josso.gateway.Constants.JOSSO_SINGLE_SIGN_ON_COOKIE, value);
        ssoCookie.setMaxAge(-1);
        ssoCookie.setPath(path);

        // TODO : Check domain / secure ?
        //ssoCookie.setDomain(cfg.getSessionTokenScope());
        //ssoCookie.setSecure(true);

        return ssoCookie;
    }
    
    @SuppressWarnings("unchecked")
	public String buildAutomaticSubmitForm(HttpServletRequest request){
    	//TODO - remove permanently
    	return null;
    }



    /**
     * This method builds request URL parameters that will be sent to the gateway when attempting logins and identity assertions
     * trhough the front channel (HTTP)
     *
     * @param hreq
     * @return
     */
    protected String buildLoginUrlParams(HttpServletRequest hreq) {

        String urlParams = "";
        for (FrontChannelParametersBuilder builder : _builders) {
            SSONameValuePair[] params = builder.buildParamters(hreq);
            for (SSONameValuePair param : params) {
                urlParams += "&" + param.getName() + "=" + param.getValue();
            }
        }

        return urlParams;

    }

    /**
     * This method builds request URL parameters that will be sent to the gateway when attempting logins and identity assertions
     * trhough the front channel (HTTP)
     *
     * @param hreq
     * @return
     */
    protected String buildLogoutUrlParams(HttpServletRequest hreq) {

        String urlParams = "";
        for (FrontChannelParametersBuilder builder : _builders) {
            SSONameValuePair[] params = builder.buildParamters(hreq);
            for (SSONameValuePair param : params) {
                urlParams += "&" + param.getName() + "=" + param.getValue();
            }
        }

        return urlParams;

    }
    
    @SuppressWarnings("unchecked")
	@Override
    protected void sendCustomAuthentication(SSOAgentRequest request) throws IOException {
    	HttpServletRequest hreq = ((HttpSSOAgentRequest) request).getRequest();
    	HttpServletResponse hres = ((HttpSSOAgentRequest) request).getResponse();
    	prepareNonCacheResponse(hres);
    	
    	SSOPartnerAppConfig cfg = this.getPartnerAppConfig(hreq.getServerName(), hreq.getContextPath());
    	
        String splash_resource = null;
        /* If this is an authentication request, our splash resource will be one of the following (in the given order):
         * 1. submitted josso_splash_resource parameter
         * 2. default splash resource, defined in josso-agent-config
         * TODO : Referer values should be handled by agent when processing LOGIN_REQUESTS (josso_login) 3. value from referrer header
         * 
         * If this is not authentication request, splash resource will be request URI
         */         
        if (hreq.getRequestURI().endsWith(this.getJOSSOAuthenticationUri())) {
        	//try josso_splash_resource defined as hidden field
        	splash_resource = hreq.getParameter(Constants.JOSSO_SPLASH_RESOURCE_PARAMETER); 
        	
	        if( splash_resource == null || "".equals(splash_resource)){
	        	if(cfg != null){
	        		splash_resource = cfg.getSplashResource();
	        	}
                /* TODO :Verify this! Agents should store referer values as SAVED_REQUESTS when
                processing a login or automatic request
        		if(splash_resource == null || "".equals(splash_resource)){
        			//fall back to referer
        			splash_resource = hreq.getHeader("referer");
        		}
                 */
	        }
        } else {

            if (debug > 0)
                log("sendCustomAuthentication executed but URL does not match AUTHENTICATION URI");

            // TODO : Verify this! We should never get here ..

            StringBuffer sb = new StringBuffer(hreq.getRequestURI());
            if (hreq.getQueryString() != null) {
                sb.append('?');
                sb.append(hreq.getQueryString());
            }
            String[] uriArray = new String[1];
            splash_resource = sb.toString();
        }

        if (debug > 0)
            log("Storing Splash resource '"+splash_resource+"'");
        
    	hreq.getSession(true).setAttribute(Constants.JOSSO_SPLASH_RESOURCE_PARAMETER, splash_resource);
    	
    	StringBuilder sb = new StringBuilder();
        
        // TODO : Use a template instead ?
        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.1//EN\"\n" +
                "\"http://www.w3.org/TR/xhtml11/DTD/xhtml11.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xml:lang=\"en\">\n"
                + "<body onload=\"document.forms[0].submit()\">\n" +
                "<noscript>\n" + "<p>\n" + "<strong>Note:</strong> Since your browser does not support JavaScript,\n" +
                "you must press the Continue button once to proceed.\n" + "</p>\n" + "</noscript>\n" +
                "<form action=\"").append(getGatewayLoginUrl()).
                append("\" method=\"post\" name=\"usernamePasswordLoginForm\" enctype=\"application/x-www-form-urlencoded\">\n"
                        + "        <div>");

        //copy all submitted parameters into hidden fields
        Enumeration paramNames = hreq.getParameterNames();
        while (paramNames.hasMoreElements()) {
            String paramName = (String) paramNames.nextElement();
            String paramValue = hreq.getParameter(paramName);
            if (!Constants.JOSSO_SPLASH_RESOURCE_PARAMETER.equals(paramName)) {
                sb.append("\n            <input type=\"hidden\" value=\"").append(paramValue).append("\" name=\"").append(paramName).append("\" />");
            }
        }

//        sb.append("\n            <input type=\"hidden\" name=\"josso_back_to\"value=\"").append(buildBackToURL(hreq, getJOSSOSecurityCheckUri())).append("\"/>\n").
                sb.append("\n            <noscript><input type=\"submit\" value=\"Continue\"/></noscript>\n" +
                        "        </div>\n" +
                        "</form>\n" +
                        "</body>\n" +
                        "</html>");


        hres.setContentType("text/html");
        PrintWriter out = hres.getWriter();
        out.print(sb.toString());

        if (debug >= 1) {
            log("Sending an automatic post form : \n" + sb.toString());
        }
        
        out.flush();    	
    }
    
    /**
     * Sets non cache headers in HttpServletResponse
     * @param response 
     */
    public void prepareNonCacheResponse(HttpServletResponse response) {
    	response.setHeader("Cache-Control", "no-cache");
    	response.setHeader("Pragma", "no-cache");
    	response.setHeader("Expires", "0");
    }

    /**
     * Checks if the requested resource is subject to SSO protection 
     * (it compares the request path against configured <ignore-url-patterns>).
     * 
     * @param cfg partner application configuration
     * @param request http request
     * @return true if requested resource should be ignored, false otherwise
     */
    public boolean isResourceIgnored(SSOPartnerAppConfig cfg, HttpServletRequest request) {
        // There are some url-patterns to ignore
        String[] ignoredUrlPatterns = cfg.getIgnoredUrlPatterns();

        if (debug >= 1)
            log("Found [" +  (ignoredUrlPatterns!= null ? ignoredUrlPatterns.length+"" : "no") + "] ignored url patterns ");

        if (ignoredUrlPatterns != null && ignoredUrlPatterns.length > 0) {
            
        	String requestPath = request.getServletPath();
        	
            // Add the path info, if there is any
            String pathInfo = request.getPathInfo();
            if (pathInfo != null) {
                requestPath += pathInfo;
            }
            
        	for (int i=0; i<ignoredUrlPatterns.length; i++) {
	        	String ignoredUrlPattern = ignoredUrlPatterns[i];
	        	
	        	if (matchPattern(requestPath, ignoredUrlPattern)) {
	        		
	        		// We should ignore this URI, it's not subject to SSO protection
	        		if (debug >= 1)
	                    log("Not subject to SSO protection :  url-pattern:" + ignoredUrlPattern);
	                
	        		return true;
	        	}
        	}
        }

        return false;
    }
    
    /**
     * Does the specified request path match the specified URL pattern?
     * This method follows the same rules (in the same order) as those used
     * for mapping requests to servlets.
     *
     * @param path Context-relative request path to be checked
     *  (must start with '/')
     * @param pattern URL pattern to be compared against
     */
    protected boolean matchPattern(String path, String pattern) {
        // Normalize the argument strings
        if ((path == null) || (path.length() == 0))
            path = "/";
        if ((pattern == null) || (pattern.length() == 0))
            pattern = "/";

        // Check for exact match
        if (path.equals(pattern))
            return (true);

        // Check for path prefix matching
        if (pattern.startsWith("/") && pattern.endsWith("/*")) {
            pattern = pattern.substring(0, pattern.length() - 2);
            if (pattern.length() == 0)
                return (true);  // "/*" is the same as "/"
            if (path.endsWith("/"))
                path = path.substring(0, path.length() - 1);
            while (true) {
                if (pattern.equals(path))
                    return (true);
                int slash = path.lastIndexOf('/');
                if (slash <= 0)
                    break;
                path = path.substring(0, slash);
            }
            return (false);
        }

        // Check for suffix matching
        if (pattern.startsWith("*.")) {
            int slash = path.lastIndexOf('/');
            int period = path.lastIndexOf('.');
            if ((slash >= 0) && (period > slash) &&
                path.endsWith(pattern.substring(1))) {
                return (true);
            }
            return (false);
        }

        // Check for universal mapping
        if (pattern.equals("/"))
            return (true);

        return (false);
    }

    public String getJOSSOLoginUri() {
        return JOSSO_LOGIN_URI;
    }

    public String getJOSSOUserLoginUri() {
        return JOSSO_USER_LOGIN_URI;
    }
    
    public String getJOSSOSecurityCheckUri() {
        return JOSSO_SECURITY_CHECK_URI;
    }

    public String getJOSSOLogoutUri() {
        return JOSSO_LOGOUT_URI;
    }
    
    public String getJOSSOAuthenticationUri(){
    	return JOSSO_AUTHENTICATION_URI;
    }


    // --------------------------- Spring friendly

    public void setParametersBuilders(List<FrontChannelParametersBuilder> builders) {
        this._builders = builders;
    }

    public List<FrontChannelParametersBuilder> getParametersBuilders() {
        return _builders;
    }
    protected void log(String message) {
        /*org.apache.catalina.Logger logger = _container.getLogger();
        if (logger != null)
        logger.log(this.toString() + ": " + message);
        else*/
        System.out.println(this.toString() + ": " + message);
    }

    protected void log(String message, Throwable throwable) {
        /*org.apache.catalina.Logger logger = _container.getLogger();
        if (logger != null)
        logger.log(this.toString() + ": " + message, throwable);
        else*/
        System.out.println(this.toString() + ": " + message);
    }

    /**
     * Return a String rendering of this object.
     */
    public String toString() {

        StringBuffer sb = new StringBuffer("httpSSOAgent[");
        sb.append(sCtx != null ? sCtx.toString() : "");
        sb.append("]");
        return (sb.toString());
    }
    /**
    *
    * You need an inner class to be able to call FacesContext.setCurrentInstance since it's a protected method.
    *
    * @version $Revision: 1.2 $
    */
    private abstract static class AbstractInnerFacesContext
    extends FacesContext {
        protected static void setFacesContextAsCurrentInstance(final FacesContext facesContext) {
            FacesContext.setCurrentInstance(facesContext);
        }
    }


    @SuppressWarnings("unused")
    private FacesContext getFacesContext(final ServletRequest request, final ServletResponse response) {

        FacesContext facesContext = FacesContext.getCurrentInstance();
        if (facesContext != null) {
        return facesContext;
        }

        FacesContextFactory contextFactory = (FacesContextFactory)FactoryFinder.getFactory(FactoryFinder. FACES_CONTEXT_FACTORY);
        LifecycleFactory lifecycleFactory = (LifecycleFactory)FactoryFinder.getFactory(FactoryFinder. LIFECYCLE_FACTORY);
        Lifecycle lifecycle = lifecycleFactory.getLifecycle(LifecycleFactory. DEFAULT_LIFECYCLE);

        facesContext = contextFactory.getFacesContext(sCtx, request, response, lifecycle);

        return facesContext;
    }
    
}
