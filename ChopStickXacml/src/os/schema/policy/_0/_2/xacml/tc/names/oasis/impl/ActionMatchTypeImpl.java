/*
 * XML Type:  ActionMatchType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ActionMatchType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ActionMatchTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType
{
    
    public ActionMatchTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEVALUE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "AttributeValue");
    private static final javax.xml.namespace.QName ACTIONATTRIBUTEDESIGNATOR$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ActionAttributeDesignator");
    private static final javax.xml.namespace.QName ATTRIBUTESELECTOR$4 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "AttributeSelector");
    private static final javax.xml.namespace.QName MATCHID$6 = 
        new javax.xml.namespace.QName("", "MatchId");
    
    
    /**
     * Gets the "AttributeValue" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType getAttributeValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().find_element_user(ATTRIBUTEVALUE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttributeValue" element
     */
    public void setAttributeValue(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType attributeValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().find_element_user(ATTRIBUTEVALUE$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().add_element_user(ATTRIBUTEVALUE$0);
            }
            target.set(attributeValue);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeValue" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType addNewAttributeValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().add_element_user(ATTRIBUTEVALUE$0);
            return target;
        }
    }
    
    /**
     * Gets the "ActionAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType getActionAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().find_element_user(ACTIONATTRIBUTEDESIGNATOR$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "ActionAttributeDesignator" element
     */
    public boolean isSetActionAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ACTIONATTRIBUTEDESIGNATOR$2) != 0;
        }
    }
    
    /**
     * Sets the "ActionAttributeDesignator" element
     */
    public void setActionAttributeDesignator(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType actionAttributeDesignator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().find_element_user(ACTIONATTRIBUTEDESIGNATOR$2, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().add_element_user(ACTIONATTRIBUTEDESIGNATOR$2);
            }
            target.set(actionAttributeDesignator);
        }
    }
    
    /**
     * Appends and returns a new empty "ActionAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType addNewActionAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().add_element_user(ACTIONATTRIBUTEDESIGNATOR$2);
            return target;
        }
    }
    
    /**
     * Unsets the "ActionAttributeDesignator" element
     */
    public void unsetActionAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ACTIONATTRIBUTEDESIGNATOR$2, 0);
        }
    }
    
    /**
     * Gets the "AttributeSelector" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType getAttributeSelector()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType)get_store().find_element_user(ATTRIBUTESELECTOR$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "AttributeSelector" element
     */
    public boolean isSetAttributeSelector()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ATTRIBUTESELECTOR$4) != 0;
        }
    }
    
    /**
     * Sets the "AttributeSelector" element
     */
    public void setAttributeSelector(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType attributeSelector)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType)get_store().find_element_user(ATTRIBUTESELECTOR$4, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType)get_store().add_element_user(ATTRIBUTESELECTOR$4);
            }
            target.set(attributeSelector);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeSelector" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType addNewAttributeSelector()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType)get_store().add_element_user(ATTRIBUTESELECTOR$4);
            return target;
        }
    }
    
    /**
     * Unsets the "AttributeSelector" element
     */
    public void unsetAttributeSelector()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ATTRIBUTESELECTOR$4, 0);
        }
    }
    
    /**
     * Gets the "MatchId" attribute
     */
    public java.lang.String getMatchId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(MATCHID$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "MatchId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetMatchId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(MATCHID$6);
            return target;
        }
    }
    
    /**
     * Sets the "MatchId" attribute
     */
    public void setMatchId(java.lang.String matchId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(MATCHID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(MATCHID$6);
            }
            target.setStringValue(matchId);
        }
    }
    
    /**
     * Sets (as xml) the "MatchId" attribute
     */
    public void xsetMatchId(org.apache.xmlbeans.XmlAnyURI matchId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(MATCHID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(MATCHID$6);
            }
            target.set(matchId);
        }
    }
}
