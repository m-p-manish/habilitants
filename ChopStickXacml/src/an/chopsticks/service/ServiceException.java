package an.chopsticks.service;

public class ServiceException extends Exception {
    private static final long serialVersionUID = 5837066545797417615L;

    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable t) {
        super(message, t);
    }
}