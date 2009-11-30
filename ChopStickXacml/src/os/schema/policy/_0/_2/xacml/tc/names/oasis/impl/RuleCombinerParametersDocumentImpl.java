/*
 * An XML document type.
 * Localname: RuleCombinerParameters
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one RuleCombinerParameters(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class RuleCombinerParametersDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersDocument
{
    
    public RuleCombinerParametersDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RULECOMBINERPARAMETERS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "RuleCombinerParameters");
    
    
    /**
     * Gets the "RuleCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType getRuleCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType)get_store().find_element_user(RULECOMBINERPARAMETERS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "RuleCombinerParameters" element
     */
    public void setRuleCombinerParameters(os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType ruleCombinerParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType)get_store().find_element_user(RULECOMBINERPARAMETERS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType)get_store().add_element_user(RULECOMBINERPARAMETERS$0);
            }
            target.set(ruleCombinerParameters);
        }
    }
    
    /**
     * Appends and returns a new empty "RuleCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType addNewRuleCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType)get_store().add_element_user(RULECOMBINERPARAMETERS$0);
            return target;
        }
    }
}
