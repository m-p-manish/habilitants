/*
 * XML Type:  FunctionType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML FunctionType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class FunctionTypeImpl extends os.schema.policy._0._2.xacml.tc.names.oasis.impl.ExpressionTypeImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.FunctionType
{
    
    public FunctionTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName FUNCTIONID$0 = 
        new javax.xml.namespace.QName("", "FunctionId");
    
    
    /**
     * Gets the "FunctionId" attribute
     */
    public java.lang.String getFunctionId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FUNCTIONID$0);
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
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(FUNCTIONID$0);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(FUNCTIONID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(FUNCTIONID$0);
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
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(FUNCTIONID$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(FUNCTIONID$0);
            }
            target.set(functionId);
        }
    }
}
