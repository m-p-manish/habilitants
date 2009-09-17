/*
Copyright Stéphane Georges Popoff, (mai 2009)

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
package techDecision2.dao.permissions;
import java.util.List;
import java.util.ArrayList;
import javax.ejb.Stateless;
import techDecision2.exception.*;
import techDecision2.entites.Permissions;
import techDecision2.entites.Role;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Query;

import javax.ejb.Stateless;

/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless
public class PermissionsBean implements PermissionsRemote, PermissionsLocal {
    @PersistenceContext(unitName="techDecisionEJB2PU")
    private EntityManager em;
    public void create(Permissions permissions) throws PreexistingEntityException, RollbackFailureException, Exception {
        try {
            Role fkrole = permissions.getFkrole();
            if (fkrole != null) {
                fkrole = em.getReference(fkrole.getClass(), fkrole.getPkrole());
                permissions.setFkrole(fkrole);
            }
            em.persist(permissions);
            if (fkrole != null) {
                fkrole.getPermissionsCollection().add(permissions);
                fkrole = em.merge(fkrole);
            }
        } catch (Exception ex) {
            if (findPermissions(permissions.getPkpermissions()) != null) {
                throw new PreexistingEntityException("Permissions " + permissions + " already exists.", ex);
            }
            throw ex;
        } finally {
        }
    }

    public void edit(Permissions permissions) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Permissions persistentPermissions = em.find(Permissions.class, permissions.getPkpermissions());
            Role fkroleOld = persistentPermissions.getFkrole();
            Role fkroleNew = permissions.getFkrole();
            if (fkroleNew != null) {
                fkroleNew = em.getReference(fkroleNew.getClass(), fkroleNew.getPkrole());
                permissions.setFkrole(fkroleNew);
            }
            permissions = em.merge(permissions);
            if (fkroleOld != null && !fkroleOld.equals(fkroleNew)) {
                fkroleOld.getPermissionsCollection().remove(permissions);
                fkroleOld = em.merge(fkroleOld);
            }
            if (fkroleNew != null && !fkroleNew.equals(fkroleOld)) {
                fkroleNew.getPermissionsCollection().add(permissions);
                fkroleNew = em.merge(fkroleNew);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = permissions.getPkpermissions();
                if (findPermissions(id) == null) {
                    throw new NonexistentEntityException("The permissions with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Permissions permissions;
            try {
                permissions = em.getReference(Permissions.class, id);
                permissions.getPkpermissions();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The permissions with id " + id + " no longer exists.", enfe);
            }
            Role fkrole = permissions.getFkrole();
            if (fkrole != null) {
                fkrole.getPermissionsCollection().remove(permissions);
                fkrole = em.merge(fkrole);
            }
            em.remove(permissions);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<Permissions> findPermissionsEntities() {
        return findPermissionsEntities(true, -1, -1);
    }

    public List<Permissions> findPermissionsEntities(int maxResults, int firstResult) {
        return findPermissionsEntities(false, maxResults, firstResult);
    }

    private List<Permissions> findPermissionsEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Permissions as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public Permissions findPermissions(Integer id) {
        try {
            return em.find(Permissions.class, id);
        } finally {
        }
    }

    public int getPermissionsCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Permissions as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
 
}
