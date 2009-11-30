/*
 * An XML document type.
 * Localname: Condition
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ConditionDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Condition(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public class ConditionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.ConditionDocument
{
    
    public ConditionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName CONDITION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Condition");
    
    
    /**
     * Gets the "Condition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType getCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType)get_store().find_element_user(CONDITION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Condition" element
     */
    public void setCondition(os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType condition)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType)get_store().find_element_user(CONDITION$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType)get_store().add_element_user(CONDITION$0);
            }
            target.set(condition);
        }
    }
    
    /**
     * Appends and returns a new empty "Condition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType addNewCondition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ConditionType)get_store().add_element_user(CONDITION$0);
            return target;
        }
    }
}
