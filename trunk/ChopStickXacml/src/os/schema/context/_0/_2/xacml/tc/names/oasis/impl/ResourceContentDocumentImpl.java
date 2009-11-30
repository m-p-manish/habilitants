/*
 * An XML document type.
 * Localname: ResourceContent
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one ResourceContent(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class ResourceContentDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentDocument
{
    
    public ResourceContentDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCECONTENT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "ResourceContent");
    
    
    /**
     * Gets the "ResourceContent" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType getResourceContent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType)get_store().find_element_user(RESOURCECONTENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ResourceContent" element
     */
    public void setResourceContent(os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType resourceContent)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType)get_store().find_element_user(RESOURCECONTENT$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType)get_store().add_element_user(RESOURCECONTENT$0);
            }
            target.set(resourceContent);
        }
    }
    
    /**
     * Appends and returns a new empty "ResourceContent" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType addNewResourceContent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType)get_store().add_element_user(RESOURCECONTENT$0);
            return target;
        }
    }
}
