/*
 * An XML document type.
 * Localname: PolicyDefaults
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicyDefaultsDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one PolicyDefaults(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class PolicyDefaultsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicyDefaultsDocument
{
    
    public PolicyDefaultsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYDEFAULTS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicyDefaults");
    
    
    /**
     * Gets the "PolicyDefaults" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType getPolicyDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().find_element_user(POLICYDEFAULTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PolicyDefaults" element
     */
    public void setPolicyDefaults(os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType policyDefaults)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().find_element_user(POLICYDEFAULTS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().add_element_user(POLICYDEFAULTS$0);
            }
            target.set(policyDefaults);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicyDefaults" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType addNewPolicyDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().add_element_user(POLICYDEFAULTS$0);
            return target;
        }
    }
}
