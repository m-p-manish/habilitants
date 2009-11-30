/*
 * XML Type:  CombinerParameterType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML CombinerParameterType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class CombinerParameterTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType
{
    
    public CombinerParameterTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEVALUE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "AttributeValue");
    private static final javax.xml.namespace.QName PARAMETERNAME$2 = 
        new javax.xml.namespace.QName("", "ParameterName");
    
    
    /**
     * Gets the "AttributeValue" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType getAttributeValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().find_element_user(ATTRIBUTEVALUE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttributeValue" element
     */
    public void setAttributeValue(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType attributeValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().find_element_user(ATTRIBUTEVALUE$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().add_element_user(ATTRIBUTEVALUE$0);
            }
            target.set(attributeValue);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeValue" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType addNewAttributeValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().add_element_user(ATTRIBUTEVALUE$0);
            return target;
        }
    }
    
    /**
     * Gets the "ParameterName" attribute
     */
    public java.lang.String getParameterName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PARAMETERNAME$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ParameterName" attribute
     */
    public org.apache.xmlbeans.XmlString xgetParameterName()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(PARAMETERNAME$2);
            return target;
        }
    }
    
    /**
     * Sets the "ParameterName" attribute
     */
    public void setParameterName(java.lang.String parameterName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(PARAMETERNAME$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(PARAMETERNAME$2);
            }
            target.setStringValue(parameterName);
        }
    }
    
    /**
     * Sets (as xml) the "ParameterName" attribute
     */
    public void xsetParameterName(org.apache.xmlbeans.XmlString parameterName)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(PARAMETERNAME$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(PARAMETERNAME$2);
            }
            target.set(parameterName);
        }
    }
}
