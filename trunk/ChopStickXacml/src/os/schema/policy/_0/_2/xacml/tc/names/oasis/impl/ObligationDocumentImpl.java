/*
 * An XML document type.
 * Localname: Obligation
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ObligationDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Obligation(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ObligationDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ObligationDocument
{
    
    public ObligationDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OBLIGATION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Obligation");
    
    
    /**
     * Gets the "Obligation" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType getObligation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType)get_store().find_element_user(OBLIGATION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Obligation" element
     */
    public void setObligation(os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType obligation)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType)get_store().find_element_user(OBLIGATION$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType)get_store().add_element_user(OBLIGATION$0);
            }
            target.set(obligation);
        }
    }
    
    /**
     * Appends and returns a new empty "Obligation" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType addNewObligation()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationType)get_store().add_element_user(OBLIGATION$0);
            return target;
        }
    }
}
