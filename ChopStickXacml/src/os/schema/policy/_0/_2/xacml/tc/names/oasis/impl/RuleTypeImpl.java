/*
 * XML Type:  RuleType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.RuleType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML RuleType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class RuleTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.RuleType
{
    
    public RuleTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DESCRIPTION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Description");
    private static final javax.xml.namespace.QName TARGET$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Target");
    private static final javax.xml.namespace.QName CONDITION$4 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Condition");
    private static final javax.xml.namespace.QName RULEID$6 = 
        new javax.xml.namespace.QName("", "RuleId");
    private static final javax.xml.namespace.QName EFFECT$8 = 
        new javax.xml.namespace.QName("", "Effect");
    
    
    /**
     * Gets the "Description" element
     */
    public java.lang.String getDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Description" element
     */
    public org.apache.xmlbeans.XmlString xgetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "Description" element
     */
    public boolean isSetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DESCRIPTION$0) != 0;
        }
    }
    
    /**
     * Sets the "Description" element
     */
    public void setDescription(java.lang.String description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DESCRIPTION$0);
            }
            target.setStringValue(description);
        }
    }
    
    /**
     * Sets (as xml) the "Description" element
     */
    public void xsetDescription(org.apache.xmlbeans.XmlString description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DESCRIPTION$0);
            }
            target.set(description);
        }
    }
    
    /**
     * Unsets the "Description" element
     */
    public void unsetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DESCRIPTION$0, 0);
        }
    }
    
    /**
     * Gets the "Target" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.TargetType getTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().find_element_user(TARGET$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Target" element
     */
    public boolean isSetTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(TARGET$2) != 0;
        }
    }
    
    /**
     * Sets the "Target" element
     */
    public void setTarget(os.schema.policy._0._2.xacml.tc.names.oasis.TargetType targetValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().find_element_user(TARGET$2, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().add_element_user(TARGET$2);
            }
            target.set(targetValue);
        }
    }
    
    /**
     * Appends and returns a new empty "Target" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.TargetType addNewTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().add_element_user(TARGET$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Target" element
     */
    public void unsetTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(TARGET$2, 0);
        }
    }
    
    /**
     * Gets the "Condition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType getCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType)get_store().find_element_user(CONDITION$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Condition" element
     */
    public boolean isSetCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(CONDITION$4) != 0;
        }
    }
    
    /**
     * Sets the "Condition" element
     */
    public void setCondition(os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType condition)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType)get_store().find_element_user(CONDITION$4, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType)get_store().add_element_user(CONDITION$4);
            }
            target.set(condition);
        }
    }
    
    /**
     * Appends and returns a new empty "Condition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType addNewCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType)get_store().add_element_user(CONDITION$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Condition" element
     */
    public void unsetCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(CONDITION$4, 0);
        }
    }
    
    /**
     * Gets the "RuleId" attribute
     */
    public java.lang.String getRuleId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULEID$6);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "RuleId" attribute
     */
    public org.apache.xmlbeans.XmlString xgetRuleId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(RULEID$6);
            return target;
        }
    }
    
    /**
     * Sets the "RuleId" attribute
     */
    public void setRuleId(java.lang.String ruleId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULEID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(RULEID$6);
            }
            target.setStringValue(ruleId);
        }
    }
    
    /**
     * Sets (as xml) the "RuleId" attribute
     */
    public void xsetRuleId(org.apache.xmlbeans.XmlString ruleId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(RULEID$6);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(RULEID$6);
            }
            target.set(ruleId);
        }
    }
    
    /**
     * Gets the "Effect" attribute
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EffectType.Enum getEffect()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(EFFECT$8);
            if (target == null)
            {
                return null;
            }
            return (os.schema.policy._0._2.xacml.tc.names.oasis.EffectType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Effect" attribute
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EffectType xgetEffect()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EffectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EffectType)get_store().find_attribute_user(EFFECT$8);
            return target;
        }
    }
    
    /**
     * Sets the "Effect" attribute
     */
    public void setEffect(os.schema.policy._0._2.xacml.tc.names.oasis.EffectType.Enum effect)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(EFFECT$8);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(EFFECT$8);
            }
            target.setEnumValue(effect);
        }
    }
    
    /**
     * Sets (as xml) the "Effect" attribute
     */
    public void xsetEffect(os.schema.policy._0._2.xacml.tc.names.oasis.EffectType effect)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EffectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EffectType)get_store().find_attribute_user(EFFECT$8);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.EffectType)get_store().add_attribute_user(EFFECT$8);
            }
            target.set(effect);
        }
    }
}
