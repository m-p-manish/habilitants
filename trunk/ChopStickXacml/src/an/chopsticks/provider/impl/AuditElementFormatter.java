package an.chopsticks.provider.impl;

/**
 * An audit event is splitted to several parts, we called each part "element". This interface defines a mechanism that
 * can format the element to a string according to a specific rule.
 */
public interface AuditElementFormatter {
    /**
     * Format the given element to a string according to a specific rule, and return the formatted text. 
     * @param input The original element text.
     * @return The formatted text.
     */
    public String format(Object input);
}