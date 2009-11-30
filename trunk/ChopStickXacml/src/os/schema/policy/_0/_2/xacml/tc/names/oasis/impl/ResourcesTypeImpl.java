/*
 * XML Type:  ResourcesType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ResourcesType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ResourcesTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType
{
    
    public ResourcesTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Resource");
    
    
    /**
     * Gets array of all "Resource" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType[] getResourceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RESOURCE$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Resource" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType getResourceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType)get_store().find_element_user(RESOURCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Resource" element
     */
    public int sizeOfResourceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESOURCE$0);
        }
    }
    
    /**
     * Sets array of all "Resource" element
     */
    public void setResourceArray(os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType[] resourceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(resourceArray, RESOURCE$0);
        }
    }
    
    /**
     * Sets ith "Resource" element
     */
    public void setResourceArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType resource)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType)get_store().find_element_user(RESOURCE$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(resource);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Resource" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType insertNewResource(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType)get_store().insert_element_user(RESOURCE$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Resource" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType addNewResource()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType)get_store().add_element_user(RESOURCE$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Resource" element
     */
    public void removeResource(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESOURCE$0, i);
        }
    }
}
