package an.chopsticks.service;


/**
 * A named attribute object that have type and value elements, which is used in Context. The attribute may have a single
 * value or an array value. The attribute's value represents as String type for convenience. Its value will be converted
 * to corresponding Java type after it is passed to providers according to each provider's convention.
 */
public class Attribute {
    private boolean isArray;
    private String name;
    private AttributeType type;
    private String[] stringValues;
    private String stringValuesAsString;
    private String stringValue;

    public Attribute(String name, AttributeType type, String value) {
        this.isArray = false;
        this.name = name;
        this.type = type;
        this.stringValue = value;
        this.stringValuesAsString = value;
        if (value != null) {
            this.stringValues = new String[] {value};
        }
    }

    public Attribute(String name, AttributeType type, String[] value) {
        this.isArray = true;
        this.name = name;
        this.type = type;
        this.stringValues = value;
        this.stringValuesAsString = convertArrayValue2String();
    }

    public String getName() {
        return name;
    }

    public String[] getArrayValue() {
        return stringValues;
    }

    public String getArrayValueAsString() {
        return stringValuesAsString;
    }

    private String convertArrayValue2String() {
        StringBuffer buf = new StringBuffer();
        if (stringValues != null && stringValues.length > 0) {
            buf.append(stringValues[0]);
            for (int x = 1; x < stringValues.length; x ++) {
                buf.append(",");
                buf.append(stringValues[x]);
            }
        }
        return buf.toString();
    }

    public String getValue() {
       return stringValue;
    }

    public AttributeType getType() {
        return type;
    }

    public boolean isArray() {
        return isArray;
    }

    public String toString() {
        return name + "=" + (isArray() ? stringValuesAsString : stringValue) + "[" + type + "]";
    }
}