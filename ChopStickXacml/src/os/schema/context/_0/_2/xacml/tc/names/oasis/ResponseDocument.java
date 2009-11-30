/*
 * An XML document type.
 * Localname: Response
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis;


/**
 * A document containing one Response(@urn:oasis:names:tc:xacml:2.0:context:schema:os) element.
 *
 * This is a complex type.
 */
public interface ResponseDocument extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ResponseDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s66018507165A80C1B966D93133E02489").resolveHandle("response61d0doctype");
    
    /**
     * Gets the "Response" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.ResponseType getResponse();
    
    /**
     * Sets the "Response" element
     */
    void setResponse(os.schema.context._0._2.xacml.tc.names.oasis.ResponseType response);
    
    /**
     * Appends and returns a new empty "Response" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.ResponseType addNewResponse();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument newInstance() {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
