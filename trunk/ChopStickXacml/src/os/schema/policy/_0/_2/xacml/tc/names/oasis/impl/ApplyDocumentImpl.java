/*
 * An XML document type.
 * Localname: Apply
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ApplyDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Apply(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ApplyDocumentImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionDocumentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ApplyDocument
{
    
    public ApplyDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName APPLY$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Apply");
    
    
    /**
     * Gets the "Apply" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType getApply()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType)get_store().find_element_user(APPLY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Apply" element
     */
    public void setApply(os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType apply)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType)get_store().find_element_user(APPLY$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType)get_store().add_element_user(APPLY$0);
            }
            target.set(apply);
        }
    }
    
    /**
     * Appends and returns a new empty "Apply" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType addNewApply()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType)get_store().add_element_user(APPLY$0);
            return target;
        }
    }
}
