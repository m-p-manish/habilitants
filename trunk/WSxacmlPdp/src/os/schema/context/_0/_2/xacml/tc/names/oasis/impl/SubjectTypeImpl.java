/*
 * XML Type:  SubjectType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.SubjectType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML SubjectType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public class SubjectTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.SubjectType
{
    
    public SubjectTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Attribute");
    private static final javax.xml.namespace.QName SUBJECTCATEGORY$2 = 
        new javax.xml.namespace.QName("", "SubjectCategory");
    
    
    /**
     * Gets array of all "Attribute" elements
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeType[] getAttributeArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(ATTRIBUTE$0, targetList);
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
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().find_element_user(ATTRIBUTE$0, i);
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
            return get_store().count_elements(ATTRIBUTE$0);
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
            arraySetterHelper(attributeArray, ATTRIBUTE$0);
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
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().find_element_user(ATTRIBUTE$0, i);
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
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().insert_element_user(ATTRIBUTE$0, i);
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
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().add_element_user(ATTRIBUTE$0);
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
            get_store().remove_element(ATTRIBUTE$0, i);
        }
    }
    
    /**
     * Gets the "SubjectCategory" attribute
     */
    public java.lang.String getSubjectCategory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBJECTCATEGORY$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(SUBJECTCATEGORY$2);
            }
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "SubjectCategory" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetSubjectCategory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(SUBJECTCATEGORY$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_default_attribute_value(SUBJECTCATEGORY$2);
            }
            return target;
        }
    }
    
    /**
     * True if has "SubjectCategory" attribute
     */
    public boolean isSetSubjectCategory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(SUBJECTCATEGORY$2) != null;
        }
    }
    
    /**
     * Sets the "SubjectCategory" attribute
     */
    public void setSubjectCategory(java.lang.String subjectCategory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBJECTCATEGORY$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SUBJECTCATEGORY$2);
            }
            target.setStringValue(subjectCategory);
        }
    }
    
    /**
     * Sets (as xml) the "SubjectCategory" attribute
     */
    public void xsetSubjectCategory(org.apache.xmlbeans.XmlAnyURI subjectCategory)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(SUBJECTCATEGORY$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(SUBJECTCATEGORY$2);
            }
            target.set(subjectCategory);
        }
    }
    
    /**
     * Unsets the "SubjectCategory" attribute
     */
    public void unsetSubjectCategory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(SUBJECTCATEGORY$2);
        }
    }
}
