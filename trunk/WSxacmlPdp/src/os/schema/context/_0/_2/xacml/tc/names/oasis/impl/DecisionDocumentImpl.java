/*
 * An XML document type.
 * Localname: Decision
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.DecisionDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one Decision(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class DecisionDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.DecisionDocument
{
    
    public DecisionDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DECISION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "Decision");
    
    
    /**
     * Gets the "Decision" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.DecisionType.Enum getDecision()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DECISION$0, 0);
            if (target == null)
            {
                return null;
            }
            return (os.schema.context._0._2.xacml.tc.names.oasis.DecisionType.Enum)target.getEnumValue();
        }
    }
    
    /**
     * Gets (as xml) the "Decision" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.DecisionType xgetDecision()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.DecisionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.DecisionType)get_store().find_element_user(DECISION$0, 0);
            return target;
        }
    }
    
    /**
     * Sets the "Decision" element
     */
    public void setDecision(os.schema.context._0._2.xacml.tc.names.oasis.DecisionType.Enum decision)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DECISION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DECISION$0);
            }
            target.setEnumValue(decision);
        }
    }
    
    /**
     * Sets (as xml) the "Decision" element
     */
    public void xsetDecision(os.schema.context._0._2.xacml.tc.names.oasis.DecisionType decision)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.DecisionType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.DecisionType)get_store().find_element_user(DECISION$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.DecisionType)get_store().add_element_user(DECISION$0);
            }
            target.set(decision);
        }
    }
}
