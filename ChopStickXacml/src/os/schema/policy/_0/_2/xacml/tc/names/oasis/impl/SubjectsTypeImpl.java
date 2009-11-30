/*
 * XML Type:  SubjectsType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML SubjectsType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class SubjectsTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType
{
    
    public SubjectsTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Subject");
    
    
    /**
     * Gets array of all "Subject" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType[] getSubjectArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SUBJECT$0, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Subject" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType getSubjectArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType)get_store().find_element_user(SUBJECT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Subject" element
     */
    public int sizeOfSubjectArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SUBJECT$0);
        }
    }
    
    /**
     * Sets array of all "Subject" element
     */
    public void setSubjectArray(os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType[] subjectArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(subjectArray, SUBJECT$0);
        }
    }
    
    /**
     * Sets ith "Subject" element
     */
    public void setSubjectArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType subject)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType)get_store().find_element_user(SUBJECT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(subject);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Subject" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType insertNewSubject(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectType)get_store().insert_element_user(SUBJECT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Subject" element
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
    
    /**
     * Removes the ith "Subject" element
     */
    public void removeSubject(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SUBJECT$0, i);
        }
    }
}
