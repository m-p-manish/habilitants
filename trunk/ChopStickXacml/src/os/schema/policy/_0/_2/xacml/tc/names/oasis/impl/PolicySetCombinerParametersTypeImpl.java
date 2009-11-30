/*
 * XML Type:  PolicySetCombinerParametersType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML PolicySetCombinerParametersType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class PolicySetCombinerParametersTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.CombinerParametersTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType
{
    
    public PolicySetCombinerParametersTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYSETIDREF$0 = 
        new javax.xml.namespace.QName("", "PolicySetIdRef");
    
    
    /**
     * Gets the "PolicySetIdRef" attribute
     */
    public java.lang.String getPolicySetIdRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYSETIDREF$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "PolicySetIdRef" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetPolicySetIdRef()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYSETIDREF$0);
            return target;
        }
    }
    
    /**
     * Sets the "PolicySetIdRef" attribute
     */
    public void setPolicySetIdRef(java.lang.String policySetIdRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYSETIDREF$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(POLICYSETIDREF$0);
            }
            target.setStringValue(policySetIdRef);
        }
    }
    
    /**
     * Sets (as xml) the "PolicySetIdRef" attribute
     */
    public void xsetPolicySetIdRef(org.apache.xmlbeans.XmlAnyURI policySetIdRef)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYSETIDREF$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(POLICYSETIDREF$0);
            }
            target.set(policySetIdRef);
        }
    }
}
