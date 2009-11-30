/*
 * An XML document type.
 * Localname: PolicyIdReference
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicyIdReferenceDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one PolicyIdReference(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class PolicyIdReferenceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicyIdReferenceDocument
{
    
    public PolicyIdReferenceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYIDREFERENCE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicyIdReference");
    
    
    /**
     * Gets the "PolicyIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType getPolicyIdReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().find_element_user(POLICYIDREFERENCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PolicyIdReference" element
     */
    public void setPolicyIdReference(os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType policyIdReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().find_element_user(POLICYIDREFERENCE$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().add_element_user(POLICYIDREFERENCE$0);
            }
            target.set(policyIdReference);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicyIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType addNewPolicyIdReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().add_element_user(POLICYIDREFERENCE$0);
            return target;
        }
    }
}
