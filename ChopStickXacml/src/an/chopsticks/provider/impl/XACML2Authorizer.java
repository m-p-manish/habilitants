package an.chopsticks.provider.impl;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import an.chopsticks.provider.Authorizer;
import an.chopsticks.provider.Provider;
import an.chopsticks.service.Action;
import an.chopsticks.service.Attribute;
import an.chopsticks.service.AttributeType;
import an.chopsticks.service.AuthorizationFailedException;
import an.chopsticks.service.AuthorizationResult;
import an.chopsticks.service.Context;
import an.chopsticks.service.DefaultContextImpl;
import an.chopsticks.service.ProviderInitializationException;
import an.chopsticks.service.Resource;
import an.chopsticks.service.Service;
import an.chopsticks.service.Subject;
import an.chopsticks.service.Decision;
import an.config.ConfigElement;
import an.control.OperationFailedException;
import an.control.Status;
import an.xacml.Constants;
import an.xacml.IndeterminateException;
import an.xacml.context.Environment;
import an.xacml.context.Request;
import an.xacml.context.Response;
import an.xacml.context.Result;
import an.xacml.context.StatusCode;
import an.xacml.engine.PDP;
import an.xacml.engine.PDPInitializeException;
import an.xacml.policy.AttributeAssignment;
import an.xacml.policy.Obligation;
import an.xacml.policy.Obligations;
import an.xml.XMLDataTypeMappingException;

public class XACML2Authorizer implements Authorizer {
    /**
     * A AttributeType to XACML types map.
     */
    private static Map<AttributeType, URI> svcTypeMap = new HashMap<AttributeType, URI>();
    private static Map<URI, AttributeType> xacmlTypeMap = new HashMap<URI, AttributeType>();
    static {
        svcTypeMap.put(AttributeType.String, Constants.TYPE_STRING);
        svcTypeMap.put(AttributeType.Boolean, Constants.TYPE_BOOLEAN);
        svcTypeMap.put(AttributeType.Integer, Constants.TYPE_INTEGER);
        svcTypeMap.put(AttributeType.Double, Constants.TYPE_DOUBLE);
        svcTypeMap.put(AttributeType.Date, Constants.TYPE_DATE);
        svcTypeMap.put(AttributeType.Time, Constants.TYPE_TIME);
        svcTypeMap.put(AttributeType.DateTime, Constants.TYPE_DATETIME);

        xacmlTypeMap.put(Constants.TYPE_STRING, AttributeType.String);
        xacmlTypeMap.put(Constants.TYPE_BOOLEAN, AttributeType.Boolean);
        xacmlTypeMap.put(Constants.TYPE_INTEGER, AttributeType.Integer);
        xacmlTypeMap.put(Constants.TYPE_DOUBLE, AttributeType.Double);
        xacmlTypeMap.put(Constants.TYPE_DATE, AttributeType.Date);
        xacmlTypeMap.put(Constants.TYPE_TIME, AttributeType.Time);
        xacmlTypeMap.put(Constants.TYPE_DATETIME, AttributeType.DateTime);
    }

    private String providerName;
    private PDP pdp;

    public XACML2Authorizer(Service service, ConfigElement config) throws ProviderInitializationException {
        providerName = (String)config.getAttributeValueByName(Provider.ATTR_PROVIDER_NAME);
        System.out.println("Initializing XACML authorization provider '" + providerName + "' ...");
        try {
            pdp = PDP.getInstance(config);
        } catch (PDPInitializeException e) {
            throw new ProviderInitializationException("Error occurs while initializing XACML authorizer.", e);
        }
        System.out.println("XACML authorization provider '" + providerName + "' initialized.");
    }

    public AuthorizationResult[] authorize(Subject[] subjects, Resource[] resources, Action action, Context context)
    throws AuthorizationFailedException {
        try {
            Request req = constructXACMLRequest(subjects, resources, action, context);
            Response res = (Response)pdp.handleRequest(req);
            return constructAuthorizationResult(res);
        }
        catch (Exception ex) {
            throw new AuthorizationFailedException("Error while doing authorization.", ex);
        }
    }

    private static an.xacml.context.Request constructXACMLRequest(
            Subject[] subjects, Resource[] resources, Action action, Context context)
    throws XMLDataTypeMappingException, URISyntaxException {
        an.xacml.context.Subject[] xacmlSubjects = new an.xacml.context.Subject[subjects.length];
        for (int i = 0; i < subjects.length; i ++) {
            xacmlSubjects[i] = constructXACMLSubject(subjects[i]);
        }

        an.xacml.context.Resource[] xacmlResources = new an.xacml.context.Resource[resources.length];
        for (int i = 0; i < resources.length; i ++) {
            xacmlResources[i] = constructXACMLResource(resources[i]);
        }

        an.xacml.context.Action xacmlAction = constructXACMLAction(action);
        // put all context attributes to XACML Environment attributes.
        an.xacml.context.Environment xacmlEnv = constructXACMLEnvironmentFromContext(context);

        return new Request(xacmlSubjects, xacmlResources, xacmlAction, xacmlEnv);
    }

    private static an.xacml.context.Subject constructXACMLSubject(Subject subj) throws XMLDataTypeMappingException {
        // TODO process anonymous
        an.xacml.context.Attribute[] attrs = new an.xacml.context.Attribute[1];
        attrs[0] = new an.xacml.context.Attribute(
                Constants.ATTR_SUBJECT_ID, Constants.TYPE_STRING, null, new String[] {subj.getSubjectName()});
        return new an.xacml.context.Subject(Constants.ATTR_SUBJECT_CATEGORY_ACCESS, attrs);
    }

    private static an.xacml.context.Resource constructXACMLResource(Resource res) throws XMLDataTypeMappingException {
        an.xacml.context.Attribute[] attrs = new an.xacml.context.Attribute[1];
        attrs[0] = new an.xacml.context.Attribute(
                Constants.ATTR_RESOURCE_ID, Constants.TYPE_STRING, null, new String[] {res.getResourceString()});
        return new an.xacml.context.Resource(null, attrs);
    }

    private static an.xacml.context.Action constructXACMLAction(Action action) throws XMLDataTypeMappingException {
        an.xacml.context.Attribute[] attrs = new an.xacml.context.Attribute[1];
        attrs[0] = new an.xacml.context.Attribute(
                Constants.ATTR_ACTION_ID, Constants.TYPE_STRING, null, new String[] {action.getActionName()});
        return new an.xacml.context.Action(attrs);
    }

    private static Environment constructXACMLEnvironmentFromContext(Context context)
    throws XMLDataTypeMappingException, URISyntaxException {
        if (context != null) {
            Collection<Attribute> attrs = context.getAllAttributes();
            Iterator<Attribute> itr = attrs.iterator();
            an.xacml.context.Attribute[] xacmlAttrs = new an.xacml.context.Attribute[attrs.size()];
            for (int i = 0; i < attrs.size(); i ++) {
                Attribute attr = itr.next();
                xacmlAttrs[i] = new an.xacml.context.Attribute(
                        new URI(attr.getName()), svcTypeMap.get(attr.getType()), null, attr.getArrayValue());
            }
            return new Environment(xacmlAttrs);
        }
        // Environment should not be null
        return new Environment(null);
    }

    private static AuthorizationResult[] constructAuthorizationResult(Response res) throws IndeterminateException {
        Result[] xacmlResult = res.getResults();
        AuthorizationResult[] result = new AuthorizationResult[xacmlResult.length];
        for (int i = 0; i < xacmlResult.length; i ++) {
            Decision decision = constructDecision(xacmlResult[i].getDecision());

            String resId = xacmlResult[i].getResourceId();
            Resource resource = null;
            if (resId != null) {
                resource = new Resource(resId);
            }

            String message = constructMessage(xacmlResult[i].getStatus());
            Context responseContext = constructResponseContext(xacmlResult[i].getObligations());

            result[i] = new AuthorizationResult(decision, resource, message, responseContext);
        }
        return result;
    }

    private static Decision constructDecision(an.xacml.context.Decision xacmlDecision) {
        if (xacmlDecision == an.xacml.context.Decision.Permit) {
            return Decision.Permit;
        }
        else if (xacmlDecision == an.xacml.context.Decision.Deny) {
            return Decision.Deny;
        }
        else if (xacmlDecision == an.xacml.context.Decision.NotApplicable) {
            return Decision.NotApplicable;
        }
        else {
            return Decision.Indeterminate;
        }
    }

    private static String constructMessage(an.xacml.context.Status status) {
        if (status == null) {
            return null;
        }
        StatusCode root = status.getStatusCode();
        String code = root.getValue().toString();
        while (root.getChild() != null) {
            code = code + ":\n\t" + root.getChild().getValue().toString();
            root = root.getChild();
        }
        String msg = status.getStatusMessage() == null ? "" : status.getStatusMessage();
        String detail = status.getStatusDetail() == null ? "" : status.getStatusDetail().toString();
        return "code = " + code + "\nmessage = " + msg + "\ndetail = " + detail;
    }

    private static Context constructResponseContext(Obligations obls) throws IndeterminateException {
        Context ctx = null;
        if (obls != null) {
            Obligation[] obligations = obls.getAllObligations();
            if (obligations != null && obligations.length > 0) {
                ctx = new DefaultContextImpl();
                for (Obligation obl : obligations) {
                    AttributeAssignment[] attrAssns = obl.getAttributeAssignments();
                    if (attrAssns != null && attrAssns.length > 0) {
                        for (AttributeAssignment attrAssn : attrAssns) {
                            String attrName = attrAssn.getAttributeId().toString();
                            AttributeType attrType = xacmlTypeMap.get(attrAssn.getDataType());
                            // If we don't get an XACML type from map, it means we don't support such XACML type in
                            // service layer, then we get it as String type.
                            if (attrType == null) {
                                attrType = AttributeType.String;
                            }
                            String strValue = attrAssn.getValue().toString();
                            Attribute attr = new Attribute(attrName, attrType, strValue);
                            ctx.setAttribute(attr);
                        }
                    }
                }
            }
        }
        return ctx;
    }

    public String getProviderName() {
        return providerName;
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
        throw new OperationFailedException("We don't support pause operation on this provider.");
    }

    public void resume() throws OperationFailedException {
        throw new OperationFailedException("We don't support resume operation on this provider.");
    }

    public void shutdown() throws OperationFailedException {
        System.out.println("Shutting down XACML authorization provider '" + providerName + "' ...");
        pdp.shutdown();
        System.out.println("XACML authorization provider '" + providerName + "' shutdown.");
    }

    public void shutdownForce() throws OperationFailedException {
        System.out.println("Shutting down XACML authorization provider '" + providerName + "' ...");
        pdp.shutdownForce();
        System.out.println("XACML authorization provider '" + providerName + "' shutdown.");
    }

    public void start() throws OperationFailedException {
        System.out.println("Starting XACML authorization provider '" + providerName + "' ...");
        pdp.start();
        System.out.println("XACML authorization provider '" + providerName + "' started.");
    }
}