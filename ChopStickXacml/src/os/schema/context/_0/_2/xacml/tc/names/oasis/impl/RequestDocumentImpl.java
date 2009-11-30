/*
 * An XML document type.
 * Localname: Request
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Request(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class RequestDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument
{
    
    public RequestDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName REQUEST$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Request");
    
    
    /**
     * Gets the "Request" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.RequestType getRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.RequestType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.RequestType)get_store().find_element_user(REQUEST$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Request" element
     */
    public void setRequest(os.schema.context._0._2.xacml.tc.names.oasis.RequestType request)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.RequestType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.RequestType)get_store().find_element_user(REQUEST$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.RequestType)get_store().add_element_user(REQUEST$0);
            }
            target.set(request);
        }
    }
    
    /**
     * Appends and returns a new empty "Request" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.RequestType addNewRequest()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.RequestType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.RequestType)get_store().add_element_user(REQUEST$0);
            return target;
        }
    }
}
