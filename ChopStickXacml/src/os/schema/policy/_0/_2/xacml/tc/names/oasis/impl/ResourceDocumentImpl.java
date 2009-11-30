/*
 * An XML document type.
 * Localname: Resource
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ResourceDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Resource(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ResourceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ResourceDocument
{
    
    public ResourceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Resource");
    
    
    /**
     * Gets the "Resource" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType getResource()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType)get_store().find_element_user(RESOURCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Resource" element
     */
    public void setResource(os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType resource)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType)get_store().find_element_user(RESOURCE$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType)get_store().add_element_user(RESOURCE$0);
            }
            target.set(resource);
        }
    }
    
    /**
     * Appends and returns a new empty "Resource" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType addNewResource()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType)get_store().add_element_user(RESOURCE$0);
            return target;
        }
    }
}
