/*
 * XML Type:  EnvironmentType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML EnvironmentType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class EnvironmentTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentType
{
    
    public EnvironmentTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENVIRONMENTMATCH$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "EnvironmentMatch");
    
    
    /**
     * Gets array of all "EnvironmentMatch" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType[] getEnvironmentMatchArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ENVIRONMENTMATCH$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "EnvironmentMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType getEnvironmentMatchArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType)get_store().find_element_user(ENVIRONMENTMATCH$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "EnvironmentMatch" element
     */
    public int sizeOfEnvironmentMatchArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ENVIRONMENTMATCH$0);
        }
    }
    
    /**
     * Sets array of all "EnvironmentMatch" element
     */
    public void setEnvironmentMatchArray(os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType[] environmentMatchArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(environmentMatchArray, ENVIRONMENTMATCH$0);
        }
    }
    
    /**
     * Sets ith "EnvironmentMatch" element
     */
    public void setEnvironmentMatchArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType environmentMatch)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType)get_store().find_element_user(ENVIRONMENTMATCH$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(environmentMatch);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "EnvironmentMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType insertNewEnvironmentMatch(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType)get_store().insert_element_user(ENVIRONMENTMATCH$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "EnvironmentMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType addNewEnvironmentMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType)get_store().add_element_user(ENVIRONMENTMATCH$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "EnvironmentMatch" element
     */
    public void removeEnvironmentMatch(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ENVIRONMENTMATCH$0, i);
        }
    }
}
