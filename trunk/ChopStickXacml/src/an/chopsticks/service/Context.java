package an.chopsticks.service;

import java.util.Collection;

/**
 * A context interfacethat can carry named attributes while doing security operations.
 */
public interface Context {
    /**
     * Set an attribute to the context.
     * @param attr The attribute.
     */
    public void setAttribute(Attribute attr);
    /**
     * Get the attribute from context.
     * @param name The attribute's name.
     */
    public Attribute getAttribute(String name);
    /**
     * Import all attributes from an other context.
     * @param ctx The other context.
     */
    public void setAllAttributes(Context ctx);
    /**
     * Get all attributes from the context.
     * @return A collection of Attribute.
     */
    public Collection<Attribute> getAllAttributes();
}