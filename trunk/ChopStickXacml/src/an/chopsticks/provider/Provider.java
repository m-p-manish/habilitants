package an.chopsticks.provider;

import an.control.Controllable;
import an.control.Monitorable;

/**
 * Define the base interface of Provider.
 */
public interface Provider extends Monitorable, Controllable {
    public final static String NS_PROVIDER = "http://an.com/chopsticks/provider";

    public static final String ATTR_PROVIDER_NAME = "ProviderName";
    public static final String ATTR_PROVIDER_INTERFACE = "ProviderInterface";
    /**
     * Return the provider's name.
     * @return The provider's name.
     */
    public String getProviderName();
}