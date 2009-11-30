/*
 * An XML document type.
 * Localname: ResourceMatch
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one ResourceMatch(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ResourceMatchDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchDocument
{
    
    public ResourceMatchDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCEMATCH$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ResourceMatch");
    
    
    /**
     * Gets the "ResourceMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType getResourceMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType)get_store().find_element_user(RESOURCEMATCH$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ResourceMatch" element
     */
    public void setResourceMatch(os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType resourceMatch)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType)get_store().find_element_user(RESOURCEMATCH$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType)get_store().add_element_user(RESOURCEMATCH$0);
            }
            target.set(resourceMatch);
        }
    }
    
    /**
     * Appends and returns a new empty "ResourceMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType addNewResourceMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType)get_store().add_element_user(RESOURCEMATCH$0);
            return target;
        }
    }
}
