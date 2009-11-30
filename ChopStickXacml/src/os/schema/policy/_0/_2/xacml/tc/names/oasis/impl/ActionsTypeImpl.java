/*
 * XML Type:  ActionsType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ActionsType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ActionsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType
{
    
    public ActionsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACTION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Action");
    
    
    /**
     * Gets array of all "Action" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionType[] getActionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ACTION$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.ActionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Action" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionType getActionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionType)get_store().find_element_user(ACTION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Action" element
     */
    public int sizeOfActionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ACTION$0);
        }
    }
    
    /**
     * Sets array of all "Action" element
     */
    public void setActionArray(os.schema.policy._0._2.xacml.tc.names.oasis.ActionType[] actionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(actionArray, ACTION$0);
        }
    }
    
    /**
     * Sets ith "Action" element
     */
    public void setActionArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.ActionType action)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionType)get_store().find_element_user(ACTION$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(action);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Action" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionType insertNewAction(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionType)get_store().insert_element_user(ACTION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Action" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionType addNewAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionType)get_store().add_element_user(ACTION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Action" element
     */
    public void removeAction(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ACTION$0, i);
        }
    }
}
