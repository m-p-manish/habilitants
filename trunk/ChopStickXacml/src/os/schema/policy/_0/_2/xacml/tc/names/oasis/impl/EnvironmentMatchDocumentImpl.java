/*
 * An XML document type.
 * Localname: EnvironmentMatch
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one EnvironmentMatch(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class EnvironmentMatchDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchDocument
{
    
    public EnvironmentMatchDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENVIRONMENTMATCH$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "EnvironmentMatch");
    
    
    /**
     * Gets the "EnvironmentMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType getEnvironmentMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType)get_store().find_element_user(ENVIRONMENTMATCH$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "EnvironmentMatch" element
     */
    public void setEnvironmentMatch(os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType environmentMatch)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType)get_store().find_element_user(ENVIRONMENTMATCH$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType)get_store().add_element_user(ENVIRONMENTMATCH$0);
            }
            target.set(environmentMatch);
        }
    }
    
    /**
     * Appends and returns a new empty "EnvironmentMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType addNewEnvironmentMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentMatchType)get_store().add_element_user(ENVIRONMENTMATCH$0);
            return target;
        }
    }
}
