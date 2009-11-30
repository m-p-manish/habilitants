/*
 * XML Type:  IdReferenceType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML IdReferenceType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is an atomic type that is a restriction of os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType.
 */
public class IdReferenceTypeImpl extends org.apache.xmlbeans.impl.values.JavaUriHolderEx implements os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType
{
    
    public IdReferenceTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType, true);
    }
    
    protected IdReferenceTypeImpl(org.apache.xmlbeans.SchemaType sType, boolean b)
    {
        super(sType, b);
    }
    
    private static final javax.xml.namespace.QName VERSION$0 = 
        new javax.xml.namespace.QName("", "Version");
    private static final javax.xml.namespace.QName EARLIESTVERSION$2 = 
        new javax.xml.namespace.QName("", "EarliestVersion");
    private static final javax.xml.namespace.QName LATESTVERSION$4 = 
        new javax.xml.namespace.QName("", "LatestVersion");
    
    
    /**
     * Gets the "Version" attribute
     */
    public java.lang.String getVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VERSION$0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Version" attribute
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType xgetVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().find_attribute_user(VERSION$0);
            return target;
        }
    }
    
    /**
     * True if has "Version" attribute
     */
    public boolean isSetVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(VERSION$0) != null;
        }
    }
    
    /**
     * Sets the "Version" attribute
     */
    public void setVersion(java.lang.String version)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VERSION$0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(VERSION$0);
            }
            target.setStringValue(version);
        }
    }
    
    /**
     * Sets (as xml) the "Version" attribute
     */
    public void xsetVersion(os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType version)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().find_attribute_user(VERSION$0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().add_attribute_user(VERSION$0);
            }
            target.set(version);
        }
    }
    
    /**
     * Unsets the "Version" attribute
     */
    public void unsetVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(VERSION$0);
        }
    }
    
    /**
     * Gets the "EarliestVersion" attribute
     */
    public java.lang.String getEarliestVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(EARLIESTVERSION$2);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "EarliestVersion" attribute
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType xgetEarliestVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().find_attribute_user(EARLIESTVERSION$2);
            return target;
        }
    }
    
    /**
     * True if has "EarliestVersion" attribute
     */
    public boolean isSetEarliestVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(EARLIESTVERSION$2) != null;
        }
    }
    
    /**
     * Sets the "EarliestVersion" attribute
     */
    public void setEarliestVersion(java.lang.String earliestVersion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(EARLIESTVERSION$2);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(EARLIESTVERSION$2);
            }
            target.setStringValue(earliestVersion);
        }
    }
    
    /**
     * Sets (as xml) the "EarliestVersion" attribute
     */
    public void xsetEarliestVersion(os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType earliestVersion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().find_attribute_user(EARLIESTVERSION$2);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().add_attribute_user(EARLIESTVERSION$2);
            }
            target.set(earliestVersion);
        }
    }
    
    /**
     * Unsets the "EarliestVersion" attribute
     */
    public void unsetEarliestVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(EARLIESTVERSION$2);
        }
    }
    
    /**
     * Gets the "LatestVersion" attribute
     */
    public java.lang.String getLatestVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LATESTVERSION$4);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "LatestVersion" attribute
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType xgetLatestVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().find_attribute_user(LATESTVERSION$4);
            return target;
        }
    }
    
    /**
     * True if has "LatestVersion" attribute
     */
    public boolean isSetLatestVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().find_attribute_user(LATESTVERSION$4) != null;
        }
    }
    
    /**
     * Sets the "LatestVersion" attribute
     */
    public void setLatestVersion(java.lang.String latestVersion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(LATESTVERSION$4);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(LATESTVERSION$4);
            }
            target.setStringValue(latestVersion);
        }
    }
    
    /**
     * Sets (as xml) the "LatestVersion" attribute
     */
    public void xsetLatestVersion(os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType latestVersion)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().find_attribute_user(LATESTVERSION$4);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType)get_store().add_attribute_user(LATESTVERSION$4);
            }
            target.set(latestVersion);
        }
    }
    
    /**
     * Unsets the "LatestVersion" attribute
     */
    public void unsetLatestVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_attribute(LATESTVERSION$4);
        }
    }
}
