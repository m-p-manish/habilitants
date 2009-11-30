/*
 * An XML document type.
 * Localname: CombinerParameter
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one CombinerParameter(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class CombinerParameterDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterDocument
{
    
    public CombinerParameterDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName COMBINERPARAMETER$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "CombinerParameter");
    
    
    /**
     * Gets the "CombinerParameter" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType getCombinerParameter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType)get_store().find_element_user(COMBINERPARAMETER$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "CombinerParameter" element
     */
    public void setCombinerParameter(os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType combinerParameter)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType)get_store().find_element_user(COMBINERPARAMETER$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType)get_store().add_element_user(COMBINERPARAMETER$0);
            }
            target.set(combinerParameter);
        }
    }
    
    /**
     * Appends and returns a new empty "CombinerParameter" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType addNewCombinerParameter()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParameterType)get_store().add_element_user(COMBINERPARAMETER$0);
            return target;
        }
    }
}
