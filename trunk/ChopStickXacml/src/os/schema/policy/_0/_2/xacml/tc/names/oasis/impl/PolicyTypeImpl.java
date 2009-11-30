/*
 * XML Type:  PolicyType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML PolicyType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class PolicyTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType
{
    
    public PolicyTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DESCRIPTION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Description");
    private static final javax.xml.namespace.QName POLICYDEFAULTS$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicyDefaults");
    private static final javax.xml.namespace.QName COMBINERPARAMETERS$4 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "CombinerParameters");
    private static final javax.xml.namespace.QName TARGET$6 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Target");
    private static final javax.xml.namespace.QName RULECOMBINERPARAMETERS$8 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "RuleCombinerParameters");
    private static final javax.xml.namespace.QName VARIABLEDEFINITION$10 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "VariableDefinition");
    private static final javax.xml.namespace.QName RULE$12 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Rule");
    private static final javax.xml.namespace.QName OBLIGATIONS$14 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Obligations");
    private static final javax.xml.namespace.QName POLICYID$16 = 
        new javax.xml.namespace.QName("", "PolicyId");
    private static final javax.xml.namespace.QName VERSION$18 = 
        new javax.xml.namespace.QName("", "Version");
    private static final javax.xml.namespace.QName RULECOMBININGALGID$20 = 
        new javax.xml.namespace.QName("", "RuleCombiningAlgId");
    
    
    /**
     * Gets the "Description" element
     */
    public java.lang.String getDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$0, 0);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "Description" element
     */
    public org.apache.xmlbeans.XmlString xgetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$0, 0);
            return target;
        }
    }
    
    /**
     * True if has "Description" element
     */
    public boolean isSetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(DESCRIPTION$0) != 0;
        }
    }
    
    /**
     * Sets the "Description" element
     */
    public void setDescription(java.lang.String description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_element_user(DESCRIPTION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_element_user(DESCRIPTION$0);
            }
            target.setStringValue(description);
        }
    }
    
    /**
     * Sets (as xml) the "Description" element
     */
    public void xsetDescription(org.apache.xmlbeans.XmlString description)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlString target = null;
            target = (org.apache.xmlbeans.XmlString)get_store().find_element_user(DESCRIPTION$0, 0);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlString)get_store().add_element_user(DESCRIPTION$0);
            }
            target.set(description);
        }
    }
    
    /**
     * Unsets the "Description" element
     */
    public void unsetDescription()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(DESCRIPTION$0, 0);
        }
    }
    
    /**
     * Gets the "PolicyDefaults" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType getPolicyDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().find_element_user(POLICYDEFAULTS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "PolicyDefaults" element
     */
    public boolean isSetPolicyDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYDEFAULTS$2) != 0;
        }
    }
    
    /**
     * Sets the "PolicyDefaults" element
     */
    public void setPolicyDefaults(os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType policyDefaults)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().find_element_user(POLICYDEFAULTS$2, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().add_element_user(POLICYDEFAULTS$2);
            }
            target.set(policyDefaults);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicyDefaults" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType addNewPolicyDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().add_element_user(POLICYDEFAULTS$2);
            return target;
        }
    }
    
    /**
     * Unsets the "PolicyDefaults" element
     */
    public void unsetPolicyDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYDEFAULTS$2, 0);
        }
    }
    
    /**
     * Gets array of all "CombinerParameters" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType[] getCombinerParametersArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(COMBINERPARAMETERS$4, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "CombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType getCombinerParametersArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().find_element_user(COMBINERPARAMETERS$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "CombinerParameters" element
     */
    public int sizeOfCombinerParametersArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(COMBINERPARAMETERS$4);
        }
    }
    
    /**
     * Sets array of all "CombinerParameters" element
     */
    public void setCombinerParametersArray(os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType[] combinerParametersArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(combinerParametersArray, COMBINERPARAMETERS$4);
        }
    }
    
    /**
     * Sets ith "CombinerParameters" element
     */
    public void setCombinerParametersArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType combinerParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().find_element_user(COMBINERPARAMETERS$4, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(combinerParameters);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "CombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType insertNewCombinerParameters(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().insert_element_user(COMBINERPARAMETERS$4, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "CombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType addNewCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().add_element_user(COMBINERPARAMETERS$4);
            return target;
        }
    }
    
    /**
     * Removes the ith "CombinerParameters" element
     */
    public void removeCombinerParameters(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(COMBINERPARAMETERS$4, i);
        }
    }
    
    /**
     * Gets the "Target" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.TargetType getTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().find_element_user(TARGET$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * Sets the "Target" element
     */
    public void setTarget(os.schema.policy._0._2.xacml.tc.names.oasis.TargetType targetValue)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().find_element_user(TARGET$6, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().add_element_user(TARGET$6);
            }
            target.set(targetValue);
        }
    }
    
    /**
     * Appends and returns a new empty "Target" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.TargetType addNewTarget()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.TargetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().add_element_user(TARGET$6);
            return target;
        }
    }
    
    /**
     * Gets array of all "RuleCombinerParameters" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType[] getRuleCombinerParametersArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RULECOMBINERPARAMETERS$8, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "RuleCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType getRuleCombinerParametersArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType)get_store().find_element_user(RULECOMBINERPARAMETERS$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "RuleCombinerParameters" element
     */
    public int sizeOfRuleCombinerParametersArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RULECOMBINERPARAMETERS$8);
        }
    }
    
    /**
     * Sets array of all "RuleCombinerParameters" element
     */
    public void setRuleCombinerParametersArray(os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType[] ruleCombinerParametersArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(ruleCombinerParametersArray, RULECOMBINERPARAMETERS$8);
        }
    }
    
    /**
     * Sets ith "RuleCombinerParameters" element
     */
    public void setRuleCombinerParametersArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType ruleCombinerParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType)get_store().find_element_user(RULECOMBINERPARAMETERS$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(ruleCombinerParameters);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "RuleCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType insertNewRuleCombinerParameters(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType)get_store().insert_element_user(RULECOMBINERPARAMETERS$8, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "RuleCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType addNewRuleCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleCombinerParametersType)get_store().add_element_user(RULECOMBINERPARAMETERS$8);
            return target;
        }
    }
    
    /**
     * Removes the ith "RuleCombinerParameters" element
     */
    public void removeRuleCombinerParameters(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RULECOMBINERPARAMETERS$8, i);
        }
    }
    
    /**
     * Gets array of all "VariableDefinition" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType[] getVariableDefinitionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(VARIABLEDEFINITION$10, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "VariableDefinition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType getVariableDefinitionArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType)get_store().find_element_user(VARIABLEDEFINITION$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "VariableDefinition" element
     */
    public int sizeOfVariableDefinitionArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(VARIABLEDEFINITION$10);
        }
    }
    
    /**
     * Sets array of all "VariableDefinition" element
     */
    public void setVariableDefinitionArray(os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType[] variableDefinitionArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(variableDefinitionArray, VARIABLEDEFINITION$10);
        }
    }
    
    /**
     * Sets ith "VariableDefinition" element
     */
    public void setVariableDefinitionArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType variableDefinition)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType)get_store().find_element_user(VARIABLEDEFINITION$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(variableDefinition);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "VariableDefinition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType insertNewVariableDefinition(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType)get_store().insert_element_user(VARIABLEDEFINITION$10, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "VariableDefinition" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType addNewVariableDefinition()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VariableDefinitionType)get_store().add_element_user(VARIABLEDEFINITION$10);
            return target;
        }
    }
    
    /**
     * Removes the ith "VariableDefinition" element
     */
    public void removeVariableDefinition(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(VARIABLEDEFINITION$10, i);
        }
    }
    
    /**
     * Gets array of all "Rule" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleType[] getRuleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(RULE$12, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.RuleType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Rule" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleType getRuleArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleType)get_store().find_element_user(RULE$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Rule" element
     */
    public int sizeOfRuleArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RULE$12);
        }
    }
    
    /**
     * Sets array of all "Rule" element
     */
    public void setRuleArray(os.schema.policy._0._2.xacml.tc.names.oasis.RuleType[] ruleArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(ruleArray, RULE$12);
        }
    }
    
    /**
     * Sets ith "Rule" element
     */
    public void setRuleArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.RuleType rule)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleType)get_store().find_element_user(RULE$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(rule);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Rule" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleType insertNewRule(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleType)get_store().insert_element_user(RULE$12, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Rule" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.RuleType addNewRule()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.RuleType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.RuleType)get_store().add_element_user(RULE$12);
            return target;
        }
    }
    
    /**
     * Removes the ith "Rule" element
     */
    public void removeRule(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RULE$12, i);
        }
    }
    
    /**
     * Gets the "Obligations" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType getObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().find_element_user(OBLIGATIONS$14, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Obligations" element
     */
    public boolean isSetObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(OBLIGATIONS$14) != 0;
        }
    }
    
    /**
     * Sets the "Obligations" element
     */
    public void setObligations(os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType obligations)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().find_element_user(OBLIGATIONS$14, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().add_element_user(OBLIGATIONS$14);
            }
            target.set(obligations);
        }
    }
    
    /**
     * Appends and returns a new empty "Obligations" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType addNewObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().add_element_user(OBLIGATIONS$14);
            return target;
        }
    }
    
    /**
     * Unsets the "Obligations" element
     */
    public void unsetObligations()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(OBLIGATIONS$14, 0);
        }
    }
    
    /**
     * Gets the "PolicyId" attribute
     */
    public java.lang.String getPolicyId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYID$16);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "PolicyId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetPolicyId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYID$16);
            return target;
        }
    }
    
    /**
     * Sets the "PolicyId" attribute
     */
    public void setPolicyId(java.lang.String policyId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYID$16);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(POLICYID$16);
            }
            target.setStringValue(policyId);
        }
    }
    
    /**
     * Sets (as xml) the "PolicyId" attribute
     */
    public void xsetPolicyId(org.apache.xmlbeans.XmlAnyURI policyId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYID$16);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(POLICYID$16);
            }
            target.set(policyId);
        }
    }
    
    /**
     * Gets the "Version" attribute
     */
    public java.lang.String getVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VERSION$18);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(VERSION$18);
            }
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
    public os.schema.policy._0._2.xacml.tc.names.oasis.VersionType xgetVersion()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VersionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionType)get_store().find_attribute_user(VERSION$18);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionType)get_default_attribute_value(VERSION$18);
            }
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
            return get_store().find_attribute_user(VERSION$18) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VERSION$18);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(VERSION$18);
            }
            target.setStringValue(version);
        }
    }
    
    /**
     * Sets (as xml) the "Version" attribute
     */
    public void xsetVersion(os.schema.policy._0._2.xacml.tc.names.oasis.VersionType version)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.VersionType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionType)get_store().find_attribute_user(VERSION$18);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionType)get_store().add_attribute_user(VERSION$18);
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
            get_store().remove_attribute(VERSION$18);
        }
    }
    
    /**
     * Gets the "RuleCombiningAlgId" attribute
     */
    public java.lang.String getRuleCombiningAlgId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULECOMBININGALGID$20);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "RuleCombiningAlgId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetRuleCombiningAlgId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(RULECOMBININGALGID$20);
            return target;
        }
    }
    
    /**
     * Sets the "RuleCombiningAlgId" attribute
     */
    public void setRuleCombiningAlgId(java.lang.String ruleCombiningAlgId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(RULECOMBININGALGID$20);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(RULECOMBININGALGID$20);
            }
            target.setStringValue(ruleCombiningAlgId);
        }
    }
    
    /**
     * Sets (as xml) the "RuleCombiningAlgId" attribute
     */
    public void xsetRuleCombiningAlgId(org.apache.xmlbeans.XmlAnyURI ruleCombiningAlgId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(RULECOMBININGALGID$20);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(RULECOMBININGALGID$20);
            }
            target.set(ruleCombiningAlgId);
        }
    }
}
