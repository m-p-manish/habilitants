/*
 * An XML document type.
 * Localname: CombinerParameters
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one CombinerParameters(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class CombinerParametersDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersDocument
{
    
    public CombinerParametersDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMBINERPARAMETERS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "CombinerParameters");
    
    
    /**
     * Gets the "CombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType getCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().find_element_user(COMBINERPARAMETERS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CombinerParameters" element
     */
    public void setCombinerParameters(os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType combinerParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().find_element_user(COMBINERPARAMETERS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().add_element_user(COMBINERPARAMETERS$0);
            }
            target.set(combinerParameters);
        }
    }
    
    /**
     * Appends and returns a new empty "CombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType addNewCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().add_element_user(COMBINERPARAMETERS$0);
            return target;
        }
    }
}
