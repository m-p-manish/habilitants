package an.chopsticks.service;

public class SessionSizeExceedCapacityException extends ServiceException {
    private static final long serialVersionUID = 4802865145361079639L;

    public SessionSizeExceedCapacityException(String message) {
        super(message);
    }

    public SessionSizeExceedCapacityException(String message, Throwable t) {
        super(message, t);
    }
}