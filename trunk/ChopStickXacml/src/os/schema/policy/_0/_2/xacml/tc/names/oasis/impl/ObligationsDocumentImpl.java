/*
 * An XML document type.
 * Localname: Obligations
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Obligations(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ObligationsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsDocument
{
    
    public ObligationsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName OBLIGATIONS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Obligations");
    
    
    /**
     * Gets the "Obligations" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType getObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().find_element_user(OBLIGATIONS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Obligations" element
     */
    public void setObligations(os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType obligations)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().find_element_user(OBLIGATIONS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().add_element_user(OBLIGATIONS$0);
            }
            target.set(obligations);
        }
    }
    
    /**
     * Appends and returns a new empty "Obligations" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType addNewObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().add_element_user(OBLIGATIONS$0);
            return target;
        }
    }
}
