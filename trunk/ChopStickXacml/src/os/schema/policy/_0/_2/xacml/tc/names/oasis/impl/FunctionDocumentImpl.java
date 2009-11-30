/*
 * An XML document type.
 * Localname: Function
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.FunctionDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Function(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class FunctionDocumentImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionDocumentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.FunctionDocument
{
    
    public FunctionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FUNCTION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Function");
    
    
    /**
     * Gets the "Function" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType getFunction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType)get_store().find_element_user(FUNCTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Function" element
     */
    public void setFunction(os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType function)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType)get_store().find_element_user(FUNCTION$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType)get_store().add_element_user(FUNCTION$0);
            }
            target.set(function);
        }
    }
    
    /**
     * Appends and returns a new empty "Function" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType addNewFunction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType)get_store().add_element_user(FUNCTION$0);
            return target;
        }
    }
}
