/*
 * An XML document type.
 * Localname: SubjectMatch
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one SubjectMatch(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class SubjectMatchDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchDocument
{
    
    public SubjectMatchDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECTMATCH$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "SubjectMatch");
    
    
    /**
     * Gets the "SubjectMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType getSubjectMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType)get_store().find_element_user(SUBJECTMATCH$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "SubjectMatch" element
     */
    public void setSubjectMatch(os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType subjectMatch)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType)get_store().find_element_user(SUBJECTMATCH$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType)get_store().add_element_user(SUBJECTMATCH$0);
            }
            target.set(subjectMatch);
        }
    }
    
    /**
     * Appends and returns a new empty "SubjectMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType addNewSubjectMatch()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType)get_store().add_element_user(SUBJECTMATCH$0);
            return target;
        }
    }
}
