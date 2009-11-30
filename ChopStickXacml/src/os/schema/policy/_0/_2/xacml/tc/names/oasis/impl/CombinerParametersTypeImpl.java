/*
 * XML Type:  CombinerParametersType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML CombinerParametersType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class CombinerParametersTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType
{
    
    public CombinerParametersTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMBINERPARAMETER$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "CombinerParameter");
    private static final javax.xml.namespace.QName QWERTY$2 = 
        new javax.xml.namespace.QName("", "qwerty");
    
    
    /**
     * Gets array of all "CombinerParameter" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType[] getCombinerParameterArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COMBINERPARAMETER$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CombinerParameter" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType getCombinerParameterArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType)get_store().find_element_user(COMBINERPARAMETER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CombinerParameter" element
     */
    public int sizeOfCombinerParameterArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMBINERPARAMETER$0);
        }
    }
    
    /**
     * Sets array of all "CombinerParameter" element
     */
    public void setCombinerParameterArray(os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType[] combinerParameterArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(combinerParameterArray, COMBINERPARAMETER$0);
        }
    }
    
    /**
     * Sets ith "CombinerParameter" element
     */
    public void setCombinerParameterArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType combinerParameter)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType)get_store().find_element_user(COMBINERPARAMETER$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(combinerParameter);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CombinerParameter" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType insertNewCombinerParameter(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType)get_store().insert_element_user(COMBINERPARAMETER$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CombinerParameter" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType addNewCombinerParameter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType)get_store().add_element_user(COMBINERPARAMETER$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "CombinerParameter" element
     */
    public void removeCombinerParameter(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMBINERPARAMETER$0, i);
        }
    }
    
    /**
     * Gets the "qwerty" attribute
     */
    public org.apache.xmlbeans.XmlAnySimpleType getQwerty()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnySimpleType target = null;
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(QWERTY$2);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "qwerty" attribute
     */
    public boolean isSetQwerty()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(QWERTY$2) != null;
        }
    }
    
    /**
     * Sets the "qwerty" attribute
     */
    public void setQwerty(org.apache.xmlbeans.XmlAnySimpleType qwerty)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnySimpleType target = null;
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().find_attribute_user(QWERTY$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(QWERTY$2);
            }
            target.set(qwerty);
        }
    }
    
    /**
     * Appends and returns a new empty "qwerty" attribute
     */
    public org.apache.xmlbeans.XmlAnySimpleType addNewQwerty()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnySimpleType target = null;
            target = (org.apache.xmlbeans.XmlAnySimpleType)get_store().add_attribute_user(QWERTY$2);
            return target;
        }
    }
    
    /**
     * Unsets the "qwerty" attribute
     */
    public void unsetQwerty()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(QWERTY$2);
        }
    }
}
