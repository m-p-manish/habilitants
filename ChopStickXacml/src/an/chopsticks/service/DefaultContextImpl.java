package an.chopsticks.service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * A default Context implementation using HashMap.
 */
public class DefaultContextImpl implements Context {
    private Map<String, Attribute> ctx = new HashMap<String, Attribute>();

    public Attribute getAttribute(String name) {
        return ctx.get(name);
    }

    public void setAttribute(Attribute attr) {
        ctx.put(attr.getName(), attr);
    }

    /**
     * Populate all attributes in the passed in context to current context.
     */
    public void setAllAttributes(Context ctx) {
        if (ctx != null) {
            Collection<Attribute> all = ctx.getAllAttributes();
            Iterator<Attribute> i = all.iterator();
            while (i.hasNext()) {
                Attribute each = i.next();
                setAttribute(each);
            }
        }
    }

    public Collection<Attribute> getAllAttributes() {
        return ctx.values();
    }

    public void removeAttribute(String name) {
        ctx.remove(name);
    }

    public String toString() {
        String str = "";
        Collection<Attribute> all = getAllAttributes();
        Iterator<Attribute> i = all.iterator();
        while (i.hasNext()) {
            str = str + i.next().toString();
        }
        return str;
    }
}