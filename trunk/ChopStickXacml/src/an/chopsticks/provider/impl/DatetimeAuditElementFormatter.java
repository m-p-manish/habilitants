package an.chopsticks.provider.impl;

import java.util.Formatter;

import an.config.ConfigElement;

public class DatetimeAuditElementFormatter implements AuditElementFormatter {
    static final String ATTR_FORMAT = "format";

    private String format;
    private static Formatter fmt = new Formatter(new StringBuffer());

    public DatetimeAuditElementFormatter(ConfigElement config) {
        format = (String)config.getAttributeValueByName(ATTR_FORMAT);
    }

    public String format(Object input) {
        if (input == null) {
            return "";
        }
        StringBuffer strBuff = (StringBuffer)fmt.out();
        strBuff.delete(0, strBuff.length());

        return fmt.format(format, input).toString();
    }
}