package an.chopsticks.service;

public enum AuditSeverity {
    NONE,          // Record none audit event
    AUDITERROR,    // There are errors occur while writing audit information
    ERROR,         // There are errors occur while perform the audited event
    FAILURE,       // The audited event failed
    SUCCESS,       // The audited event succeeded
    INFO,          // For information
    ALL            // Record all audit events
}