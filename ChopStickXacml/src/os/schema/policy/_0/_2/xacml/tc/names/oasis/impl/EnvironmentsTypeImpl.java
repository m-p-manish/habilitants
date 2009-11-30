/*
 * XML Type:  EnvironmentsType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML EnvironmentsType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class EnvironmentsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType
{
    
    public EnvironmentsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENVIRONMENT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Environment");
    
    
    /**
     * Gets array of all "Environment" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType[] getEnvironmentArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ENVIRONMENT$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Environment" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType getEnvironmentArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().find_element_user(ENVIRONMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Environment" element
     */
    public int sizeOfEnvironmentArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ENVIRONMENT$0);
        }
    }
    
    /**
     * Sets array of all "Environment" element
     */
    public void setEnvironmentArray(os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType[] environmentArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(environmentArray, ENVIRONMENT$0);
        }
    }
    
    /**
     * Sets ith "Environment" element
     */
    public void setEnvironmentArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType environment)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().find_element_user(ENVIRONMENT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(environment);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Environment" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType insertNewEnvironment(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().insert_element_user(ENVIRONMENT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Environment" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType addNewEnvironment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().add_element_user(ENVIRONMENT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Environment" element
     */
    public void removeEnvironment(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ENVIRONMENT$0, i);
        }
    }
}
