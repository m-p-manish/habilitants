package an.chopsticks.service;

public class ProviderInvalidException extends ServiceException {
    private static final long serialVersionUID = -5421011778742184694L;

    public ProviderInvalidException(String message) {
        super(message);
    }

    public ProviderInvalidException(String message, Throwable t) {
        super(message, t);
    }
}