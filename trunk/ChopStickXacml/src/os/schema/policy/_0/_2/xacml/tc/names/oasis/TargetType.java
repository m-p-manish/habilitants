/*
 * XML Type:  TargetType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.TargetType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis;


/**
 * An XML TargetType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public interface TargetType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(TargetType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s66018507165A80C1B966D93133E02489").resolveHandle("targettype4931type");
    
    /**
     * Gets the "Subjects" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType getSubjects();
    
    /**
     * True if has "Subjects" element
     */
    boolean isSetSubjects();
    
    /**
     * Sets the "Subjects" element
     */
    void setSubjects(os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType subjects);
    
    /**
     * Appends and returns a new empty "Subjects" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType addNewSubjects();
    
    /**
     * Unsets the "Subjects" element
     */
    void unsetSubjects();
    
    /**
     * Gets the "Resources" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType getResources();
    
    /**
     * True if has "Resources" element
     */
    boolean isSetResources();
    
    /**
     * Sets the "Resources" element
     */
    void setResources(os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType resources);
    
    /**
     * Appends and returns a new empty "Resources" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType addNewResources();
    
    /**
     * Unsets the "Resources" element
     */
    void unsetResources();
    
    /**
     * Gets the "Actions" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType getActions();
    
    /**
     * True if has "Actions" element
     */
    boolean isSetActions();
    
    /**
     * Sets the "Actions" element
     */
    void setActions(os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType actions);
    
    /**
     * Appends and returns a new empty "Actions" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType addNewActions();
    
    /**
     * Unsets the "Actions" element
     */
    void unsetActions();
    
    /**
     * Gets the "Environments" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType getEnvironments();
    
    /**
     * True if has "Environments" element
     */
    boolean isSetEnvironments();
    
    /**
     * Sets the "Environments" element
     */
    void setEnvironments(os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType environments);
    
    /**
     * Appends and returns a new empty "Environments" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType addNewEnvironments();
    
    /**
     * Unsets the "Environments" element
     */
    void unsetEnvironments();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType newInstance() {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.TargetType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
