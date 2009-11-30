/*
 * XML Type:  ResourceType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.ResourceType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ResourceType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public class ResourceTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.ResourceType
{
    
    public ResourceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCECONTENT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "ResourceContent");
    private static final javax.xml.namespace.QName ATTRIBUTE$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Attribute");
    
    
    /**
     * Gets the "ResourceContent" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType getResourceContent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType)get_store().find_element_user(RESOURCECONTENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ResourceContent" element
     */
    public boolean isSetResourceContent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESOURCECONTENT$0) != 0;
        }
    }
    
    /**
     * Sets the "ResourceContent" element
     */
    public void setResourceContent(os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType resourceContent)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType)get_store().find_element_user(RESOURCECONTENT$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType)get_store().add_element_user(RESOURCECONTENT$0);
            }
            target.set(resourceContent);
        }
    }
    
    /**
     * Appends and returns a new empty "ResourceContent" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType addNewResourceContent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceContentType)get_store().add_element_user(RESOURCECONTENT$0);
            return target;
        }
    }
    
    /**
     * Unsets the "ResourceContent" element
     */
    public void unsetResourceContent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESOURCECONTENT$0, 0);
        }
    }
    
    /**
     * Gets array of all "Attribute" elements
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeType[] getAttributeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTE$2, targetList);
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeType[] result = new os.schema.context._0._2.xacml.tc.names.oasis.AttributeType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Attribute" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeType getAttributeArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().find_element_user(ATTRIBUTE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Attribute" element
     */
    public int sizeOfAttributeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTE$2);
        }
    }
    
    /**
     * Sets array of all "Attribute" element
     */
    public void setAttributeArray(os.schema.context._0._2.xacml.tc.names.oasis.AttributeType[] attributeArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(attributeArray, ATTRIBUTE$2);
        }
    }
    
    /**
     * Sets ith "Attribute" element
     */
    public void setAttributeArray(int i, os.schema.context._0._2.xacml.tc.names.oasis.AttributeType attribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().find_element_user(ATTRIBUTE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(attribute);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Attribute" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeType insertNewAttribute(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().insert_element_user(ATTRIBUTE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Attribute" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeType addNewAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().add_element_user(ATTRIBUTE$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "Attribute" element
     */
    public void removeAttribute(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTE$2, i);
        }
    }
}
