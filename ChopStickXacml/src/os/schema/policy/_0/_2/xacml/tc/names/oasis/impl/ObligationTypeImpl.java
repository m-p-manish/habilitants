/*
 * XML Type:  ObligationType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ObligationType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ObligationTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType
{
    
    public ObligationTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEASSIGNMENT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "AttributeAssignment");
    private static final javax.xml.namespace.QName OBLIGATIONID$2 = 
        new javax.xml.namespace.QName("", "ObligationId");
    private static final javax.xml.namespace.QName FULFILLON$4 = 
        new javax.xml.namespace.QName("", "FulfillOn");
    
    
    /**
     * Gets array of all "AttributeAssignment" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType[] getAttributeAssignmentArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTEASSIGNMENT$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "AttributeAssignment" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType getAttributeAssignmentArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType)get_store().find_element_user(ATTRIBUTEASSIGNMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "AttributeAssignment" element
     */
    public int sizeOfAttributeAssignmentArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTEASSIGNMENT$0);
        }
    }
    
    /**
     * Sets array of all "AttributeAssignment" element
     */
    public void setAttributeAssignmentArray(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType[] attributeAssignmentArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attributeAssignmentArray, ATTRIBUTEASSIGNMENT$0);
        }
    }
    
    /**
     * Sets ith "AttributeAssignment" element
     */
    public void setAttributeAssignmentArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType attributeAssignment)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType)get_store().find_element_user(ATTRIBUTEASSIGNMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attributeAssignment);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AttributeAssignment" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType insertNewAttributeAssignment(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType)get_store().insert_element_user(ATTRIBUTEASSIGNMENT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AttributeAssignment" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType addNewAttributeAssignment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType)get_store().add_element_user(ATTRIBUTEASSIGNMENT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "AttributeAssignment" element
     */
    public void removeAttributeAssignment(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTEASSIGNMENT$0, i);
        }
    }
    
    /**
     * Gets the "ObligationId" attribute
     */
    public java.lang.String getObligationId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(OBLIGATIONID$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "ObligationId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetObligationId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(OBLIGATIONID$2);
            return target;
        }
    }
    
    /**
     * Sets the "ObligationId" attribute
     */
    public void setObligationId(java.lang.String obligationId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(OBLIGATIONID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(OBLIGATIONID$2);
            }
            target.setStringValue(obligationId);
        }
    }
    
    /**
     * Sets (as xml) the "ObligationId" attribute
     */
    public void xsetObligationId(org.apache.xmlbeans.XmlAnyURI obligationId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(OBLIGATIONID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(OBLIGATIONID$2);
            }
            target.set(obligationId);
        }
    }
    
    /**
     * Gets the "FulfillOn" attribute
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EffectType.Enum getFulfillOn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FULFILLON$4);
            if (target == null)
            {
                return null;
            }
            return (os.schema.policy._0._2.xacml.tc.names.oasis.EffectType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "FulfillOn" attribute
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EffectType xgetFulfillOn()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EffectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EffectType)get_store().find_attribute_user(FULFILLON$4);
            return target;
        }
    }
    
    /**
     * Sets the "FulfillOn" attribute
     */
    public void setFulfillOn(os.schema.policy._0._2.xacml.tc.names.oasis.EffectType.Enum fulfillOn)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FULFILLON$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(FULFILLON$4);
            }
            target.setEnumValue(fulfillOn);
        }
    }
    
    /**
     * Sets (as xml) the "FulfillOn" attribute
     */
    public void xsetFulfillOn(os.schema.policy._0._2.xacml.tc.names.oasis.EffectType fulfillOn)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EffectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EffectType)get_store().find_attribute_user(FULFILLON$4);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.EffectType)get_store().add_attribute_user(FULFILLON$4);
            }
            target.set(fulfillOn);
        }
    }
}
