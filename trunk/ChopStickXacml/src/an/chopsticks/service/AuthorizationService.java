package an.chopsticks.service;

import static an.util.PackageUtil.findClassesByPackage;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

import an.chopsticks.provider.AdjudicationAlgorithm;
import an.chopsticks.provider.AdjudicationProvider;
import an.chopsticks.provider.Authorizer;
import an.config.ConfigElement;
import an.control.OperationFailedException;
import an.control.Status;
import an.xml.XMLElement;

public final class AuthorizationService implements Service {
    static final String ATTR_ADJUDICATION = "adjudicationAlgorithm";
    static final String ADJ_ALGORITHM_PACKAGE = "an.chopsticks.provider.impl";
    private static Map<String, AdjudicationAlgorithmIntf> algReg;

    private AuditService adtSvc;
    private String serviceName;
    private String adjudicationAlg;
    private Authorizer[] authorizers;
    /**
     * Save service instances by given service manager, so that we can support multiple service managers in a JVM.
     */
    private static Map<ServiceManager, AuthorizationService> atzSvcBySM =
        new HashMap<ServiceManager, AuthorizationService>();

    /**
     * We don't want expose the constructor as public, because we don't want user use their own configuration to create
     * service instance, this may lead security hole.
     * @param svcMgr
     * @return
     * @throws ProviderInitializationException 
     * @throws ProviderInvalidException 
     * @throws ServiceInitializationException 
     * @throws ClassNotFoundException 
     * @throws IOException 
     */
    public static synchronized AuthorizationService getInstance(ServiceManager svcMgr)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException,
    IOException, ClassNotFoundException {
        loadAdjudicationAlgorithms();
        AuthorizationService svc = atzSvcBySM.get(svcMgr);
        if (svc == null) {
            svc = new AuthorizationService(svcMgr);
            atzSvcBySM.put(svcMgr, svc);
        }
        return svc;
    }

    private AuthorizationService(ServiceManager svcMgr)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException {
        ConfigElement config = svcMgr.getServiceConfiguration(AuthorizationService.class);
        serviceName = (String)config.getAttributeValueByName(Service.ATTR_SERVICE_NAME);
        System.out.println("Initializing authorization service '" + serviceName + "' ...");
        adjudicationAlg = (String)config.getAttributeValueByName(ATTR_ADJUDICATION);
        adtSvc = AuditService.getInstance(svcMgr);
        if (adjudicationAlg == null) {
            System.out.println("No adjudication algorithm is configured for authorization service.");
        }
        else {
            System.out.println("The configured adjudication algorithm is '" + adjudicationAlg + "'.");
        }
        initializeProviders(svcMgr, config);
        System.out.println("Authorization service '" + serviceName + "' initialized.");
    }

    private void initializeProviders(ServiceManager svcMgr, ConfigElement serviceConfig)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException {
        System.out.println("Loading authorization providers ...");
        // Get all authorization provider's configuration
        XMLElement[] authorizerConfigs = serviceConfig.getXMLElementsByTypeNS(
                Service.NS_SERVICE, Authorizer.ELEMTYPE_AUTHORIZER);
        if (authorizerConfigs != null && authorizerConfigs.length > 0) {
            if (authorizerConfigs.length > 1 && (adjudicationAlg == null || adjudicationAlg.length() == 0)) {
                throw new ServiceInitializationException(
                        "Configured multiple authorizers but didn't configured adjudication algorithm.");
            }

            authorizers = new Authorizer[authorizerConfigs.length];
            for (int i = 0; i < authorizers.length; i ++) {
                // Construct each authorization provider.
                authorizers[i] = (Authorizer)ProviderFactory.createProvider(
                        svcMgr, this, (ConfigElement)authorizerConfigs[i]);
            }
        }
        System.out.println((authorizers == null ? 0 : authorizers.length) + " authorization providers loaded.");
    }

    private Attribute createSubjectAttr(Subject[] subjects) {
        String[] subjs = new String[subjects.length];
        for (int x = 0; x < subjs.length; x ++) {
            subjs[x] = subjects[x].getSubjectName();
        }
        return new Attribute(AuditConstants.ATTR_SUBJECTS, AttributeType.String, subjs);
    }

    private Attribute createResourceAttr(Resource[] resources) {
        String[] reses = new String[resources.length];
        for (int x = 0; x < reses.length; x ++) {
            reses[x] = resources[x].getResourceString();
        }
        return new Attribute(AuditConstants.ATTR_RESOURCES, AttributeType.String, reses);
    }

    private Context createAuditContext(Subject[] subjects, Resource[] resources, Action action, Context ctx) {
        Context result = new DefaultContextImpl();
        result.setAllAttributes(ctx);

        result.setAttribute(createSubjectAttr(subjects));
        result.setAttribute(createResourceAttr(resources));
        result.setAttribute(new Attribute(AuditConstants.ATTR_ACTION, AttributeType.String, action.getActionName()));

        return result;
    }

    private void audit(Subject[] subjs, AuditSeverity severity, String message, Context ctx, Throwable t) {
        // Make an audit event for each subject.
        for (Subject each : subjs) {
            adtSvc.audit(each, severity, AuditService.CATEGORY_AUTHORIZATION, message, ctx, t);
        }
    }

    public AuthorizationResult[] authorize(Subject[] subjects, Resource[] resources, Action action, Context context)
    throws AuthorizationFailedException {
        if (subjects == null || subjects.length == 0 ||
            resources == null || resources.length == 0 || action == null) {
            throw new IllegalArgumentException("Parameters should not be null.");
        }

        // Create audit context
        Context adtCtx = createAuditContext(subjects, resources, action, context);

        if (authorizers == null || authorizers.length == 0) {
            String msg = "No authorization provider configured for authorization service '" + serviceName + "'.";
            audit(subjects, AuditSeverity.FAILURE, msg, adtCtx, null);
            // throw an exception
            throw new AuthorizationFailedException(msg);
        }

        try {
            AuthorizationResult[] result = null;
            if (adjudicationAlg == null) {
                result = authorizers[0].authorize(subjects, resources, action, context);
            }
            else {
                AdjudicationAlgorithmIntf alg = algReg.get(adjudicationAlg);
                if (alg != null) {
                    result = alg.adjudicate(authorizers, subjects, resources, action, context);
                }
                else {
                    String msg = "Can not find the configured adjudication algorithm.";
                    audit(subjects, AuditSeverity.ERROR, msg, adtCtx, null);
                    // throw an exception
                    throw new AuthorizationFailedException(msg);
                }
            }

            for (AuthorizationResult each : result) {
                Decision decision = each.getDecision();
                Resource res = each.getAuthorizedResource();
                adtCtx.setAllAttributes(each.getResponseContext());
                adtCtx.setAttribute(
                        new Attribute(AuditConstants.ATTR_DECISION, AttributeType.String, decision.toString()));
                if (result.length > 1) {
                    // If there are multiple authorization results, then each result should have a corresponding
                    // resource.
                    adtCtx.setAttribute(
                            new Attribute(AuditConstants.ATTR_RESOURCE, AttributeType.String, res.getResourceString()));
                // Otherwise, there should not be a resource in result.
                }
                audit(subjects, AuditSeverity.SUCCESS, "Authorization succeeded.", adtCtx, null);
            }
            return result;
        }
        catch (InvocationTargetException inEx) {
            Throwable t = inEx.getTargetException();
            if (t instanceof AuthorizationFailedException) {
                audit(subjects, AuditSeverity.FAILURE, "Authorization failed.", adtCtx, t);
                throw (AuthorizationFailedException)t;
            }
            else {
                String msg = "Error occurs while doing adjudication.";
                audit(subjects, AuditSeverity.ERROR, msg, adtCtx, t);
                throw new AuthorizationFailedException(msg, t);
            }
        }
        catch (AuthorizationFailedException aEx) {
            // We have already aduited this event.
            throw aEx;
        }
        catch (Exception e) {
            String msg = "Error occurs while authorizing.";
            audit(subjects, AuditSeverity.ERROR, msg, adtCtx, e);
            throw new AuthorizationFailedException(msg, e);
        }
    }

    public String getServiceName() {
        return serviceName;
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
        System.out.println("Shutting down authorization service '" + serviceName + "' ...");
        if (authorizers != null && authorizers.length > 0) {
            for (Authorizer each : authorizers) {
                each.shutdown();
            }
        }
        String msg = "Authorization service '" + serviceName + "' shutdown.";
        adtSvc.audit(Subject.SYSTEM, AuditSeverity.INFO, AuditConstants.ATTR_SERVICE, msg, null, null);
        System.out.println(msg);
    }

    public void shutdownForce() throws OperationFailedException {
        System.out.println("Shutting down authorization service '" + serviceName + "' ...");
        if (authorizers != null && authorizers.length > 0) {
            for (Authorizer each : authorizers) {
                each.shutdownForce();
            }
        }
        String msg = "Authorization service '" + serviceName + "' shutdown.";
        adtSvc.audit(Subject.SYSTEM, AuditSeverity.INFO, AuditConstants.ATTR_SERVICE, msg, null, null);
        System.out.println(msg);
    }

    public void start() throws OperationFailedException {
        System.out.println("Starting authorization service '" + serviceName + "' ...");
        if (authorizers != null && authorizers.length > 0) {
            for (Authorizer each : authorizers) {
                each.start();
            }
        }
        String msg = "Authorization service '" + serviceName + "' started.";
        adtSvc.audit(Subject.SYSTEM, AuditSeverity.INFO, AuditConstants.ATTR_SERVICE, msg, null, null);
        System.out.println(msg);
    }

    private static void loadAdjudicationAlgorithms()
    throws IOException, ClassNotFoundException, ServiceInitializationException {
        if (algReg != null) {
            return;
        }
        System.out.println("Loading adjudication algorithms for authorizatio services ...");
        algReg = new Hashtable<String, AdjudicationAlgorithmIntf>();
        // Start to load algorithms.
        Set<Class<?>> adjClasses = new HashSet<Class<?>>();

        findClassesByPackage(ADJ_ALGORITHM_PACKAGE, true, adjClasses);
        // loop on adjudication algorithm provider classes
        for (Class<?> provider : adjClasses) {
            if (provider.isAnnotationPresent(AdjudicationProvider.class)) {
                Method[] methods = provider.getMethods();
                // find the matching method on adjudication provider
                for (final Method current : methods) {
                    AdjudicationAlgorithm algAnn = current.getAnnotation(AdjudicationAlgorithm.class);
                    if (algAnn != null) {
                        if (!Modifier.isStatic(current.getModifiers())) {
                            throw new IllegalArgumentException(
                                    "We expected method '" + provider.getSimpleName() + ":" + current.getName() +
                                    "' is static, but it isn't, we can't load a non-static adjudication algorithm.");
                            }

                        // Get all algorithms specified on a method.
                        String[] algNames = algAnn.value();
                        if (algNames.length == 0) {
                            // If no annotation value provided, we use the method name instead.
                            algNames = new String[] {current.getName()};
                        }

                        // Register algorithm.
                        for (String algName : algNames) {
                            if (algName != null && algName.trim().length() > 0) {
                                algName = algName.trim();

                                if (algReg.get(algName) != null) {
                                    throw new ServiceInitializationException("The adjudication algorithm '" + algName +
                                            "' has been registered.");
                                }

                                AdjudicationAlgorithmIntf alg = new AdjudicationAlgorithmIntf() {
                                    public AuthorizationResult[] adjudicate(Authorizer[] authorizers,
                                            Subject[] subjects, Resource[] resources, Action action, Context context)
                                    throws Exception {
                                        return (AuthorizationResult[])current.invoke(null,
                                                authorizers, subjects, resources, action, context);
                                    }
                                };
                                algReg.put(algName, alg);
                            }
                        }
                    }
                }
            }
        }
        System.out.println(algReg.size() + " adjudication algorithms loaded.");
    }

    /**
     * It defines a call interface for adjudication algorithm.
     */
    interface AdjudicationAlgorithmIntf {
        public AuthorizationResult[] adjudicate(Authorizer[] authorizers,
                Subject[] subjects, Resource[] resources, Action action, Context context) throws Exception;
    }
}