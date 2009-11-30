/*
 * An XML document type.
 * Localname: PolicySetIdReference
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetIdReferenceDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one PolicySetIdReference(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class PolicySetIdReferenceDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetIdReferenceDocument
{
    
    public PolicySetIdReferenceDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName POLICYSETIDREFERENCE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicySetIdReference");
    
    
    /**
     * Gets the "PolicySetIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType getPolicySetIdReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().find_element_user(POLICYSETIDREFERENCE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "PolicySetIdReference" element
     */
    public void setPolicySetIdReference(os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType policySetIdReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().find_element_user(POLICYSETIDREFERENCE$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().add_element_user(POLICYSETIDREFERENCE$0);
            }
            target.set(policySetIdReference);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicySetIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType addNewPolicySetIdReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().add_element_user(POLICYSETIDREFERENCE$0);
            return target;
        }
    }
}
