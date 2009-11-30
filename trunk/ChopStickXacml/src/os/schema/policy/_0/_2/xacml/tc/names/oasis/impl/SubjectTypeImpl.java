/*
 * XML Type:  SubjectType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML SubjectType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class SubjectTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType
{
    
    public SubjectTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECTMATCH$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "SubjectMatch");
    
    
    /**
     * Gets array of all "SubjectMatch" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType[] getSubjectMatchArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SUBJECTMATCH$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "SubjectMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType getSubjectMatchArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType)get_store().find_element_user(SUBJECTMATCH$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "SubjectMatch" element
     */
    public int sizeOfSubjectMatchArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SUBJECTMATCH$0);
        }
    }
    
    /**
     * Sets array of all "SubjectMatch" element
     */
    public void setSubjectMatchArray(os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType[] subjectMatchArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(subjectMatchArray, SUBJECTMATCH$0);
        }
    }
    
    /**
     * Sets ith "SubjectMatch" element
     */
    public void setSubjectMatchArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType subjectMatch)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType)get_store().find_element_user(SUBJECTMATCH$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(subjectMatch);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "SubjectMatch" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType insertNewSubjectMatch(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectMatchType)get_store().insert_element_user(SUBJECTMATCH$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "SubjectMatch" element
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
    
    /**
     * Removes the ith "SubjectMatch" element
     */
    public void removeSubjectMatch(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SUBJECTMATCH$0, i);
        }
    }
}
