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

package techDecision;

   import java.io.IOException;
   import java.util.Map;
   import javax.security.auth.Subject;
   import javax.security.auth.callback.Callback;
   import javax.security.auth.callback.CallbackHandler;
   import javax.security.auth.callback.UnsupportedCallbackException;
   import javax.security.auth.message.AuthException;
   import javax.security.auth.message.AuthStatus;
   import javax.security.auth.message.MessageInfo;
   import javax.security.auth.message.MessagePolicy;
   import javax.security.auth.message.callback.CallerPrincipalCallback;
   import javax.security.auth.message.callback.GroupPrincipalCallback;
   import javax.security.auth.message.callback.PasswordValidationCallback;
   import javax.security.auth.message.module.ServerAuthModule;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
   import org.josso.agent.Lookup;
import org.josso.agent.SSOPartnerAppConfig;
import org.josso.agent.http.WebAccessControlUtil;
   import org.josso.gl2.agent.FacesSSOAgent;
   import org.josso.agent.Constants;
   //import org.apache.catalina.util.Base64;
/**
 * Module de sécurité basé sur JSR 196
 * @author spopoff@rocketmail.com
 * @version 0.1
 */

public class JossoSAM implements ServerAuthModule {

      protected static final Class[] supportedMessageTypes = new Class[]{
          HttpServletRequest.class,
          HttpServletResponse.class
      };

      private MessagePolicy requestPolicy;
      private MessagePolicy responsePolicy;
      private CallbackHandler handler;
      private Map options;
      private String realmName = null;
      private String defaultGroup[] = null;
      private static final String REALM_PROPERTY_NAME = "realm.name";
      private static final String GROUP_PROPERTY_NAME = "group.name";
      private static final String BASIC = "Basic";
      static final String AUTHORIZATION_HEADER = "authorization";
      static final String AUTHENTICATION_HEADER = "WWW-Authenticate";
      private final String pourLeRetour = "josso_security_check";
      private final String pasBonFautPas = "http://localhost:8082/josso/signon/login.do?josso_cmd=login_optional&josso_back_to=";
      private FacesSSOAgent _agent;
      private int debug = 1;

      public void initialize(MessagePolicy reqPolicy, MessagePolicy resPolicy, CallbackHandler cBH, Map opts) throws AuthException {
          requestPolicy = reqPolicy;
          responsePolicy = resPolicy;
          handler = cBH;
          options = opts;
          if (options != null) {
              realmName = (String)
                  options.get(REALM_PROPERTY_NAME);
              if (options.containsKey(GROUP_PROPERTY_NAME)) {
                  defaultGroup = new String[]{(String)
                      options.get(GROUP_PROPERTY_NAME)};
              }
          }
          Lookup lookup = Lookup.getInstance();
        try {
            _agent = (FacesSSOAgent) lookup.lookupSSOAgent("josso-agent2-config.xml");
        } catch (Exception ex) {
             System.err.println("Erreur initialisation module techDecision.JossoSAM trouve pas agent "+ex.toString());
        }
          System.out.println("Fin initialisation module techDecision.JossoSAM ");
      }

      public Class[] getSupportedMessageTypes() {
          return supportedMessageTypes;
      }

      public AuthStatus validateRequest(MessageInfo msgInfo, Subject client, Subject server) throws AuthException {
          try {
              //en premier vérifier qu'on ne revient pas en boucle
              HttpServletRequest request = (HttpServletRequest) msgInfo.getRequestMessage();
              String jeVeux = request.getRequestURL().toString();
              System.out.println("**JossoSAM** On traite le message en vue d'authentification="+jeVeux);
              /*if(jeVeux.endsWith(pourLeRetour)){
              System.out.println("Je boucle !!! il faut que je réponde qqchose mais quoi ?");
              return AuthStatus.SEND_CONTINUE;
              }*/
              String contextPath = request.getContextPath();
              String vhost = request.getServerName();
              if (!_agent.isPartnerApp(vhost, contextPath)) {
                       System.out.println("**JossoSAM** Context is not a josso partner app : " + request.getContextPath());
              }else{
                  System.out.println("**JossoSAM** Context IS a josso partner app =" + request.getContextPath());
              }

             // Get our session ...
             HttpSession session = request.getSession(true);
             // ------------------------------------------------------------------
             // Check if the partner application required the login form
             // ------------------------------------------------------------------
             if (debug==1)
                 System.out.println("Checking if its a josso_login_request for '" + request.getRequestURI() + "'");

             if (request.getRequestURI().endsWith(_agent.getJOSSOLoginUri()) ||
             		request.getRequestURI().endsWith(_agent.getJOSSOUserLoginUri())) {

                 if (debug==1)
                     System.out.println("josso_login_request received for uri '" + request.getRequestURI() + "'");

                 //save referer url in case the user clicked on Login from some public resource (page)
                 //so agent can redirect the user back to that page after successful login
                 if (request.getRequestURI().endsWith(_agent.getJOSSOUserLoginUri())) {
                 	saveLoginBackToURL(request, session, true);
                 } else {
                 	saveLoginBackToURL(request, session, false);
                 }

                 String loginUrl = _agent.buildLoginUrl(request);

                 if (debug==1)
                     System.out.println("Redirecting to login url '" + loginUrl + "'");

                 //set non cache headers
                 HttpServletResponse response = (HttpServletResponse) msgInfo.getResponseMessage();
                 response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                 _agent.prepareNonCacheResponse(response);
                 response.sendRedirect(response.encodeRedirectURL(loginUrl));
 
                 return AuthStatus.SEND_CONTINUE;

             }

             String username = processAuthorizationToken(msgInfo, client);
              if (username == null && requestPolicy.isMandatory()) {
                  System.out.println("**JossoSAM** Il faut une authentification préalable !");
                  //return sendAuthenticateChallenge(msgInfo);
                  //return sendAuthenticateChallenge2(msgInfo);
                  HttpServletResponse response = (HttpServletResponse) msgInfo.getResponseMessage();
                  response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                  //response.setHeader("Location", jeVeux);
                  response.sendRedirect(request.getContextPath() + "/josso_login/");
                  return AuthStatus.SEND_CONTINUE;
              }
             System.out.println("**JossoSAM** On est authentifé donc autorisé donc on ajoute le groupe qui va bien ...");
             setAuthenticationResult(username, client, msgInfo);
             return AuthStatus.SUCCESS;

          } catch (Exception e) {
              AuthException ae = new AuthException();
              ae.initCause(e);
              throw ae;
          }
      }

      private String processAuthorizationToken(MessageInfo msgInfo, Subject s) throws AuthException {

          System.out.println("Analyse du message en vue d'authentification...");
          HttpServletRequest request = (HttpServletRequest) msgInfo.getRequestMessage();

          String token = request.getHeader(AUTHORIZATION_HEADER);

          if (token != null && token.startsWith(BASIC + " ")) {
              token = token.substring(6).trim();

              System.out.println("décodage du token="+token);
              // Decode and parse the authorization token
              String decoded = new String(Base64.decode(token));

              int colon = decoded.indexOf(':');
              if (colon <= 0 || colon == decoded.length() - 1) {
                  System.err.println("Erreur analyse decoded token="+decoded);
                  return (null);
              }

              String username = decoded.substring(0, colon);

             // use the callback to ask the container to
             // validate the password
            System.out.println("On valide le mot de passe de username="+username);
            PasswordValidationCallback pVC = new PasswordValidationCallback(s, username, decoded.substring(colon + 1).toCharArray());
            try {
                handler.handle(new Callback[]{pVC});
                pVC.clearPassword();
            } catch (Exception e) {
                AuthException ae = new AuthException();
                ae.initCause(e);
                throw ae;
            }

            if (pVC.getResult()) {
                return username;
            }
      }
      System.err.println("Rien dans le header échec de l'authentification...");
      return null;
   }
   /**
    * on redirige vers le serveur d'authentification
    * @param msgInfo
    * @return
    */
   private AuthStatus sendAuthenticateChallenge2(MessageInfo msgInfo) {
      HttpServletRequest request = (HttpServletRequest) msgInfo.getRequestMessage();
      String leretour = request.getRequestURL().toString();
      System.out.println("On redirige vers une page de login="+pasBonFautPas+leretour+pourLeRetour);
      HttpServletResponse response = (HttpServletResponse) msgInfo.getResponseMessage();
      response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
      response.setHeader("Location", pasBonFautPas+leretour+pourLeRetour);
      return AuthStatus.SEND_CONTINUE;
   }
   /**
    * on renvoit une demande d'authentification classique
    * @param msgInfo
    * @return
    */
   private AuthStatus sendAuthenticateChallenge(MessageInfo msgInfo) {

       System.out.println("On va envoyer une demande d'authentification royaume="+realmName);
       String realm = realmName;
         // if the realm property is set use it,
         // otherwise use the name of the server
         // as the realm name.
       if (realm == null) {

          HttpServletRequest request = (HttpServletRequest) msgInfo.getRequestMessage();
          realm = request.getServerName();
        }

       HttpServletResponse response = (HttpServletResponse) msgInfo.getResponseMessage();

       String header = BASIC + " realm=\"" + realm + "\"";
       response.setHeader(AUTHENTICATION_HEADER, header);
       response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
       return AuthStatus.SEND_CONTINUE;
   }

   public AuthStatus secureResponse(MessageInfo msgInfo, Subject service) throws AuthException {
       return AuthStatus.SEND_SUCCESS;
   }

   public void cleanSubject(MessageInfo msgInfo, Subject subject) throws AuthException {
      if (subject != null) {
          subject.getPrincipals().clear();
      }
   }

   private static final String AUTH_TYPE_INFO_KEY = "javax.servlet.http.authType";

   // distinguish the caller principal
   // and assign default groups
   private void setAuthenticationResult(String name, Subject s, MessageInfo m) throws IOException, UnsupportedCallbackException {
       handler.handle(new Callback[]{
           new CallerPrincipalCallback(s, name)
       });
       if (name != null) {
	     // add the default group if the property is set
           if (defaultGroup != null) {
               handler.handle(new Callback[]{
                   new GroupPrincipalCallback(s, defaultGroup)
               });
           }
           m.getMap().put(AUTH_TYPE_INFO_KEY, "techDecisionSAM");
       }
   }
    /**
     * Return the splash resource from session so that we can redirect the user to it
     * if (s)he was logged in using custom form
     * @param session Our current session
     */
    private String getSavedSplashResource(HttpSession session){
    	return  (String)session.getAttribute(Constants.JOSSO_SPLASH_RESOURCE_PARAMETER);
    }


    /**
     * Return the request URI (with the corresponding query string, if any)
     * from the saved request so that we can redirect to it.
     *
     * @param session Our current session
     */
    private String getSavedRequestURL(HttpSession session) {

        return  (String) session.getAttribute(WebAccessControlUtil.KEY_JOSSO_SAVED_REQUEST_URI);

    }

    /**
     * Creates a new request
     */
    /*protected SSOAgentRequest doMakeSSOAgentRequest(int action, String sessionId, LocalSession session, String assertionId,
    HttpServletRequest hreq, HttpServletResponse hres) {
    CatalinaSSOAgentRequest r = new CatalinaSSOAgentRequest(action, sessionId, session, assertionId);
    r.setRequest(hreq);
    r.setResponse(hres);

    return r;

    }*/
    /**
     * Saves the original request URL into our session.
     *
     * @param request The request to be saved
     * @param session The session to contain the saved information
     * @throws IOException
     */
    private void saveRequestURL(HttpServletRequest hreq, HttpSession session) {
    	StringBuffer sb = new StringBuffer(hreq.getRequestURI());
        if (hreq.getQueryString() != null) {
            sb.append('?');
            sb.append(hreq.getQueryString());
        }
        session.setAttribute(WebAccessControlUtil.KEY_JOSSO_SAVED_REQUEST_URI, sb.toString());
        System.out.println("***On a sauvé la requête url="+sb.toString());
    }

    /**
     * Save referer URI into our session for later use.
     *
     * This method is used so agent can know from which
     * public resource (page) user requested login.
     *
     * @param request http request
     * @param session current session
     * @param overrideSavedResource true if saved resource should be overridden, false otherwise
     */
    protected void saveLoginBackToURL(HttpServletRequest request, HttpSession session, boolean overrideSavedResource) {
    	String referer = request.getHeader("referer");
    	if ((getSavedRequestURL(session) == null || overrideSavedResource) && referer != null && !referer.equals("")) {
    		session.setAttribute(WebAccessControlUtil.KEY_JOSSO_SAVED_REQUEST_URI, referer);
        }
    }

    /**
     * Remove saved request URLs from session
     * to avoid mixing up resources from previous operations
     * (logins, logouts) with the current one.
     *
     * @param session Our current session
     */
    protected void clearSavedRequestURLs(HttpSession session) {
    	session.removeAttribute(WebAccessControlUtil.KEY_JOSSO_SAVED_REQUEST_URI);
    	session.removeAttribute(Constants.JOSSO_SPLASH_RESOURCE_PARAMETER);
    }

}
