/*
 * XML Type:  MissingAttributeDetailType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis;


/**
 * An XML MissingAttributeDetailType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public interface MissingAttributeDetailType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(MissingAttributeDetailType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA4B507837A9209130051EA081A1B3E51").resolveHandle("missingattributedetailtype42c0type");
    
    /**
     * Gets array of all "AttributeValue" elements
     */
    os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType[] getAttributeValueArray();
    
    /**
     * Gets ith "AttributeValue" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType getAttributeValueArray(int i);
    
    /**
     * Returns number of "AttributeValue" element
     */
    int sizeOfAttributeValueArray();
    
    /**
     * Sets array of all "AttributeValue" element
     */
    void setAttributeValueArray(os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType[] attributeValueArray);
    
    /**
     * Sets ith "AttributeValue" element
     */
    void setAttributeValueArray(int i, os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType attributeValue);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "AttributeValue" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType insertNewAttributeValue(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "AttributeValue" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType addNewAttributeValue();
    
    /**
     * Removes the ith "AttributeValue" element
     */
    void removeAttributeValue(int i);
    
    /**
     * Gets the "AttributeId" attribute
     */
    java.lang.String getAttributeId();
    
    /**
     * Gets (as xml) the "AttributeId" attribute
     */
    org.apache.xmlbeans.XmlAnyURI xgetAttributeId();
    
    /**
     * Sets the "AttributeId" attribute
     */
    void setAttributeId(java.lang.String attributeId);
    
    /**
     * Sets (as xml) the "AttributeId" attribute
     */
    void xsetAttributeId(org.apache.xmlbeans.XmlAnyURI attributeId);
    
    /**
     * Gets the "DataType" attribute
     */
    java.lang.String getDataType();
    
    /**
     * Gets (as xml) the "DataType" attribute
     */
    org.apache.xmlbeans.XmlAnyURI xgetDataType();
    
    /**
     * Sets the "DataType" attribute
     */
    void setDataType(java.lang.String dataType);
    
    /**
     * Sets (as xml) the "DataType" attribute
     */
    void xsetDataType(org.apache.xmlbeans.XmlAnyURI dataType);
    
    /**
     * Gets the "Issuer" attribute
     */
    java.lang.String getIssuer();
    
    /**
     * Gets (as xml) the "Issuer" attribute
     */
    org.apache.xmlbeans.XmlString xgetIssuer();
    
    /**
     * True if has "Issuer" attribute
     */
    boolean isSetIssuer();
    
    /**
     * Sets the "Issuer" attribute
     */
    void setIssuer(java.lang.String issuer);
    
    /**
     * Sets (as xml) the "Issuer" attribute
     */
    void xsetIssuer(org.apache.xmlbeans.XmlString issuer);
    
    /**
     * Unsets the "Issuer" attribute
     */
    void unsetIssuer();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType newInstance() {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.MissingAttributeDetailType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
