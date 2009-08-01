/*
Copyright Stéphane Georges Popoff, (mars 2009)

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
package techDecision.dao.idntcpte;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.IdntCpte;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IdntCpteJpaSimple {

    private EntityManager em = null;

    IdntCpteJpaSimple(EntityManager emm) {
        em = emm;
    }
    public void create(IdntCpte idntCpte) throws RollbackFailureException, Exception {
        try {
            em.persist(idntCpte);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(IdntCpte idntCpte) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            idntCpte = em.merge(idntCpte);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = idntCpte.getPkidntCpte();
                if (findIdntCpte(id) == null) {
                    throw new NonexistentEntityException("The idntCpte with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            IdntCpte idntCpte;
            try {
                idntCpte = em.getReference(IdntCpte.class, id);
                idntCpte.getPkidntCpte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The idntCpte with id " + id + " no longer exists.", enfe);
            }
            em.remove(idntCpte);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<IdntCpte> findIdntCpteEntities() {
        return findIdntCpteEntities(true, -1, -1);
    }

    public List<IdntCpte> findIdntCpteEntities(int maxResults, int firstResult) {
        return findIdntCpteEntities(false, maxResults, firstResult);
    }

    private List<IdntCpte> findIdntCpteEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from IdntCpte as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public IdntCpte findIdntCpte(Integer id) {
        try {
            return em.find(IdntCpte.class, id);
        } finally {
        }
    }

    public Long getIdntCpteCount() {
        try {
            return ((Long) em.createQuery("select count(o) from IdntCpte as o").getSingleResult()).longValue();
        } finally {
        }
    }
    public List<IdntCpte> relatedCpte(int idIdnt){
        List<IdntCpte> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM IDNT_CPTE WHERE FKIDNT = ?fkidnt", IdntCpte.class);
            qHbltVal.setParameter("fkidnt", idIdnt);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
        }
        return ret;
    }
    public List<IdntCpte> relatedIdnt(int idCpte){
        List<IdntCpte> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM IDNT_CPTE WHERE FKCPTE = ?fkcpte", IdntCpte.class);
            qHbltVal.setParameter("fkcpte", idCpte);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
        }
        return ret;
    }

}
