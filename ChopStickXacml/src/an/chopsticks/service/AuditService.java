package an.chopsticks.service;

import java.util.HashMap;
import java.util.Map;

import an.chopsticks.provider.Auditor;
import an.config.ConfigElement;
import an.control.OperationFailedException;
import an.control.Status;
import an.log.LogFactory;
import an.log.Logger;
import an.xml.XMLElement;

public final class AuditService implements Service {
    public static final String CATEGORY_AUTHENTICATION = "Authentication";
    public static final String CATEGORY_AUTHORIZATION = "Authorization";
    public static final String CATEGORY_CREDENTIAL = "Credential";
    public static final String CATEGORY_AUDIT = "Audit";
    public static final String CATEGORY_MANAGEMENT = "Management";
    public static final String CATEGORY_NOCATEGORY = "NoCategory";

    static final String ATTR_FORK = "fork";
    static final String ATTR_GLOBALSEVERITY = "globalSeverity";
    static final String ATTR_GLOBALCATEGORYFILTER = "globalCategoryFilter";
    static final String ATTR_GLOBALWITHCONTEXT = "globalWithContext";

    private String serviceName;
    private Auditor[] auditors;
    private boolean fork;
    private AuditSeverity globalSeverity;
    private String globalCategoryFilter;
    private boolean isSetGlobalWithContext = false;
    private boolean globalWithContext;
    /**
     * Save service instances by given service manager, so that we can support multiple service managers in a JVM.
     */
    private static Map<ServiceManager, AuditService> adtSvcBySM = new HashMap<ServiceManager, AuditService>();

    /**
     * We don't want expose the constructor as public, because we don't want user use their own configuration to create
     * service instance, this may lead security hole.
     * @param svcMgr
     * @return
     * @throws ServiceInitializationException 
     * @throws ProviderInvalidException 
     * @throws ProviderInitializationException 
     */
    public static synchronized AuditService getInstance(ServiceManager svcMgr)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException {
        AuditService svc = adtSvcBySM.get(svcMgr);
        if (svc == null) {
            svc = new AuditService(svcMgr);
            adtSvcBySM.put(svcMgr, svc);
        }
        return svc;
    }

    private AuditService(ServiceManager svcMgr)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException {
        ConfigElement config = svcMgr.getServiceConfiguration(AuditService.class);
        serviceName = (String)config.getAttributeValueByName(Service.ATTR_SERVICE_NAME);
        System.out.println("Initializing auditing service '" + serviceName + "' ...");

        // Loading configurations.
        fork = (Boolean)config.getAttributeValueByName(ATTR_FORK);
        if (fork) {
            // TODO initialize a thread pool.
        }

        String strGSeverity = (String)config.getAttributeValueByName(ATTR_GLOBALSEVERITY);
        if (strGSeverity != null) {
            try {
                globalSeverity = AuditSeverity.valueOf(strGSeverity);
            }
            // If the configured value doesn't match any of the enum value, we leave the global severity as null.
            catch (Exception e) {
                globalSeverity = null;
            }
        }

        globalCategoryFilter = (String)config.getAttributeValueByName(ATTR_GLOBALCATEGORYFILTER);
        if (globalCategoryFilter != null && globalCategoryFilter.length() == 0) {
            globalCategoryFilter = null;
        }

        String strGlobalWithContext = (String)config.getAttributeValueByName(ATTR_GLOBALWITHCONTEXT);
        if (strGlobalWithContext != null &&
           (strGlobalWithContext.equalsIgnoreCase("true") || strGlobalWithContext.equalsIgnoreCase("false"))) {
            globalWithContext = Boolean.valueOf(strGlobalWithContext);
            isSetGlobalWithContext = true;
        }
        // If the configured value doesn't match any of the enum value, we leave the isSetGlobalWithContext as false.

        // initialize providers
        initializeProviders(svcMgr, config);
        System.out.println("Auditing service '" + serviceName + "' initialized.");
    }

    private void initializeProviders(ServiceManager svcMgr, ConfigElement serviceConfig)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException {
        System.out.println("Loading auditing providers ...");
        // Get all auditing provider's configuration
        XMLElement[] adtConfigs = serviceConfig.getXMLElementsByTypeNS(Service.NS_SERVICE, Auditor.ELEMTYPE_AUDITOR);
        if (adtConfigs != null && adtConfigs.length > 0) {
            auditors = new Auditor[adtConfigs.length];
            for (int i = 0; i < auditors.length; i ++) {
                // Construct each auditor.
                auditors[i] = (Auditor)ProviderFactory.createProvider(svcMgr, this, (ConfigElement)adtConfigs[i]);
            }
        }
        System.out.println((auditors == null ? 0 : auditors.length) + " auditing providers loaded.");
    }

    /**
     * The main method of audit should have current logged in subject as a parameter, and it also need to be logged to
     * audit event.
     * @param current
     * @param severity
     * @param message
     * @param ctx
     * @throws AuditFailedException
     */
    public void audit(
            Subject current, AuditSeverity severity, String category, String message, Context ctx, Throwable t) {
        if (auditors != null && auditors.length > 0) {
            Context adtCtx = new DefaultContextImpl();
            adtCtx.setAllAttributes(ctx);
            Attribute subjAttr = new Attribute(Subject.ATTR_SUBJECT, AttributeType.String, current.getSubjectName());
            adtCtx.setAttribute(subjAttr);
            for (int x = 0; x < auditors.length; x ++) {
                auditors[x].audit(severity, category, message, adtCtx, t);
            }
        }
        // We don't throw an exception
        else {
            Logger logger = LogFactory.getLogger();
            logger.warn("No audit provider configured for audit service, we can't audit any event.");
        }
    }

    public void audit(Subject current, AuditSeverity severity, String category, String message, Context ctx) {
        audit(current, severity, category, message, ctx, null);
    }

    public String getServiceName() {
        return serviceName;
    }

    public AuditSeverity getGlobalSeverity() {
        return globalSeverity;
    }

    public String getGlobalCategoryFilter() {
        return globalCategoryFilter;
    }

    public boolean isSetGlobalWithContext() {
        return isSetGlobalWithContext;
    }

    public boolean getGlobalWithContext() {
        if (isSetGlobalWithContext()) {
            return globalWithContext;
        }
        else {
            throw new UnsupportedOperationException("Current service was not set the 'globalWithContext'.");
        }
    }

    public Status getStatus() {
        // TODO Auto-generated method stub
        return null;
    }

    public Object getStatusProperty(String key) {
        // TODO Auto-generated method stub
        return null;
    }

    public void pause() throws OperationFailedException {
        throw new OperationFailedException("We don't support pause operation on this service.");
    }

    public void resume() throws OperationFailedException {
        throw new OperationFailedException("We don't support resume operation on this service.");
    }

    public void shutdown() throws OperationFailedException {
        System.out.println("Shutting down auditing service '" + serviceName + "' ...");
        if (auditors != null && auditors.length > 0) {
            for (Auditor each : auditors) {
                each.shutdown();
            }
        }
        System.out.println("Auditing service '" + serviceName + "' shutdown.");
    }

    public void shutdownForce() throws OperationFailedException {
        System.out.println("Shutting down auditing service '" + serviceName + "' ...");
        if (auditors != null && auditors.length > 0) {
            for (Auditor each : auditors) {
                each.shutdownForce();
            }
        }
        System.out.println("Auditing service '" + serviceName + "' shutdown.");
    }

    public void start() throws OperationFailedException {
        System.out.println("Starting auditing service '" + serviceName + "' ...");
        if (auditors != null && auditors.length > 0) {
            for (Auditor each : auditors) {
                each.start();
            }
        }
        System.out.println("Auditing service '" + serviceName + "' started.");
    }
}