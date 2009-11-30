/*
 * An XML document type.
 * Localname: ResourceAttributeDesignator
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ResourceAttributeDesignatorDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one ResourceAttributeDesignator(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ResourceAttributeDesignatorDocumentImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionDocumentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ResourceAttributeDesignatorDocument
{
    
    public ResourceAttributeDesignatorDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESOURCEATTRIBUTEDESIGNATOR$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ResourceAttributeDesignator");
    
    
    /**
     * Gets the "ResourceAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType getResourceAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().find_element_user(RESOURCEATTRIBUTEDESIGNATOR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "ResourceAttributeDesignator" element
     */
    public void setResourceAttributeDesignator(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType resourceAttributeDesignator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().find_element_user(RESOURCEATTRIBUTEDESIGNATOR$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().add_element_user(RESOURCEATTRIBUTEDESIGNATOR$0);
            }
            target.set(resourceAttributeDesignator);
        }
    }
    
    /**
     * Appends and returns a new empty "ResourceAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType addNewResourceAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().add_element_user(RESOURCEATTRIBUTEDESIGNATOR$0);
            return target;
        }
    }
}
