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
package techDecision2.dao.rolelink;

import javax.ejb.Stateless;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.Stateless;
import javax.persistence.Query;
import techDecision2.entites.Roleslink;
import techDecision2.exception.*;
import javax.persistence.EntityNotFoundException;
import techDecision2.entites.Role;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import techDecision2.tools.UniqueArrayList;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class RoleLinkBean implements RoleLinkRemote, RoleLinkLocal {
    @PersistenceContext(unitName="techDecisionEJB2PU")
    private EntityManager em;
    private List<Roleslink> deepRole = null;

    public void create(Roleslink roleslink) throws TechDecision2Erreurs, Exception {
        try {
            Role fkparentrole = roleslink.getFkparentrole();
            if (fkparentrole != null) {
                fkparentrole = em.getReference(fkparentrole.getClass(), fkparentrole.getPkrole());
                roleslink.setFkparentrole(fkparentrole);
            }
            Role fkchildrole = roleslink.getFkchildrole();
            if (fkchildrole != null) {
                fkchildrole = em.getReference(fkchildrole.getClass(), fkchildrole.getPkrole());
                roleslink.setFkchildrole(fkchildrole);
            }
            em.persist(roleslink);
            if (fkparentrole != null) {
                fkparentrole.getRoleslinkCollection().add(roleslink);
                fkparentrole = em.merge(fkparentrole);
            }
            if (fkchildrole != null) {
                fkchildrole.getRoleslinkCollection().add(roleslink);
                fkchildrole = em.merge(fkchildrole);
            }
        } catch (Exception ex) {
            if (findRoleslink(roleslink.getPkrolelink()) != null) {
                throw new TechDecision2Erreurs(new PreexistingEntityException("Roleslink " + roleslink + " already exists.", ex),17);
            }
            throw new TechDecision2Erreurs(ex,17);
        } finally {
        }
    }

    public void destroy(Integer id) throws TechDecision2Erreurs, Exception {
        try {
            Roleslink roleslink;
            try {
                roleslink = em.getReference(Roleslink.class, id);
                roleslink.getPkrolelink();
            } catch (EntityNotFoundException enfe) {
                throw new TechDecision2Erreurs(new NonexistentEntityException("The roleslink with id " + id + " no longer exists.", enfe),18);
            }
            Role fkparentrole = roleslink.getFkparentrole();
            if (fkparentrole != null) {
                fkparentrole.getRoleslinkCollection().remove(roleslink);
                fkparentrole = em.merge(fkparentrole);
            }
            Role fkchildrole = roleslink.getFkchildrole();
            if (fkchildrole != null) {
                fkchildrole.getRoleslinkCollection().remove(roleslink);
                fkchildrole = em.merge(fkchildrole);
            }
            em.remove(roleslink);
        } catch (Exception ex) {
            throw new TechDecision2Erreurs(ex,19);
        } finally {
        }
    }

    public void edit(Roleslink roleslink) throws TechDecision2Erreurs, Exception {
        try {
            Roleslink persistentRoleslink = em.find(Roleslink.class, roleslink.getPkrolelink());
            Role fkparentroleOld = persistentRoleslink.getFkparentrole();
            Role fkparentroleNew = roleslink.getFkparentrole();
            Role fkchildroleOld = persistentRoleslink.getFkchildrole();
            Role fkchildroleNew = roleslink.getFkchildrole();
            if (fkparentroleNew != null) {
                fkparentroleNew = em.getReference(fkparentroleNew.getClass(), fkparentroleNew.getPkrole());
                roleslink.setFkparentrole(fkparentroleNew);
            }
            if (fkchildroleNew != null) {
                fkchildroleNew = em.getReference(fkchildroleNew.getClass(), fkchildroleNew.getPkrole());
                roleslink.setFkchildrole(fkchildroleNew);
            }
            roleslink = em.merge(roleslink);
            if (fkparentroleOld != null && !fkparentroleOld.equals(fkparentroleNew)) {
                fkparentroleOld.getRoleslinkCollection().remove(roleslink);
                fkparentroleOld = em.merge(fkparentroleOld);
            }
            if (fkparentroleNew != null && !fkparentroleNew.equals(fkparentroleOld)) {
                fkparentroleNew.getRoleslinkCollection().add(roleslink);
                fkparentroleNew = em.merge(fkparentroleNew);
            }
            if (fkchildroleOld != null && !fkchildroleOld.equals(fkchildroleNew)) {
                fkchildroleOld.getRoleslinkCollection().remove(roleslink);
                fkchildroleOld = em.merge(fkchildroleOld);
            }
            if (fkchildroleNew != null && !fkchildroleNew.equals(fkchildroleOld)) {
                fkchildroleNew.getRoleslinkCollection().add(roleslink);
                fkchildroleNew = em.merge(fkchildroleNew);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = roleslink.getPkrolelink();
                if (findRoleslink(id) == null) {
                    throw new TechDecision2Erreurs(new NonexistentEntityException("The roleslink with id " + id + " no longer exists."),20);
                }
            }
            throw new TechDecision2Erreurs(ex,21);
        } finally {
        }
    }

    public Roleslink findRoleslink(Integer id) {
        try {
            return em.find(Roleslink.class, id);
        } finally {
        }
    }

    public List<Roleslink> findRoleslinkEntities() {
        return findRoleslinkEntities(true, -1, -1);
    }

    public List<Roleslink> findRoleslinkEntities(int maxResults, int firstResult) {
        return findRoleslinkEntities(false, maxResults, firstResult);
    }

    public int getRoleslinkCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Roleslink as o").getSingleResult()).intValue();
        } finally {
        }
    }

    public List<Roleslink> relatedRole(int roleId)  throws TechDecision2Erreurs{
        List<Roleslink> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM ROLESLINK WHERE FKPARENTROLE = ?fkPRole", Roleslink.class);
            qHbltVal.setParameter("fkPRole", roleId);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            throw new TechDecision2Erreurs(err,92);
        }
        return ret;
    }

    public List<Roleslink> relatedRoleDeep(int roleId, boolean start)  throws TechDecision2Erreurs{
        if(start) deepRole = new UniqueArrayList();
        for(Roleslink unRole : relatedRole(roleId)){
            if(deepRole.add(unRole)){
                relatedRoleDeep(unRole.getFkchildrole().getPkrole(),false);
            }else{
                throw new TechDecision2Erreurs("Erreur liaison ciculaire de role",92);
            }
        }
        return deepRole;
    }

    private List<Roleslink> findRoleslinkEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Roleslink as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

}
