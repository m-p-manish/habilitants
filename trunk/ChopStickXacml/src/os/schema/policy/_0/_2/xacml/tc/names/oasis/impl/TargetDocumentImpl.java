/*
 * An XML document type.
 * Localname: Target
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.TargetDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Target(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class TargetDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.TargetDocument
{
    
    public TargetDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName TARGET$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Target");
    
    
    /**
     * Gets the "Target" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.TargetType getTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().find_element_user(TARGET$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Target" element
     */
    public void setTarget(os.schema.policy._0._2.xacml.tc.names.oasis.TargetType targetValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().find_element_user(TARGET$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().add_element_user(TARGET$0);
            }
            target.set(targetValue);
        }
    }
    
    /**
     * Appends and returns a new empty "Target" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.TargetType addNewTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().add_element_user(TARGET$0);
            return target;
        }
    }
}
