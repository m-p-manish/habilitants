package an.chopsticks.service;

/**
 * This class represents a credential that used to do authentication, such as user name and password, or something like
 * a X509 certificate identified a user.
 */
public interface Credential {
    public static final String TYPE_ATN_NAMEPASS = "NamePassword";
    public static final String TYPE_ASSERTION_AN = "AnAssertion";
    public static final String TYPE_ASSERTION_SAML2 = "Saml2Assertion";

    public static final String KEY_USERNAME = "Username";
    public static final String KEY_PASSWORD = "Password";
    /**
     * Indicates current credential's type, such as "Username password", "X509" or "SAMLAssertion". The authentication
     * service should use this type to determine which provider should be used to do authentication. Authentication
     * providers should process those credentials that they supported.
     * @return
     */
    public String getType();
    /**
     * Get the actual credential object. For example, if current credential is "X509" type, then the credential object
     * should be an X509 certificate that can identify someone's identity.
     * @return
     */
    public Object getCredentialObject();
}