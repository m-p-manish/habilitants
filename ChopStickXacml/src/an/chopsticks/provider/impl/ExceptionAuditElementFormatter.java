package an.chopsticks.provider.impl;

import java.io.ByteArrayOutputStream;
import java.io.PrintWriter;

import an.config.ConfigElement;

public class ExceptionAuditElementFormatter implements AuditElementFormatter {
    public ExceptionAuditElementFormatter(ConfigElement config) {}

    public String format(Object input) {
        if (input == null) {
            return "";
        }
        if (!(input instanceof Throwable)) {
            throw new IllegalArgumentException(
                    "We expected 'Throwable' but got a '" + input.getClass().getSimpleName() + "' type");
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ((Throwable)input).printStackTrace(new PrintWriter(out));
        return "\n" + out.toString();
    }
}