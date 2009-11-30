/*
 * XML Type:  TargetType
 * Namespace: urn:oasis:names:tc:xacml:2.0:policy:schema:os
 * Java type: os.schema.policy._0._2.xacml.tc.names.oasis.TargetType
 *
 * Automatically generated - do not modify.
 */
package os.schema.policy._0._2.xacml.tc.names.oasis.impl;
/**
 * An XML TargetType(@urn:oasis:names:tc:xacml:2.0:policy:schema:os).
 *
 * This is a complex type.
 */
public class TargetTypeImpl extends org.apache.xmlbeans.impl.values.XmlComplexContentImpl implements os.schema.policy._0._2.xacml.tc.names.oasis.TargetType
{
    
    public TargetTypeImpl(org.apache.xmlbeans.SchemaType sType)
    {
        super(sType);
    }
    
    private static final javax.xml.namespace.QName SUBJECTS$0 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Subjects");
    private static final javax.xml.namespace.QName RESOURCES$2 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Resources");
    private static final javax.xml.namespace.QName ACTIONS$4 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Actions");
    private static final javax.xml.namespace.QName ENVIRONMENTS$6 = 
        new javax.xml.namespace.QName("urn:oasis:names:tc:xacml:2.0:policy:schema:os", "Environments");
    
    
    /**
     * Gets the "Subjects" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType getSubjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType)get_store().find_element_user(SUBJECTS$0, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Subjects" element
     */
    public boolean isSetSubjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(SUBJECTS$0) != 0;
        }
    }
    
    /**
     * Sets the "Subjects" element
     */
    public void setSubjects(os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType subjects)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType)get_store().find_element_user(SUBJECTS$0, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType)get_store().add_element_user(SUBJECTS$0);
            }
            target.set(subjects);
        }
    }
    
    /**
     * Appends and returns a new empty "Subjects" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType addNewSubjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.SubjectsType)get_store().add_element_user(SUBJECTS$0);
            return target;
        }
    }
    
    /**
     * Unsets the "Subjects" element
     */
    public void unsetSubjects()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(SUBJECTS$0, 0);
        }
    }
    
    /**
     * Gets the "Resources" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType getResources()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType)get_store().find_element_user(RESOURCES$2, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Resources" element
     */
    public boolean isSetResources()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(RESOURCES$2) != 0;
        }
    }
    
    /**
     * Sets the "Resources" element
     */
    public void setResources(os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType resources)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType)get_store().find_element_user(RESOURCES$2, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType)get_store().add_element_user(RESOURCES$2);
            }
            target.set(resources);
        }
    }
    
    /**
     * Appends and returns a new empty "Resources" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType addNewResources()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ResourcesType)get_store().add_element_user(RESOURCES$2);
            return target;
        }
    }
    
    /**
     * Unsets the "Resources" element
     */
    public void unsetResources()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(RESOURCES$2, 0);
        }
    }
    
    /**
     * Gets the "Actions" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType getActions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType)get_store().find_element_user(ACTIONS$4, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Actions" element
     */
    public boolean isSetActions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ACTIONS$4) != 0;
        }
    }
    
    /**
     * Sets the "Actions" element
     */
    public void setActions(os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType actions)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType)get_store().find_element_user(ACTIONS$4, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType)get_store().add_element_user(ACTIONS$4);
            }
            target.set(actions);
        }
    }
    
    /**
     * Appends and returns a new empty "Actions" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType addNewActions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.ActionsType)get_store().add_element_user(ACTIONS$4);
            return target;
        }
    }
    
    /**
     * Unsets the "Actions" element
     */
    public void unsetActions()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ACTIONS$4, 0);
        }
    }
    
    /**
     * Gets the "Environments" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType getEnvironments()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType)get_store().find_element_user(ENVIRONMENTS$6, 0);
            if (target == null)
            {
                return null;
            }
            return target;
        }
    }
    
    /**
     * True if has "Environments" element
     */
    public boolean isSetEnvironments()
    {
        synchronized (monitor())
        {
            check_orphaned();
            return get_store().count_elements(ENVIRONMENTS$6) != 0;
        }
    }
    
    /**
     * Sets the "Environments" element
     */
    public void setEnvironments(os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType environments)
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType)get_store().find_element_user(ENVIRONMENTS$6, 0);
            if (target == null)
            {
                target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType)get_store().add_element_user(ENVIRONMENTS$6);
            }
            target.set(environments);
        }
    }
    
    /**
     * Appends and returns a new empty "Environments" element
     */
    public os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType addNewEnvironments()
    {
        synchronized (monitor())
        {
            check_orphaned();
            os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType target = null;
            target = (os.schema.policy._0._2.xacml.tc.names.oasis.EnvironmentsType)get_store().add_element_user(ENVIRONMENTS$6);
            return target;
        }
    }
    
    /**
     * Unsets the "Environments" element
     */
    public void unsetEnvironments()
    {
        synchronized (monitor())
        {
            check_orphaned();
            get_store().remove_element(ENVIRONMENTS$6, 0);
        }
    }
}
