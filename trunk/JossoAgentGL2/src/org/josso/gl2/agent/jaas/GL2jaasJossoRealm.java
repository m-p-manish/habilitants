/*
Copyright Stéphane Georges Popoff, (octobre 2009)

spopoff@rocketmail.com

Ce logiciel est un programme informatique servant à gérer des habilitations.

Ce logiciel est régi par la licence [CeCILL|CeCILL-B|CeCILL-C] soumise au droit français et
respectant les principes de diffusion des logiciels libres. Vous pouvez
utiliser, modifier et/ou redistribuer ce programme sous les conditions
de la licence [CeCILL|CeCILL-B|CeCILL-C] telle que diffusée par le CEA, le CNRS et l'INRIA
sur le site "http://www.cecill.info".

En contrepartie de l'accessibilité au code source et des droits de copie,
de modification et de redistribution accordés par cette licence, il n'est
offert aux utilisateurs qu'une garantie limitée.  Pour les mêmes raisons,
seule une responsabilité restreinte pèse sur l'auteur du programme,  le
titulaire des droits patrimoniaux et les concédants successifs.

A cet égard  l'attention de l'utilisateur est attirée sur les risques
associés au chargement,  à l'utilisation,  à la modification et/ou au
développement et à la reproduction du logiciel par l'utilisateur étant
donné sa spécificité de logiciel libre, qui peut le rendre complexe à
manipuler et qui le réserve donc à des développeurs et des professionnels
avertis possédant  des  connaissances  informatiques approfondies.  Les
utilisateurs sont donc invités à charger  et  tester  l'adéquation  du
logiciel à leurs besoins dans des conditions permettant d'assurer la
sécurité de leurs systèmes et ou de leurs données et, plus généralement,
à l'utiliser et l'exploiter dans les mêmes conditions de sécurité.

Le fait que vous puissiez accéder à cet en-tête signifie que vous avez
pris connaissance de la licence [CeCILL|CeCILL-B|CeCILL-C], et que vous en avez accepté les
termes.
 */

package org.josso.gl2.agent.jaas;

/**
 * Royaume pour GL2 qui utilise la gateway Josso
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
import com.sun.appserv.security.AppservRealm;
//import com.sun.enterprise.security.auth.realm.User;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import com.sun.enterprise.security.auth.realm.NoSuchRealmException;
//import com.sun.enterprise.security.auth.realm.AuthenticationHandler;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import java.util.Enumeration;
import java.util.Vector;
//import java.util.Hashtable;
import java.util.Properties;

import org.josso.agent.Lookup;
import org.josso.gateway.identity.SSORole;
import org.josso.gateway.identity.SSOUser;
//import org.josso.gateway.identity.exceptions.SSOIdentityException;
import org.josso.gateway.identity.service.SSOIdentityManagerService;

public class GL2jaasJossoRealm extends AppservRealm{

    private static String AUTH_TYPE = "JOSSO";
    private static String AUTH_TYPE_PARAM = "auth-type";
    private String authType = null;

    // the logged user and his roles.
    protected String _currentSSOSessionId;
    protected SSOUser _ssoUserPrincipal;
    protected SSORole[] _ssoRolePrincipals;
    /*
     * This method is invoked during server startup when the realm is
     * initially loaded.
     * The props argument contains the properties defined
     * for this realm in domain.xml.
     * The realm can do any initialization it needs in this method.
     * If the method returns without throwing an exception,
     * J2EE Application Server assumes the realm is ready
     * to service authentication requests.
     * If an exception is thrown, the realm is disabled,
     * check the server.log for messages.
     */
    @Override
   public void init(Properties props) throws BadRealmException, NoSuchRealmException{

       super.init(props);
       System.out.println("*** init() de GL2jaasJossoRealm");



       /*
        * Set the jaas context, otherwise server doesn't indentify the login module.
        * jaas-context is the property specified in domain.xml and
        * is the name corresponding to LoginModule
        * config/login.conf
        */
       String jaasCtx = props.getProperty(AppservRealm.JAAS_CONTEXT_PARAM);
       this.setProperty(AppservRealm.JAAS_CONTEXT_PARAM, jaasCtx);

       /*
        * Get any other interested properties from configuration file - domain.xml
        * say auth-type defined in domain.xml.
        */
       String authTypeProp = props.getProperty(AUTH_TYPE_PARAM);
       if (authTypeProp!=null){
            this.authType = authTypeProp;
       }else{
           this.authType = AUTH_TYPE;
       }


   }
    @Override
    public String getAuthType() {
        return this.authType;
    }

    @Override
    public Enumeration getGroupNames(String user) throws InvalidOperationException, NoSuchUserException {

        Vector groups = new Vector();

        try {
            // obtain user roles principals and add it to the subject
            SSOIdentityManagerService im = Lookup.getInstance().lookupSSOAgent().getSSOIdentityManager();

            for(SSORole r : im.findRolesBySSOSessionId(_currentSSOSessionId)){
		String gname=(String)r.getName();
                if (!groups.contains(gname)){
			groups.add(gname);
		}
            }
            return groups.elements();
        } catch(Exception e) {
            // logger.error("Session login failed for Principal : " + _ssoUserPrincipal, e);
            throw new NoSuchUserException("Session login failed for Principal : " + _ssoUserPrincipal);
        }
    }

    public String[] getGroups(String sessionId) throws InvalidOperationException, NoSuchUserException {


        try {
            // obtain user roles principals and add it to the subject
            SSOIdentityManagerService im = Lookup.getInstance().lookupSSOAgent().getSSOIdentityManager();
            Integer i = 0;
            for(SSORole r : im.findRolesBySSOSessionId(sessionId)){
                i++;
            }
            String[] groups = new String[i];
            i = 0;
            for(SSORole r : im.findRolesBySSOSessionId(sessionId)){
		String gname=(String)r.getName();
                groups[i] = gname;
                i++;
            }
            return groups;
        } catch(Exception e) {
            // logger.error("Session login failed for Principal : " + _ssoUserPrincipal, e);
            throw new NoSuchUserException("Session login failed for Principal : " + _ssoUserPrincipal+ " err="+e.toString());
        }
    }

    public void authenticatedUser(String sessionId){
        _currentSSOSessionId = sessionId;
        System.out.println("authenticatedUser pour=" + _currentSSOSessionId);
    }
}
