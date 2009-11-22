package an.chopsticks.service;

import java.lang.reflect.Constructor;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import an.chopsticks.provider.Provider;
import an.config.ConfigElement;
import an.xml.XMLElement;

/**
 * The ProviderFactory is to create and cache providers. It create providers from given configuration, and then save the
 * created provider by service manager and configuration. One can create a new provider or retrieve an existing provider
 * from it.   The provider is identified by service manager and provider configuration. Each service manager can have
 * only one provider with same configuration.
 */
public class ProviderFactory {
    private static Map<ServiceManager, Map<ConfigElement, Provider>> providerReg =
        new HashMap<ServiceManager, Map<ConfigElement, Provider>>();

    /**
     * We only allow create providers that have already been defined in service manager's configuration, so we will
     * check the given provider configuration against the service manager. If all things are ok, we will go on to create
     * provider.
     * @param svcMgr The service manager that we will create providers on it.
     * @param service The service this provider initialize for.
     * @param providerConfig The provider's configuration.
     * @return The create provider.
     * @throws ProviderInvalidException This exception throws if the given provider configuration doesn't exist in the
     * given service manager.
     * @throws ProviderInitializationException Indicates there are errors while initializing provider.
     */
    public static synchronized Provider createProvider(
            ServiceManager svcMgr, Service service, ConfigElement providerConfig)
    throws ProviderInvalidException, ProviderInitializationException {
        if (providerConfig == null) {
            throw new NullPointerException("The given provider configuration is null.");
        }
        // Check if the given configuration is valid. If it's not valid, we throw an exception.
        checkProviderConfigValid(svcMgr, providerConfig);
        // Try to get provider from registry
        Map<ConfigElement, Provider> providers = providerReg.get(svcMgr);
        if (providers == null) {
            // If the provider's registry is null, we create an empty one.
            providers = new HashMap<ConfigElement, Provider>();
            providerReg.put(svcMgr, providers);
        }
        // Get provider from registry by configuration.
        Provider provider = providers.get(providerConfig);
        if (provider == null) {
            // If provider doesn't exist in the registry, we create the provider and add it to registry
            String providerClass = (String)providerConfig.getAttributeValueByName(Provider.ATTR_PROVIDER_INTERFACE);
            try {
                Class<?> providerClaz = Class.forName(providerClass);
                Constructor<?> cons = providerClaz.getConstructor(Service.class, ConfigElement.class);
                provider = (Provider)cons.newInstance(service, providerConfig);
                // Add it to registry
                providers.put(providerConfig, provider);
            } catch (Exception e) {
                // If there is any error, we throw an exception.
                throw new ProviderInitializationException("Error occur while initializing provider: " + 
                        providerConfig.getAttributeValueByName(Provider.ATTR_PROVIDER_NAME), e);
            }
        }
        return provider;
    }

    /**
     * We provide such a method to check a given provider is created from service manager or not, if not, we consider it
     * is invalid and throw a ProviderInvalidException.
     * @param provider
     * @throws ProviderInvalidException
     */
    public static void checkProviderValid(Provider provider) throws ProviderInvalidException {
        Collection<Map<ConfigElement, Provider>> allReg = providerReg.values();
        for (Map<ConfigElement, Provider> each : allReg) {
            Collection<Provider> providers = each.values();
            for (Provider p : providers) {
                // We only check reference to confirm the provider is created by ProviderFactory, not by the invoker
                // itself.
                if (provider == p) {
                    return;
                }
            }
        }
        throw new ProviderInvalidException("The given provider does't in the service manager's registry."); 
    }

    private static void checkProviderConfigValid(ServiceManager svcMgr, ConfigElement providerConfig)
    throws ProviderInvalidException {
        ConfigElement[] serviceConfigs = svcMgr.getAllServicesConfigurations();
        for (ConfigElement service : serviceConfigs) {
            // Search the configuration in all service configurations.
            if (findChildConfigElement(service, providerConfig)) {
                return;
            }
        }
        throw new ProviderInvalidException("The given provider configuration does't in the service's configuration.");
    }

    private static boolean findChildConfigElement(ConfigElement root, ConfigElement toFind) {
        XMLElement[] children = root.getChildElements();
        for (XMLElement each : children) {
            if (toFind.equals(each)) {
                return true;
            }
            else {
                boolean result = findChildConfigElement((ConfigElement)each, toFind);
                if (result) {
                    return true;
                }
            }
        }
        return false;
    }

    /*packaged*/ static void cleanup() {
        providerReg.clear();
    }

    /*packaged*/ static void cleanup(ServiceManager svcMgr) {
        providerReg.remove(svcMgr);
    }
}