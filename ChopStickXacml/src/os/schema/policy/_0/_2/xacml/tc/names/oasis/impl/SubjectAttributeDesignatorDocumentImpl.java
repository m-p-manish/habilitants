/*
 * An XML document type.
 * Localname: SubjectAttributeDesignator
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one SubjectAttributeDesignator(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class SubjectAttributeDesignatorDocumentImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionDocumentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorDocument
{
    
    public SubjectAttributeDesignatorDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECTATTRIBUTEDESIGNATOR$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "SubjectAttributeDesignator");
    
    
    /**
     * Gets the "SubjectAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType getSubjectAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType)get_store().find_element_user(SUBJECTATTRIBUTEDESIGNATOR$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SubjectAttributeDesignator" element
     */
    public void setSubjectAttributeDesignator(os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType subjectAttributeDesignator)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType)get_store().find_element_user(SUBJECTATTRIBUTEDESIGNATOR$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType)get_store().add_element_user(SUBJECTATTRIBUTEDESIGNATOR$0);
            }
            target.set(subjectAttributeDesignator);
        }
    }
    
    /**
     * Appends and returns a new empty "SubjectAttributeDesignator" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType addNewSubjectAttributeDesignator()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectAttributeDesignatorType)get_store().add_element_user(SUBJECTATTRIBUTEDESIGNATOR$0);
            return target;
        }
    }
}
