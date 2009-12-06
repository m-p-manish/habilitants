/*
 * An XML document type.
 * Localname: Status
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.StatusDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Status(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class StatusDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.StatusDocument
{
    
    public StatusDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATUS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Status");
    
    
    /**
     * Gets the "Status" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.StatusType getStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusType)get_store().find_element_user(STATUS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Status" element
     */
    public void setStatus(os.schema.context._0._2.xacml.tc.names.oasis.StatusType status)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusType)get_store().find_element_user(STATUS$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusType)get_store().add_element_user(STATUS$0);
            }
            target.set(status);
        }
    }
    
    /**
     * Appends and returns a new empty "Status" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.StatusType addNewStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusType)get_store().add_element_user(STATUS$0);
            return target;
        }
    }
}
