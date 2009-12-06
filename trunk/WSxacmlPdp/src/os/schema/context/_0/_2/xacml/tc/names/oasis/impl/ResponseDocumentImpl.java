/*
 * An XML document type.
 * Localname: Response
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Response(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class ResponseDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument
{
    
    public ResponseDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESPONSE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Response");
    
    
    /**
     * Gets the "Response" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResponseType getResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResponseType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResponseType)get_store().find_element_user(RESPONSE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Response" element
     */
    public void setResponse(os.schema.context._0._2.xacml.tc.names.oasis.ResponseType response)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResponseType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResponseType)get_store().find_element_user(RESPONSE$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.ResponseType)get_store().add_element_user(RESPONSE$0);
            }
            target.set(response);
        }
    }
    
    /**
     * Appends and returns a new empty "Response" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResponseType addNewResponse()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResponseType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResponseType)get_store().add_element_user(RESPONSE$0);
            return target;
        }
    }
}
