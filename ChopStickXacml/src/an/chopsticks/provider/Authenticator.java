package an.chopsticks.provider;

import an.chopsticks.service.AuthenticationFailedException;
import an.chopsticks.service.AuthenticationFlag;
import an.chopsticks.service.Context;
import an.chopsticks.service.Credential;

/**
 * This provide is to authenticate against given credential, and return an assertion if needed. This interface support
 * both authentication and identity assertion. The implementation should have following constructor:
 * 
 *      public AuthenticationImpl(Service service, an.config.ConfigElement config);
 */
public interface Authenticator extends Provider {
    public static final String ELEMTYPE_AUTHENTICATOR = "AuthenticatorType";
    public static final String ATTR_AUTHENTICATIONFLAG = "authenticationFlag";

    /**
     * Get this authenticator supported credential type, such as "NamePassword", "X509", "SAML2Assertion" or others.
     * The credential types are define in Credential class.
     * @return
     */
    public String getSupportedCredentialType();
    /**
     * Authenticate against the given credential. If successfully authenticated the credential, a context with response
     * attributes or null will be returned. Otherwise, an AuthenticationFailedException will be thrown.
     * @param cred This parameter represent some information that can be identify a subject. For example, it can be
     * username and password, or a X509 certificate, or any other such information.
     * @param context The context information may be used in authenticating.
     * @return Response attributes in context.
     */
    public Context authenticate(Credential cred, Context context) throws AuthenticationFailedException;
    /**
     * Each authenticator has a configuration item that represent the authentication flag. The flag should be following
     * value (come from JAAS):
     * 
     *      Required     - The authenticator is required to succeed. If it succeeds or fails, authentication still
     *                     continues to proceed down the authenticator list.
     *      Requisite    - The authenticator is required to succeed. If it succeeds, authentication continues down the
     *                     authenticator list.  If it fails, control immediately returns to the application
     *                     (authentication does not proceed down the authenticator list).
     *      Sufficient   - The authenticator is not required to succeed.  If it does succeed, control immediately
     *                     returns to the application (authentication does not proceed down the authenticator list).  If
     *                     it fails, authentication continues down the authenticator list.
     *      Optional     - The authenticator is not required to succeed.  If it succeeds or fails, authentication still
     *                     continues to proceed down the authenticator list.
     *
     * @return Current authenticator's authentication flag.
     */
    public AuthenticationFlag getFlag();
}