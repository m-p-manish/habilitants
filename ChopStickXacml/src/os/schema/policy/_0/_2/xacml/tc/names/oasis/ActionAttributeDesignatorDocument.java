/*
 * An XML document type.
 * Localname: ActionAttributeDesignator
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis;


/**
 * A document containing one ActionAttributeDesignator(@urn:oasis:names:tc:xacml:2.0:policy:schema:os) element.
 *
 * This is a complex type.
 */
public interface ActionAttributeDesignatorDocument extends os.schema.policy._0._2.xacml.tc.names.oasis.ExpressionDocument
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(ActionAttributeDesignatorDocument.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s66018507165A80C1B966D93133E02489").resolveHandle("actionattributedesignator01fedoctype");
    
    /**
     * Gets the "ActionAttributeDesignator" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType getActionAttributeDesignator();
    
    /**
     * Sets the "ActionAttributeDesignator" element
     */
    void setActionAttributeDesignator(os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType actionAttributeDesignator);
    
    /**
     * Appends and returns a new empty "ActionAttributeDesignator" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.AttributeDesignatorType addNewActionAttributeDesignator();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument newInstance() {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.ActionAttributeDesignatorDocument) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
