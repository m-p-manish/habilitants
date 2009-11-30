/*
 * An XML document type.
 * Localname: VariableReference
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one VariableReference(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class VariableReferenceDocumentImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionDocumentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceDocument
{
    
    public VariableReferenceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VARIABLEREFERENCE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "VariableReference");
    
    
    /**
     * Gets the "VariableReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType getVariableReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType)get_store().find_element_user(VARIABLEREFERENCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "VariableReference" element
     */
    public void setVariableReference(os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType variableReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType)get_store().find_element_user(VARIABLEREFERENCE$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType)get_store().add_element_user(VARIABLEREFERENCE$0);
            }
            target.set(variableReference);
        }
    }
    
    /**
     * Appends and returns a new empty "VariableReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType addNewVariableReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType)get_store().add_element_user(VARIABLEREFERENCE$0);
            return target;
        }
    }
}
