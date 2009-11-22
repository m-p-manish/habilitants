package an.chopsticks.provider;

import an.chopsticks.service.Credential;
import an.chopsticks.service.Subject;

/**
 * The credential converter perform conversion between different type of credentials. This converter also can convert
 * an authenticated Subject to a dest credential type. The implementation should have following constructor:
 * 
 *      public CredentialConverterImpl(Service service, an.config.ConfigElement);
 */
public interface CredentialConverter {
    /**
     * Return the supported source credential type. If this converter only support convert Subject to a type of
     * credential, then this method should return null.
     * @return
     */
    public String getSupportedSourceType();
    /**
     * Return the supported destination credential type.
     * @return
     */
    public String getSupportedDestType();
    /**
     * Perform conversion from source credential to a destination credential.
     * @param source The source credential.
     * @param destType The destination credential's type.
     * @return The destination credential object.
     */
    public Credential convert(Credential source, String destType);
    /**
     * Perform conversion from Subject to a destination credential.
     * @param subject The authenticated subject.
     * @param destType The destination credential's type.
     * @return The destination credential object.
     */
    public Credential convert(Subject subject, String destType);
}