/*
 * An XML document type.
 * Localname: Environments
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Environments(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class EnvironmentsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsDocument
{
    
    public EnvironmentsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENVIRONMENTS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Environments");
    
    
    /**
     * Gets the "Environments" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType getEnvironments()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType)get_store().find_element_user(ENVIRONMENTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Environments" element
     */
    public void setEnvironments(os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType environments)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType)get_store().find_element_user(ENVIRONMENTS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType)get_store().add_element_user(ENVIRONMENTS$0);
            }
            target.set(environments);
        }
    }
    
    /**
     * Appends and returns a new empty "Environments" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType addNewEnvironments()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType)get_store().add_element_user(ENVIRONMENTS$0);
            return target;
        }
    }
}
