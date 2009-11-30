/*
 * An XML document type.
 * Localname: PolicySetCombinerParameters
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one PolicySetCombinerParameters(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class PolicySetCombinerParametersDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersDocument
{
    
    public PolicySetCombinerParametersDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYSETCOMBINERPARAMETERS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicySetCombinerParameters");
    
    
    /**
     * Gets the "PolicySetCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType getPolicySetCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType)get_store().find_element_user(POLICYSETCOMBINERPARAMETERS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PolicySetCombinerParameters" element
     */
    public void setPolicySetCombinerParameters(os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType policySetCombinerParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType)get_store().find_element_user(POLICYSETCOMBINERPARAMETERS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType)get_store().add_element_user(POLICYSETCOMBINERPARAMETERS$0);
            }
            target.set(policySetCombinerParameters);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicySetCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType addNewPolicySetCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType)get_store().add_element_user(POLICYSETCOMBINERPARAMETERS$0);
            return target;
        }
    }
}
