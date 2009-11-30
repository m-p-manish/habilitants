/*
 * An XML document type.
 * Localname: Resources
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Resources(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ResourcesDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesDocument
{
    
    public ResourcesDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCES$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Resources");
    
    
    /**
     * Gets the "Resources" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType getResources()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType)get_store().find_element_user(RESOURCES$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Resources" element
     */
    public void setResources(os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType resources)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType)get_store().find_element_user(RESOURCES$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType)get_store().add_element_user(RESOURCES$0);
            }
            target.set(resources);
        }
    }
    
    /**
     * Appends and returns a new empty "Resources" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType addNewResources()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType)get_store().add_element_user(RESOURCES$0);
            return target;
        }
    }
}
