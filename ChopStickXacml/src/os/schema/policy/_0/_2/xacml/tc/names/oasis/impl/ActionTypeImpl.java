/*
 * XML Type:  ActionType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ActionType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ActionType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ActionTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ActionType
{
    
    public ActionTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACTIONMATCH$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ActionMatch");
    
    
    /**
     * Gets array of all "ActionMatch" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType[] getActionMatchArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ACTIONMATCH$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ActionMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType getActionMatchArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType)get_store().find_element_user(ACTIONMATCH$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ActionMatch" element
     */
    public int sizeOfActionMatchArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ACTIONMATCH$0);
        }
    }
    
    /**
     * Sets array of all "ActionMatch" element
     */
    public void setActionMatchArray(os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType[] actionMatchArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(actionMatchArray, ACTIONMATCH$0);
        }
    }
    
    /**
     * Sets ith "ActionMatch" element
     */
    public void setActionMatchArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType actionMatch)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType)get_store().find_element_user(ACTIONMATCH$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(actionMatch);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ActionMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType insertNewActionMatch(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType)get_store().insert_element_user(ACTIONMATCH$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ActionMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType addNewActionMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType)get_store().add_element_user(ACTIONMATCH$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ActionMatch" element
     */
    public void removeActionMatch(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ACTIONMATCH$0, i);
        }
    }
}
