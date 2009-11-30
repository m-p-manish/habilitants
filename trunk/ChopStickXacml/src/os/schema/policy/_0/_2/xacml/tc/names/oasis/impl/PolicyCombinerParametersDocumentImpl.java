/*
 * An XML document type.
 * Localname: PolicyCombinerParameters
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one PolicyCombinerParameters(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class PolicyCombinerParametersDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersDocument
{
    
    public PolicyCombinerParametersDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYCOMBINERPARAMETERS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicyCombinerParameters");
    
    
    /**
     * Gets the "PolicyCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType getPolicyCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType)get_store().find_element_user(POLICYCOMBINERPARAMETERS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PolicyCombinerParameters" element
     */
    public void setPolicyCombinerParameters(os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType policyCombinerParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType)get_store().find_element_user(POLICYCOMBINERPARAMETERS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType)get_store().add_element_user(POLICYCOMBINERPARAMETERS$0);
            }
            target.set(policyCombinerParameters);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicyCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType addNewPolicyCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType)get_store().add_element_user(POLICYCOMBINERPARAMETERS$0);
            return target;
        }
    }
}
