/*
 * An XML document type.
 * Localname: MissingAttributeDetail
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis.impl;
/**
 * A document containing one MissingAttributeDetail(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public class MissingAttributeDetailDocumentImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailDocument
{
    
    public MissingAttributeDetailDocumentImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName MISSINGATTRIBUTEDETAIL$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:context:schema:os", "MissingAttributeDetail");
    
    
    /**
     * Gets the "MissingAttributeDetail" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType getMissingAttributeDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType)get_store().find_element_user(MISSINGATTRIBUTEDETAIL$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "MissingAttributeDetail" element
     */
    public void setMissingAttributeDetail(os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType missingAttributeDetail)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType)get_store().find_element_user(MISSINGATTRIBUTEDETAIL$0, 0);
            if (target == null)
            {
                target = (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType)get_store().add_element_user(MISSINGATTRIBUTEDETAIL$0);
            }
            target.set(missingAttributeDetail);
        }
    }
    
    /**
     * Appends and returns a new empty "MissingAttributeDetail" element
     */
    public os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType addNewMissingAttributeDetail()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType target = null;
            target = (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType)get_store().add_element_user(MISSINGATTRIBUTEDETAIL$0);
            return target;
        }
    }
}
