/*
 * An XML document type.
 * Localname: PolicySet
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one PolicySet(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class PolicySetDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetDocument
{
    
    public PolicySetDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYSET$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicySet");
    
    
    /**
     * Gets the "PolicySet" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType getPolicySet()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType)get_store().find_element_user(POLICYSET$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PolicySet" element
     */
    public void setPolicySet(os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType policySet)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType)get_store().find_element_user(POLICYSET$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType)get_store().add_element_user(POLICYSET$0);
            }
            target.set(policySet);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicySet" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType addNewPolicySet()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType)get_store().add_element_user(POLICYSET$0);
            return target;
        }
    }
}
