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

package org.josso.agent;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.josso.agent.config.ComponentKeeperFactory;
import org.josso.agent.config.ComponentKeeper;
import org.josso.agent.reverseproxy.ReverseProxyConfiguration;
import techDecision.xacmlPep.WSclientInterface;

/**
 * This class provides a singleton interface to the SSO components
 * This class should be used by any client that needs a reference to a
 * component.
 *
 * @author <a href="mailto:gbrigand@josso.org">Gianluca Brigandi</a>
 * @version CVS $Id: Lookup.java 974 2009-01-14 00:39:45Z sgonzalez $
 */

public class Lookup {

    private static final Log logger = LogFactory.getLog(Lookup.class);

    /**
     * The name of the resource holding JOSSO configuration
     */
    private String configResourceName;

    /**
     * Single statically instantiated instance of lookup.
     */
    private static final Lookup INSTANCE = new Lookup();

    /**
     * Reference to the component keeper.
     */
    private ComponentKeeper _componentKeeper;

    private List<ComponentKeeper> listCK;
    private List<String> list;

    // ======================================================
    // Used only by Agent instances
    // ======================================================
    private SSOAgent _ssoAgent;

    // ======================================================
    // Used only by Reverse Proxy instances
    // ======================================================
    private ReverseProxyConfiguration reverseProxyConfiguration;
    private WSclientInterface _pdpService;

    /**
     * Private constructor so that this class can only be instantiated by the singleton.
     */
    private Lookup() {
         listCK = new ArrayList<ComponentKeeper>();
         list = new ArrayList<String>();
   }

    /**
     * Static factory method for getting a reference to the singleton
     * Lookup.
     *
     * @return Lookup
     */
    public static Lookup getInstance() {
        return Lookup.INSTANCE;
    }

    /**
     * Initializes the Lookup instance using the specified resource as configuration resource.
     *
     * @param configResourceName the name of the resource holding josso configuration (agent or gateway)
     */
    public void init(String configResourceName, String from) {
        logger.info("Init resourceName <" + configResourceName + "> from="+from);
        this.configResourceName = configResourceName;
    }

    /**
     * Initializes the Lookup instance using the specified resource as configuration resource.
     *
     * @param configResourceName the name of the resource holding josso configuration (agent or gateway)
     */
    public void add(String configResourceName) {
        logger.info("Add resourceName <" + configResourceName + ">");
        Boolean bFound = false;
        for(String n : list){
            if(n.equals(configResourceName)) bFound = true;
        }
        if(bFound){
            logger.info("resourceName <" + configResourceName + "> déjà là");
        }else{
            list.add(configResourceName);
            ComponentKeeperFactory factory = ComponentKeeperFactory.getInstance();

            factory.setResourceFileName(configResourceName);
            _componentKeeper = factory.newComponentKeeper();
            listCK.add(_componentKeeper);
            logger.info("Using ComponentKeeper : " + this._componentKeeper.getClass().getName()+" nom="+configResourceName);
        }
    }
    /**
     * Fetches the Single Sign-On agent component.
     *
     * @return a reference to the agent component.
     */
    public synchronized WSclientInterface lookupPdpService() throws Exception {
        if (_pdpService == null)
            _pdpService = getComponentKeeper().fetchPdpService();

        return _pdpService;
    }
    /**
     * Fetches the Single Sign-On agent component.
     *
     * @return a reference to the agent component.
     */
    public synchronized SSOAgent lookupSSOAgent() throws Exception {
        if (_ssoAgent == null)
            _ssoAgent = getComponentKeeper().fetchSSOAgent();

        return _ssoAgent;
    }

     /**
     * Fetches the Single Sign-On agent component.
     * @param nom le nom de l'agent
     * @return a reference to the agent component.
     */
    public synchronized SSOAgent lookupSSOAgent(String nom) throws Exception {
        Boolean bFound = false;
        int i = 0;
        for(String n : list){
            if(n.equals(configResourceName)){
                bFound = true;
            }
            i++;
        }
        if(!bFound) return null;
        _ssoAgent = (SSOAgent) listCK.get(i).fetchSSOAgent();

        return _ssoAgent;
    }
   /**
     * Gets the ComponentKeeper for the system.
     * The first time a component keeper is required. This method tries to get a ComponentKeeperFactory instance
     * to build the ComponentKeeper.
     * <p/>
     * If no factory can be found, the default component keeper is used.
     *
     * @return the ComponentKeeper
     * @see org.josso.agent.config.SpringComponentKeeperImpl
     * @see ComponentKeeperFactory
     */
    public ComponentKeeper getComponentKeeper() throws Exception {

        if (this._componentKeeper == null) {

            ComponentKeeperFactory factory = ComponentKeeperFactory.getInstance();

            factory.setResourceFileName(this.configResourceName);
            _componentKeeper = factory.newComponentKeeper();
            logger.info("Using ComponentKeeper : " + this._componentKeeper.getClass().getName());
        }

        return this._componentKeeper;
    }

    /**
      * Fetches the Reverse Proxy Configuration.
      *
      * @return a reference to the reverse proxy configuration.
      */
     public ReverseProxyConfiguration lookupReverseProxyConfiguration() throws Exception {
         if (reverseProxyConfiguration == null)
             reverseProxyConfiguration = getComponentKeeper().fetchReverseProxyConfiguration();

         return reverseProxyConfiguration;
     }

     public ReverseProxyConfiguration getReverseProxyConfiguration() {
         return reverseProxyConfiguration;
     }

     public void setReverseProxyConfiguration(ReverseProxyConfiguration reverseProxyConfiguration) {
         this.reverseProxyConfiguration = reverseProxyConfiguration;
     }



    public static void main(String args[]) throws Exception {
        // Lookup.getInstance().lookupSecurityDomain();
    }


}
