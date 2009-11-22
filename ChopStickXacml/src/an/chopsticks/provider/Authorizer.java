package an.chopsticks.provider;

import an.chopsticks.service.Action;
import an.chopsticks.service.AuthorizationFailedException;
import an.chopsticks.service.AuthorizationResult;
import an.chopsticks.service.Context;
import an.chopsticks.service.Resource;
import an.chopsticks.service.Subject;

/**
 * This provider is to do authorization against given subjects, resources and action. The implementation should have
 * following constructor:
 * 
 *      public AuthorizerImpl(Service service, an.config.ConfigElement);
 */
public interface Authorizer extends Provider {
    public static final String ELEMTYPE_AUTHORIZER = "AuthorizerType";
    /**
     * Do authorization against given subjects, resources and action.
     * @param subjects Subjects that need to be authorized. Subject can be multiple.
     * @param resources Resources that the subjects are to access. Resource can be multiple.
     * @param action Action that on the resources.
     * @param context Related context information.
     * @return A combined AuthorizationResult include decision, message and response context.
     */
    public AuthorizationResult[] authorize(Subject[] subjects, Resource[] resources, Action action, Context context)
    throws AuthorizationFailedException;
}