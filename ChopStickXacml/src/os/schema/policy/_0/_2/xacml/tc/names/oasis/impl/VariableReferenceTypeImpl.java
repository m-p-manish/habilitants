/*
 * XML Type:  VariableReferenceType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML VariableReferenceType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class VariableReferenceTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.VariableReferenceType
{
    
    public VariableReferenceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName VARIABLEID$0 = 
        new javax.xml.namespace.QName("", "VariableId");
    
    
    /**
     * Gets the "VariableId" attribute
     */
    public java.lang.String getVariableId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VARIABLEID$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "VariableId" attribute
     */
    public org.apache.xmlbeans.XmlString xgetVariableId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(VARIABLEID$0);
            return target;
        }
    }
    
    /**
     * Sets the "VariableId" attribute
     */
    public void setVariableId(java.lang.String variableId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VARIABLEID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(VARIABLEID$0);
            }
            target.setStringValue(variableId);
        }
    }
    
    /**
     * Sets (as xml) the "VariableId" attribute
     */
    public void xsetVariableId(org.apache.xmlbeans.XmlString variableId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_attribute_user(VARIABLEID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_attribute_user(VARIABLEID$0);
            }
            target.set(variableId);
        }
    }
}
