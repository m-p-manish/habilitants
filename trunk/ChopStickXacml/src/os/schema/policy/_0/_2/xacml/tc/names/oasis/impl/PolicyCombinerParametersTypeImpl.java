/*
 * XML Type:  PolicyCombinerParametersType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML PolicyCombinerParametersType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class PolicyCombinerParametersTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.CombinerParametersTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType
{
    
    public PolicyCombinerParametersTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYIDREF$0 = 
        new javax.xml.namespace.QName("", "PolicyIdRef");
    
    
    /**
     * Gets the "PolicyIdRef" attribute
     */
    public java.lang.String getPolicyIdRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYIDREF$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "PolicyIdRef" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetPolicyIdRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYIDREF$0);
            return target;
        }
    }
    
    /**
     * Sets the "PolicyIdRef" attribute
     */
    public void setPolicyIdRef(java.lang.String policyIdRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYIDREF$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(POLICYIDREF$0);
            }
            target.setStringValue(policyIdRef);
        }
    }
    
    /**
     * Sets (as xml) the "PolicyIdRef" attribute
     */
    public void xsetPolicyIdRef(org.apache.xmlbeans.XmlAnyURI policyIdRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYIDREF$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(POLICYIDREF$0);
            }
            target.set(policyIdRef);
        }
    }
}
