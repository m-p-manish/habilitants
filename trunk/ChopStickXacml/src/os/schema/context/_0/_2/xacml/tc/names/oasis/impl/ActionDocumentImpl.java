/*
 * An XML document type.
 * Localname: Action
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.ActionDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Action(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class ActionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.ActionDocument
{
    
    public ActionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACTION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Action");
    
    
    /**
     * Gets the "Action" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ActionType getAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ActionType)get_store().find_element_user(ACTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Action" element
     */
    public void setAction(os.schema.context._0._2.xacml.tc.names.oasis.ActionType action)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ActionType)get_store().find_element_user(ACTION$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.ActionType)get_store().add_element_user(ACTION$0);
            }
            target.set(action);
        }
    }
    
    /**
     * Appends and returns a new empty "Action" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ActionType addNewAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ActionType)get_store().add_element_user(ACTION$0);
            return target;
        }
    }
}
