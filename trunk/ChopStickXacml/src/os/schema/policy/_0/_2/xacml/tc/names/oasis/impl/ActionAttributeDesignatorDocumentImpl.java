/*
 * An XML document type.
 * Localname: ActionAttributeDesignator
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one ActionAttributeDesignator(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ActionAttributeDesignatorDocumentImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionDocumentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument
{
    
    public ActionAttributeDesignatorDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ACTIONATTRIBUTEDESIGNATOR$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ActionAttributeDesignator");
    
    
    /**
     * Gets the "ActionAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType getActionAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().find_element_user(ACTIONATTRIBUTEDESIGNATOR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ActionAttributeDesignator" element
     */
    public void setActionAttributeDesignator(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType actionAttributeDesignator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().find_element_user(ACTIONATTRIBUTEDESIGNATOR$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().add_element_user(ACTIONATTRIBUTEDESIGNATOR$0);
            }
            target.set(actionAttributeDesignator);
        }
    }
    
    /**
     * Appends and returns a new empty "ActionAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType addNewActionAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().add_element_user(ACTIONATTRIBUTEDESIGNATOR$0);
            return target;
        }
    }
}
