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

package org.josso.gateway.signon;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.josso.gateway.SSOContext;
import org.josso.gateway.SSOException;
import org.josso.auth.exceptions.SSOAuthenticationException;
import org.josso.auth.Credential;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Cookie;

/**
 * This action redirects to the proper action based on the authentication scheme configurated.
 *
 * @author <a href="mailto:sgonzalez@josso.org">Sebastian Gonzalez Oyuela</a>
 * @version $Id: LoginSelectorAction.java 543 2008-03-18 21:34:58Z sgonzalez $
 */
public class LoginSelectorAction extends SignonBaseAction {

    private static final Log logger = LogFactory.getLog(LoginSelectorAction.class);

    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

        if (logger.isDebugEnabled())
            logger.debug("JOSSO Command : [cmd="+getSSOCmd(request)+"]");

        try {
            prepareContext(request);
        } catch (SSOException e) {
            logger.error(e.getMessage(), e);
        } catch (SSOAuthenticationException e) {
            logger.error(e.getMessage(), e);
        }

        
        // Strong Authentication
        boolean clientAuth =
                (request.getAttribute("javax.servlet.request.X509Certificate") != null);

        if (clientAuth) {

            ActionForward af = mapping.findForward("strong-authentication");

            String path = af.getPath();
            String host = request.getHeader("Host");
            String strongAuthLoginUrl = "https://" + host + path + "?josso_cmd=login" +
                    (request.getQueryString() != null ?
                            "&" + request.getQueryString() : ""
                    );

            try {
                logger.debug("Triggering 'strong-authentication'.  Redirecting to: " + strongAuthLoginUrl);
                response.sendRedirect(strongAuthLoginUrl);
                return null;

            } catch (Exception e) {
                logger.debug(e.getMessage(), e);
            }
        }

        String remembermeCookieName = JOSSO_REMEMBERME_TOKEN + "_" + SSOContext.getCurrent().getSecurityDomain().getName();

        if (logger.isDebugEnabled())
            logger.debug("Looking for cookie: " + JOSSO_REMEMBERME_TOKEN + "_" + SSOContext.getCurrent().getSecurityDomain().getName());

        Cookie remembermeCookie = getCookie(request, remembermeCookieName);

        if (remembermeCookie != null && !remembermeCookie.getValue().equals("-")) {
            if (logger.isDebugEnabled())
                logger.debug("RemembermeCookie found!");

            logger.debug("Triggering 'rememberme-authentication'");
            return mapping.findForward("rememberme-authentication");
        }

        if (logger.isDebugEnabled())
            logger.debug("RemembermeCookie NOT found!");

        // NTLM Authentication
        boolean ntlmAuth = (request.getHeader("Authorization") != null && request.getHeader("Authorization").startsWith( "NTLM" ));
        ntlmAuth = ntlmAuth || (request.getSession().getAttribute( "ntlmHttpPa" ) != null);

        if (ntlmAuth) {
            try {
                logger.debug("Triggering 'ntlm-authentication'");
                return mapping.findForward("ntlm-authentication");
            } catch (Exception e) {
                logger.error(e.getMessage(), e);
            }
        }

        // Basic (default) Authentication
        logger.debug("Triggering 'basic-authentication'");
        return mapping.findForward("basic-authentication");

    }

}
