/*
Copyright Stéphane Georges Popoff, (avril 2009)

spopoff@rocketmail.com

Ce logiciel est un programme informatique servant à gérer des habilitations.

Ce logiciel est régi par la licence [CeCILL|CeCILL-B|CeCILL-C] soumise au droit français et
respectant les principes de diffusion des logiciels libres. Vous pouvez
utiliser, modifier et/ou redistribuer ce programme sous les conditions
de la licence [CeCILL|CeCILL-B|CeCILL-C] telle que diffusée par le CEA, le CNRS et l'INRIA
sur le site "http://www.cecill.info".

En contrepartie de l'accessibilité au code source et des droits de copie,
de modification et de redistribution accordés par cette licence, il n'est
offert aux utilisateurs qu'une garantie limitée.  Pour les mêmes raisons,
seule une responsabilité restreinte pèse sur l'auteur du programme,  le
titulaire des droits patrimoniaux et les concédants successifs.

A cet égard  l'attention de l'utilisateur est attirée sur les risques
associés au chargement,  à l'utilisation,  à la modification et/ou au
développement et à la reproduction du logiciel par l'utilisateur étant
donné sa spécificité de logiciel libre, qui peut le rendre complexe à
manipuler et qui le réserve donc à des développeurs et des professionnels
avertis possédant  des  connaissances  informatiques approfondies.  Les
utilisateurs sont donc invités à charger  et  tester  l'adéquation  du
logiciel à leurs besoins dans des conditions permettant d'assurer la
sécurité de leurs systèmes et ou de leurs données et, plus généralement,
à l'utiliser et l'exploiter dans les mêmes conditions de sécurité.

Le fait que vous puissiez accéder à cet en-tête signifie que vous avez
pris connaissance de la licence [CeCILL|CeCILL-B|CeCILL-C], et que vous en avez accepté les
termes.
 */
package techDecision2.dao.role;

import java.util.List;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;
import techDecision2.exception.*;
import javax.persistence.EntityNotFoundException;
import techDecision2.entites.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import techDecision2.entites.Members;
import techDecision2.entites.Roleslink;
import techDecision2.entites.Permissions;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class RoleBean implements RoleBeanRemote, RoleBeanLocal {
    @PersistenceContext(unitName="techDecisionEJB2PU")
    private EntityManager em;

    public void create(Role role) throws TechDecision2Erreurs, Exception {
        if (role.getRoleslinkCollection() == null) {
            role.setRoleslinkCollection(new ArrayList<Roleslink>());
        }
        if (role.getRoleslinkCollection1() == null) {
            role.setRoleslinkCollection1(new ArrayList<Roleslink>());
        }
        if (role.getMembersCollection() == null) {
            role.setMembersCollection(new ArrayList<Members>());
        }
        if (role.getPermissionsCollection() == null) {
            role.setPermissionsCollection(new ArrayList<Permissions>());
        }
        try {
            List<Roleslink> attachedRoleslinkCollection = new ArrayList<Roleslink>();
            for (Roleslink roleslinkCollectionRoleslinkToAttach : role.getRoleslinkCollection()) {
                roleslinkCollectionRoleslinkToAttach = em.getReference(roleslinkCollectionRoleslinkToAttach.getClass(), roleslinkCollectionRoleslinkToAttach.getPkrolelink());
                attachedRoleslinkCollection.add(roleslinkCollectionRoleslinkToAttach);
            }
            role.setRoleslinkCollection(attachedRoleslinkCollection);
            List<Roleslink> attachedRoleslinkCollection1 = new ArrayList<Roleslink>();
            for (Roleslink roleslinkCollection1RoleslinkToAttach : role.getRoleslinkCollection1()) {
                roleslinkCollection1RoleslinkToAttach = em.getReference(roleslinkCollection1RoleslinkToAttach.getClass(), roleslinkCollection1RoleslinkToAttach.getPkrolelink());
                attachedRoleslinkCollection1.add(roleslinkCollection1RoleslinkToAttach);
            }
            role.setRoleslinkCollection1(attachedRoleslinkCollection1);
            List<Members> attachedMembersCollection = new ArrayList<Members>();
            for (Members membersCollectionMembersToAttach : role.getMembersCollection()) {
                membersCollectionMembersToAttach = em.getReference(membersCollectionMembersToAttach.getClass(), membersCollectionMembersToAttach.getPkmember());
                attachedMembersCollection.add(membersCollectionMembersToAttach);
            }
            role.setMembersCollection(attachedMembersCollection);
            List<Permissions> attachedPermissionsCollection = new ArrayList<Permissions>();
            for (Permissions permissionsCollectionPermissionsToAttach : role.getPermissionsCollection()) {
                permissionsCollectionPermissionsToAttach = em.getReference(permissionsCollectionPermissionsToAttach.getClass(), permissionsCollectionPermissionsToAttach.getPkpermissions());
                attachedPermissionsCollection.add(permissionsCollectionPermissionsToAttach);
            }
            role.setPermissionsCollection(attachedPermissionsCollection);
            em.persist(role);
            for (Roleslink roleslinkCollectionRoleslink : role.getRoleslinkCollection()) {
                Role oldFkparentroleOfRoleslinkCollectionRoleslink = roleslinkCollectionRoleslink.getFkparentrole();
                roleslinkCollectionRoleslink.setFkparentrole(role);
                roleslinkCollectionRoleslink = em.merge(roleslinkCollectionRoleslink);
                if (oldFkparentroleOfRoleslinkCollectionRoleslink != null) {
                    oldFkparentroleOfRoleslinkCollectionRoleslink.getRoleslinkCollection().remove(roleslinkCollectionRoleslink);
                    oldFkparentroleOfRoleslinkCollectionRoleslink = em.merge(oldFkparentroleOfRoleslinkCollectionRoleslink);
                }
            }
            for (Roleslink roleslinkCollection1Roleslink : role.getRoleslinkCollection1()) {
                Role oldFkchildroleOfRoleslinkCollection1Roleslink = roleslinkCollection1Roleslink.getFkchildrole();
                roleslinkCollection1Roleslink.setFkchildrole(role);
                roleslinkCollection1Roleslink = em.merge(roleslinkCollection1Roleslink);
                if (oldFkchildroleOfRoleslinkCollection1Roleslink != null) {
                    oldFkchildroleOfRoleslinkCollection1Roleslink.getRoleslinkCollection1().remove(roleslinkCollection1Roleslink);
                    oldFkchildroleOfRoleslinkCollection1Roleslink = em.merge(oldFkchildroleOfRoleslinkCollection1Roleslink);
                }
            }
            for (Members membersCollectionMembers : role.getMembersCollection()) {
                Role oldFkroleOfMembersCollectionMembers = membersCollectionMembers.getFkrole();
                membersCollectionMembers.setFkrole(role);
                membersCollectionMembers = em.merge(membersCollectionMembers);
                if (oldFkroleOfMembersCollectionMembers != null) {
                    oldFkroleOfMembersCollectionMembers.getMembersCollection().remove(membersCollectionMembers);
                    oldFkroleOfMembersCollectionMembers = em.merge(oldFkroleOfMembersCollectionMembers);
                }
            }
            for (Permissions permissionsCollectionPermissions : role.getPermissionsCollection()) {
                Role oldFkroleOfPermissionsCollectionPermissions = permissionsCollectionPermissions.getFkrole();
                permissionsCollectionPermissions.setFkrole(role);
                permissionsCollectionPermissions = em.merge(permissionsCollectionPermissions);
                if (oldFkroleOfPermissionsCollectionPermissions != null) {
                    oldFkroleOfPermissionsCollectionPermissions.getPermissionsCollection().remove(permissionsCollectionPermissions);
                    oldFkroleOfPermissionsCollectionPermissions = em.merge(oldFkroleOfPermissionsCollectionPermissions);
                }
            }
        } catch (Exception ex) {
            if (findRole(role.getPkrole()) != null) {
                throw new TechDecision2Erreurs(new PreexistingEntityException("Role " + role + " already exists.", ex),10);
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws TechDecision2Erreurs, Exception {
        try {
            Role role;
            try {
                role = em.getReference(Role.class, id);
                role.getPkrole();
            } catch (EntityNotFoundException enfe) {
                throw new TechDecision2Erreurs(new NonexistentEntityException("The role with id " + id + " no longer exists.", enfe),11);
            }
            List<String> illegalOrphanMessages = null;
            List<Roleslink> roleslinkCollectionOrphanCheck = role.getRoleslinkCollection();
            for (Roleslink roleslinkCollectionOrphanCheckRoleslink : roleslinkCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Role (" + role + ") cannot be destroyed since the Roleslink " + roleslinkCollectionOrphanCheckRoleslink + " in its roleslinkCollection field has a non-nullable fkparentrole field.");
            }
            List<Roleslink> roleslinkCollection1OrphanCheck = role.getRoleslinkCollection1();
            for (Roleslink roleslinkCollection1OrphanCheckRoleslink : roleslinkCollection1OrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Role (" + role + ") cannot be destroyed since the Roleslink " + roleslinkCollection1OrphanCheckRoleslink + " in its roleslinkCollection1 field has a non-nullable fkchildrole field.");
            }
            List<Members> membersCollectionOrphanCheck = role.getMembersCollection();
            for (Members membersCollectionOrphanCheckMembers : membersCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Role (" + role + ") cannot be destroyed since the Members " + membersCollectionOrphanCheckMembers + " in its membersCollection field has a non-nullable fkrole field.");
            }
            List<Permissions> permissionsCollectionOrphanCheck = role.getPermissionsCollection();
            for (Permissions permissionsCollectionOrphanCheckPermissions : permissionsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Role (" + role + ") cannot be destroyed since the Permissions " + permissionsCollectionOrphanCheckPermissions + " in its permissionsCollection field has a non-nullable fkrole field.");
            }
            if (illegalOrphanMessages != null) {
               throw new TechDecision2Erreurs(new IllegalOrphanException(illegalOrphanMessages),12);
            }
            em.remove(role);
        } catch (Exception ex) {
            throw new TechDecision2Erreurs(ex,13);
        } finally {
        }
    }

    public void edit(Role role) throws TechDecision2Erreurs, Exception {
        try {
            Role persistentRole = em.find(Role.class, role.getPkrole());
            List<Roleslink> roleslinkCollectionOld = persistentRole.getRoleslinkCollection();
            List<Roleslink> roleslinkCollectionNew = role.getRoleslinkCollection();
            List<Roleslink> roleslinkCollection1Old = persistentRole.getRoleslinkCollection1();
            List<Roleslink> roleslinkCollection1New = role.getRoleslinkCollection1();
            List<Members> membersCollectionOld = persistentRole.getMembersCollection();
            List<Members> membersCollectionNew = role.getMembersCollection();
            List<Permissions> permissionsCollectionOld = persistentRole.getPermissionsCollection();
            List<Permissions> permissionsCollectionNew = role.getPermissionsCollection();
            List<String> illegalOrphanMessages = null;
            for (Roleslink roleslinkCollectionOldRoleslink : roleslinkCollectionOld) {
                if (!roleslinkCollectionNew.contains(roleslinkCollectionOldRoleslink)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Roleslink " + roleslinkCollectionOldRoleslink + " since its fkparentrole field is not nullable.");
                }
            }
            for (Roleslink roleslinkCollection1OldRoleslink : roleslinkCollection1Old) {
                if (!roleslinkCollection1New.contains(roleslinkCollection1OldRoleslink)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Roleslink " + roleslinkCollection1OldRoleslink + " since its fkchildrole field is not nullable.");
                }
            }
            for (Members membersCollectionOldMembers : membersCollectionOld) {
                if (!membersCollectionNew.contains(membersCollectionOldMembers)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Members " + membersCollectionOldMembers + " since its fkrole field is not nullable.");
                }
            }
            for (Permissions permissionsCollectionOldPermissions : permissionsCollectionOld) {
                if (!permissionsCollectionNew.contains(permissionsCollectionOldPermissions)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Permissions " + permissionsCollectionOldPermissions + " since its fkrole field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new TechDecision2Erreurs(new IllegalOrphanException(illegalOrphanMessages),14);
            }
            List<Roleslink> attachedRoleslinkCollectionNew = new ArrayList<Roleslink>();
            for (Roleslink roleslinkCollectionNewRoleslinkToAttach : roleslinkCollectionNew) {
                roleslinkCollectionNewRoleslinkToAttach = em.getReference(roleslinkCollectionNewRoleslinkToAttach.getClass(), roleslinkCollectionNewRoleslinkToAttach.getPkrolelink());
                attachedRoleslinkCollectionNew.add(roleslinkCollectionNewRoleslinkToAttach);
            }
            roleslinkCollectionNew = attachedRoleslinkCollectionNew;
            role.setRoleslinkCollection(roleslinkCollectionNew);
            List<Roleslink> attachedRoleslinkCollection1New = new ArrayList<Roleslink>();
            for (Roleslink roleslinkCollection1NewRoleslinkToAttach : roleslinkCollection1New) {
                roleslinkCollection1NewRoleslinkToAttach = em.getReference(roleslinkCollection1NewRoleslinkToAttach.getClass(), roleslinkCollection1NewRoleslinkToAttach.getPkrolelink());
                attachedRoleslinkCollection1New.add(roleslinkCollection1NewRoleslinkToAttach);
            }
            roleslinkCollection1New = attachedRoleslinkCollection1New;
            role.setRoleslinkCollection1(roleslinkCollection1New);
            List<Members> attachedMembersCollectionNew = new ArrayList<Members>();
            for (Members membersCollectionNewMembersToAttach : membersCollectionNew) {
                membersCollectionNewMembersToAttach = em.getReference(membersCollectionNewMembersToAttach.getClass(), membersCollectionNewMembersToAttach.getPkmember());
                attachedMembersCollectionNew.add(membersCollectionNewMembersToAttach);
            }
            membersCollectionNew = attachedMembersCollectionNew;
            role.setMembersCollection(membersCollectionNew);
            List<Permissions> attachedPermissionsCollectionNew = new ArrayList<Permissions>();
            for (Permissions permissionsCollectionNewPermissionsToAttach : permissionsCollectionNew) {
                permissionsCollectionNewPermissionsToAttach = em.getReference(permissionsCollectionNewPermissionsToAttach.getClass(), permissionsCollectionNewPermissionsToAttach.getPkpermissions());
                attachedPermissionsCollectionNew.add(permissionsCollectionNewPermissionsToAttach);
            }
            permissionsCollectionNew = attachedPermissionsCollectionNew;
            role.setPermissionsCollection(permissionsCollectionNew);
            role = em.merge(role);
            for (Roleslink roleslinkCollectionNewRoleslink : roleslinkCollectionNew) {
                if (!roleslinkCollectionOld.contains(roleslinkCollectionNewRoleslink)) {
                    Role oldFkparentroleOfRoleslinkCollectionNewRoleslink = roleslinkCollectionNewRoleslink.getFkparentrole();
                    roleslinkCollectionNewRoleslink.setFkparentrole(role);
                    roleslinkCollectionNewRoleslink = em.merge(roleslinkCollectionNewRoleslink);
                    if (oldFkparentroleOfRoleslinkCollectionNewRoleslink != null && !oldFkparentroleOfRoleslinkCollectionNewRoleslink.equals(role)) {
                        oldFkparentroleOfRoleslinkCollectionNewRoleslink.getRoleslinkCollection().remove(roleslinkCollectionNewRoleslink);
                        oldFkparentroleOfRoleslinkCollectionNewRoleslink = em.merge(oldFkparentroleOfRoleslinkCollectionNewRoleslink);
                    }
                }
            }
            for (Roleslink roleslinkCollection1NewRoleslink : roleslinkCollection1New) {
                if (!roleslinkCollection1Old.contains(roleslinkCollection1NewRoleslink)) {
                    Role oldFkchildroleOfRoleslinkCollection1NewRoleslink = roleslinkCollection1NewRoleslink.getFkchildrole();
                    roleslinkCollection1NewRoleslink.setFkchildrole(role);
                    roleslinkCollection1NewRoleslink = em.merge(roleslinkCollection1NewRoleslink);
                    if (oldFkchildroleOfRoleslinkCollection1NewRoleslink != null && !oldFkchildroleOfRoleslinkCollection1NewRoleslink.equals(role)) {
                        oldFkchildroleOfRoleslinkCollection1NewRoleslink.getRoleslinkCollection1().remove(roleslinkCollection1NewRoleslink);
                        oldFkchildroleOfRoleslinkCollection1NewRoleslink = em.merge(oldFkchildroleOfRoleslinkCollection1NewRoleslink);
                    }
                }
            }
            for (Members membersCollectionNewMembers : membersCollectionNew) {
                if (!membersCollectionOld.contains(membersCollectionNewMembers)) {
                    Role oldFkroleOfMembersCollectionNewMembers = membersCollectionNewMembers.getFkrole();
                    membersCollectionNewMembers.setFkrole(role);
                    membersCollectionNewMembers = em.merge(membersCollectionNewMembers);
                    if (oldFkroleOfMembersCollectionNewMembers != null && !oldFkroleOfMembersCollectionNewMembers.equals(role)) {
                        oldFkroleOfMembersCollectionNewMembers.getMembersCollection().remove(membersCollectionNewMembers);
                        oldFkroleOfMembersCollectionNewMembers = em.merge(oldFkroleOfMembersCollectionNewMembers);
                    }
                }
            }
            for (Permissions permissionsCollectionNewPermissions : permissionsCollectionNew) {
                if (!permissionsCollectionOld.contains(permissionsCollectionNewPermissions)) {
                    Role oldFkroleOfPermissionsCollectionNewPermissions = permissionsCollectionNewPermissions.getFkrole();
                    permissionsCollectionNewPermissions.setFkrole(role);
                    permissionsCollectionNewPermissions = em.merge(permissionsCollectionNewPermissions);
                    if (oldFkroleOfPermissionsCollectionNewPermissions != null && !oldFkroleOfPermissionsCollectionNewPermissions.equals(role)) {
                        oldFkroleOfPermissionsCollectionNewPermissions.getPermissionsCollection().remove(permissionsCollectionNewPermissions);
                        oldFkroleOfPermissionsCollectionNewPermissions = em.merge(oldFkroleOfPermissionsCollectionNewPermissions);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = role.getPkrole();
                if (findRole(id) == null) {
                    throw new TechDecision2Erreurs(new NonexistentEntityException("The role with id " + id + " no longer exists."),15);
                }
            }
            throw new TechDecision2Erreurs(ex,16);
        } finally {
        }
    }

    public Role findRole(Integer id) {
        try {
            return em.find(Role.class, id);
        } finally {
        }
    }

    public List<Role> findRoleEntities() {
        return findRoleEntities(true, -1, -1);
    }

    public List<Role> findRoleEntities(int maxResults, int firstResult) {
        return findRoleEntities(false, maxResults, firstResult);
    }

    public int getRoleCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Role as o").getSingleResult()).intValue();
        } finally {
        }
    }
    
    private List<Role> findRoleEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Role as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }
}
