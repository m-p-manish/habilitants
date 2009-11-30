/*
 * XML Type:  SubjectAttributeDesignatorType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML SubjectAttributeDesignatorType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class SubjectAttributeDesignatorTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.AttributeDesignatorTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType
{
    
    public SubjectAttributeDesignatorTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECTCATEGORY$0 = 
        new javax.xml.namespace.QName("", "SubjectCategory");
    
    
    /**
     * Gets the "SubjectCategory" attribute
     */
    public java.lang.String getSubjectCategory()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBJECTCATEGORY$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(SUBJECTCATEGORY$0);
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
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(SUBJECTCATEGORY$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_default_attribute_value(SUBJECTCATEGORY$0);
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
            return get_store().find_attribute_user(SUBJECTCATEGORY$0) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(SUBJECTCATEGORY$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(SUBJECTCATEGORY$0);
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
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(SUBJECTCATEGORY$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(SUBJECTCATEGORY$0);
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
            get_store().remove_attribute(SUBJECTCATEGORY$0);
        }
    }
}
