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

//import com.sun.enterprise.security.LoginContext;
//import com.sun.enterprise.security.LoginException;
import com.sun.enterprise.security.auth.login.PasswordCredential;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
//import javax.security.jacc.PolicyContext;
//import javax.security.jacc.PolicyContextException;
import com.sun.enterprise.security.SecurityContext;
import com.sun.enterprise.security.auth.realm.User;
import java.security.Principal;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import com.sun.enterprise.security.auth.realm.Realm;
/**
 * Module utilisé par l'agent bricolé pour faire une authent sur le Realm / LoginModule
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class JAASHelper {

  private LoginContext loginContext = null;
  private Subject sub = null;
  private SecurityContext sc = null;
  private Boolean bAuth =  false;
  private static final Log logg = LogFactory.getLog(JAASHelper.class);

  public JAASHelper() {
  }

  public boolean authenticate(String userid, String password) {
    boolean result = false;
    try {
        /*loginContext = new LoginContext("josso", new LoginCallback(userid, password));
        Subject s = loginContext.getSubject();
        loginContext.login();*/
      //loginContext = new LoginContext();
      //loginContext.login(userid, password);
      result = true;
    }
    catch (Exception e) {
      // A production quality implementation would log this message
      log("Erreur login JAASHelper ",e);
      result = false;
    }
    return result;
  }
  public boolean authentif(String user, String pass){
      try {
            sub = new Subject();
            //sub.getPrivateCredentials().add(new PasswordCredential(user,pass, Realm.getDefaultRealm()));
            sub.getPrivateCredentials().add(new PasswordCredential(user,pass, "jossoRealm"));
            loginContext = new LoginContext("josso", sub);
            loginContext.login();
            log("Succès JAASHelper login recheche subject");
            sub = loginContext.getSubject();
            //SecurityContext securityContext =  new SecurityContext(user,lc.getSubject(), Realm.getDefaultRealm());
            sc =  new SecurityContext(user,loginContext.getSubject(), "jossoRealm");

            SecurityContext.setCurrent(sc);
            bAuth = true;
            if(sc.didServerGenerateCredentials()){
                log("Info JAASHelper credential OK");
            }
            log("Info JAASHelper fin authentif sub="+sub.toString());
      } catch (LoginException e) {
            log("Erreur JAASHelper login ",e);
      }
      return bAuth;
  }

  public Subject getSubject () {
    //log("Info JAASHelper.getSubject sub="+sub.toString());
      Subject s = null;
      if(bAuth){
          s = sc.getSubject();
      }
      return s;
      /*if(sub != null) return sub;
      Subject result = null;
      if (null != loginContext) {
      try {
      result = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
      } catch (PolicyContextException ex) {
      log("Erreur JAASHelper retrouver subject "+ex.toString());
      }
      //result = loginContext.getSubject();
      }
      return result;*/
  }
  public Principal getPrincipal(){
      Principal p = null;
      if (bAuth){
            p = sc.getCallerPrincipal();
      }
      return p;
  }
    public User infoUser(String nom){
        User ret = null;
        if(loginContext!=null){
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
        return "JAASHelper ";
    }

  public static class LoginCallback implements CallbackHandler {
    private String userName = null;
    private String password = null;

    public LoginCallback(String userName, String password) {
      this.userName = userName;
      this.password = password;
    }

    public void handle(Callback[] callbacks) {
      for (int i = 0; i< callbacks.length; i++) {
        if (callbacks[i] instanceof NameCallback) {
          NameCallback nc = (NameCallback)callbacks[i];
          nc.setName(userName);
        } else if (callbacks[i] instanceof PasswordCallback) {
          PasswordCallback pc = (PasswordCallback)callbacks[i];
          pc.setPassword(password.toCharArray());
        }
      }
    }
  }
}
