/*
 * An XML document type.
 * Localname: PolicySetDefaults
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetDefaultsDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one PolicySetDefaults(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class PolicySetDefaultsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetDefaultsDocument
{
    
    public PolicySetDefaultsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYSETDEFAULTS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicySetDefaults");
    
    
    /**
     * Gets the "PolicySetDefaults" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType getPolicySetDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().find_element_user(POLICYSETDEFAULTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PolicySetDefaults" element
     */
    public void setPolicySetDefaults(os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType policySetDefaults)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().find_element_user(POLICYSETDEFAULTS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().add_element_user(POLICYSETDEFAULTS$0);
            }
            target.set(policySetDefaults);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicySetDefaults" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType addNewPolicySetDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().add_element_user(POLICYSETDEFAULTS$0);
            return target;
        }
    }
}
