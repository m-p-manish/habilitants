/*
 * An XML document type.
 * Localname: AttributeAssignment
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one AttributeAssignment(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class AttributeAssignmentDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentDocument
{
    
    public AttributeAssignmentDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName ATTRIBUTEASSIGNMENT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "AttributeAssignment");
    
    
    /**
     * Gets the "AttributeAssignment" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType getAttributeAssignment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType)get_store().find_element_user(ATTRIBUTEASSIGNMENT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "AttributeAssignment" element
     */
    public void setAttributeAssignment(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType attributeAssignment)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType)get_store().find_element_user(ATTRIBUTEASSIGNMENT$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType)get_store().add_element_user(ATTRIBUTEASSIGNMENT$0);
            }
            target.set(attributeAssignment);
        }
    }
    
    /**
     * Appends and returns a new empty "AttributeAssignment" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType addNewAttributeAssignment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.AttributeAssignmentType)get_store().add_element_user(ATTRIBUTEASSIGNMENT$0);
            return target;
        }
    }
}
