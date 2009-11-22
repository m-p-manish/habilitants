package an.chopsticks.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import an.chopsticks.provider.Authenticator;
import an.config.ConfigElement;
import an.control.OperationFailedException;
import an.control.Status;
import an.log.LogFactory;
import an.log.Logger;
import an.xml.XMLElement;

public final class AuthenticationService implements Service {
    private String serviceName;
    private Authenticator[] authenticators;
    private Map<String, List<Authenticator>> atnsByCredType = new HashMap<String, List<Authenticator>>();
    private AuditService adtSvc;
    /**
     * Save service instances by given service manager, so that we can support multiple service managers in a JVM.
     */
    private static Map<ServiceManager, AuthenticationService> atnSvcBySM =
        new HashMap<ServiceManager, AuthenticationService>();

    /**
     * We don't want expose the constructor as public, because we don't want user use their own configuration to create
     * service instance, this may lead security hole.
     * @param svcMgr
     * @return
     * @throws ServiceInitializationException 
     * @throws ProviderInvalidException 
     * @throws ProviderInitializationException 
     */
    public static synchronized AuthenticationService getInstance(ServiceManager svcMgr)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException {
        AuthenticationService svc = atnSvcBySM.get(svcMgr);
        if (svc == null) {
            svc = new AuthenticationService(svcMgr);
            atnSvcBySM.put(svcMgr, svc);
        }
        return svc;
    }

    private AuthenticationService(ServiceManager svcMgr)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException {
        ConfigElement config = svcMgr.getServiceConfiguration(AuthenticationService.class);
        serviceName = (String)config.getAttributeValueByName(Service.ATTR_SERVICE_NAME);
        System.out.println("Initializing authentication service '" + serviceName + "' ...");
        adtSvc = AuditService.getInstance(svcMgr);
        // initialize providers
        initializeProviders(svcMgr, config);
        System.out.println("Authentication service '" + serviceName + "' initialized.");
    }

    private void initializeProviders(ServiceManager svcMgr, ConfigElement serviceConfig)
    throws ProviderInitializationException, ProviderInvalidException, ServiceInitializationException {
        System.out.println("Loading authentication providers ...");
        // Get all authentication provider's configuration
        XMLElement[] atnConfigs = serviceConfig.getXMLElementsByTypeNS(
                Service.NS_SERVICE, Authenticator.ELEMTYPE_AUTHENTICATOR);
        if (atnConfigs != null && atnConfigs.length > 0) {
            authenticators = new Authenticator[atnConfigs.length];
            for (int i = 0; i < authenticators.length; i ++) {
                // Construct each authenticator provider.
                authenticators[i] = (Authenticator)ProviderFactory.createProvider(
                        svcMgr, this, (ConfigElement)atnConfigs[i]);
                // Get supported credential type
                String credType = authenticators[i].getSupportedCredentialType();
                // Get all the credential typed authenticators.
                List<Authenticator> typedAtns = atnsByCredType.get(credType);
                if (typedAtns == null) {
                    // If no collection for this type of authenticators, we create a such collection. Note, we use
                    // ArrayList to assure the authenticators order in configuration.
                    typedAtns = new ArrayList<Authenticator>();
                    atnsByCredType.put(credType, typedAtns);
                }
                typedAtns.add(authenticators[i]);
            }
        }
        System.out.println((authenticators == null ? 0 : authenticators.length) + " authentication providers loaded.");
    }

    private void audit(Subject subj, AuditSeverity severity, String message, Context ctx, Throwable t) {
        adtSvc.audit(subj, severity, AuditService.CATEGORY_AUTHENTICATION, message, ctx, t);
    }

    public Subject authenticate(Credential cred, Context ctx) throws AuthenticationFailedException {
        if (authenticators == null || authenticators.length == 0) {
            String msg = "No authentication provider configured for authentication service '" + serviceName + "'.";
            // Audit the failure.
            audit(Subject.SYSTEM, AuditSeverity.FAILURE, msg, ctx, null);
            // throw an exception
            throw new AuthenticationFailedException(msg);
        }

        if (cred == null || cred.getType() == null || cred.getCredentialObject() == null) {
            String msg = "The credential or its owned object should not be null.";
            // Audit the failure.
            audit(Subject.SYSTEM, AuditSeverity.ERROR, msg, ctx, null);
            // Then throw an exception
            throw new AuthenticationFailedException(msg);
        }

        String type = cred.getType();
        List<Authenticator> matchedAtns = atnsByCredType.get(type);

        if (matchedAtns != null && matchedAtns.size() > 0) {
            int size = matchedAtns.size();

            Subject subject = null;
            Context responseCtx = new DefaultContextImpl();

            boolean succeed = false;
            AuthenticationFailedException firstEx = null;
            AuthenticationFailedException firstRequiredEx = null;

            for (int i = 0; i < size; i ++) {
                AuthenticationFailedException ex = null;
                Authenticator atn = matchedAtns.get(i);
                AuthenticationFlag flag = atn.getFlag();

                // Initialize audit context.
                Context adtCtx = new DefaultContextImpl();
                adtCtx.setAllAttributes(ctx);
                adtCtx.setAttribute(new Attribute(AuditConstants.ATTR_PROVIDER_NAME,
                        AttributeType.String, atn.getProviderName()));
                adtCtx.setAttribute(new Attribute(AuditConstants.ATTR_AUTHENTICATION_FLAG,
                        AttributeType.String, atn.getFlag().toString()));
                adtCtx.setAttribute(new Attribute(AuditConstants.ATTR_CREDENTIAL_TYPE,
                        AttributeType.String, atn.getSupportedCredentialType()));

                // Start login, the logic is same with JAAS' implementation
                try {
                    subject = new Subject(cred, atn, ctx);
                    responseCtx.setAllAttributes(subject);
                    succeed = true;

                    adtCtx.setAllAttributes(responseCtx);
                    audit(subject, AuditSeverity.SUCCESS, "Authentication succeeded.", adtCtx, null);

                    if (flag == AuthenticationFlag.Sufficient) {
                        // If succeed, and no required authenticators failed so far, we populate the response context to
                        // current subject and directly return it.
                        if (firstRequiredEx == null) {
                            subject.setAllAttributes(responseCtx);
                            return subject;
                        }
                    }
                }
                catch (ServiceException atnEx) {
                    succeed = false;

                    // Audit the failure.
                    audit(Subject.SYSTEM, AuditSeverity.FAILURE, "Authentication failed.", adtCtx, atnEx);

                    // Log the exception for debug
                    Logger logger = LogFactory.getLogger();
                    logger.warn("Authenticator '" + atn.getProviderName() + "' (" + flag.toString() +
                            ") failed to authenticated user.", ex);

                    if (atnEx instanceof AuthenticationFailedException) {
                        ex = (AuthenticationFailedException)atnEx;
                    }
                    // If there is error, we stop authenticating and throw the exception directly.
                    else {
                        throw new AuthenticationFailedException("Error occurs while authenticating user.", atnEx);
                    }

                    if (flag == AuthenticationFlag.Required) {
                        // If the authenticator is required, we save the first required failed exception, then go on
                        // with other authenticators in the list.
                        if (firstRequiredEx == null) {
                            firstRequiredEx = ex;
                        }
                    }
                    else if (flag == AuthenticationFlag.Requisite) {
                        // If not succeed, we directly throw the saved exception.
                        throw firstRequiredEx == null ? ex : firstRequiredEx;
                    }
                    // Optional & Sufficient
                    else {
                        // If there is error, we save the first error.
                        if (firstEx == null) {
                            firstEx = ex;
                        }
                    }
                }
            }

            if (firstRequiredEx != null) {
                // Required authenticators failed, even there is success, we still make it fail and throw the first
                // required exception.
                throw firstRequiredEx;
            }
            else if (!succeed && firstEx != null) {
                // The last one is failed, return the first exception
                throw firstEx;
            }
            else {
                // The last one is succeeded.
                subject.setAllAttributes(responseCtx);
                return subject;
            }
        }
        else {
            String msg = "No matched authentication provider for credential type '" + type + "'.";
            // Audit the failure.
            Context adtCtx = new DefaultContextImpl();
            adtCtx.setAllAttributes(ctx);
            adtCtx.setAttribute(new Attribute(AuditConstants.ATTR_CREDENTIAL_TYPE, AttributeType.String, type));
            audit(Subject.SYSTEM, AuditSeverity.FAILURE, msg, adtCtx, null);
            // Throw an exception.
            throw new AuthenticationFailedException(msg);
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
        System.out.println("Shutting down authentication service '" + serviceName + "' ...");
        if (authenticators != null && authenticators.length > 0) {
            for (Authenticator each : authenticators) {
                each.shutdown();
            }
        }
        String msg = "Authentication service '" + serviceName + "' shutdown.";
        adtSvc.audit(Subject.SYSTEM, AuditSeverity.INFO, AuditConstants.ATTR_SERVICE, msg, null, null);
        System.out.println(msg);
    }

    public void shutdownForce() throws OperationFailedException {
        System.out.println("Shutting down authentication service '" + serviceName + "' ...");
        if (authenticators != null && authenticators.length > 0) {
            for (Authenticator each : authenticators) {
                each.shutdownForce();
            }
        }
        String msg = "Authentication service '" + serviceName + "' shutdown.";
        adtSvc.audit(Subject.SYSTEM, AuditSeverity.INFO, AuditConstants.ATTR_SERVICE, msg, null, null);
        System.out.println(msg);
    }

    public void start() throws OperationFailedException {
        System.out.println("Starting authentication service '" + serviceName + "' ...");
        if (authenticators != null && authenticators.length > 0) {
            for (Authenticator each : authenticators) {
                each.start();
            }
        }
        String msg = "Authentication service '" + serviceName + "' started.";
        adtSvc.audit(Subject.SYSTEM, AuditSeverity.INFO, AuditConstants.ATTR_SERVICE, msg, null, null);
        System.out.println(msg);
    }
}