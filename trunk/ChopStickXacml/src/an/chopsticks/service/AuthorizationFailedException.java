package an.chopsticks.service;

public class AuthorizationFailedException extends ServiceException {
    private static final long serialVersionUID = 9027142966158597162L;

    public AuthorizationFailedException(String message) {
        super(message);
    }

    public AuthorizationFailedException(String message, Throwable t) {
        super(message, t);
    }
}