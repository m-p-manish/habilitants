/*
 * XML Type:  RequestType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.RequestType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML RequestType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public class RequestTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.RequestType
{
    
    public RequestTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Subject");
    private static final javax.xml.namespace.QName RESOURCE$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Resource");
    private static final javax.xml.namespace.QName ACTION$4 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Action");
    private static final javax.xml.namespace.QName ENVIRONMENT$6 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Environment");
    
    
    /**
     * Gets array of all "Subject" elements
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.SubjectType[] getSubjectArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(SUBJECT$0, targetList);
            os.schema.context._0._2.xacml.tc.names.oasis.SubjectType[] result = new os.schema.context._0._2.xacml.tc.names.oasis.SubjectType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Subject" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.SubjectType getSubjectArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.SubjectType)get_store().find_element_user(SUBJECT$0, i);
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
    public void setSubjectArray(os.schema.context._0._2.xacml.tc.names.oasis.SubjectType[] subjectArray)
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
    public void setSubjectArray(int i, os.schema.context._0._2.xacml.tc.names.oasis.SubjectType subject)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.SubjectType)get_store().find_element_user(SUBJECT$0, i);
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
    public os.schema.context._0._2.xacml.tc.names.oasis.SubjectType insertNewSubject(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.SubjectType)get_store().insert_element_user(SUBJECT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Subject" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.SubjectType addNewSubject()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.SubjectType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.SubjectType)get_store().add_element_user(SUBJECT$0);
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
    
    /**
     * Gets array of all "Resource" elements
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResourceType[] getResourceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RESOURCE$2, targetList);
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceType[] result = new os.schema.context._0._2.xacml.tc.names.oasis.ResourceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Resource" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResourceType getResourceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceType)get_store().find_element_user(RESOURCE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Resource" element
     */
    public int sizeOfResourceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESOURCE$2);
        }
    }
    
    /**
     * Sets array of all "Resource" element
     */
    public void setResourceArray(os.schema.context._0._2.xacml.tc.names.oasis.ResourceType[] resourceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(resourceArray, RESOURCE$2);
        }
    }
    
    /**
     * Sets ith "Resource" element
     */
    public void setResourceArray(int i, os.schema.context._0._2.xacml.tc.names.oasis.ResourceType resource)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceType)get_store().find_element_user(RESOURCE$2, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(resource);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Resource" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResourceType insertNewResource(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceType)get_store().insert_element_user(RESOURCE$2, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Resource" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResourceType addNewResource()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResourceType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResourceType)get_store().add_element_user(RESOURCE$2);
            return target;
        }
    }
    
    /**
     * Removes the ith "Resource" element
     */
    public void removeResource(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESOURCE$2, i);
        }
    }
    
    /**
     * Gets the "Action" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ActionType getAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ActionType)get_store().find_element_user(ACTION$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Action" element
     */
    public void setAction(os.schema.context._0._2.xacml.tc.names.oasis.ActionType action)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ActionType)get_store().find_element_user(ACTION$4, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.ActionType)get_store().add_element_user(ACTION$4);
            }
            target.set(action);
        }
    }
    
    /**
     * Appends and returns a new empty "Action" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ActionType addNewAction()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ActionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ActionType)get_store().add_element_user(ACTION$4);
            return target;
        }
    }
    
    /**
     * Gets the "Environment" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType getEnvironment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().find_element_user(ENVIRONMENT$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Environment" element
     */
    public void setEnvironment(os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType environment)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().find_element_user(ENVIRONMENT$6, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().add_element_user(ENVIRONMENT$6);
            }
            target.set(environment);
        }
    }
    
    /**
     * Appends and returns a new empty "Environment" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType addNewEnvironment()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType)get_store().add_element_user(ENVIRONMENT$6);
            return target;
        }
    }
}
