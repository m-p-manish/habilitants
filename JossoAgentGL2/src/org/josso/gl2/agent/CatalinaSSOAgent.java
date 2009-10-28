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

import org.apache.catalina.Container;
import org.apache.catalina.Context;
import org.apache.catalina.Realm;
import org.josso.agent.SSOAgentRequest;
import org.josso.agent.http.HttpSSOAgent;

import java.security.Principal;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import org.josso.agent.SingleSignOnEntry;

import org.josso.agent.http.JOSSOSecurityContext;
import org.josso.servlet.agent.GenericServletLocalSession;
import org.josso.servlet.agent.GenericServletSSOAgentRequest;
import org.josso.servlet.agent.SSOGatewayHandler;

/**
 * @org.apache.xbean.XBean element="agent"
 *
 * Catalina SSO Agent Implementation that authenticates using the configured Catalina Realm's
 * Gateway SSO Login module.
 *
 * @author <a href="mailto:gbrigand@josso.org">Gianluca Brigandi</a>
 * @version CVS $Id: CatalinaSSOAgent.java 974 2009-01-14 00:39:45Z sgonzalez $
 */
public class CatalinaSSOAgent extends HttpSSOAgent {

    private Container _container;
    private int debug = 1;

    public CatalinaSSOAgent() {
        super();
    }


    public CatalinaSSOAgent(Container container) {
        super();
        System.out.println("****Création et Mise à jour du container CatalinaSSOAgent !");
        _container  = container;

    }

    @Override
    public void start() {
        super.start();
        System.out.println("****Started CatalinaSSOAgent !");
        // Add context config as partner app ...
        if (_container instanceof Context) {
            Context context = (Context) _container;
            _cfg.addSSOPartnerApp(context.getPublicId(), null, context.getPath(), null, null);
        }
    }

    /**
     * Sets the Catalina Context to be used by the authenticator.
     *
     * @param container
     */
    public void setCatalinaContainer(Container container) {
        System.out.println("****Mise à jour du container CatalinaSSOAgent !");
        _container = container;

    }

    /**
     * Authenticates the Single Sign-on Session by calling the
     * configured Realm for the Catalina Context. The configured Realm
     * should be the JAAS one so that the GatewayLoginModule can act
     * and validate de given SSO Session Identifier in the Gateway.
     *
     * @param request
     * @return the authenticated principal.
     */
    protected Principal authenticate(SSOAgentRequest request) {
        //cas du filtre pour JSF deprecated
        if(request instanceof CatalinaSSOAgentRequest){

            if (debug==1)
                System.out.println("Attempting SSO Session authentication CatalinaSSOAgent.authenticate ");
            try{
                CatalinaSSOAgentRequest r = (CatalinaSSOAgentRequest) request;
                /*Context c = r.getContext();

                if (debug==1)
                System.out.println("contexte CatalinaSSOAgentRequest="+c.toString());*/
                
                // Invoke authentication à partir du realm du container !!
                Realm realm = _container.getRealm();//c.getRealm();


                if (debug > 0)
                    System.out.println("Using realm : " + realm.getClass().getName() + " SSOSID : " + r.getSessionId()+" assertId="+r.getAssertionId());
                //authenticate(user, password)
                Principal p = realm.authenticate(r.getSessionId(), r.getAssertionId());

                if (debug > 0)
                    System.out.println("Received principal : " + p + "[" + ( p != null ? p.getClass().getName() : "<null>" ) +"]");

                return p;
            }catch(Exception err){
                System.err.println("Erreur CatalinaSSOAgent.authenticate="+err.toString());
                return null;
            }
        //autre cas avec Valve agent
        } else {
            if (debug==1)
                System.err.println("Pas normal!!!");
            return null;
        }
    }

    /**
     * This extension will delegate processing to super class and publish JOSSO Security Context
     * instance in the LocalSession associated to the request.
     */
    @Override
    public SingleSignOnEntry processRequest(SSOAgentRequest request) {

        SingleSignOnEntry  entry = super.processRequest(request);

        //dans le cas du filtre on fait des choses en plus !!
        if(request instanceof GenericServletSSOAgentRequest){
            GenericServletSSOAgentRequest r = (GenericServletSSOAgentRequest) request;
            GenericServletLocalSession localSession = (GenericServletLocalSession) r.getLocalSession();
            if (entry != null) {
                if (r.getSecurityContext() != null) {

                    if (debug > 0)
                        log("Publishing JOSSO Security Context instance in session ["+(entry != null ? entry.ssoId : "<NO-SSO-ID>") +"]");

                    localSession.setSecurityContext(r.getSecurityContext());
                }

            } else {
                if (localSession != null) {
                    if (debug > 0)
                        log("Clearing JOSSO Security Context for session ["+ localSession.getId() +  "]");

                    localSession.setSecurityContext(null);
                    r.setSecurityContext(null);
                }
            }
        }
        return entry;
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

        StringBuffer sb = new StringBuffer("CatalinaSSOAgent[");
        sb.append(_container != null ? _container.getName() : "");
        sb.append("]");
        return (sb.toString());

    }


}
