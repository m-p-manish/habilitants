/*
 * An XML document type.
 * Localname: AttributeValue
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one AttributeValue(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class AttributeValueDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueDocument
{
    
    public AttributeValueDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEVALUE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "AttributeValue");
    
    
    /**
     * Gets the "AttributeValue" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType getAttributeValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().find_element_user(ATTRIBUTEVALUE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttributeValue" element
     */
    public void setAttributeValue(os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType attributeValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().find_element_user(ATTRIBUTEVALUE$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().add_element_user(ATTRIBUTEVALUE$0);
            }
            target.set(attributeValue);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeValue" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType addNewAttributeValue()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType)get_store().add_element_user(ATTRIBUTEVALUE$0);
            return target;
        }
    }
}
