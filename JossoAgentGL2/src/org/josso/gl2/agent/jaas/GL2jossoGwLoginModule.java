/*
Copyright Stéphane Georges Popoff, (octobre - novembre 2009)

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

import com.sun.appserv.security.AppservPasswordLoginModule;
//import com.sun.enterprise.security.auth.realm.AuthenticationHandler;
import com.sun.enterprise.security.auth.realm.BadRealmException;
import com.sun.enterprise.security.auth.realm.InvalidOperationException;
import com.sun.enterprise.security.auth.realm.NoSuchUserException;
import com.sun.enterprise.security.auth.realm.User;
import java.util.Iterator;
//import java.util.Map;
//import java.util.logging.Logger;
//import java.util.logging.Level;

//import javax.resource.spi.security.PasswordCredential;
//import javax.security.auth.Subject;
import javax.security.auth.callback.*;
import javax.security.auth.login.FailedLoginException;
import javax.security.auth.login.LoginException;
//import javax.security.auth.spi.LoginModule;

import org.josso.agent.Lookup;
import org.josso.gateway.identity.SSORole;
import org.josso.gateway.identity.SSOUser;
import org.josso.gateway.identity.exceptions.SSOIdentityException;
import org.josso.gateway.identity.service.SSOIdentityManagerService;
import org.josso.gl2.agent.FacesSSOAgent;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Module de login bricolé pour GL2 qui utilise la gateway Josso
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class GL2jossoGwLoginModule extends AppservPasswordLoginModule {

    // initial state
    private CallbackHandler _callbackHandler;

    // the logged user and his roles.
    protected String _currentSSOSessionId;
    protected SSOUser _ssoUserPrincipal;
    protected SSORole[] _ssoRolePrincipals;
    private String userName = null;
    private static final Log logg = LogFactory.getLog(GL2jossoGwLoginModule.class);
    /*
     * Initialize this  LoginModule
     *
     * @param subject the Subject to be authenticated.
     *
     * @param callbackHandler a CallbackHandler for communicating
     *            with the end user (prompting for user names and
     *            passwords, for example).
     *
     * @param sharedState shared LoginModule state.
     *
     * @param options options specified in the login Configuration
     *        for this particular LoginModule.
     */

    @Override
    protected void authenticateUser() throws LoginException {

        log("GL2jossoGwLoginModule Auth Info:_username:"+_username+";_password:"+_password+";_currentrealm:"+_currentRealm);

        //vérifie la présence d'un mot de passe mais normalement déjà fait dans super.login()
        testPassw();

        // Get the current realm and check whether it is instance of your realm
        if (!(_currentRealm instanceof GL2jaasJossoRealm)) {
            log("Le realm n'est pas du bon type GL2jaasJossoRealm");
            throw new LoginException("GL2jaasJossoRealm:badrealm");
        }

        if (!jossoLogin()) {  // JAAS behavior
            throw new LoginException("GL2jaasJossoRealm:Login Failed with user  "
                                      +_username);
        } else {

            try {

                // Add the SSOUser as a Principal
                if (!_subject.getPrincipals().contains(_ssoUserPrincipal)) {
                    _subject.getPrincipals().add(_ssoUserPrincipal);
                }
                userName = _ssoUserPrincipal.getName();
                log("Added SSOUser Principal to the Subject : " + userName);

                _ssoRolePrincipals = getRoleSets();

                // Add to the Subject the SSORoles associated with the SSOUser .
                for (int i=0; i < _ssoRolePrincipals .length; i++) {
                    if (_subject.getPrincipals().contains(_ssoRolePrincipals [i]))
                        continue;

                    _subject.getPrincipals().add(_ssoRolePrincipals [i]);
                    log("Added SSORole Principal to the Subject : " + _ssoRolePrincipals [i]);
                }

            } catch (Exception e) {
                // logger.error("Session login failed for Principal : " + _ssoUserPrincipal, e);
                throw new LoginException("Session login failed for Principal : " + _ssoUserPrincipal);
            } finally {
                // in any case, clean out state
                clearCredentials();
            }
        }
        GL2jaasJossoRealm myCustomRealm = (GL2jaasJossoRealm)_currentRealm;

        myCustomRealm.authenticatedUser(_username);

        log("***Avant myCustomRealm.getGroups");
        String[] grpList;
        try {
            grpList = myCustomRealm.getGroups(_username);
        } catch (InvalidOperationException ex) {
            log(GL2jossoGwLoginModule.class.getName()+" " +ex.toString());
            throw new LoginException("GL2jaasJossoRealm:Login Failed with user  "+_username);
        } catch (NoSuchUserException ex) {
            log(GL2jossoGwLoginModule.class.getName()+" " +ex.toString());
            throw new LoginException("GL2jaasJossoRealm:Login Failed with user  "+_username);
        }

        log("login succeeded for  " + _username);

        // Add the code related to authenticating to your user database.
        String[] groupListToForward = (String[])grpList.clone();

        /*
         * Call the commitAuthentication to populate
         * grpList with the set of groups to which
         * _username belongs in this realm.
         */

        /* commitUserAuthentication(_username, _password,
                             _currentRealm, groupListToForward);
        */
        String[] retAgent = new String[grpList.length+1];
        retAgent[0] = _username;
        String l = "";
        int i = 1;
        for(String s : groupListToForward){
            l+="["+s+"]";
            retAgent[i] = s;
            i++;
        }
        log("***avant commitUserAuthentication groupe="+l+" retAgent taille="+retAgent.length);
        FacesSSOAgent ag = null;
        try {
            ag = (FacesSSOAgent) Lookup.getInstance().lookupSSOAgent();
        } catch (Exception ex) {
            log("Erreur GL2jossoGwLoginModule trouve pas FacesSSOAgent "+ex.toString());
        }
        ag.setInfoUserGroups(retAgent);
        ag.addEntrySSOIDsuccessed(_username, userName);
        commitUserAuthentication(groupListToForward);
        //super.commit();
        log("***terminé authenticateUser");
    }
    /**
     * Authenticate the user by prompting for the SSO Session Identifier assigned by the SSO Gateway on logon.
     *
     * This method obtains from the gateway, using the provided session identifier, the user associated with
     * such session identifier.
     * Only the NameCallBack is used, since its not a user/password pair but only one value containing the session
     * identifier. Any other callback type is ignored.
     *
     * @return true in all cases since this LoginModule
     *        should not be ignored.
     *
     * @exception javax.security.auth.login.FailedLoginException if the authentication fails.
     *
     * @exception javax.security.auth.login.LoginException if this LoginModule
     *        is unable to perform the authentication.
     */
    private boolean jossoLogin() throws LoginException {
        log("***login josso");
        String ssoSessionId = _username;
        String ssoSessionId2 = _password;
        log("Session requested authentication to gateway : " + ssoSessionId + "/" + ssoSessionId2 );
        try {

            _currentSSOSessionId = ssoSessionId;

            //important! Fait un appel vers l'identity manager pour valider le user en cours
            SSOIdentityManagerService im = Lookup.getInstance().lookupSSOAgent().getSSOIdentityManager();
            SSOUser ssoUser = im.findUserInSession(ssoSessionId);

            log("Session authentication succeeded : " + ssoSessionId);
            _ssoUserPrincipal = ssoUser;
            _succeeded = true;

        } catch (SSOIdentityException e) {
            // Ignore this ... (user does not exist for this session)
            //if ( logger.isDebugEnabled())
                log(e.getMessage());
            _succeeded = false;
            return false;

        } catch (Exception e) {
            // logger.error("Session authentication failed : " + ssoSessionId, e);
            _succeeded = false;
            clearCredentials();
            throw new FailedLoginException("Fatal error authenticating session : " + e);
        }

        return true;
    }
    /**
     * Reset the login module state.
     */
    private void clearCredentials() {
        _ssoUserPrincipal = null;
        _ssoRolePrincipals = null;
        _currentSSOSessionId = null;
    }
    /**
     * Retreives the list of roles associated to current principal
     */
    protected SSORole[] getRoleSets() throws LoginException {
        try {
            // obtain user roles principals and add it to the subject
            SSOIdentityManagerService im = Lookup.getInstance().lookupSSOAgent().getSSOIdentityManager();

            return im.findRolesBySSOSessionId(_currentSSOSessionId);
        } catch(Exception e) {
            // logger.error("Session login failed for Principal : " + _ssoUserPrincipal, e);
            throw new LoginException("Session login failed for Principal : " + _ssoUserPrincipal);
        }

    }
    private void testPassw(){
        try {
            Iterator i = _subject.getPrivateCredentials().iterator();
            if(!i.hasNext()) {
                    log("Erreur pas de mot de passe !");
            }
        } catch (Exception e) {
            log("passwordlm.nocreds=",e);
        }

    }
    public User infoUser(String nom){
        User ret = null;
        try {
            ret = _currentRealm.getUser(nom);
        } catch (NoSuchUserException ex) {
            log("JossoLoginModule user="+nom+" existe pas ",ex);
        } catch (BadRealmException ex) {
            log("JossoLoginModule user="+nom+" erreur sur real ",ex);
        }
        return ret;
    }
    /**
     * Log a message on the Logger associated with our Container (if any).
     *
     * @param message Message to be logged
     */
    protected void log(String message) {

        logg.info(this.toString() + ": " + message);
        //je sais c'est pas bien mais des fois il faut !!!
        //log(this.toString() + ": " + message);
    }


    /**
     * Log a message on the Logger associated with our Container (if any).
     *
     * @param message   Message to be logged
     * @param throwable Associated exception
     */
    protected void log(String message, Throwable throwable) {

        logg.error(this.toString() + ": " + message);
        throwable.printStackTrace(System.out);
    }
    @Override
    public String toString(){
        return "GL2jossoGwLoginModule ";
    }

}
