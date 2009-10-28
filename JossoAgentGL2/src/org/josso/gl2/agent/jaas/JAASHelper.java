/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.josso.gl2.agent.jaas;

//import com.sun.enterprise.security.LoginContext;
//import com.sun.enterprise.security.LoginException;
//import com.sun.enterprise.security.auth.login.PasswordCredential;

import javax.security.auth.Subject;
import javax.security.auth.callback.Callback;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.callback.NameCallback;
import javax.security.auth.callback.PasswordCallback;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;
import javax.security.jacc.PolicyContext;
import javax.security.jacc.PolicyContextException;
/**
 *
 * @author spopoff
 */
public class JAASHelper {

  private LoginContext loginContext = null;
  private Subject sub = null;

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
      System.err.println("Erreur login JAASHelper "+e.toString());
      result = false;
    }
    return result;
  }
  public boolean authentif(String user, String pass){
      Boolean ret = false;
      try {
            sub = new Subject();
            sub.getPrivateCredentials().add(new com.sun.enterprise.security.auth.login.PasswordCredential(user,pass, com.sun.enterprise.security.auth.realm.Realm.getDefaultRealm()));
            LoginContext lc = new LoginContext("josso", sub);
            lc.login();
            System.out.println("Succès JAASHelper login recheche subject");
            sub = lc.getSubject();
            //com.sun.enterprise.security.SecurityContext securityContext =  new com.sun.enterprise.security.SecurityContext(user,lc.getSubject(),com.sun.enterprise.security.auth.realm.Realm.getDefaultRealm());

            //com.sun.enterprise.security.SecurityContext.setCurrent(securityContext);
            ret = true;
            System.out.println("Info JAASHelper fin authentif sub="+sub.toString());
      } catch (LoginException e) {
            System.err.println("Erreur JAASHelper login "+e.toString());
      }
      return ret;
  }

  public Subject getSubject () {
    //System.out.println("Info JAASHelper.getSubject sub="+sub.toString());
    if(sub != null) return sub;
    Subject result = null;
    if (null != loginContext) {
        try {
          result = (Subject) PolicyContext.getContext("javax.security.auth.Subject.container");
        } catch (PolicyContextException ex) {
            System.err.println("Erreur JAASHelper retrouver subject "+ex.toString());
        }
        //result = loginContext.getSubject();
    }
    return result;
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
