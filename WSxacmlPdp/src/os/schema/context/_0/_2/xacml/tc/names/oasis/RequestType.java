/*
 * XML Type:  RequestType
 * Namespace: urn:oasis:names:tc:xacml:2.0:context:schema:os
 * Java type: os.schema.context._0._2.xacml.tc.names.oasis.RequestType
 *
 * Automatically generated - do not modify.
 */
package os.schema.context._0._2.xacml.tc.names.oasis;


/**
 * An XML RequestType(@urn:oasis:names:tc:xacml:2.0:context:schema:os).
 *
 * This is a complex type.
 */
public interface RequestType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(RequestType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.sA4B507837A9209130051EA081A1B3E51").resolveHandle("requesttype90aatype");
    
    /**
     * Gets array of all "Subject" elements
     */
    os.schema.context._0._2.xacml.tc.names.oasis.SubjectType[] getSubjectArray();
    
    /**
     * Gets ith "Subject" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.SubjectType getSubjectArray(int i);
    
    /**
     * Returns number of "Subject" element
     */
    int sizeOfSubjectArray();
    
    /**
     * Sets array of all "Subject" element
     */
    void setSubjectArray(os.schema.context._0._2.xacml.tc.names.oasis.SubjectType[] subjectArray);
    
    /**
     * Sets ith "Subject" element
     */
    void setSubjectArray(int i, os.schema.context._0._2.xacml.tc.names.oasis.SubjectType subject);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Subject" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.SubjectType insertNewSubject(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Subject" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.SubjectType addNewSubject();
    
    /**
     * Removes the ith "Subject" element
     */
    void removeSubject(int i);
    
    /**
     * Gets array of all "Resource" elements
     */
    os.schema.context._0._2.xacml.tc.names.oasis.ResourceType[] getResourceArray();
    
    /**
     * Gets ith "Resource" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.ResourceType getResourceArray(int i);
    
    /**
     * Returns number of "Resource" element
     */
    int sizeOfResourceArray();
    
    /**
     * Sets array of all "Resource" element
     */
    void setResourceArray(os.schema.context._0._2.xacml.tc.names.oasis.ResourceType[] resourceArray);
    
    /**
     * Sets ith "Resource" element
     */
    void setResourceArray(int i, os.schema.context._0._2.xacml.tc.names.oasis.ResourceType resource);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Resource" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.ResourceType insertNewResource(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Resource" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.ResourceType addNewResource();
    
    /**
     * Removes the ith "Resource" element
     */
    void removeResource(int i);
    
    /**
     * Gets the "Action" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.ActionType getAction();
    
    /**
     * Sets the "Action" element
     */
    void setAction(os.schema.context._0._2.xacml.tc.names.oasis.ActionType action);
    
    /**
     * Appends and returns a new empty "Action" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.ActionType addNewAction();
    
    /**
     * Gets the "Environment" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType getEnvironment();
    
    /**
     * Sets the "Environment" element
     */
    void setEnvironment(os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType environment);
    
    /**
     * Appends and returns a new empty "Environment" element
     */
    os.schema.context._0._2.xacml.tc.names.oasis.EnvironmentType addNewEnvironment();
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType newInstance() {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.context._0._2.xacml.tc.names.oasis.RequestType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.context._0._2.xacml.tc.names.oasis.RequestType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
