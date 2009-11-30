/*
 * XML Type:  PolicySetType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis;


/**
 * An XML PolicySetType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public interface PolicySetType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(PolicySetType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s66018507165A80C1B966D93133E02489").resolveHandle("policysettype8bfetype");
    
    /**
     * Gets the "Description" element
     */
    java.lang.String getDescription();
    
    /**
     * Gets (as xml) the "Description" element
     */
    org.apache.xmlbeans.XmlString xgetDescription();
    
    /**
     * True if has "Description" element
     */
    boolean isSetDescription();
    
    /**
     * Sets the "Description" element
     */
    void setDescription(java.lang.String description);
    
    /**
     * Sets (as xml) the "Description" element
     */
    void xsetDescription(org.apache.xmlbeans.XmlString description);
    
    /**
     * Unsets the "Description" element
     */
    void unsetDescription();
    
    /**
     * Gets the "PolicySetDefaults" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType getPolicySetDefaults();
    
    /**
     * True if has "PolicySetDefaults" element
     */
    boolean isSetPolicySetDefaults();
    
    /**
     * Sets the "PolicySetDefaults" element
     */
    void setPolicySetDefaults(os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType policySetDefaults);
    
    /**
     * Appends and returns a new empty "PolicySetDefaults" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType addNewPolicySetDefaults();
    
    /**
     * Unsets the "PolicySetDefaults" element
     */
    void unsetPolicySetDefaults();
    
    /**
     * Gets the "Target" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.TargetType getTarget();
    
    /**
     * Sets the "Target" element
     */
    void setTarget(os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target);
    
    /**
     * Appends and returns a new empty "Target" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.TargetType addNewTarget();
    
    /**
     * Gets array of all "PolicySet" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType[] getPolicySetArray();
    
    /**
     * Gets ith "PolicySet" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType getPolicySetArray(int i);
    
    /**
     * Returns number of "PolicySet" element
     */
    int sizeOfPolicySetArray();
    
    /**
     * Sets array of all "PolicySet" element
     */
    void setPolicySetArray(os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType[] policySetArray);
    
    /**
     * Sets ith "PolicySet" element
     */
    void setPolicySetArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType policySet);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicySet" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType insertNewPolicySet(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicySet" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType addNewPolicySet();
    
    /**
     * Removes the ith "PolicySet" element
     */
    void removePolicySet(int i);
    
    /**
     * Gets array of all "Policy" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType[] getPolicyArray();
    
    /**
     * Gets ith "Policy" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType getPolicyArray(int i);
    
    /**
     * Returns number of "Policy" element
     */
    int sizeOfPolicyArray();
    
    /**
     * Sets array of all "Policy" element
     */
    void setPolicyArray(os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType[] policyArray);
    
    /**
     * Sets ith "Policy" element
     */
    void setPolicyArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType policy);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Policy" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType insertNewPolicy(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Policy" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType addNewPolicy();
    
    /**
     * Removes the ith "Policy" element
     */
    void removePolicy(int i);
    
    /**
     * Gets array of all "PolicySetIdReference" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] getPolicySetIdReferenceArray();
    
    /**
     * Gets ith "PolicySetIdReference" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType getPolicySetIdReferenceArray(int i);
    
    /**
     * Returns number of "PolicySetIdReference" element
     */
    int sizeOfPolicySetIdReferenceArray();
    
    /**
     * Sets array of all "PolicySetIdReference" element
     */
    void setPolicySetIdReferenceArray(os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] policySetIdReferenceArray);
    
    /**
     * Sets ith "PolicySetIdReference" element
     */
    void setPolicySetIdReferenceArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType policySetIdReference);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicySetIdReference" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType insertNewPolicySetIdReference(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicySetIdReference" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType addNewPolicySetIdReference();
    
    /**
     * Removes the ith "PolicySetIdReference" element
     */
    void removePolicySetIdReference(int i);
    
    /**
     * Gets array of all "PolicyIdReference" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] getPolicyIdReferenceArray();
    
    /**
     * Gets ith "PolicyIdReference" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType getPolicyIdReferenceArray(int i);
    
    /**
     * Returns number of "PolicyIdReference" element
     */
    int sizeOfPolicyIdReferenceArray();
    
    /**
     * Sets array of all "PolicyIdReference" element
     */
    void setPolicyIdReferenceArray(os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] policyIdReferenceArray);
    
    /**
     * Sets ith "PolicyIdReference" element
     */
    void setPolicyIdReferenceArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType policyIdReference);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicyIdReference" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType insertNewPolicyIdReference(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicyIdReference" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType addNewPolicyIdReference();
    
    /**
     * Removes the ith "PolicyIdReference" element
     */
    void removePolicyIdReference(int i);
    
    /**
     * Gets array of all "CombinerParameters" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType[] getCombinerParametersArray();
    
    /**
     * Gets ith "CombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType getCombinerParametersArray(int i);
    
    /**
     * Returns number of "CombinerParameters" element
     */
    int sizeOfCombinerParametersArray();
    
    /**
     * Sets array of all "CombinerParameters" element
     */
    void setCombinerParametersArray(os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType[] combinerParametersArray);
    
    /**
     * Sets ith "CombinerParameters" element
     */
    void setCombinerParametersArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType combinerParameters);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType insertNewCombinerParameters(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType addNewCombinerParameters();
    
    /**
     * Removes the ith "CombinerParameters" element
     */
    void removeCombinerParameters(int i);
    
    /**
     * Gets array of all "PolicyCombinerParameters" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType[] getPolicyCombinerParametersArray();
    
    /**
     * Gets ith "PolicyCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType getPolicyCombinerParametersArray(int i);
    
    /**
     * Returns number of "PolicyCombinerParameters" element
     */
    int sizeOfPolicyCombinerParametersArray();
    
    /**
     * Sets array of all "PolicyCombinerParameters" element
     */
    void setPolicyCombinerParametersArray(os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType[] policyCombinerParametersArray);
    
    /**
     * Sets ith "PolicyCombinerParameters" element
     */
    void setPolicyCombinerParametersArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType policyCombinerParameters);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicyCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType insertNewPolicyCombinerParameters(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicyCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType addNewPolicyCombinerParameters();
    
    /**
     * Removes the ith "PolicyCombinerParameters" element
     */
    void removePolicyCombinerParameters(int i);
    
    /**
     * Gets array of all "PolicySetCombinerParameters" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType[] getPolicySetCombinerParametersArray();
    
    /**
     * Gets ith "PolicySetCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType getPolicySetCombinerParametersArray(int i);
    
    /**
     * Returns number of "PolicySetCombinerParameters" element
     */
    int sizeOfPolicySetCombinerParametersArray();
    
    /**
     * Sets array of all "PolicySetCombinerParameters" element
     */
    void setPolicySetCombinerParametersArray(os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType[] policySetCombinerParametersArray);
    
    /**
     * Sets ith "PolicySetCombinerParameters" element
     */
    void setPolicySetCombinerParametersArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType policySetCombinerParameters);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicySetCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType insertNewPolicySetCombinerParameters(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicySetCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType addNewPolicySetCombinerParameters();
    
    /**
     * Removes the ith "PolicySetCombinerParameters" element
     */
    void removePolicySetCombinerParameters(int i);
    
    /**
     * Gets the "Obligations" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType getObligations();
    
    /**
     * True if has "Obligations" element
     */
    boolean isSetObligations();
    
    /**
     * Sets the "Obligations" element
     */
    void setObligations(os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType obligations);
    
    /**
     * Appends and returns a new empty "Obligations" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType addNewObligations();
    
    /**
     * Unsets the "Obligations" element
     */
    void unsetObligations();
    
    /**
     * Gets the "PolicySetId" attribute
     */
    java.lang.String getPolicySetId();
    
    /**
     * Gets (as xml) the "PolicySetId" attribute
     */
    org.apache.xmlbeans.XmlAnyURI xgetPolicySetId();
    
    /**
     * Sets the "PolicySetId" attribute
     */
    void setPolicySetId(java.lang.String policySetId);
    
    /**
     * Sets (as xml) the "PolicySetId" attribute
     */
    void xsetPolicySetId(org.apache.xmlbeans.XmlAnyURI policySetId);
    
    /**
     * Gets the "Version" attribute
     */
    java.lang.String getVersion();
    
    /**
     * Gets (as xml) the "Version" attribute
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.VersionType xgetVersion();
    
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
    void xsetVersion(os.schema.policy._0._2.xacml.tc.names.oasis.VersionType version);
    
    /**
     * Unsets the "Version" attribute
     */
    void unsetVersion();
    
    /**
     * Gets the "PolicyCombiningAlgId" attribute
     */
    java.lang.String getPolicyCombiningAlgId();
    
    /**
     * Gets (as xml) the "PolicyCombiningAlgId" attribute
     */
    org.apache.xmlbeans.XmlAnyURI xgetPolicyCombiningAlgId();
    
    /**
     * Sets the "PolicyCombiningAlgId" attribute
     */
    void setPolicyCombiningAlgId(java.lang.String policyCombiningAlgId);
    
    /**
     * Sets (as xml) the "PolicyCombiningAlgId" attribute
     */
    void xsetPolicyCombiningAlgId(org.apache.xmlbeans.XmlAnyURI policyCombiningAlgId);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType newInstance() {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
