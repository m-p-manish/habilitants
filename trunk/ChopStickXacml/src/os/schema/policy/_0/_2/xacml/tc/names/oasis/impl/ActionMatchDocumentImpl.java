/*
 * An XML document type.
 * Localname: ActionMatch
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one ActionMatch(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ActionMatchDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchDocument
{
    
    public ActionMatchDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACTIONMATCH$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ActionMatch");
    
    
    /**
     * Gets the "ActionMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType getActionMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType)get_store().find_element_user(ACTIONMATCH$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ActionMatch" element
     */
    public void setActionMatch(os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType actionMatch)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType)get_store().find_element_user(ACTIONMATCH$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType)get_store().add_element_user(ACTIONMATCH$0);
            }
            target.set(actionMatch);
        }
    }
    
    /**
     * Appends and returns a new empty "ActionMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType addNewActionMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionMatchType)get_store().add_element_user(ACTIONMATCH$0);
            return target;
        }
    }
}
