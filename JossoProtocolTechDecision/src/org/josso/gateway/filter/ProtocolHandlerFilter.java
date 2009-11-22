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

package org.josso.gateway.filter;

import an.chopsticks.service.Action;
import an.chopsticks.service.Attribute;
import an.chopsticks.service.AttributeType;
import an.chopsticks.service.AuthorizationFailedException;
import an.chopsticks.service.AuthorizationResult;
import an.chopsticks.service.Context;
import an.chopsticks.service.Decision;
import an.chopsticks.service.Resource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.josso.Lookup;
import org.josso.SecurityDomain;
import org.josso.gateway.SSORequestImpl;
import org.josso.gateway.SSOContext;
import org.josso.gateway.SSOWebConfiguration;
import org.josso.gateway.protocol.SSOProtocolManager;
import org.josso.gateway.session.SSOSession;
import org.josso.gateway.session.exceptions.NoSuchSessionException;
import org.josso.gateway.session.service.SSOSessionManager;
import org.josso.gateway.signon.Constants;

import org.josso.spring.SpringComponentKeeperImpl;
import org.springframework.context.ApplicationContext;
import techDecision.xacmlPdp.XaclmlAutorise;
import an.chopsticks.service.DefaultContextImpl;
import an.chopsticks.service.Subject;
import java.net.URI;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: ajadzinsky
 * Date: Apr 25, 2008
 * Time: 11:48:31 AM
 * To change this template use File | Settings | File Templates.
 */
public class ProtocolHandlerFilter implements Filter {
    private static final Log logger = LogFactory.getLog(ProtocolHandlerFilter.class);
    private XaclmlAutorise xacml = null;
    private Boolean once = false;
    static final String USER = "j_username";
    static final String PASS = "j_password";
    static final String XACML_URI = "XACML_URI";
    static final String PARAM_MESSAGE = "an.Message";

    //------------------------------------------------------ javax.servlet.Filter implementation section

    public void init(FilterConfig filterConfig) throws ServletException {
        //call = 1;
    }

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {


        try {

            HttpServletRequest hreq = (HttpServletRequest) servletRequest;
            HttpServletResponse hres = (HttpServletResponse) servletResponse;

            // Get our security domain
            SSOContext ctx = Lookup.getInstance().lookupSSOGateway().prepareSSOContext(new SSORequestImpl(hreq));
            SecurityDomain sd = ctx.getSecurityDomain();

            // TODO : Handle this in a more general way.
            // See if P3P configuration is enabled
            // This is required by Microsoft Internet Explorer when embedding JOSSO in a IFRAME
            SSOWebConfiguration cfg = sd.getSSOWebConfiguration();
            if (cfg.isSendP3PHeader()) {

                if (!hres.isCommitted()) {
                    hres.setHeader("P3P", cfg.getP3PHeaderValue());
                    if (logger.isDebugEnabled())
                        logger.debug("Adding P3P Header:" + cfg.getP3PHeaderValue());
                } else {
                    logger.warn("Already commited response, cannot set P3P header");
                }
            }

            // Handle specific protocol requests
            if (!existJossoSession(hreq, sd)) {

                SSOProtocolManager pm = sd.getProtocolManager();
                // We have a Protocol Manager and the request was processed, do not continue with the chain.
                if (pm != null && !pm.dispatchRequest(hreq, hres))
                    return;

            }
            //on test les requêtes XACML
            if(!once){
                try {
                    SpringComponentKeeperImpl cks = (SpringComponentKeeperImpl) Lookup.getInstance().getComponentKeeper();
                    ApplicationContext appCtx = cks.getSpringContext();
                    xacml = (XaclmlAutorise) appCtx.getBean("chopSticksXacml");
                    if(xacml!=null){
                        System.out.println("retrouvé le bean chopSticksXacml");
                    }else{
                        System.err.println("Le bean chopSticksXacml est vide !");
                    }
                } catch (Exception exception) {
                    System.err.println("Erreur récupération bean chopSticksXacml"+exception.toString());
                }
                once = true;
            }
            if(xacml!=null){
                //on peut faire un contrôle de la requête pour savoir si elle contient du xacml
                String uri = hreq.getRequestURI();
                Resource[] resources = processResource(uri);
                if(isXacmlRequest(resources)) {
                    //alors on valide
                    try {
                        Action action = new Action(hreq.getMethod());
                        Context ctxe = createContextFromRequest(hreq);
                        SSOSession sess = getJossoSession(hreq, sd);
                        Subject subject = new Subject();
                        subject.setSubjecName(sess.getUsername());
                        Subject[] subjects = new Subject[]{subject};
                        AuthorizationResult result = null;
                        result = doAuthorization(subjects, resources, action, ctxe);
                        Decision decision = result.getDecision();
                        String msg = result.getMessage();
                        Context responseCtx = result.getResponseContext();
                        System.out.println("Decsion=" + decision + ";Subject=" + subject.getSubjectName() + ";Resource=" + uri +
                                ";Action=" + action.getActionName() +
                                (responseCtx == null ? "" : ";ResponseContext=[" + responseCtx + "]"));

                        // Set the returned message to request
                        if (msg != null) {
                            logger.debug("\n" + msg);
                            hreq.setAttribute(PARAM_MESSAGE, msg);
                        }
                        // Set the response context's attributes to request
                        if (responseCtx != null) {
                            Collection<Attribute> attrs = responseCtx.getAllAttributes();
                            for (Attribute each : attrs) {
                                hreq.setAttribute(
                                        each.getName(), each.isArray() ? each.getArrayValueAsString() : each.getValue());
                            }
                        }

                        if (decision == Decision.Permit) {
                            System.out.println("Resources '" + uri + "' was permit to be access.");
                            ((HttpServletResponse)hres).sendError(HttpServletResponse.SC_ACCEPTED);
                            return;
                        }
                        else {
                            String atzFailMsg = "Resources '" + uri + "' was not permit to be access.";
                            logger.debug(atzFailMsg);
                            hreq.setAttribute(PARAM_MESSAGE, atzFailMsg);
                            ((HttpServletResponse)hres).sendError(HttpServletResponse.SC_FORBIDDEN);
                            return;
                        }
                    } catch (Exception ex) {
                        System.err.println("Erreur dans test XACML"+ex.toString());
                        ((HttpServletResponse)hres).sendError(HttpServletResponse.SC_EXPECTATION_FAILED);
                        return;
                }
                }
            }

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }

        filterChain.doFilter(servletRequest, servletResponse);

    }

    public void destroy() {
    }

    protected boolean existJossoSession(HttpServletRequest request, SecurityDomain sd) throws Exception {
        String jossoSessionId = getJossoSessionId(request);

        if (jossoSessionId == null)
            return false;

        SSOSessionManager ssoSessionManager = sd.getSessionManager();

        try {
            SSOSession s = ssoSessionManager.getSession(jossoSessionId);
            if (s != null && s.isValid())
                return true;

        } catch (NoSuchSessionException nsse) {

            HttpSession ssn = request.getSession(true);
            // TODO : FIXME This component should not be boud to NTLM!
            /*
            if (ssn.getAttribute(NtlmProtocolHandler.NTLM_PASS_AUTHENTICATION) != null)
                ssn.removeAttribute(NtlmProtocolHandler.NTLM_PASS_AUTHENTICATION); */

            if (ssn.getAttribute( "ntlmHttpPa" ) != null)
                ssn.removeAttribute( "ntlmHttpPa" );
        }

        return false;
    }
    protected SSOSession getJossoSession(HttpServletRequest request, SecurityDomain sd) throws Exception {
        String jossoSessionId = getJossoSessionId(request);

        if (jossoSessionId == null)
            return null;

        SSOSessionManager ssoSessionManager = sd.getSessionManager();

        try {
            SSOSession s = ssoSessionManager.getSession(jossoSessionId);
            if (s != null && s.isValid())
                return s;

        } catch (NoSuchSessionException nsse) {

        }

        return null;
    }

    // ----------------------------------------------------- methods
    protected String getJossoSessionId(HttpServletRequest request) {
        Cookie c = getJossoCookie(request);
        if (c != null)
            return c.getValue();

        return null;
    }

    protected Cookie getJossoCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(Constants.JOSSO_SINGLE_SIGN_ON_COOKIE)) {
                return cookie;
            }
        }
        return null;
    }
    /**
     * valide une demande d'autorisation XACML la première uri doit êre XACML_URI
     * @param request
     * @return vrai ou faux
     */
    private Boolean isXacmlRequest(Resource[] resources){
        Boolean ret = false;
        System.out.println("teste la 2ème URI");
        if(resources.length>1){
            if(resources[1].getResourceString().equals(XACML_URI)){
                System.out.println("requête XACML");
                ret = true;
            }
        }
        return ret;
    }
    /**
     * Fait un tableau de l'uri
     * @param uri
     * @return un tableau
     */
    private Resource[] processResource(String uri) {
        String splitter = "/";
        List<Resource> result = new ArrayList<Resource>();
        // Trim the ending "/"
        while (uri.endsWith(splitter)) {
            uri = uri.substring(0, uri.length() - 1);
        }
        if (uri.equals("")) {
            return new Resource[] {new Resource(splitter)};
        }

        result.add(new Resource(uri));
        int pos = uri.lastIndexOf(splitter);
        while (pos > 0) {
            uri = uri.substring(0, pos);
            result.add(new Resource(uri));
            pos = uri.lastIndexOf(splitter);
        }
        // Add the root finally.
        result.add(new Resource(splitter));
        return result.toArray(new Resource[0]);
    }
    @SuppressWarnings("unchecked")
    private Context createContextFromRequest(HttpServletRequest req) {
        Enumeration e = req.getParameterNames();
        if (e != null) {
            Context ctx = new DefaultContextImpl();
            while (e.hasMoreElements()) {
                String name = (String)e.nextElement();
                if (!name.equals(USER) && !name.equals(PASS)) {
                    try {
                        // Test if it is an valid URI, otherwise, we don't add it to context.
                        new URI(name);
                        ctx.setAttribute(new Attribute(name, AttributeType.String, req.getParameter(name)));
                    } catch (URISyntaxException e1) {
                        continue;
                    }
                }
            }
            return ctx;
        }
        return null;
    }
    private AuthorizationResult doAuthorization(Subject[] subjs, Resource[] reses, Action action, Context ctx)
    throws AuthorizationFailedException {
        AuthorizationResult[] result = null;
        for (Resource res : reses) {
            result = xacml.getAtzSvc().authorize(subjs, new Resource[] {res}, action, ctx);
            if (result[0].getDecision() == Decision.Permit) {
                return result[0];
            }
        }
        return result == null ? null : result[0];
    }
}