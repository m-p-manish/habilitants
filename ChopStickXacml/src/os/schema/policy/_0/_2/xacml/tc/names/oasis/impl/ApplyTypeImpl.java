/*
 * XML Type:  ApplyType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ApplyType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ApplyTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ApplyType
{
    
    public ApplyTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName EXPRESSION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Expression");
    private static final org.apache.xmlbeans.QNameSet EXPRESSION$1 = org.apache.xmlbeans.QNameSet.forArray( new javax.xml.namespace.QName[] { 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "AttributeSelector"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "AttributeValue"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ActionAttributeDesignator"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "ResourceAttributeDesignator"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Apply"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Expression"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Function"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "VariableReference"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "EnvironmentAttributeDesignator"),
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "SubjectAttributeDesignator"),
    });
    private static final javax.xml.namespace.QName FUNCTIONID$2 = 
        new javax.xml.namespace.QName("", "FunctionId");
    
    
    /**
     * Gets array of all "Expression" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType[] getExpressionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(EXPRESSION$1, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Expression" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType getExpressionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType)get_store().find_element_user(EXPRESSION$1, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Expression" element
     */
    public int sizeOfExpressionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(EXPRESSION$1);
        }
    }
    
    /**
     * Sets array of all "Expression" element
     */
    public void setExpressionArray(os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType[] expressionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(expressionArray, EXPRESSION$0, EXPRESSION$1);
        }
    }
    
    /**
     * Sets ith "Expression" element
     */
    public void setExpressionArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType expression)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType)get_store().find_element_user(EXPRESSION$1, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(expression);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Expression" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType insertNewExpression(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType)get_store().insert_element_user(EXPRESSION$1, EXPRESSION$0, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Expression" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType addNewExpression()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType)get_store().add_element_user(EXPRESSION$0);
            return target;
        }
    }
    
    /**
     * Removes the ith "Expression" element
     */
    public void removeExpression(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(EXPRESSION$1, i);
        }
    }
    
    /**
     * Gets the "FunctionId" attribute
     */
    public java.lang.String getFunctionId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FUNCTIONID$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "FunctionId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetFunctionId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(FUNCTIONID$2);
            return target;
        }
    }
    
    /**
     * Sets the "FunctionId" attribute
     */
    public void setFunctionId(java.lang.String functionId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FUNCTIONID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(FUNCTIONID$2);
            }
            target.setStringValue(functionId);
        }
    }
    
    /**
     * Sets (as xml) the "FunctionId" attribute
     */
    public void xsetFunctionId(org.apache.xmlbeans.XmlAnyURI functionId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(FUNCTIONID$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(FUNCTIONID$2);
            }
            target.set(functionId);
        }
    }
}
