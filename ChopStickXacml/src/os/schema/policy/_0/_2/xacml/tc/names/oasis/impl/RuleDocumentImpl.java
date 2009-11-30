/*
 * An XML document type.
 * Localname: Rule
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.RuleDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Rule(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class RuleDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.RuleDocument
{
    
    public RuleDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RULE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Rule");
    
    
    /**
     * Gets the "Rule" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleType getRule()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleType)get_store().find_element_user(RULE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Rule" element
     */
    public void setRule(os.schema.policy._0._2.xacml.tc.names.oasis.RuleType rule)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleType)get_store().find_element_user(RULE$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleType)get_store().add_element_user(RULE$0);
            }
            target.set(rule);
        }
    }
    
    /**
     * Appends and returns a new empty "Rule" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleType addNewRule()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleType)get_store().add_element_user(RULE$0);
            return target;
        }
    }
}
