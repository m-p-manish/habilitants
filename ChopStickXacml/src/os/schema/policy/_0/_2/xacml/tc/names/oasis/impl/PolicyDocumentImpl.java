/*
 * An XML document type.
 * Localname: Policy
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicyDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Policy(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class PolicyDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicyDocument
{
    
    public PolicyDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICY$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Policy");
    
    
    /**
     * Gets the "Policy" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType getPolicy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType)get_store().find_element_user(POLICY$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Policy" element
     */
    public void setPolicy(os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType policy)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType)get_store().find_element_user(POLICY$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType)get_store().add_element_user(POLICY$0);
            }
            target.set(policy);
        }
    }
    
    /**
     * Appends and returns a new empty "Policy" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType addNewPolicy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType)get_store().add_element_user(POLICY$0);
            return target;
        }
    }
}
