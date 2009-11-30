/*
 * An XML document type.
 * Localname: VariableDefinition
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one VariableDefinition(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class VariableDefinitionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionDocument
{
    
    public VariableDefinitionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VARIABLEDEFINITION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "VariableDefinition");
    
    
    /**
     * Gets the "VariableDefinition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType getVariableDefinition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType)get_store().find_element_user(VARIABLEDEFINITION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "VariableDefinition" element
     */
    public void setVariableDefinition(os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType variableDefinition)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType)get_store().find_element_user(VARIABLEDEFINITION$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType)get_store().add_element_user(VARIABLEDEFINITION$0);
            }
            target.set(variableDefinition);
        }
    }
    
    /**
     * Appends and returns a new empty "VariableDefinition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType addNewVariableDefinition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType)get_store().add_element_user(VARIABLEDEFINITION$0);
            return target;
        }
    }
}
