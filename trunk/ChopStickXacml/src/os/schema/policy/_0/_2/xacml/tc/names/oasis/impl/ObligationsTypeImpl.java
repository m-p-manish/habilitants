/*
 * XML Type:  ObligationsType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ObligationsType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ObligationsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType
{
    
    public ObligationsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OBLIGATION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Obligation");
    
    
    /**
     * Gets array of all "Obligation" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType[] getObligationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(OBLIGATION$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Obligation" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType getObligationArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType)get_store().find_element_user(OBLIGATION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Obligation" element
     */
    public int sizeOfObligationArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OBLIGATION$0);
        }
    }
    
    /**
     * Sets array of all "Obligation" element
     */
    public void setObligationArray(os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType[] obligationArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(obligationArray, OBLIGATION$0);
        }
    }
    
    /**
     * Sets ith "Obligation" element
     */
    public void setObligationArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType obligation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType)get_store().find_element_user(OBLIGATION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(obligation);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Obligation" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType insertNewObligation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType)get_store().insert_element_user(OBLIGATION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Obligation" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType addNewObligation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType)get_store().add_element_user(OBLIGATION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Obligation" element
     */
    public void removeObligation(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OBLIGATION$0, i);
        }
    }
}
