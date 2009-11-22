package an.chopsticks.service;

public class ServiceInitializationException extends ServiceException {
    private static final long serialVersionUID = -140673744285583993L;

    public ServiceInitializationException(String message) {
        super(message);
    }

    public ServiceInitializationException(String message, Throwable t) {
        super(message, t);
    }
}