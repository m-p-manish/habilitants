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

import java.io.IOException;
import java.util.HashMap;

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
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpSession;

import org.josso.agent.Lookup;

//import org.apache.catalina.Context;
import org.josso.gl2.agent.FacesSSOAgent;


/**
 * JOSSO Servlet Filter for Generic SSO Agent, this replaces the Valve in tomcat or other container specific components.
 * The fillter will handle web logic to authenticate, login and logout users.
 * Ne sert plus à rien tout est dans le SAM sauf pour l'init de l'agent !!!
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
        ServletContext ctx = filterConfig.getServletContext();
        sCtx = ctx;
        ctx.setAttribute(KEY_SESSION_MAP, new HashMap());
        System.out.println("** Initialisation du filtre josso sur servlet (debut) context="+ctx.getContextPath());

        if (_agent == null) {

            try {

                Lookup lookup = Lookup.getInstance();
                //O n'initialise pas on suppose que c'est déjà fait !!!
                //lookup.add("josso-agent2-config.xml"); // For spring compatibility ...
                lookup.init("josso-agent2-config.xml");

                // We need at least an abstract SSO Agent
                //_agent = (FacesSSOAgent) lookup.lookupSSOAgent("josso-agent2-config.xml");
                _agent = (FacesSSOAgent) lookup.lookupSSOAgent();
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

        System.out.println("** Initialisation du filtre josso sur servlet (fin) context="+ctx.getContextPath());
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {

        try {
            _agent.setRep(response);
            _agent.setReq(request);
            FacesContext inst = _agent.getLeFacesContext(request, response);
            Boolean bFaces = false;
            if (inst != null) {
                bFaces = true;
                ExternalContext externalContext = inst.getExternalContext();
                if (externalContext != null) {
                    HttpSession session = (HttpSession) externalContext.getSession(false);
                    if (session != null) {
                        System.out.println("_-*-_Filtre On fonctionne dans Faces session=" + session.toString());
                    } else {
                        System.err.println("_-*-_Filtre On fonctionne dans Faces sans session !");
                    }
                } else {
                    System.err.println("_-*-_Filtre On fonctionne dans Faces sans externalContext !");
                }
            } else {
                System.err.println("_-*-_Filtre On fonctionne sans Faces !");
            }
        } catch (Exception e) {
            System.err.println("_-*-_Filtre On fonctionne sans Faces mais avec erreur="+e.toString());
        } finally {
            // ------------------------------------------------------------------
            // Invoke the next Valve in our pipeline
            // ------------------------------------------------------------------
            filterChain.doFilter (request, response);
        }
    }

    public void destroy() {
        // Validate and update our current component state
        if (_agent != null) {
            _agent.stop();
            _agent = null;
        }


    }
    
}
