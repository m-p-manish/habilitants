package an.chopsticks.service;

public class SessionPersistenceException extends ServiceException {
    private static final long serialVersionUID = 1892031971978384374L;

    public SessionPersistenceException(String message) {
        super(message);
    }

    public SessionPersistenceException(String message, Throwable t) {
        super(message, t);
    }
}