/*
 * XML Type:  AttributeType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.AttributeType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML AttributeType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public class AttributeTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.AttributeType
{
    
    public AttributeTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEVALUE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "AttributeValue");
    private static final javax.xml.namespace.QName ATTRIBUTEID$2 = 
        new javax.xml.namespace.QName("", "AttributeId");
    private static final javax.xml.namespace.QName DATATYPE$4 = 
        new javax.xml.namespace.QName("", "DataType");
    private static final javax.xml.namespace.QName ISSUER$6 = 
        new javax.xml.namespace.QName("", "Issuer");
    
    
    /**
     * Gets array of all "AttributeValue" elements
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType[] getAttributeValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTEVALUE$0, targetList);
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType[] result = new os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "AttributeValue" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType getAttributeValueArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().find_element_user(ATTRIBUTEVALUE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "AttributeValue" element
     */
    public int sizeOfAttributeValueArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTEVALUE$0);
        }
    }
    
    /**
     * Sets array of all "AttributeValue" element
     */
    public void setAttributeValueArray(os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType[] attributeValueArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attributeValueArray, ATTRIBUTEVALUE$0);
        }
    }
    
    /**
     * Sets ith "AttributeValue" element
     */
    public void setAttributeValueArray(int i, os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType attributeValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().find_element_user(ATTRIBUTEVALUE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attributeValue);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AttributeValue" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType insertNewAttributeValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().insert_element_user(ATTRIBUTEVALUE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AttributeValue" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType addNewAttributeValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().add_element_user(ATTRIBUTEVALUE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "AttributeValue" element
     */
    public void removeAttributeValue(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTEVALUE$0, i);
        }
    }
    
    /**
     * Gets the "AttributeId" attribute
     */
    public java.lang.String getAttributeId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ATTRIBUTEID$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "AttributeId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetAttributeId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(ATTRIBUTEID$2);
            return target;
        }
    }
    
    /**
     * Sets the "AttributeId" attribute
     */
    public void setAttributeId(java.lang.String attributeId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ATTRIBUTEID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ATTRIBUTEID$2);
            }
            target.setStringValue(attributeId);
        }
    }
    
    /**
     * Sets (as xml) the "AttributeId" attribute
     */
    public void xsetAttributeId(org.apache.xmlbeans.XmlAnyURI attributeId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(ATTRIBUTEID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(ATTRIBUTEID$2);
            }
            target.set(attributeId);
        }
    }
    
    /**
     * Gets the "DataType" attribute
     */
    public java.lang.String getDataType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(DATATYPE$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "DataType" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetDataType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(DATATYPE$4);
            return target;
        }
    }
    
    /**
     * Sets the "DataType" attribute
     */
    public void setDataType(java.lang.String dataType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(DATATYPE$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(DATATYPE$4);
            }
            target.setStringValue(dataType);
        }
    }
    
    /**
     * Sets (as xml) the "DataType" attribute
     */
    public void xsetDataType(org.apache.xmlbeans.XmlAnyURI dataType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(DATATYPE$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(DATATYPE$4);
            }
            target.set(dataType);
        }
    }
    
    /**
     * Gets the "Issuer" attribute
     */
    public java.lang.String getIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ISSUER$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Issuer" attribute
     */
    public org.apache.xmlbeans.XmlString xgetIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ISSUER$6);
            return target;
        }
    }
    
    /**
     * True if has "Issuer" attribute
     */
    public boolean isSetIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ISSUER$6) != null;
        }
    }
    
    /**
     * Sets the "Issuer" attribute
     */
    public void setIssuer(java.lang.String issuer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ISSUER$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ISSUER$6);
            }
            target.setStringValue(issuer);
        }
    }
    
    /**
     * Sets (as xml) the "Issuer" attribute
     */
    public void xsetIssuer(org.apache.xmlbeans.XmlString issuer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ISSUER$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(ISSUER$6);
            }
            target.set(issuer);
        }
    }
    
    /**
     * Unsets the "Issuer" attribute
     */
    public void unsetIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ISSUER$6);
        }
    }
}
