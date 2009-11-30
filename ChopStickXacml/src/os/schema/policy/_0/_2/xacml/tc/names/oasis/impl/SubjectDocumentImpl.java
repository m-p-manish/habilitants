/*
 * An XML document type.
 * Localname: Subject
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.SubjectDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Subject(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class SubjectDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.SubjectDocument
{
    
    public SubjectDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Subject");
    
    
    /**
     * Gets the "Subject" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType getSubject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType)get_store().find_element_user(SUBJECT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Subject" element
     */
    public void setSubject(os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType subject)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType)get_store().find_element_user(SUBJECT$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType)get_store().add_element_user(SUBJECT$0);
            }
            target.set(subject);
        }
    }
    
    /**
     * Appends and returns a new empty "Subject" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType addNewSubject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType)get_store().add_element_user(SUBJECT$0);
            return target;
        }
    }
}
