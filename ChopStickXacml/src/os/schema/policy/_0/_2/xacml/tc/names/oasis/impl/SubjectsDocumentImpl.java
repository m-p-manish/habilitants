/*
 * An XML document type.
 * Localname: Subjects
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Subjects(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class SubjectsDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsDocument
{
    
    public SubjectsDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECTS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Subjects");
    
    
    /**
     * Gets the "Subjects" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType getSubjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType)get_store().find_element_user(SUBJECTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Subjects" element
     */
    public void setSubjects(os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType subjects)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType)get_store().find_element_user(SUBJECTS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType)get_store().add_element_user(SUBJECTS$0);
            }
            target.set(subjects);
        }
    }
    
    /**
     * Appends and returns a new empty "Subjects" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType addNewSubjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType)get_store().add_element_user(SUBJECTS$0);
            return target;
        }
    }
}
