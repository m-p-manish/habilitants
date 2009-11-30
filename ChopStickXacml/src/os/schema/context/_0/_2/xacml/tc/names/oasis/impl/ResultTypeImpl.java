/*
 * XML Type:  ResultType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.ResultType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ResultType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public class ResultTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.ResultType
{
    
    public ResultTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DECISION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Decision");
    private static final javax.xml.namespace.QName STATUS$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Status");
    private static final javax.xml.namespace.QName OBLIGATIONS$4 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Obligations");
    private static final javax.xml.namespace.QName RESOURCEID$6 = 
        new javax.xml.namespace.QName("", "ResourceId");
    
    
    /**
     * Gets the "Decision" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.DecisionType.Enum getDecision()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DECISION$0, 0);
            if (target == null)
            {
                return null;
            }
            return (os.schema.context._0._2.xacml.tc.names.oasis.DecisionType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Decision" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.DecisionType xgetDecision()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.DecisionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.DecisionType)get_store().find_element_user(DECISION$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Decision" element
     */
    public void setDecision(os.schema.context._0._2.xacml.tc.names.oasis.DecisionType.Enum decision)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DECISION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DECISION$0);
            }
            target.setEnumValue(decision);
        }
    }
    
    /**
     * Sets (as xml) the "Decision" element
     */
    public void xsetDecision(os.schema.context._0._2.xacml.tc.names.oasis.DecisionType decision)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.DecisionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.DecisionType)get_store().find_element_user(DECISION$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.DecisionType)get_store().add_element_user(DECISION$0);
            }
            target.set(decision);
        }
    }
    
    /**
     * Gets the "Status" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.StatusType getStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.StatusType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusType)get_store().find_element_user(STATUS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Status" element
     */
    public boolean isSetStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(STATUS$2) != 0;
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
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusType)get_store().find_element_user(STATUS$2, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusType)get_store().add_element_user(STATUS$2);
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
            target = (os.schema.context._0._2.xacml.tc.names.oasis.StatusType)get_store().add_element_user(STATUS$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Status" element
     */
    public void unsetStatus()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(STATUS$2, 0);
        }
    }
    
    /**
     * Gets the "Obligations" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType getObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().find_element_user(OBLIGATIONS$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Obligations" element
     */
    public boolean isSetObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OBLIGATIONS$4) != 0;
        }
    }
    
    /**
     * Sets the "Obligations" element
     */
    public void setObligations(os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType obligations)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().find_element_user(OBLIGATIONS$4, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().add_element_user(OBLIGATIONS$4);
            }
            target.set(obligations);
        }
    }
    
    /**
     * Appends and returns a new empty "Obligations" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType addNewObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().add_element_user(OBLIGATIONS$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Obligations" element
     */
    public void unsetObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OBLIGATIONS$4, 0);
        }
    }
    
    /**
     * Gets the "ResourceId" attribute
     */
    public java.lang.String getResourceId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RESOURCEID$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ResourceId" attribute
     */
    public org.apache.xmlbeans.XmlString xgetResourceId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(RESOURCEID$6);
            return target;
        }
    }
    
    /**
     * True if has "ResourceId" attribute
     */
    public boolean isSetResourceId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(RESOURCEID$6) != null;
        }
    }
    
    /**
     * Sets the "ResourceId" attribute
     */
    public void setResourceId(java.lang.String resourceId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RESOURCEID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(RESOURCEID$6);
            }
            target.setStringValue(resourceId);
        }
    }
    
    /**
     * Sets (as xml) the "ResourceId" attribute
     */
    public void xsetResourceId(org.apache.xmlbeans.XmlString resourceId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(RESOURCEID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(RESOURCEID$6);
            }
            target.set(resourceId);
        }
    }
    
    /**
     * Unsets the "ResourceId" attribute
     */
    public void unsetResourceId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(RESOURCEID$6);
        }
    }
}
