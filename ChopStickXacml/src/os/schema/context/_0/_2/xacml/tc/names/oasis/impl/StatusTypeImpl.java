/*
 * XML Type:  StatusType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.StatusType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML StatusType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public class StatusTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.StatusType
{
    
    public StatusTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName STATUSCODE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "StatusCode");
    private static final javax.xml.namespace.QName STATUSMESSAGE$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "StatusMessage");
    private static final javax.xml.namespace.QName STATUSDETAIL$4 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "StatusDetail");
    
    
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
    
    /**
     * Gets the "StatusMessage" element
     */
    public java.lang.String getStatusMessage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSMESSAGE$2, 0);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STATUSMESSAGE$2, 0);
            return target;
        }
    }
    
    /**
     * True if has "StatusMessage" element
     */
    public boolean isSetStatusMessage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(STATUSMESSAGE$2) != 0;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(STATUSMESSAGE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(STATUSMESSAGE$2);
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
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(STATUSMESSAGE$2, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(STATUSMESSAGE$2);
            }
            target.set(statusMessage);
        }
    }
    
    /**
     * Unsets the "StatusMessage" element
     */
    public void unsetStatusMessage()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(STATUSMESSAGE$2, 0);
        }
    }
    
    /**
     * Gets the "StatusDetail" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType getStatusDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType)get_store().find_element_user(STATUSDETAIL$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "StatusDetail" element
     */
    public boolean isSetStatusDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(STATUSDETAIL$4) != 0;
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
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType)get_store().find_element_user(STATUSDETAIL$4, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType)get_store().add_element_user(STATUSDETAIL$4);
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
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusDetailType)get_store().add_element_user(STATUSDETAIL$4);
            return target;
        }
    }
    
    /**
     * Unsets the "StatusDetail" element
     */
    public void unsetStatusDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(STATUSDETAIL$4, 0);
        }
    }
}
