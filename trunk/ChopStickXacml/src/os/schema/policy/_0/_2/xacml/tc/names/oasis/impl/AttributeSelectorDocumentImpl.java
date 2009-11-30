/*
 * An XML document type.
 * Localname: AttributeSelector
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one AttributeSelector(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class AttributeSelectorDocumentImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionDocumentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorDocument
{
    
    public AttributeSelectorDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTESELECTOR$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "AttributeSelector");
    
    
    /**
     * Gets the "AttributeSelector" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType getAttributeSelector()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType)get_store().find_element_user(ATTRIBUTESELECTOR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttributeSelector" element
     */
    public void setAttributeSelector(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType attributeSelector)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType)get_store().find_element_user(ATTRIBUTESELECTOR$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType)get_store().add_element_user(ATTRIBUTESELECTOR$0);
            }
            target.set(attributeSelector);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeSelector" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType addNewAttributeSelector()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeSelectorType)get_store().add_element_user(ATTRIBUTESELECTOR$0);
            return target;
        }
    }
}
