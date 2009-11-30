/*
 * XML Type:  AttributeAssignmentType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML AttributeAssignmentType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class AttributeAssignmentTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.AttributeValueTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType
{
    
    public AttributeAssignmentTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEID$0 = 
        new javax.xml.namespace.QName("", "AttributeId");
    
    
    /**
     * Gets the "AttributeId" attribute
     */
    public java.lang.String getAttributeId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ATTRIBUTEID$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "AttributeId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetAttributeId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(ATTRIBUTEID$0);
            return target;
        }
    }
    
    /**
     * Sets the "AttributeId" attribute
     */
    public void setAttributeId(java.lang.String attributeId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ATTRIBUTEID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ATTRIBUTEID$0);
            }
            target.setStringValue(attributeId);
        }
    }
    
    /**
     * Sets (as xml) the "AttributeId" attribute
     */
    public void xsetAttributeId(org.apache.xmlbeans.XmlAnyURI attributeId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(ATTRIBUTEID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(ATTRIBUTEID$0);
            }
            target.set(attributeId);
        }
    }
}
