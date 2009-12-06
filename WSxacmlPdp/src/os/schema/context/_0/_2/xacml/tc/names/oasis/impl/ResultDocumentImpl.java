/*
 * An XML document type.
 * Localname: Result
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.ResultDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Result(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class ResultDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.ResultDocument
{
    
    public ResultDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName RESULT$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Result");
    
    
    /**
     * Gets the "Result" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.ResultType getResult()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResultType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResultType)get_store().find_element_user(RESULT$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Result" element
     */
    public void setResult(os.schema.context._0._2.xacml.tc.names.oasis.ResultType result)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.ResultType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.ResultType)get_store().find_element_user(RESULT$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.ResultType)get_store().add_element_user(RESULT$0);
            }
            target.set(result);
        }
    }
    
    /**
     * Appends and returns a new empty "Result" element
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
}
