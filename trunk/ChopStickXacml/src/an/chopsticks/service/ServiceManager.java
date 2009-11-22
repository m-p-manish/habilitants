package an.chopsticks.service;

import java.io.FileNotFoundException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.xml.namespace.QName;

import an.config.ConfigElement;
import an.config.Configuration;
import an.config.ConfigurationException;
import an.control.OperationFailedException;
import an.datatype.encryption;
import an.log.LogFactory;
import an.log.LogInitializationException;
import an.xml.XMLDataTypeRegistry;
import an.xml.XMLGeneralException;
import java.io.InputStream;
import org.springframework.context.ApplicationContext;

public final class ServiceManager {
    static final String ELEMTYPE_LOG = "LogType";
    static final String ELEMTYPE_SERVICE = "ServiceType";
    static final String ELEM_ADMIN = "Admin";
    static final String ELEM_SESSION = "SessionManager";
    static final String ELEM_SERVICES = "Services";
    static final String ATTR_SECONDARYURL = "secondaryURL";
    static final String ATTR_LAZYLOAD = "lazyLoadServices";

    static {
        // The data type we are using.
        XMLDataTypeRegistry.register(
                new QName(Service.NS_SERVICE, encryption.class.getSimpleName()), encryption.class);
    }
    /**
     * Service configuration registry
     */
    private Map<Class<?>, ConfigElement> serviceConfigurations = new HashMap<Class<?>, ConfigElement>();
    /**
     * Service registry
     */
    private Map<Class<?>, Service> services = new HashMap<Class<?>, Service>();
    /**
     * The backup admin URL when the primary admin is failed.
     */
    private String secondaryAdminURL;
    /**
     * Session configuration may be null, if it is null, we don't need initialize session manager.
     */
    private ConfigElement sessionConfig;
    private boolean lazyLoadServices;

    /*************************************************/
    /**XXX This constructor is only for test purpose**/
    /*************************************************/
    private static Map<String, ServiceManager> mgrReg = new HashMap<String, ServiceManager>();
    private ApplicationContext ctxSpring = null;

    public ApplicationContext getCtxSpring() {
        return ctxSpring;
    }

    public void setCtxSpring(ApplicationContext ctxSpring) {
        this.ctxSpring = ctxSpring;
    }

    public static synchronized ServiceManager getInstance(String configFile) throws ServiceInitializationException,
    FileNotFoundException, XMLGeneralException, LogInitializationException, ConfigurationException {
        System.out.println("registe manager contient ="+mgrReg.size());
        ServiceManager mgr = mgrReg.get(configFile);
        if (mgr == null) {
            mgr = new ServiceManager(configFile);
            mgrReg.put(configFile, mgr);
        }
        return mgr;
    }
    public static synchronized ServiceManager getInstance(String configFile, InputStream inn) throws ServiceInitializationException,
    FileNotFoundException, XMLGeneralException, LogInitializationException, ConfigurationException {
        ServiceManager mgr = mgrReg.get(configFile);
        if (mgr == null) {
            mgr = new ServiceManager(inn);
            mgrReg.put(configFile, mgr);
            System.out.println("registe manager contient ="+mgrReg.size()+" avec="+configFile);
        }
        return mgr;
    }

    private ServiceManager(String configFile) throws XMLGeneralException,
    LogInitializationException, ServiceInitializationException, FileNotFoundException, ConfigurationException {
        System.out.println("Initializing Service Manager pour configFile="+configFile);
        // Construct from configuration that retrieved from admin.
        
        Configuration config = null;
        config = new Configuration(configFile);
        ConfigElement root = config.getConfigurationElement();
        initialize(root);
        System.out.println("Service Manager initialized.");
    }
    
    private ServiceManager(InputStream in) throws XMLGeneralException,
    LogInitializationException, ServiceInitializationException, FileNotFoundException, ConfigurationException {
        System.out.println("Initializing Service Manager pour stream");
        // Construct from configuration that retrieved from admin.
        Configuration config = null;
        if(in!=null){
            config = new Configuration(in);
        }else{
            System.err.println("Erreur sur fichier config vide !");
            throw new ConfigurationException("Erreur sur stream config vide");
        }
        ConfigElement root = config.getConfigurationElement();
        initialize(root);
        System.out.println("Service Manager initialized.");
    }
    /*************************************************/
    /*************************************************/

    /**
     * TODO We will add a getInstance method that enable multiple service managers in a JVM. We use adminURL and
     * application name to separate them.
     * Construct the service manager from admin. We use the normal constructor to support multiple service manager in a
     * JVM.
     * @param adminURL The admin's URL that expose the service's configuration
     * @param applicationName The application name that service manager will be used in.
     * @throws XMLGeneralException 
     * @throws LogInitializationException 
     * @throws ServiceInitializationException 
     * @throws ConfigurationException 
     * @throws FileNotFoundException 
     */
    public ServiceManager(String adminURL, String applicationName) throws XMLGeneralException,
    LogInitializationException, ServiceInitializationException, FileNotFoundException, ConfigurationException {
        System.out.println("Initializing Service Manager ...");
        // Construct from configuration that retrieved from admin.
        Configuration config = getConfigurationFromAdmin(adminURL, applicationName);
        ConfigElement root = config.getConfigurationElement();
        initialize(root);
        System.out.println("Service Manager initialized.");
    }

    /**
     * Get the main configuration from admin.
     * @param adminURL
     * @return
     * @throws ConfigurationException 
     * @throws FileNotFoundException 
     */
    /*packaged*/ Configuration getConfigurationFromAdmin(String adminURL, String applicationName)
    throws FileNotFoundException, ConfigurationException {
        System.out.println("Geting configuration from '" + adminURL + "' for application '" + applicationName + "' ...");
        // TODO: implement it
        ///////////////////
        Configuration config = new Configuration("./config/urlsample.xml");
        ///////////////////
        System.out.println("Got configuration.");
        return config;
    }

    void initialize(ConfigElement config)
    throws XMLGeneralException, LogInitializationException, ServiceInitializationException {
        // Init log first
        ConfigElement logConfig = (ConfigElement)config.getSingleXMLElementByType(ELEMTYPE_LOG);
        LogFactory.initialize(logConfig);

        ConfigElement adminConfig = (ConfigElement)config.getSingleXMLElementByName(ELEM_ADMIN);
        secondaryAdminURL = (String)adminConfig.getAttributeValueByName(ATTR_SECONDARYURL);

        sessionConfig = (ConfigElement)config.getSingleXMLElementByName(ELEM_SESSION);
        // Get the services configuration and then initialize all services.
        ConfigElement servicesConfig = (ConfigElement)config.getSingleXMLElementByName(ELEM_SERVICES);
        initializeSevices(servicesConfig);
    }

    void initializeSevices(ConfigElement servicesConfig) throws ServiceInitializationException {
        if (servicesConfig != null) {
            System.out.println("Loading services ...");
            lazyLoadServices = (Boolean)servicesConfig.getAttributeValueByName(ATTR_LAZYLOAD);
            if (lazyLoadServices) {
                System.out.println("Services are set to 'lazy load', so they will be initialized as required.");
            }
            ConfigElement[] configs = (ConfigElement[])servicesConfig.getXMLElementsByType(ELEMTYPE_SERVICE);
            for (ConfigElement each : configs) {
                String intf = (String)each.getAttributeValueByName(Service.ATTR_SERVICE_INTERFACE);
                String svcName = (String)each.getAttributeValueByName(Service.ATTR_SERVICE_NAME);
                try {
                    Class<?> claz = Class.forName(intf);
                    // register service configuration
                    serviceConfigurations.put(claz, each);
                    // If set to lazy load, we don't need to load services now.
                    if (!lazyLoadServices) {
                        initializeService(claz);
                    }
                }
                catch (Exception e) {
                    throw new ServiceInitializationException("Error occurs while initializing service: " + svcName, e);
                }
            }
            System.out.println(serviceConfigurations.size() + " services loaded.");
        }
    }

    private void initializeService(Class<?> svcClaz) throws Exception {
        // get the service's getInstance method.
        Method getInstance = svcClaz.getMethod(Service.METHOD_GETINSTANCE, ServiceManager.class);
        // create the service from current ServiceManager object.
        Service service = (Service)getInstance.invoke(null, this);
        // Start the service
        service.start();
        // register the service
        services.put(svcClaz, service);
    }

    public String getSecondaryAdminURL() {
        return secondaryAdminURL;
    }

    /*packaged*/ ConfigElement getSessionManagerConfiguration() {
        // We return a copy of configuration object, because we don't want user get it and then change it.
        if (sessionConfig != null) {
            return new ConfigElement(sessionConfig);
        }
        return null;
    }

    /*packaged*/ ConfigElement getServiceConfiguration(Class<?> svcClaz) {
        // Return a cloned one to avoid user modify the configuration.
        ConfigElement svcConfig = serviceConfigurations.get(svcClaz);
        if (svcConfig != null) {
            return new ConfigElement(svcConfig);
        }
        return null;
    }

    /*packaged*/ ConfigElement[] getAllServicesConfigurations() {
        Collection<ConfigElement> allConfigs = serviceConfigurations.values();
        ConfigElement[] result = new ConfigElement[allConfigs.size()];
        int index = 0;
        Iterator<ConfigElement> i = allConfigs.iterator();
        while (i.hasNext()) {
            ConfigElement each = i.next();
            // We clone each ConfigElement object.
            result[index ++] = new ConfigElement(each);
        }
        return result;
    }

    public synchronized Service getService(Class<?> svcClaz)
    throws ServiceInitializationException, ServiceNotFoundException {
        System.out.println("cherchons un service ="+svcClaz.getName());
        Service svc = services.get(svcClaz);
        // If the service expected is null and we configured lazy load, we will try to load the service now.
        if (svc == null && lazyLoadServices) {
            // If no service configuration found, we'll throw a ServiceNotFoundException
            if (getServiceConfiguration(svcClaz) == null) {
                System.err.println("The configuration of service '" + svcClaz.getSimpleName() + "' was not found.");
                throw new ServiceNotFoundException("The configuration of service '" + svcClaz.getSimpleName() +
                        "' was not found.");
            }

            // If there are errors occur while initialize service, we'll throw a ServiceInitializeException.
            try {
                initializeService(svcClaz);
            }
            catch (Exception e) {
                System.err.println("Error occurs while initializing service: " + svcClaz.getSimpleName());
                throw new ServiceInitializationException("Error occurs while initializing service: " +
                        svcClaz.getSimpleName(), e);
            }
        }
        return svc;
    }

    public void shutdown() throws OperationFailedException {
        System.out.println("Shutting down Service Manager ...");
        // Shutdown all services first
        Collection<Service> allSvcs = services.values();
        for (Service each : allSvcs) {
            each.shutdown();
        }
        // Then cleanup SessionManager
        SessionManager.cleanup(this);
        // Then cleanup ProviderFactory
        ProviderFactory.cleanup(this);
        System.out.println("Service Manager shutdown.");
    }

    public void shutdownForce() throws OperationFailedException {
        System.out.println("Shutting down Service Manager ...");
        // Shutdown all services first
        Collection<Service> allSvcs = services.values();
        for (Service each : allSvcs) {
            each.shutdownForce();
        }
        // Then cleanup SessionManager
        SessionManager.cleanup(this);
        // Then cleanup ProviderFactory
        ProviderFactory.cleanup(this);
        System.out.println("Service Manager shutdown.");
    }
}