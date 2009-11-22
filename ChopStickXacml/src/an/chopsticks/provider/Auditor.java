package an.chopsticks.provider;

import an.chopsticks.service.AuditFailedException;
import an.chopsticks.service.AuditSeverity;
import an.chopsticks.service.Context;

/**
 * This provider is to audit events. The implementation should have following constructor:
 * 
 *      <code>public AuditorImpl(Service service, an.config.ConfigElement config);</code>
 */
public interface Auditor extends Provider {
    public static final String ELEMTYPE_AUDITOR = "AuditorType";
    public static final String ATTR_SEVERITY = "severity";
    public static final String ATTR_CATEGORYFILTER = "categoryFilter";
    public static final String ATTR_WITHCONTEXT = "withContext";
    /**
     * Log a single event.
     * @param severity The audit level of this event.
     * @parem category The audit event's category
     * @param message The audit message of this event.
     * @param ctx The context information related to this event.
     * @throws AuditFailedException
     */
    public void audit(AuditSeverity severity, String category, String message, Context ctx);
    /**
     * Log a single event with exception.
     * @param severity The audit level of this event.
     * @parem category The audit event's category
     * @param message The audit message of this event.
     * @param ctx The context information related to this event.
     * @param t The exception related to the audit event.
     * @throws AuditFailedException
     */
    public void audit(AuditSeverity severity, String category, String message, Context ctx, Throwable t);
}