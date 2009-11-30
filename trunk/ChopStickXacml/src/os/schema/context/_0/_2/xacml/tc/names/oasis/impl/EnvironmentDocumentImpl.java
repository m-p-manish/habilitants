/*
 * An XML document type.
 * Localname: Environment
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Environment(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class EnvironmentDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentDocument
{
    
    public EnvironmentDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENVIRONMENT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Environment");
    
    
    /**
     * Gets the "Environment" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType getEnvironment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().find_element_user(ENVIRONMENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Environment" element
     */
    public void setEnvironment(os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType environment)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().find_element_user(ENVIRONMENT$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().add_element_user(ENVIRONMENT$0);
            }
            target.set(environment);
        }
    }
    
    /**
     * Appends and returns a new empty "Environment" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType addNewEnvironment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().add_element_user(ENVIRONMENT$0);
            return target;
        }
    }
}
