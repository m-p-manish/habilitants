/*
 * XML Type:  PolicySetType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML PolicySetType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class PolicySetTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType
{
    
    public PolicySetTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName DESCRIPTION$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Description");
    private static final javax.xml.namespace.QName POLICYSETDEFAULTS$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicySetDefaults");
    private static final javax.xml.namespace.QName TARGET$4 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Target");
    private static final javax.xml.namespace.QName POLICYSET$6 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicySet");
    private static final javax.xml.namespace.QName POLICY$8 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Policy");
    private static final javax.xml.namespace.QName POLICYSETIDREFERENCE$10 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicySetIdReference");
    private static final javax.xml.namespace.QName POLICYIDREFERENCE$12 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicyIdReference");
    private static final javax.xml.namespace.QName COMBINERPARAMETERS$14 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "CombinerParameters");
    private static final javax.xml.namespace.QName POLICYCOMBINERPARAMETERS$16 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicyCombinerParameters");
    private static final javax.xml.namespace.QName POLICYSETCOMBINERPARAMETERS$18 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "PolicySetCombinerParameters");
    private static final javax.xml.namespace.QName OBLIGATIONS$20 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Obligations");
    private static final javax.xml.namespace.QName POLICYSETID$22 = 
        new javax.xml.namespace.QName("", "PolicySetId");
    private static final javax.xml.namespace.QName VERSION$24 = 
        new javax.xml.namespace.QName("", "Version");
    private static final javax.xml.namespace.QName POLICYCOMBININGALGID$26 = 
        new javax.xml.namespace.QName("", "PolicyCombiningAlgId");
    
    
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
     * Gets the "PolicySetDefaults" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType getPolicySetDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().find_element_user(POLICYSETDEFAULTS$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "PolicySetDefaults" element
     */
    public boolean isSetPolicySetDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYSETDEFAULTS$2) != 0;
        }
    }
    
    /**
     * Sets the "PolicySetDefaults" element
     */
    public void setPolicySetDefaults(os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType policySetDefaults)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().find_element_user(POLICYSETDEFAULTS$2, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().add_element_user(POLICYSETDEFAULTS$2);
            }
            target.set(policySetDefaults);
        }
    }
    
    /**
     * Appends and returns a new empty "PolicySetDefaults" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType addNewPolicySetDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.DefaultsType)get_store().add_element_user(POLICYSETDEFAULTS$2);
            return target;
        }
    }
    
    /**
     * Unsets the "PolicySetDefaults" element
     */
    public void unsetPolicySetDefaults()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYSETDEFAULTS$2, 0);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().find_element_user(TARGET$4, 0);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().find_element_user(TARGET$4, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().add_element_user(TARGET$4);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.TargetType)get_store().add_element_user(TARGET$4);
            return target;
        }
    }
    
    /**
     * Gets array of all "PolicySet" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType[] getPolicySetArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POLICYSET$6, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PolicySet" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType getPolicySetArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType)get_store().find_element_user(POLICYSET$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PolicySet" element
     */
    public int sizeOfPolicySetArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYSET$6);
        }
    }
    
    /**
     * Sets array of all "PolicySet" element
     */
    public void setPolicySetArray(os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType[] policySetArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(policySetArray, POLICYSET$6);
        }
    }
    
    /**
     * Sets ith "PolicySet" element
     */
    public void setPolicySetArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType policySet)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType)get_store().find_element_user(POLICYSET$6, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(policySet);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicySet" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType insertNewPolicySet(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType)get_store().insert_element_user(POLICYSET$6, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicySet" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType addNewPolicySet()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetType)get_store().add_element_user(POLICYSET$6);
            return target;
        }
    }
    
    /**
     * Removes the ith "PolicySet" element
     */
    public void removePolicySet(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYSET$6, i);
        }
    }
    
    /**
     * Gets array of all "Policy" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType[] getPolicyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POLICY$8, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "Policy" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType getPolicyArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType)get_store().find_element_user(POLICY$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "Policy" element
     */
    public int sizeOfPolicyArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICY$8);
        }
    }
    
    /**
     * Sets array of all "Policy" element
     */
    public void setPolicyArray(os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType[] policyArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(policyArray, POLICY$8);
        }
    }
    
    /**
     * Sets ith "Policy" element
     */
    public void setPolicyArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType policy)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType)get_store().find_element_user(POLICY$8, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(policy);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "Policy" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType insertNewPolicy(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType)get_store().insert_element_user(POLICY$8, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "Policy" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType addNewPolicy()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyType)get_store().add_element_user(POLICY$8);
            return target;
        }
    }
    
    /**
     * Removes the ith "Policy" element
     */
    public void removePolicy(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICY$8, i);
        }
    }
    
    /**
     * Gets array of all "PolicySetIdReference" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] getPolicySetIdReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POLICYSETIDREFERENCE$10, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PolicySetIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType getPolicySetIdReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().find_element_user(POLICYSETIDREFERENCE$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PolicySetIdReference" element
     */
    public int sizeOfPolicySetIdReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYSETIDREFERENCE$10);
        }
    }
    
    /**
     * Sets array of all "PolicySetIdReference" element
     */
    public void setPolicySetIdReferenceArray(os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] policySetIdReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(policySetIdReferenceArray, POLICYSETIDREFERENCE$10);
        }
    }
    
    /**
     * Sets ith "PolicySetIdReference" element
     */
    public void setPolicySetIdReferenceArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType policySetIdReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().find_element_user(POLICYSETIDREFERENCE$10, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(policySetIdReference);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicySetIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType insertNewPolicySetIdReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().insert_element_user(POLICYSETIDREFERENCE$10, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicySetIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType addNewPolicySetIdReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().add_element_user(POLICYSETIDREFERENCE$10);
            return target;
        }
    }
    
    /**
     * Removes the ith "PolicySetIdReference" element
     */
    public void removePolicySetIdReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYSETIDREFERENCE$10, i);
        }
    }
    
    /**
     * Gets array of all "PolicyIdReference" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] getPolicyIdReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POLICYIDREFERENCE$12, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PolicyIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType getPolicyIdReferenceArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().find_element_user(POLICYIDREFERENCE$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PolicyIdReference" element
     */
    public int sizeOfPolicyIdReferenceArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYIDREFERENCE$12);
        }
    }
    
    /**
     * Sets array of all "PolicyIdReference" element
     */
    public void setPolicyIdReferenceArray(os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType[] policyIdReferenceArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(policyIdReferenceArray, POLICYIDREFERENCE$12);
        }
    }
    
    /**
     * Sets ith "PolicyIdReference" element
     */
    public void setPolicyIdReferenceArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType policyIdReference)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().find_element_user(POLICYIDREFERENCE$12, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(policyIdReference);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicyIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType insertNewPolicyIdReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().insert_element_user(POLICYIDREFERENCE$12, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicyIdReference" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType addNewPolicyIdReference()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.IdReferenceType)get_store().add_element_user(POLICYIDREFERENCE$12);
            return target;
        }
    }
    
    /**
     * Removes the ith "PolicyIdReference" element
     */
    public void removePolicyIdReference(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYIDREFERENCE$12, i);
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
            get_store().find_all_element_users(COMBINERPARAMETERS$14, targetList);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().find_element_user(COMBINERPARAMETERS$14, i);
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
            return get_store().count_elements(COMBINERPARAMETERS$14);
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
            arraySetterHelper(combinerParametersArray, COMBINERPARAMETERS$14);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().find_element_user(COMBINERPARAMETERS$14, i);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().insert_element_user(COMBINERPARAMETERS$14, i);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.CombinerParametersType)get_store().add_element_user(COMBINERPARAMETERS$14);
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
            get_store().remove_element(COMBINERPARAMETERS$14, i);
        }
    }
    
    /**
     * Gets array of all "PolicyCombinerParameters" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType[] getPolicyCombinerParametersArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POLICYCOMBINERPARAMETERS$16, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PolicyCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType getPolicyCombinerParametersArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType)get_store().find_element_user(POLICYCOMBINERPARAMETERS$16, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PolicyCombinerParameters" element
     */
    public int sizeOfPolicyCombinerParametersArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYCOMBINERPARAMETERS$16);
        }
    }
    
    /**
     * Sets array of all "PolicyCombinerParameters" element
     */
    public void setPolicyCombinerParametersArray(os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType[] policyCombinerParametersArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(policyCombinerParametersArray, POLICYCOMBINERPARAMETERS$16);
        }
    }
    
    /**
     * Sets ith "PolicyCombinerParameters" element
     */
    public void setPolicyCombinerParametersArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType policyCombinerParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType)get_store().find_element_user(POLICYCOMBINERPARAMETERS$16, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(policyCombinerParameters);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicyCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType insertNewPolicyCombinerParameters(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType)get_store().insert_element_user(POLICYCOMBINERPARAMETERS$16, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicyCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType addNewPolicyCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicyCombinerParametersType)get_store().add_element_user(POLICYCOMBINERPARAMETERS$16);
            return target;
        }
    }
    
    /**
     * Removes the ith "PolicyCombinerParameters" element
     */
    public void removePolicyCombinerParameters(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYCOMBINERPARAMETERS$16, i);
        }
    }
    
    /**
     * Gets array of all "PolicySetCombinerParameters" elements
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType[] getPolicySetCombinerParametersArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            java.util.List targetList = new java.util.ArrayList();
            get_store().find_all_element_users(POLICYSETCOMBINERPARAMETERS$18, targetList);
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType[] result = new os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType[targetList.size()];
            targetList.toArray(result);
            return result;
        }
    }
    
    /**
     * Gets ith "PolicySetCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType getPolicySetCombinerParametersArray(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType)get_store().find_element_user(POLICYSETCOMBINERPARAMETERS$18, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            return target;
        }
    }
    
    /**
     * Returns number of "PolicySetCombinerParameters" element
     */
    public int sizeOfPolicySetCombinerParametersArray()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(POLICYSETCOMBINERPARAMETERS$18);
        }
    }
    
    /**
     * Sets array of all "PolicySetCombinerParameters" element
     */
    public void setPolicySetCombinerParametersArray(os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType[] policySetCombinerParametersArray)
    {
        synchronized (monitor())
        {
            check_orphaned();
            arraySetterHelper(policySetCombinerParametersArray, POLICYSETCOMBINERPARAMETERS$18);
        }
    }
    
    /**
     * Sets ith "PolicySetCombinerParameters" element
     */
    public void setPolicySetCombinerParametersArray(int i, os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType policySetCombinerParameters)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType)get_store().find_element_user(POLICYSETCOMBINERPARAMETERS$18, i);
            if (target == null)
            {
                throw new IndexOutOfBoundsException();
            }
            target.set(policySetCombinerParameters);
        }
    }
    
    /**
     * Inserts and returns a new empty value (as xml) as the ith "PolicySetCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType insertNewPolicySetCombinerParameters(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType)get_store().insert_element_user(POLICYSETCOMBINERPARAMETERS$18, i);
            return target;
        }
    }
    
    /**
     * Appends and returns a new empty value (as xml) as the last "PolicySetCombinerParameters" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType addNewPolicySetCombinerParameters()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.PolicySetCombinerParametersType)get_store().add_element_user(POLICYSETCOMBINERPARAMETERS$18);
            return target;
        }
    }
    
    /**
     * Removes the ith "PolicySetCombinerParameters" element
     */
    public void removePolicySetCombinerParameters(int i)
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(POLICYSETCOMBINERPARAMETERS$18, i);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().find_element_user(OBLIGATIONS$20, 0);
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
            return get_store().count_elements(OBLIGATIONS$20) != 0;
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().find_element_user(OBLIGATIONS$20, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().add_element_user(OBLIGATIONS$20);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ObligationsType)get_store().add_element_user(OBLIGATIONS$20);
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
            get_store().remove_element(OBLIGATIONS$20, 0);
        }
    }
    
    /**
     * Gets the "PolicySetId" attribute
     */
    public java.lang.String getPolicySetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYSETID$22);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "PolicySetId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetPolicySetId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYSETID$22);
            return target;
        }
    }
    
    /**
     * Sets the "PolicySetId" attribute
     */
    public void setPolicySetId(java.lang.String policySetId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYSETID$22);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(POLICYSETID$22);
            }
            target.setStringValue(policySetId);
        }
    }
    
    /**
     * Sets (as xml) the "PolicySetId" attribute
     */
    public void xsetPolicySetId(org.apache.xmlbeans.XmlAnyURI policySetId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYSETID$22);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(POLICYSETID$22);
            }
            target.set(policySetId);
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VERSION$24);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_default_attribute_value(VERSION$24);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionType)get_store().find_attribute_user(VERSION$24);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionType)get_default_attribute_value(VERSION$24);
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
            return get_store().find_attribute_user(VERSION$24) != null;
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
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(VERSION$24);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(VERSION$24);
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
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionType)get_store().find_attribute_user(VERSION$24);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.VersionType)get_store().add_attribute_user(VERSION$24);
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
            get_store().remove_attribute(VERSION$24);
        }
    }
    
    /**
     * Gets the "PolicyCombiningAlgId" attribute
     */
    public java.lang.String getPolicyCombiningAlgId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYCOMBININGALGID$26);
            if (target == null)
            {
                return null;
            }
            return target.getStringValue();
        }
    }
    
    /**
     * Gets (as xml) the "PolicyCombiningAlgId" attribute
     */
    public org.apache.xmlbeans.XmlAnyURI xgetPolicyCombiningAlgId()
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYCOMBININGALGID$26);
            return target;
        }
    }
    
    /**
     * Sets the "PolicyCombiningAlgId" attribute
     */
    public void setPolicyCombiningAlgId(java.lang.String policyCombiningAlgId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.SimpleValue target = null;
            target = (org.apache.xmlbeans.SimpleValue)get_store().find_attribute_user(POLICYCOMBININGALGID$26);
            if (target == null)
            {
                target = (org.apache.xmlbeans.SimpleValue)get_store().add_attribute_user(POLICYCOMBININGALGID$26);
            }
            target.setStringValue(policyCombiningAlgId);
        }
    }
    
    /**
     * Sets (as xml) the "PolicyCombiningAlgId" attribute
     */
    public void xsetPolicyCombiningAlgId(org.apache.xmlbeans.XmlAnyURI policyCombiningAlgId)
    {
        synchronized (monitor())
        {
            check_orphaned();
            org.apache.xmlbeans.XmlAnyURI target = null;
            target = (org.apache.xmlbeans.XmlAnyURI)get_store().find_attribute_user(POLICYCOMBININGALGID$26);
            if (target == null)
            {
                target = (org.apache.xmlbeans.XmlAnyURI)get_store().add_attribute_user(POLICYCOMBININGALGID$26);
            }
            target.set(policyCombiningAlgId);
        }
    }
}
