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
package techDecision.dao.objshblt;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.ObjsHblt;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ObjsHbltJpaSimple {

    private EntityManager em = null;

    ObjsHbltJpaSimple(EntityManager emm) {
        em = emm;
    }
    public void create(ObjsHblt objsHblt) throws RollbackFailureException, Exception {
        try {
            em.persist(objsHblt);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(ObjsHblt objsHblt) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            objsHblt = em.merge(objsHblt);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = objsHblt.getPkobjsHblt();
                if (findObjsHblt(id) == null) {
                    throw new NonexistentEntityException("The objsHblt with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            ObjsHblt objsHblt;
            try {
                objsHblt = em.getReference(ObjsHblt.class, id);
                objsHblt.getPkobjsHblt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objsHblt with id " + id + " no longer exists.", enfe);
            }
            em.remove(objsHblt);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<ObjsHblt> findObjsHbltEntities() {
        return findObjsHbltEntities(true, -1, -1);
    }

    public List<ObjsHblt> findObjsHbltEntities(int maxResults, int firstResult) {
        return findObjsHbltEntities(false, maxResults, firstResult);
    }

    private List<ObjsHblt> findObjsHbltEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from ObjsHblt as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public ObjsHblt findObjsHblt(Integer id) {
        try {
            return em.find(ObjsHblt.class, id);
        } finally {
        }
    }

    public Long getObjsHbltCount() {
        try {
            return ((Long) em.createQuery("select count(o) from ObjsHblt as o").getSingleResult()).longValue();
        } finally {
        }
    }
    public List<ObjsHblt> relatedHblt(int idObjs){
        List<ObjsHblt> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM OBJS_HBLT WHERE FKOBJS = ?fkobjs", ObjsHblt.class);
            qHbltVal.setParameter("fkobjs", idObjs);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
        }
        return ret;
    }
    public List<ObjsHblt> relatedObjs(int idHblt){
        List<ObjsHblt> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM OBJS_HBLT WHERE FKHBLT = ?fkhblt", ObjsHblt.class);
            qHbltVal.setParameter("fkhblt", idHblt);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
        }
        return ret;
    }
}
