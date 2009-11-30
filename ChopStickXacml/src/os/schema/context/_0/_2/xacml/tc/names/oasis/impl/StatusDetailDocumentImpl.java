/*
 * An XML document type.
 * Localname: StatusDetail
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one StatusDetail(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class StatusDetailDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailDocument
{
    
    public StatusDetailDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATUSDETAIL$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "StatusDetail");
    
    
    /**
     * Gets the "StatusDetail" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType getStatusDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType)get_store().find_element_user(STATUSDETAIL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "StatusDetail" element
     */
    public void setStatusDetail(os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType statusDetail)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType)get_store().find_element_user(STATUSDETAIL$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType)get_store().add_element_user(STATUSDETAIL$0);
            }
            target.set(statusDetail);
        }
    }
    
    /**
     * Appends and returns a new empty "StatusDetail" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType addNewStatusDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType)get_store().add_element_user(STATUSDETAIL$0);
            return target;
        }
    }
}
