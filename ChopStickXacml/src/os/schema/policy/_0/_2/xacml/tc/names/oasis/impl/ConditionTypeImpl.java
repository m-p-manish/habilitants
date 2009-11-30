/*
 * XML Type:  ConditionType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML ConditionType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class ConditionTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType
{
    
    public ConditionTypeImpl(org.apache.xmlbeans.SchemaType sType)
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
    
    
    /**
     * Gets the "Expression" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType getExpression()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType)get_store().find_element_user(EXPRESSION$1, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Expression" element
     */
    public void setExpression(os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType expression)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType)get_store().find_element_user(EXPRESSION$1, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionType)get_store().add_element_user(EXPRESSION$0);
            }
            target.set(expression);
        }
    }
    
    /**
     * Appends and returns a new empty "Expression" element
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
}
