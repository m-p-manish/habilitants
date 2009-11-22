package an.chopsticks.service;

public class ServiceNotFoundException extends ServiceException {
    private static final long serialVersionUID = 3690643325002711283L;

    public ServiceNotFoundException(String message) {
        super(message);
    }

    public ServiceNotFoundException(String message, Throwable t) {
        super(message, t);
    }
}