package an.chopsticks.service;

public class AuditFailedException extends ServiceException {
    private static final long serialVersionUID = 9055335559781605746L;

    public AuditFailedException(String message) {
        super(message);
    }

    public AuditFailedException(String message, Throwable t) {
        super(message, t);
    }
}