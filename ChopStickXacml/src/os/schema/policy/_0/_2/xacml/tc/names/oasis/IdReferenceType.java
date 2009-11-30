/*
 * XML Type:  IdReferenceType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis;


/**
 * An XML IdReferenceType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is an atomic type that is a restriction of os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType.
 */
public interface IdReferenceType extends org.apache.xmlbeans.XmlAnyURI
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(IdReferenceType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s66018507165A80C1B966D93133E02489").resolveHandle("idreferencetypeb0detype");
    
    /**
     * Gets the "Version" attribute
     */
    java.lang.String getVersion();
    
    /**
     * Gets (as xml) the "Version" attribute
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType xgetVersion();
    
    /**
     * True if has "Version" attribute
     */
    boolean isSetVersion();
    
    /**
     * Sets the "Version" attribute
     */
    void setVersion(java.lang.String version);
    
    /**
     * Sets (as xml) the "Version" attribute
     */
    void xsetVersion(os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType version);
    
    /**
     * Unsets the "Version" attribute
     */
    void unsetVersion();
    
    /**
     * Gets the "EarliestVersion" attribute
     */
    java.lang.String getEarliestVersion();
    
    /**
     * Gets (as xml) the "EarliestVersion" attribute
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType xgetEarliestVersion();
    
    /**
     * True if has "EarliestVersion" attribute
     */
    boolean isSetEarliestVersion();
    
    /**
     * Sets the "EarliestVersion" attribute
     */
    void setEarliestVersion(java.lang.String earliestVersion);
    
    /**
     * Sets (as xml) the "EarliestVersion" attribute
     */
    void xsetEarliestVersion(os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType earliestVersion);
    
    /**
     * Unsets the "EarliestVersion" attribute
     */
    void unsetEarliestVersion();
    
    /**
     * Gets the "LatestVersion" attribute
     */
    java.lang.String getLatestVersion();
    
    /**
     * Gets (as xml) the "LatestVersion" attribute
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType xgetLatestVersion();
    
    /**
     * True if has "LatestVersion" attribute
     */
    boolean isSetLatestVersion();
    
    /**
     * Sets the "LatestVersion" attribute
     */
    void setLatestVersion(java.lang.String latestVersion);
    
    /**
     * Sets (as xml) the "LatestVersion" attribute
     */
    void xsetLatestVersion(os.schema.policy._0._2.xacml.tc.names.oasis.VersionMatchType latestVersion);
    
    /**
     * Unsets the "LatestVersion" attribute
     */
    void unsetLatestVersion();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType newInstance() {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
