package an.chopsticks.service;

public class AuthenticationFailedException extends ServiceException {
    private static final long serialVersionUID = -6321475990740814185L;

    public AuthenticationFailedException(String message) {
        super(message);
    }

    public AuthenticationFailedException(String message, Throwable t) {
        super(message, t);
    }
}