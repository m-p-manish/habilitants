package an.chopsticks.service;

import an.control.Controllable;
import an.control.Monitorable;

/**
 * This class defined a general interface for a service.
 */
public interface Service extends Monitorable, Controllable {
    public final static String NS_SERVICE = "http://an.com/chopsticks/configuration";

    public final static String ATTR_SERVICE_NAME = "ServiceName";
    public final static String ATTR_SERVICE_INTERFACE = "ServiceInterface";
    public final static String ATTR_DESCRIPTION = "Description";

    public final static String METHOD_GETINSTANCE = "getInstance";
    /**
     * The service should have a name, this method return the name.
     * @return The service name.
     */
    public String getServiceName();
}