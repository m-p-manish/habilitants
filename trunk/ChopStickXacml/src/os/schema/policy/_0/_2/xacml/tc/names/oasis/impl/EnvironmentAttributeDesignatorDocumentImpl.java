/*
 * An XML document type.
 * Localname: EnvironmentAttributeDesignator
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentAttributeDesignatorDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one EnvironmentAttributeDesignator(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class EnvironmentAttributeDesignatorDocumentImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionDocumentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentAttributeDesignatorDocument
{
    
    public EnvironmentAttributeDesignatorDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ENVIRONMENTATTRIBUTEDESIGNATOR$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "EnvironmentAttributeDesignator");
    
    
    /**
     * Gets the "EnvironmentAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType getEnvironmentAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().find_element_user(ENVIRONMENTATTRIBUTEDESIGNATOR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "EnvironmentAttributeDesignator" element
     */
    public void setEnvironmentAttributeDesignator(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType environmentAttributeDesignator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().find_element_user(ENVIRONMENTATTRIBUTEDESIGNATOR$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().add_element_user(ENVIRONMENTATTRIBUTEDESIGNATOR$0);
            }
            target.set(environmentAttributeDesignator);
        }
    }
    
    /**
     * Appends and returns a new empty "EnvironmentAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType addNewEnvironmentAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType)get_store().add_element_user(ENVIRONMENTATTRIBUTEDESIGNATOR$0);
            return target;
        }
    }
}
