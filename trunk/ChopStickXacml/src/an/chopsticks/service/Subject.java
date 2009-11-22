package an.chopsticks.service;

import java.io.Serializable;

import an.chopsticks.provider.Authenticator;

/**
 * The Subject class represents a subject that has already been authenticated by system. The subject can be used in any
 * other security services.  The class is defined as "final", so no one can create a class that inherit from it and to
 * try jump over the authentication.
 * 
 * The subject object is also a context object, it will receive all response attributes while authenticating.
 */
public final class Subject extends DefaultContextImpl implements Serializable {
    private static final long serialVersionUID = 415086800631710913L;

    public static final String SUBJECT_ANONYMOUS = "anonymous";
    public static final String SUBJECT_SYSTEM = "system";

    public static final Subject ANONYMOUS = new Subject(SUBJECT_ANONYMOUS);
    public static final Subject SYSTEM = new Subject(SUBJECT_SYSTEM);

    public static final String ATTR_SUBJECT = "Subject";

    private String subjectName;
    private boolean isAnonymous = false;
    /**
     * The constructor is to authenticate user against the given authentication provider.
     * @param cred The credential need to be authenticated.
     * @param authenticatorOrAssertor The given authenticator or assertor.
     * @param ctx The context needed by authenticator.
     */
    /*packaged*/ Subject(Credential cred, Authenticator authenticator, Context ctx) throws ServiceException {
        // Check if the given provider is came from ProviderFactory.
        ProviderFactory.checkProviderValid(authenticator);
        // Authenticate user
        Context response = authenticator.authenticate(cred, ctx);
        // Merge the response context to current subject.
        setAllAttributes(response);
        // Get subject name from response attribute.
        Attribute subjAttr = getAttribute(ATTR_SUBJECT);
        if (subjAttr != null) {
            String subjName = subjAttr.getValue();
            checkSubjectNameValid(subjName);
            subjectName = subjName;
            // Clear the attribute we have processed.
            removeAttribute(ATTR_SUBJECT);
        }
        else {
            throw new ServiceException("No subject attribute in response context.");
        }
    }
    /**
     * Un subject vide puisque déja authentitfié
     */
    public Subject() {
        //rien
    }

    private void checkSubjectNameValid(String name) throws ServiceException {
        if (SUBJECT_ANONYMOUS.equals(name) || SUBJECT_SYSTEM.equals(name)) {
            throw new ServiceException("The subject name is invalid.");
        }
    }
    /**
     * Used only to construct an anonymous subject.
     */
    private Subject(String name) {
        subjectName = name;
        if (SUBJECT_ANONYMOUS.equals(name)) {
            isAnonymous = true;
        }
    }

    public String getSubjectName() {
        return subjectName;
    }
    public void setSubjecName(String nom){
        subjectName = nom;
    }

    /**
     * Is this subject an anonymous subject.
     * @return
     */
    public boolean isAnonymous() {
        return isAnonymous;
    }
}