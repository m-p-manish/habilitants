/*
 * XML Type:  ResponseType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.ResponseType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ResponseType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public class ResponseTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.ResponseType
{
    
    public ResponseTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESULT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Result");
    
    
    /**
     * Gets array of all "Result" elements
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResultType[] getResultArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RESULT$0, targetList);
            os.schema.context._0._2.xacml.tc.names.oasis.ResultType[] result = new os.schema.context._0._2.xacml.tc.names.oasis.ResultType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Result" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResultType getResultArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResultType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResultType)get_store().find_element_user(RESULT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Result" element
     */
    public int sizeOfResultArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESULT$0);
        }
    }
    
    /**
     * Sets array of all "Result" element
     */
    public void setResultArray(os.schema.context._0._2.xacml.tc.names.oasis.ResultType[] resultArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(resultArray, RESULT$0);
        }
    }
    
    /**
     * Sets ith "Result" element
     */
    public void setResultArray(int i, os.schema.context._0._2.xacml.tc.names.oasis.ResultType result)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResultType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResultType)get_store().find_element_user(RESULT$0, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(result);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Result" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResultType insertNewResult(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResultType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResultType)get_store().insert_element_user(RESULT$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Result" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResultType addNewResult()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResultType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResultType)get_store().add_element_user(RESULT$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Result" element
     */
    public void removeResult(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESULT$0, i);
        }
    }
}
