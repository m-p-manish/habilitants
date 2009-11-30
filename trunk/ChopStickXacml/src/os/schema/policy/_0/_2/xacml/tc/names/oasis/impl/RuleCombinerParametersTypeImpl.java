/*
 * XML Type:  RuleCombinerParametersType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML RuleCombinerParametersType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class RuleCombinerParametersTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.CombinerParametersTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType
{
    
    public RuleCombinerParametersTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RULEIDREF$0 = 
        new javax.xml.namespace.QName("", "RuleIdRef");
    
    
    /**
     * Gets the "RuleIdRef" attribute
     */
    public java.lang.String getRuleIdRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULEIDREF$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "RuleIdRef" attribute
     */
    public org.apache.xmlbeans.XmlString xgetRuleIdRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(RULEIDREF$0);
            return target;
        }
    }
    
    /**
     * Sets the "RuleIdRef" attribute
     */
    public void setRuleIdRef(java.lang.String ruleIdRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULEIDREF$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(RULEIDREF$0);
            }
            target.setStringValue(ruleIdRef);
        }
    }
    
    /**
     * Sets (as xml) the "RuleIdRef" attribute
     */
    public void xsetRuleIdRef(org.apache.xmlbeans.XmlString ruleIdRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(RULEIDREF$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(RULEIDREF$0);
            }
            target.set(ruleIdRef);
        }
    }
}
