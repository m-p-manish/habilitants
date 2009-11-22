package an.chopsticks.service;

public class ProviderInitializationException extends ServiceException {
    private static final long serialVersionUID = -1724187139172702771L;

    public ProviderInitializationException(String message) {
        super(message);
    }

    public ProviderInitializationException(String message, Throwable t) {
        super(message, t);
    }
}