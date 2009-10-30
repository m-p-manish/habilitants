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
import javax.servlet.http.Cookie;
   import javax.servlet.http.HttpServletRequest;
   import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.HttpSession;
   import org.josso.agent.Lookup;
import org.josso.agent.SSOPartnerAppConfig;
import org.josso.agent.http.WebAccessControlUtil;
   import org.josso.gl2.agent.FacesSSOAgent;
   import org.josso.agent.Constants;
import org.josso.agent.SSOAgentRequest;
import org.josso.agent.SingleSignOnEntry;
import org.josso.agent.http.HttpSSOAgentRequest;
import org.josso.servlet.agent.GenericServletLocalSession;
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
      public static final String KEY_JOSSO_SAVED_REQUEST = "org.josso.servlet.agent.savedRequest";

      private static final String KEY_SESSION_MAP = "org.josso.servlet.agent.sessionMap";

      // Used for the auth-type string.
      public static final String WEBAUTH_PROGRAMMATIC="PROGRAMMATIC";

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
          System.out.println("-*/*/*- Debut validate module techDecision.JossoSAM (fin init)");
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
              String contextPath = request.getContextPath();
              String vhost = request.getServerName();
              if (!_agent.isPartnerApp(vhost, contextPath)) {
                  System.out.println("**JossoSAM** Context is not a josso partner app : " + request.getContextPath());
                 HttpServletResponse response = (HttpServletResponse) msgInfo.getResponseMessage();
                 response.sendRedirect(jeVeux);
                  return AuthStatus.SUCCESS;
              }else{
                  System.out.println("**JossoSAM** Context IS a josso partner app =" + request.getContextPath());
              }

            // ------------------------------------------------------------------
            // Check some basic HTTP handling
            // ------------------------------------------------------------------
            // P3P Header for IE 6+ compatibility when embedding JOSSO in a IFRAME
            SSOPartnerAppConfig cfg = _agent.getPartnerAppConfig(vhost, contextPath);
            HttpServletResponse hres = (HttpServletResponse) msgInfo.getResponseMessage();
            if (cfg.isSendP3PHeader() && !hres.isCommitted()) {
                hres.setHeader("P3P", cfg.getP3PHeaderValue());
            }
             // Get our session ...
             HttpSession session = request.getSession(true);
             //equivalent à la page de login si pas autorisé on passe par l'authent
             String username = processAuthorizationToken(msgInfo, client);
             if (username == null && requestPolicy.isMandatory()) {
                 System.out.println("**JossoSAM** Il faut une authentification préalable (première URL)!");
                 //return sendAuthenticateChallenge(msgInfo);
                 //return sendAuthenticateChallenge2(msgInfo);
                 saveRequestURL(request, session);
                 HttpServletResponse response = (HttpServletResponse) msgInfo.getResponseMessage();
                 response.setStatus(HttpServletResponse.SC_MOVED_TEMPORARILY);
                 //response.setHeader("Location", jeVeux);
                 response.sendRedirect(request.getContextPath() + "/josso_login/");
                 return AuthStatus.SEND_CONTINUE;
             }
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

            // ------------------------------------------------------------------
            // Check if the partner application required a logout
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Checking if its a josso_logout request for '" + request.getRequestURI() + "'");

            if (request.getRequestURI().endsWith(_agent.getJOSSOLogoutUri())) {

                if (debug==1)
                    System.out.println("josso_logout request received for uri '" + request.getRequestURI() + "'");

                String logoutUrl = _agent.buildLogoutUrl(request, cfg);

                if (debug==1)
                    System.out.println("Redirecting to logout url '" + logoutUrl + "'");

                // Clear previous COOKIE ...
                HttpServletResponse response = (HttpServletResponse) msgInfo.getResponseMessage();
                Cookie ssoCookie = _agent.newJossoCookie(request.getContextPath(), "-");
                response.addCookie(ssoCookie);

                // invalidate session (unbind josso security context)
                session.invalidate();

                //set non cache headers
                _agent.prepareNonCacheResponse(response);
                response.sendRedirect(response.encodeRedirectURL(logoutUrl));

                return AuthStatus.SEND_CONTINUE;

            }

            // ------------------------------------------------------------------
            // Check for the single sign on cookie
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Checking for SSO cookie");
            Cookie cookie = null;
            Cookie cookies[] = request.getCookies();
            if (cookies == null)
                cookies = new Cookie[0];
            for (int i = 0; i < cookies.length; i++) {
                if (org.josso.gateway.Constants.JOSSO_SINGLE_SIGN_ON_COOKIE.equals(cookies[i].getName())) {
                    cookie = cookies[i];
                    break;
                }
            }

            String jossoSessionId = (cookie == null) ? null : cookie.getValue();
            GenericServletLocalSession localSession = new GenericServletLocalSession(session);

            // ------------------------------------------------------------------
            // Check if the partner application submitted custom login form
            // ------------------------------------------------------------------

            if (debug==1){
                System.out.println("Checking if its a josso_authentication for '" + request.getRequestURI() + "'");
            }
            if (request.getRequestURI().endsWith(_agent.getJOSSOAuthenticationUri())) {

            	if (debug==1){
                    System.out.println("josso_authentication received for uri '" + request.getRequestURI() + "'");
            	}

            	HttpSSOAgentRequest customAutrequestuest = new HttpSSOAgentRequest(
                		SSOAgentRequest.ACTION_CUSTOM_AUTHENTICATION, jossoSessionId, localSession);//(CatalinaSSOAgentRequest) doMakeSSOAgentRequest( SSOAgentRequest.ACTION_CUSTOM_AUTHENTICATION, jossoSessionId, localSession, null, request, hres);

                _agent.processRequest(customAutrequestuest);

                return AuthStatus.SEND_CONTINUE;
            }

            if (cookie == null || cookie.getValue().equals("-")) {

            	// ------------------------------------------------------------------
                // Trigger LOGIN OPTIONAL if required
                // ------------------------------------------------------------------

            	 if (debug==1)
            		 System.out.println("SSO cookie is not present, verifying optional login process ");

                // We have no cookie, remember me is enabled and a security check without assertion was received ...
                // This means that the user could not be identified ... go back to the original resource
                if (request.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                    request.getParameter("josso_assertion_id") == null) {

                    if (debug==1)
                             System.out.println(_agent.getJOSSOSecurityCheckUri() + " received without assertion.  Login Optional Process failed");

                    String requestURI = getSavedRequestURL(session);
                    if (debug==1)
                             System.out.println("retour à l'envoyeur ="+requestURI);
                    HttpServletResponse response = (HttpServletResponse) msgInfo.getResponseMessage();
                    _agent.prepareNonCacheResponse(response);
                    response.sendRedirect(response.encodeRedirectURL(requestURI));
                    return AuthStatus.SEND_CONTINUE;

                }

            	// This is a standard anonymous request!
                if (!request.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri())) {

                    if (!_agent.isResourceIgnored(cfg, request) &&
                    		_agent.isAutomaticLoginRequired(request)) {

                        if (debug==1)
                        	System.out.println("SSO cookie is not present, attempting automatic login");

                        // Save current request, so we can co back to it later ...
                        System.out.println("***On sauve la requête 1 ***");
                        saveRequestURL(request, session);
                        String loginUrl = _agent.buildLoginOptionalUrl(request);

                        if (debug==1)
                        	System.out.println("Redirecting to login url '" + loginUrl + "'");

                        //set non cache headers
                        _agent.prepareNonCacheResponse(hres);
                        hres.sendRedirect(hres.encodeRedirectURL(loginUrl));
                        return AuthStatus.SEND_CONTINUE;
                    } else {
                    	if (debug==1)
                    		System.out.println("SSO cookie is not present, but login optional process is not required");
                    }
                }

                if (debug==1)
                	System.out.println("SSO cookie is not present, checking for outbound relaying");

                if (!(request.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                    request.getParameter("josso_assertion_id") != null)) {
                    System.out.println("SSO cookie not present and relaying was not requested, skipping");
                    return AuthStatus.SUCCESS;
                }

            }

            // ------------------------------------------------------------------
            // Check if this URI is subject to SSO protection
            // ------------------------------------------------------------------
            if (_agent.isResourceIgnored(cfg, request)) {
                return AuthStatus.SUCCESS;
            }

            // This URI should be protected by SSO, go on ...
            if (debug==1)
                System.out.println("Session is: " + session);

            // ------------------------------------------------------------------
            // Invoke the SSO Agent
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Executing agent...");

            // ------------------------------------------------------------------
            // Check if a user has been authenitcated and should be checked by the agent.
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Checking if its a josso_security_check for '" + request.getRequestURI() + "'");

            if (request.getRequestURI().endsWith(_agent.getJOSSOSecurityCheckUri()) &&
                request.getParameter("josso_assertion_id") != null) {

                if (debug==1)
                    System.out.println("josso_security_check received for uri '" + request.getRequestURI() + "' assertion id '" +
                            request.getParameter("josso_assertion_id")
                    );

                String assertionId = request.getParameter(Constants.JOSSO_ASSERTION_ID_PARAMETER);

                HttpSSOAgentRequest relayRequest;

                if (debug==1)
                    System.out.println("Outbound relaying requested for assertion id [" + assertionId + "]");

                relayRequest = new HttpSSOAgentRequest(
                        SSOAgentRequest.ACTION_RELAY, null, localSession, assertionId);

               relayRequest.setRequest(request);
               relayRequest.setResponse(hres);
               SingleSignOnEntry entry = _agent.processRequest(relayRequest);
                if (entry == null) {
                    // This is wrong! We should have an entry here!
                    System.err.println("Outbound relaying failed for assertion id [" + assertionId + "], no Principal found.");
                    // Throw an exception and let the container send the INERNAL SERVER ERROR
                    throw new AuthException("No Principal found. Verify your SSO Agent Configuration!");
                }

                if (debug==1)
                    System.out.println("Outbound relaying succesfull for assertion id [" + assertionId + "]");

                if (debug==1)
                    System.out.println("Assertion id [" + assertionId + "] mapped to SSO session id [" + entry.ssoId + "]");

                // The cookie is valid to for the partner application only ... in the future each partner app may
                // store a different auth. token (SSO SESSION) value
                cookie = _agent.newJossoCookie(request.getContextPath(), entry.ssoId);
                hres.addCookie(cookie);

                // Redirect the user to the original request URI (which will cause
                // the original request to be restored)
                String requestURI = getSavedSplashResource(session);
                if(requestURI == null) {
                	requestURI = getSavedRequestURL(session);
	                if (requestURI == null) {

	                	if (cfg.getDefaultResource() != null) {
                            requestURI = cfg.getDefaultResource();
                        } else {
    		                // If no saved request is found, redirect to the partner app root :
	    	                requestURI = request.getRequestURI().substring(
		                        0, (request.getRequestURI().length() - _agent.getJOSSOSecurityCheckUri().length()));
                        }

	                    // If we're behind a reverse proxy, we have to alter the URL ... this was not necessary on tomcat 5.0 ?!
	                    String singlePointOfAccess = _agent.getSinglePointOfAccess();
	                    if (singlePointOfAccess != null) {
	                        requestURI = singlePointOfAccess + requestURI;
	                    } else {
	                        String reverseProxyHost = request.getHeader(org.josso.gateway.Constants.JOSSO_REVERSE_PROXY_HEADER);
	                        if (reverseProxyHost != null) {
	                            requestURI = reverseProxyHost + requestURI;
	                        }
	                    }

	                    if (debug==1)
	                        System.out.println("No saved request found, using : '" + requestURI + "'");
	                }
                }

                clearSavedRequestURLs(session);
               	_agent.clearAutomaticLoginReferer(request);
               	_agent.prepareNonCacheResponse(hres);
                //on essaie une autre méthode pour associer le principal au flux http pour ne plus avoir la redirection
                //sur la page de login
                System.out.println("Classe du principal obtenu="+entry.getClass().toString());

                /*if(!attachePrincipal(request, (com.sun.enterprise.deployment.PrincipalImpl)entry.principal, hres)){
                System.out.println("Marche pas mon truc !");
                }else{
                System.out.println("Essayer d'ajouter le principal au flux http="+entry.principal.toString());
                }*/

                setAuthenticationResult(username, client, msgInfo);

               	// Check if we have a post login resource :
                String postAuthURI = cfg.getPostAuthenticationResource();
                if (postAuthURI != null) {
                    String postAuthURL = _agent.buildPostAuthUrl(hres, requestURI, postAuthURI);
                    if (debug==1)
                        System.out.println("Redirecting to post-auth-resource '" + postAuthURL  + "'");
                    hres.sendRedirect(postAuthURL);
                } else {
                	if (debug==1)
                         System.out.println("Redirecting to original '" + requestURI + "' on SUCCESS!");
                    hres.sendRedirect(hres.encodeRedirectURL(requestURI));
                }

                return AuthStatus.SUCCESS;
            }


            SSOAgentRequest r = new HttpSSOAgentRequest(
                    SSOAgentRequest.ACTION_ESTABLISH_SECURITY_CONTEXT, jossoSessionId, localSession);
            SingleSignOnEntry entry = _agent.processRequest(r);

            if (debug==1)
                System.out.println("Executed agent.");

            // Get session map for this servlet context.
            Map sessionMap = (Map) request.getSession().getServletContext().getAttribute(KEY_SESSION_MAP);
            if (sessionMap.get(localSession.getWrapped()) == null) {
                // the local session is new so, make the valve listen for its events so that it can
                // map them to local session events.
                // Not supported : session.addSessionListener(this);
                sessionMap.put(session, localSession);
            }

            // ------------------------------------------------------------------
            // Has a valid user already been authenticated?
            // ------------------------------------------------------------------
            if (debug==1)
                System.out.println("Process request for '" + request.getRequestURI() + "'");

            if (entry != null) {
                if (debug==1)
                    System.out.println("Principal '" + entry.principal +
                        "' has already been authenticated");
                // TODO : Not supported
                // (request).setAuthType(entry.authType);
                // (request).setUserPrincipal(entry.principal);
            } else {
            	System.out.println("No Valid SSO Session, attempt an optional login?");
                // This is a standard anonymous request!

            	if (cookie != null) {
                	// cookie is not valid
                	cookie = _agent.newJossoCookie(request.getContextPath(), "-");
                	hres.addCookie(cookie);
                }

            	if (cookie != null || (getSavedRequestURL(session) == null && _agent.isAutomaticLoginRequired(request))) {

                    if (debug==1)
                    	System.out.println("SSO Session is not valid, attempting automatic login");

                    // Save current request, so we can co back to it later ...
                    System.out.println("***On sauve la requête 2 ***");
                    saveRequestURL(request, session);
                    String loginUrl = _agent.buildLoginOptionalUrl(request);

                    if (debug==1)
                    	System.out.println("Redirecting to login url '" + loginUrl + "'");

                    //set non cache headers
                    _agent.prepareNonCacheResponse(hres);
                    hres.sendRedirect(hres.encodeRedirectURL(loginUrl));
                    return AuthStatus.SEND_CONTINUE;
                } else {
                    if (debug==1)
                    	System.out.println("SSO cookie is not present, but login optional process is not required");
                }

            }

            // propagate the login and logout URLs to
            // partner applications.
            request.setAttribute("org.josso.agent.gateway-login-url", _agent.getGatewayLoginUrl() );
            request.setAttribute("org.josso.agent.gateway-logout-url", _agent.getGatewayLogoutUrl() );
            request.setAttribute("org.josso.agent.ssoSessionid", jossoSessionId);

             System.out.println("**JossoSAM** On est authentifé donc autorisé donc on ajoute le groupe qui va bien ...");
             setAuthenticationResult(username, client, msgInfo);
             return AuthStatus.SUCCESS;

          } catch (Exception e) {
              System.err.println("**JossoSAM** Erreur "+e.toString());
              AuthException ae = new AuthException();
              ae.initCause(e);
              throw ae;
          }
      }

      private String processAuthorizationToken(MessageInfo msgInfo, Subject s) throws AuthException {

          System.out.println("**JossoSAM** Analyse du message en vue d'authentification...");
          HttpServletRequest request = (HttpServletRequest) msgInfo.getRequestMessage();

          String token = request.getHeader(AUTHORIZATION_HEADER);

          if (token != null && token.startsWith(BASIC + " ")) {
              token = token.substring(6).trim();

              System.out.println("**JossoSAM** décodage du token="+token);
              // Decode and parse the authorization token
              String decoded = new String(Base64.decode(token));

              int colon = decoded.indexOf(':');
              if (colon <= 0 || colon == decoded.length() - 1) {
                  System.err.println("**JossoSAM** Erreur analyse decoded token="+decoded);
                  return (null);
              }

              String username = decoded.substring(0, colon);

             // use the callback to ask the container to
             // validate the password
            System.out.println("**JossoSAM** On valide le mot de passe de username="+username);
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
      System.err.println("**JossoSAM** Rien dans le header échec de l'authentification...");
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
     * Saves the original request URL into our session.
     *
     * @param request The request to be saved
     * @param session The session to contain the saved information
     * @throws IOException
     */
    private void saveRequestURL(HttpServletRequest request, HttpSession session) {
    	StringBuffer sb = new StringBuffer(request.getRequestURI());
        if (request.getQueryString() != null) {
            sb.append('?');
            sb.append(request.getQueryString());
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
