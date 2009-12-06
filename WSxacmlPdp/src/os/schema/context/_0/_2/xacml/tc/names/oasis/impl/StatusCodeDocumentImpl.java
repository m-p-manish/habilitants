/*
 * An XML document type.
 * Localname: StatusCode
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one StatusCode(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class StatusCodeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeDocument
{
    
    public StatusCodeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATUSCODE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "StatusCode");
    
    
    /**
     * Gets the "StatusCode" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType getStatusCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType)get_store().find_element_user(STATUSCODE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "StatusCode" element
     */
    public void setStatusCode(os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType statusCode)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType)get_store().find_element_user(STATUSCODE$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType)get_store().add_element_user(STATUSCODE$0);
            }
            target.set(statusCode);
        }
    }
    
    /**
     * Appends and returns a new empty "StatusCode" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType addNewStatusCode()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType)get_store().add_element_user(STATUSCODE$0);
            return target;
        }
    }
}
