/*
 * An XML document type.
 * Localname: Actions
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ActionsDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Actions(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ActionsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ActionsDocument
{
    
    public ActionsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACTIONS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Actions");
    
    
    /**
     * Gets the "Actions" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType getActions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType)get_store().find_element_user(ACTIONS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Actions" element
     */
    public void setActions(os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType actions)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType)get_store().find_element_user(ACTIONS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType)get_store().add_element_user(ACTIONS$0);
            }
            target.set(actions);
        }
    }
    
    /**
     * Appends and returns a new empty "Actions" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType addNewActions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType)get_store().add_element_user(ACTIONS$0);
            return target;
        }
    }
}
