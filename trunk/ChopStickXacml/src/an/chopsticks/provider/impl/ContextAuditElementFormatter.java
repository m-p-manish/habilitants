package an.chopsticks.provider.impl;

import java.util.Collection;

import an.chopsticks.service.Attribute;
import an.chopsticks.service.Context;
import an.config.ConfigElement;

public class ContextAuditElementFormatter implements AuditElementFormatter {
    public ContextAuditElementFormatter(ConfigElement config) {}

    public String format(Object input) {
        if (input == null) {
            return "";
        }

        if (!(input instanceof Context)) {
            throw new IllegalArgumentException(
                    "We expected 'Context' but got a '" + input.getClass().getSimpleName() + "' type");
        }
        StringBuffer result = new StringBuffer();
        Context ctx = (Context)input;
        Collection<Attribute> attrs = ctx.getAllAttributes();

        for (Attribute each : attrs) {
            if (result.toString().length() > 1) {
                result.append(";");
            }
            result.append(each.getName());
            result.append("=");
            result.append(each.getType());
            result.append(":");
            if (each.isArray()) {
                String[] values = each.getArrayValue();
                result.append("{");
                if (values != null && values.length > 0) {
                    result.append(values[0]);
                    for (int x = 1; x < values.length; x ++) {
                        result.append(",");
                        result.append(values[x]);
                    }
                }
                result.append("}");
            }
            else {
                result.append(each.getValue());
            }
        }
        return result.toString();
    }
}