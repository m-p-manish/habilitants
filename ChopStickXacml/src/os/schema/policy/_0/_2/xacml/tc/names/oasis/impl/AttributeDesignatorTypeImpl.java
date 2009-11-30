/*
 * XML Type:  AttributeDesignatorType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML AttributeDesignatorType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class AttributeDesignatorTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType
{
    
    public AttributeDesignatorTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEID$0 = 
        new javax.xml.namespace.QName("", "AttributeId");
    private static final javax.xml.namespace.QName DATATYPE$2 = 
        new javax.xml.namespace.QName("", "DataType");
    private static final javax.xml.namespace.QName ISSUER$4 = 
        new javax.xml.namespace.QName("", "Issuer");
    private static final javax.xml.namespace.QName MUSTBEPRESENT$6 = 
        new javax.xml.namespace.QName("", "MustBePresent");
    
    
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
    
    /**
     * Gets the "DataType" attribute
     */
    public java.lang.String getDataType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(DATATYPE$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "DataType" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetDataType()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(DATATYPE$2);
            return target;
        }
    }
    
    /**
     * Sets the "DataType" attribute
     */
    public void setDataType(java.lang.String dataType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(DATATYPE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(DATATYPE$2);
            }
            target.setStringValue(dataType);
        }
    }
    
    /**
     * Sets (as xml) the "DataType" attribute
     */
    public void xsetDataType(org.apache.xmlbeans.XmlAnyURI dataType)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(DATATYPE$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(DATATYPE$2);
            }
            target.set(dataType);
        }
    }
    
    /**
     * Gets the "Issuer" attribute
     */
    public java.lang.String getIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ISSUER$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Issuer" attribute
     */
    public org.apache.xmlbeans.XmlString xgetIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ISSUER$4);
            return target;
        }
    }
    
    /**
     * True if has "Issuer" attribute
     */
    public boolean isSetIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(ISSUER$4) != null;
        }
    }
    
    /**
     * Sets the "Issuer" attribute
     */
    public void setIssuer(java.lang.String issuer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(ISSUER$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(ISSUER$4);
            }
            target.setStringValue(issuer);
        }
    }
    
    /**
     * Sets (as xml) the "Issuer" attribute
     */
    public void xsetIssuer(org.apache.xmlbeans.XmlString issuer)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(ISSUER$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(ISSUER$4);
            }
            target.set(issuer);
        }
    }
    
    /**
     * Unsets the "Issuer" attribute
     */
    public void unsetIssuer()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(ISSUER$4);
        }
    }
    
    /**
     * Gets the "MustBePresent" attribute
     */
    public boolean getMustBePresent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(MUSTBEPRESENT$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(MUSTBEPRESENT$6);
            }
            if (target == null)
            {
                return false;
            }
            return target.getBooleanValue();
        }
    }
    
    /**
     * Gets (as xml) the "MustBePresent" attribute
     */
    public org.apache.xmlbeans.XmlBoolean xgetMustBePresent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(MUSTBEPRESENT$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_default_attribute_value(MUSTBEPRESENT$6);
            }
            return target;
        }
    }
    
    /**
     * True if has "MustBePresent" attribute
     */
    public boolean isSetMustBePresent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(MUSTBEPRESENT$6) != null;
        }
    }
    
    /**
     * Sets the "MustBePresent" attribute
     */
    public void setMustBePresent(boolean mustBePresent)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(MUSTBEPRESENT$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(MUSTBEPRESENT$6);
            }
            target.setBooleanValue(mustBePresent);
        }
    }
    
    /**
     * Sets (as xml) the "MustBePresent" attribute
     */
    public void xsetMustBePresent(org.apache.xmlbeans.XmlBoolean mustBePresent)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlBoolean target = null;
            target = (org.apache.xmlbeans.XmlBoolean)get_store().find_attribute_user(MUSTBEPRESENT$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlBoolean)get_store().add_attribute_user(MUSTBEPRESENT$6);
            }
            target.set(mustBePresent);
        }
    }
    
    /**
     * Unsets the "MustBePresent" attribute
     */
    public void unsetMustBePresent()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(MUSTBEPRESENT$6);
        }
    }
}
