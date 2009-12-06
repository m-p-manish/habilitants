/*
 * An XML document type.
 * Localname: Attribute
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.AttributeDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Attribute(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class AttributeDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.AttributeDocument
{
    
    public AttributeDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTE$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Attribute");
    
    
    /**
     * Gets the "Attribute" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeType getAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().find_element_user(ATTRIBUTE$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Attribute" element
     */
    public void setAttribute(os.schema.context._0._2.xacml.tc.names.oasis.AttributeType attribute)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().find_element_user(ATTRIBUTE$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().add_element_user(ATTRIBUTE$0);
            }
            target.set(attribute);
        }
    }
    
    /**
     * Appends and returns a new empty "Attribute" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.AttributeType addNewAttribute()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.AttributeType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.AttributeType)get_store().add_element_user(ATTRIBUTE$0);
            return target;
        }
    }
}
