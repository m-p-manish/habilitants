/*
Copyright Stéphane Georges Popoff, (avril 2009 - mars 2010)

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
package techDecision.dao.idntattr;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Identite;
import techDecision.entites.IdntAttrs;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IdntAttrsJpaSimple {

    private EntityManager em = null;

    IdntAttrsJpaSimple(EntityManager emm) {
        em = emm;
    }

    public void create(IdntAttrs idntAttrs) throws RollbackFailureException, Exception {
        try {
            em.persist(idntAttrs);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(IdntAttrs idntAttrs) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            idntAttrs = em.merge(idntAttrs);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = idntAttrs.getPkattrIdnt();
                if (findIdntAttrs(id) == null) {
                    throw new NonexistentEntityException("The idntAttrs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            IdntAttrs idntAttrs;
            try {
                idntAttrs = em.getReference(IdntAttrs.class, id);
                idntAttrs.getPkattrIdnt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The idntAttrs with id " + id + " no longer exists.", enfe);
            }
            Identite fkidnt = idntAttrs.getFkidnt();
            if (fkidnt != null) {
                fkidnt.getIdntAttrsCollection().remove(idntAttrs);
                fkidnt = em.merge(fkidnt);
            }
            em.remove(idntAttrs);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<IdntAttrs> findIdntAttrsEntities() {
        return findIdntAttrsEntities(true, -1, -1);
    }

    public List<IdntAttrs> findIdntAttrsEntities(int maxResults, int firstResult) {
        return findIdntAttrsEntities(false, maxResults, firstResult);
    }

    public List<IdntAttrs> findIdntAttrsEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from IdntAttrs as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public IdntAttrs findIdntAttrs(Integer id) {
        try {
            return em.find(IdntAttrs.class, id);
        } finally {
        }
    }

    public Long getIdntAttrsCount() {
        try {
            return ((Long) em.createQuery("select count(o) from IdntAttrs as o").getSingleResult()).longValue();
        } finally {
        }
    }
    public List<IdntAttrs> relatedAttrs(int idntId) {
        List<IdntAttrs> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM IDNT_ATTRS WHERE FKIDNT = ?idntId", IdntAttrs.class);
            qHbltVal.setParameter("idntId", idntId);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
            System.err.println(s);
        }
        return ret;
    }
    /**
     * trouve un attribut d'identité, son hash, donc son identitfiant unique
     * @param hashIdnt
     * @return
     */
    public IdntAttrs getId4IdntHash(String hashIdnt){
        IdntAttrs ret = null;
         try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM IDNT_ATTRS WHERE ATTR = 'HASHIDNT' AND VAL = ?hashIdnt", IdntAttrs.class);
            qHbltVal.setParameter("hashIdnt", hashIdnt);
            ret = (IdntAttrs) qHbltVal.getSingleResult();
        } catch(Exception err) {
            String s = err.toString();
            System.err.println(s);
        }
       return ret;
    }
}
