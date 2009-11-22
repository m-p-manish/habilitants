package an.chopsticks.service;

/**
 * The authorization result both used in authorization providers and services.
 */
public class AuthorizationResult {
    // The final decision delivered by authorization provider or service.
    private Decision decision;
    // Extra text message
    private String message;
    // If there is any response attributes from authorization provider or service, we can retrieve them from it.
    private Context responseCtx = new DefaultContextImpl();
    // It identified the target resource that has been authorized in authorization request. It is used while the
    // authorization result including more than one AuthorizationResult.
    private Resource resource;

    public AuthorizationResult(Decision decision) {
        this.decision = decision;
    }

    public AuthorizationResult(Decision decision, String message) {
        this(decision);
        this.message = message;
    }

    public AuthorizationResult(Decision decision, Context ctx) {
        this(decision);
        if (ctx != null) {
            responseCtx.setAllAttributes(ctx);
        }
    }

    public AuthorizationResult(Decision decision, String message, Context ctx) {
        this(decision, ctx);
        this.message = message;
    }

    public AuthorizationResult(Decision decision, Resource res, String message, Context ctx) {
        this(decision, message, ctx);
        this.resource = res;
    }

    public Decision getDecision() {
        return decision;
    }

    public String getMessage() {
        return message;
    }

    public Context getResponseContext() {
        return responseCtx;
    }

    public Resource getAuthorizedResource() {
        return resource;
    }

    public String toString() {
        return "[" + decision + "]" + (resource == null ? "" : " for '" + resource.getResourceString() + "'") + 
        "\n" + (message == null ? "" : message) + "\n" + responseCtx.toString();
    }
}