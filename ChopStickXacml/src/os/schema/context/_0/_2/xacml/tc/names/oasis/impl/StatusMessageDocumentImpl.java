/*
 * An XML document type.
 * Localname: StatusMessage
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.StatusMessageDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one StatusMessage(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class StatusMessageDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.StatusMessageDocument
{
    
    public StatusMessageDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATUSMESSAGE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "StatusMessage");
    
    
    /**
     * Gets the "StatusMessage" element
     */
    public java.lang.String getStatusMessage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSMESSAGE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "StatusMessage" element
     */
    public org.apache.xmlbeans.XmlString xgetStatusMessage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STATUSMESSAGE$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "StatusMessage" element
     */
    public void setStatusMessage(java.lang.String statusMessage)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSMESSAGE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSMESSAGE$0);
            }
            target.setStringValue(statusMessage);
        }
    }
    
    /**
     * Sets (as xml) the "StatusMessage" element
     */
    public void xsetStatusMessage(org.apache.xmlbeans.XmlString statusMessage)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STATUSMESSAGE$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(STATUSMESSAGE$0);
            }
            target.set(statusMessage);
        }
    }
}
