/*
Copyright Stéphane Georges Popoff, (mars 2009 - mars 2010)

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
package techDecision.dao.objscpte;

import java.util.Iterator;
import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.ObjsCpte;

/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ObjsCpteJpaSimple {

    private EntityManager em = null;

    ObjsCpteJpaSimple(EntityManager emm) {
        em = emm;
    }
    public void create(ObjsCpte objsCpte) throws RollbackFailureException, Exception {
        try {
            em.persist(objsCpte);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(ObjsCpte objsCpte) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            objsCpte = em.merge(objsCpte);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = objsCpte.getPkobjsCpte();
                if (findObjsCpte(id) == null) {
                    throw new NonexistentEntityException("The objsCpte with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            ObjsCpte objsCpte;
            try {
                objsCpte = em.getReference(ObjsCpte.class, id);
                objsCpte.getPkobjsCpte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objsCpte with id " + id + " no longer exists.", enfe);
            }
            em.remove(objsCpte);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<ObjsCpte> findObjsCpteEntities() {
        return findObjsCpteEntities(true, -1, -1);
    }

    public List<ObjsCpte> findObjsCpteEntities(int maxResults, int firstResult) {
        return findObjsCpteEntities(false, maxResults, firstResult);
    }

    private List<ObjsCpte> findObjsCpteEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from ObjsCpte as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public ObjsCpte findObjsCpte(Integer id) {
        try {
            return em.find(ObjsCpte.class, id);
        } finally {
        }
    }

    public Long getObjsCpteCount() {
        try {
            return ((Long) em.createQuery("select count(o) from ObjsCpte as o").getSingleResult()).longValue();
        } finally {
        }
    }
    public List<ObjsCpte> relatedObjs(int cpteId){
        List<ObjsCpte> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM OBJS_CPTE WHERE FKCPTE = ?fkcpte", ObjsCpte.class);
            qHbltVal.setParameter("fkcpte", cpteId);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
        }
        return ret;
    }
    public List<ObjsCpte> relatedCpte(int objsId){
        List<ObjsCpte> ret = null;
        try {
            Query qHbltVal = em.createNativeQuery("SELECT * FROM OBJS_CPTE WHERE FKOBJS = ?fkobjs", ObjsCpte.class);
            qHbltVal.setParameter("fkobjs", objsId);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
        }
        return ret;
    }
    public void truncate(){
        try {
            Query q = em.createNativeQuery("truncate OBJS_CPTE");
            ObjsCpte o = (ObjsCpte) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("On s'en fout de l'erreur truncate OBJS_CPTE");
        }
    }

}
