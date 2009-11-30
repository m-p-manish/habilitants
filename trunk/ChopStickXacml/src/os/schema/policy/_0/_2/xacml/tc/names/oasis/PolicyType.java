/*
 * XML Type:  PolicyType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis;


/**
 * An XML PolicyType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public interface PolicyType extends org.apache.xmlbeans.XmlObject
{
    public static final org.apache.xmlbeans.SchemaType type = (org.apache.xmlbeans.SchemaType)
        org.apache.xmlbeans.XmlBeans.typeSystemForClassLoader(PolicyType.class.getClassLoader(), "schemaorg_apache_xmlbeans.system.s66018507165A80C1B966D93133E02489").resolveHandle("policytype7292type");
    
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
     * Gets the "PolicyDefaults" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType getPolicyDefaults();
    
    /**
     * True if has "PolicyDefaults" element
     */
    boolean isSetPolicyDefaults();
    
    /**
     * Sets the "PolicyDefaults" element
     */
    void setPolicyDefaults(os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType policyDefaults);
    
    /**
     * Appends and returns a new empty "PolicyDefaults" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType addNewPolicyDefaults();
    
    /**
     * Unsets the "PolicyDefaults" element
     */
    void unsetPolicyDefaults();
    
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
     * Gets array of all "RuleCombinerParameters" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType[] getRuleCombinerParametersArray();
    
    /**
     * Gets ith "RuleCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType getRuleCombinerParametersArray(int i);
    
    /**
     * Returns number of "RuleCombinerParameters" element
     */
    int sizeOfRuleCombinerParametersArray();
    
    /**
     * Sets array of all "RuleCombinerParameters" element
     */
    void setRuleCombinerParametersArray(os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType[] ruleCombinerParametersArray);
    
    /**
     * Sets ith "RuleCombinerParameters" element
     */
    void setRuleCombinerParametersArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType ruleCombinerParameters);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "RuleCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType insertNewRuleCombinerParameters(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "RuleCombinerParameters" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType addNewRuleCombinerParameters();
    
    /**
     * Removes the ith "RuleCombinerParameters" element
     */
    void removeRuleCombinerParameters(int i);
    
    /**
     * Gets array of all "VariableDefinition" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType[] getVariableDefinitionArray();
    
    /**
     * Gets ith "VariableDefinition" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType getVariableDefinitionArray(int i);
    
    /**
     * Returns number of "VariableDefinition" element
     */
    int sizeOfVariableDefinitionArray();
    
    /**
     * Sets array of all "VariableDefinition" element
     */
    void setVariableDefinitionArray(os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType[] variableDefinitionArray);
    
    /**
     * Sets ith "VariableDefinition" element
     */
    void setVariableDefinitionArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType variableDefinition);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "VariableDefinition" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType insertNewVariableDefinition(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "VariableDefinition" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType addNewVariableDefinition();
    
    /**
     * Removes the ith "VariableDefinition" element
     */
    void removeVariableDefinition(int i);
    
    /**
     * Gets array of all "Rule" elements
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.RuleType[] getRuleArray();
    
    /**
     * Gets ith "Rule" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.RuleType getRuleArray(int i);
    
    /**
     * Returns number of "Rule" element
     */
    int sizeOfRuleArray();
    
    /**
     * Sets array of all "Rule" element
     */
    void setRuleArray(os.schema.policy._0._2.xacml.tc.names.oasis.RuleType[] ruleArray);
    
    /**
     * Sets ith "Rule" element
     */
    void setRuleArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.RuleType rule);
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Rule" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.RuleType insertNewRule(int i);
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Rule" element
     */
    os.schema.policy._0._2.xacml.tc.names.oasis.RuleType addNewRule();
    
    /**
     * Removes the ith "Rule" element
     */
    void removeRule(int i);
    
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
     * Gets the "PolicyId" attribute
     */
    java.lang.String getPolicyId();
    
    /**
     * Gets (as xml) the "PolicyId" attribute
     */
    org.apache.xmlbeans.XmlAnyURI xgetPolicyId();
    
    /**
     * Sets the "PolicyId" attribute
     */
    void setPolicyId(java.lang.String policyId);
    
    /**
     * Sets (as xml) the "PolicyId" attribute
     */
    void xsetPolicyId(org.apache.xmlbeans.XmlAnyURI policyId);
    
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
     * Gets the "RuleCombiningAlgId" attribute
     */
    java.lang.String getRuleCombiningAlgId();
    
    /**
     * Gets (as xml) the "RuleCombiningAlgId" attribute
     */
    org.apache.xmlbeans.XmlAnyURI xgetRuleCombiningAlgId();
    
    /**
     * Sets the "RuleCombiningAlgId" attribute
     */
    void setRuleCombiningAlgId(java.lang.String ruleCombiningAlgId);
    
    /**
     * Sets (as xml) the "RuleCombiningAlgId" attribute
     */
    void xsetRuleCombiningAlgId(org.apache.xmlbeans.XmlAnyURI ruleCombiningAlgId);
    
    /**
     * A factory class with static methods for creating instances
     * of this type.
     */
    
    public static final class Factory
    {
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType newInstance() {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType newInstance(org.apache.xmlbeans.XmlOptions options) {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newInstance( type, options ); }
        
        /** @param xmlAsString the string value to parse */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.lang.String xmlAsString) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.lang.String xmlAsString, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xmlAsString, type, options ); }
        
        /** @param file the file from which to load an xml document */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.io.File file) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.io.File file, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( file, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.net.URL u) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.net.URL u, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( u, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.io.InputStream is) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.io.InputStream is, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( is, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.io.Reader r) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(java.io.Reader r, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, java.io.IOException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( r, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(javax.xml.stream.XMLStreamReader sr) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(javax.xml.stream.XMLStreamReader sr, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( sr, type, options ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(org.w3c.dom.Node node) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, null ); }
        
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(org.w3c.dom.Node node, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( node, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType parse(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType) org.apache.xmlbeans.XmlBeans.getContextTypeLoader().parse( xis, type, options ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, null ); }
        
        /** @deprecated {@link XMLInputStream} */
        public static org.apache.xmlbeans.xml.stream.XMLInputStream newValidatingXMLInputStream(org.apache.xmlbeans.xml.stream.XMLInputStream xis, org.apache.xmlbeans.XmlOptions options) throws org.apache.xmlbeans.XmlException, org.apache.xmlbeans.xml.stream.XMLStreamException {
          return org.apache.xmlbeans.XmlBeans.getContextTypeLoader().newValidatingXMLInputStream( xis, type, options ); }
        
        private Factory() { } // No instance of this class allowed
    }
}
