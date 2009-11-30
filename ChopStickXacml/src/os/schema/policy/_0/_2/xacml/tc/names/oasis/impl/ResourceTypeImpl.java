/*
 * XML Type:  ResourceType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ResourceType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ResourceTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ResourceType
{
    
    public ResourceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCEMATCH$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ResourceMatch");
    
    
    /**
     * Gets array of all "ResourceMatch" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType[] getResourceMatchArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RESOURCEMATCH$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "ResourceMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType getResourceMatchArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType)get_store().find_element_user(RESOURCEMATCH$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "ResourceMatch" element
     */
    public int sizeOfResourceMatchArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESOURCEMATCH$0);
        }
    }
    
    /**
     * Sets array of all "ResourceMatch" element
     */
    public void setResourceMatchArray(os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType[] resourceMatchArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(resourceMatchArray, RESOURCEMATCH$0);
        }
    }
    
    /**
     * Sets ith "ResourceMatch" element
     */
    public void setResourceMatchArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType resourceMatch)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType)get_store().find_element_user(RESOURCEMATCH$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(resourceMatch);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "ResourceMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType insertNewResourceMatch(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType)get_store().insert_element_user(RESOURCEMATCH$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "ResourceMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType addNewResourceMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourceMatchType)get_store().add_element_user(RESOURCEMATCH$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "ResourceMatch" element
     */
    public void removeResourceMatch(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESOURCEMATCH$0, i);
        }
    }
}
