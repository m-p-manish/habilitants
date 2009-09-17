/*
Copyright Stéphane Georges Popoff, (mai - juin 2009)

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
package theCube.jpa;

import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceUnit;
import javax.transaction.UserTransaction;
import theCube.jpa.exceptions.NonexistentEntityException;
import theCube.jpa.exceptions.RollbackFailureException;
import theCube.entities.ObjsAttrs;
import theCube.entities.Objsecu;
/**
 * classe jpa qui gère la table des attributs des objets de sécurité pour datamart H2
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
public class ObjsAttrsJ3 {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "techDecisionCubePU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(ObjsAttrs objsAttrs) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            em.persist(objsAttrs);
            utx.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public void edit(ObjsAttrs objsAttrs) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            objsAttrs = em.merge(objsAttrs);
            utx.commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = objsAttrs.getPkattrObjs();
                if (findObjsAttrs(id) == null) {
                    throw new NonexistentEntityException("The objsAttrs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            em.close();
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            ObjsAttrs objsAttrs;
            try {
                objsAttrs = em.getReference(ObjsAttrs.class, id);
                objsAttrs.getPkattrObjs();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objsAttrs with id " + id + " no longer exists.", enfe);
            }
            Objsecu fkobjs = objsAttrs.getFkobjs();
            if (fkobjs != null) {
                fkobjs.getObjsAttrsCollection().remove(objsAttrs);
                fkobjs = em.merge(fkobjs);
            }
            utx.begin();
            em.remove(objsAttrs);
            utx.commit();
        } catch (Exception ex) {
            throw ex;
        } finally {
            em.close();
        }
    }

    public List<ObjsAttrs> findObjsAttrsEntities() {
        return findObjsAttrsEntities(true, -1, -1);
    }

    public List<ObjsAttrs> findObjsAttrsEntities(int maxResults, int firstResult) {
        return findObjsAttrsEntities(false, maxResults, firstResult);
    }

    private List<ObjsAttrs> findObjsAttrsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query q = em.createNativeQuery("SELECT * FROM OBJS_ATTRS", ObjsAttrs.class);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public ObjsAttrs findObjsAttrs(Integer id) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            return em.find(ObjsAttrs.class, id);
        } finally {
            em.close();
        }
    }

    public int getObjsAttrsCount() {
        EntityManager em = null;
        try {
            em = getEntityManager();
            return ((Long) em.createQuery("select count(o) from ObjsAttrs as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    /**
     * Tous les attributs d'un objet de sécurité
     * @param objsId
     * @return
     */
    public List<ObjsAttrs> relatedAttrs(int objsId){
        List<ObjsAttrs> ret = null;
        EntityManager em = null;
        try {
            em = getEntityManager();
            ret = lesAttrs(objsId, em);
        } catch(Exception err) {
            String s = err.toString();
            System.err.println("Erreur relatedAttrs "+s);
        }
        if(em!=null){
            em.close();
        }
        return ret;
    }
    /**
     * Tous les attributs d'un objet de sécurité
     * @param objsId
     * @param em l'entity manager passé en référence
     * @return
     */
    public List<ObjsAttrs> relatedAttrs(int objsId, EntityManager em){
        return lesAttrs(objsId, em);
    }
     /**
     * Tous les attributs d'un objet de sécurité
     * @param objsId
     * @return
     */
    private List<ObjsAttrs> lesAttrs(int objsId, EntityManager em){
        List<ObjsAttrs> ret = null;
        try {
            //em = getEntityManager();
            Query qHbltVal = em.createNativeQuery("SELECT * FROM SPOPOFF.OBJS_ATTRS WHERE FKOBJS = ?objsID", ObjsAttrs.class);
            qHbltVal.setParameter("objsID", objsId);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            String s = err.toString();
            System.err.println("Erreur lesAttrs "+s);
        }
         return ret;
    }
   /**
     * Vérifie si un objet de sécurité possède l'attribut SOMMETRAP
     * qui en fait un rôle applicatif parent
     * @param objsId l'identifiant d rôle
     * @return vrai ou faux
     */
    public Boolean isSommet(int objsId){
        Boolean ret = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query qHbltVal = em.createNativeQuery("SELECT * FROM SPOPOFF.OBJS_ATTRS WHERE FKOBJS = ?objsID AND ATTR='SOMMETRAP' AND VAL='VRAI'", ObjsAttrs.class);
            qHbltVal.setParameter("objsID", objsId);
            Object retour = qHbltVal.getSingleResult();
            if(retour !=null){
                ret = true;
            }
        } catch(Exception err) {
            String s = err.toString();
        }
        if(em!=null){
            em.close();
        }
        return ret;
    }
    /**
     * Vérifie si un objet de sécurité possède l'attribut ENFANTRAP
     * qui en fait un rôle applicatif enfant d'un parent
     * @param objsId l'identifiant d rôle
     * @return vrai ou faux
     */
    public Boolean isEnfant(int objsId){
        Boolean ret = false;
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query qHbltVal = em.createNativeQuery("SELECT * FROM SPOPOFF.OBJS_ATTRS WHERE FKOBJS = ?objsID AND ATTR='ENFANTRAP' AND VAL='VRAI'", ObjsAttrs.class);
            qHbltVal.setParameter("objsID", objsId);
            Object retour = qHbltVal.getSingleResult();
            if(retour !=null){
                ret = true;
            }
        } catch(Exception err) {
            String s = err.toString();
        }
        if(em!=null){
            em.close();
        }
        return ret;
    }
    /**
     * Tous les attributs d'un objet de sécurité
     * @param objsId
     * @return une liste le cas échéant
     */
    public List<ObjsAttrs> enfantList(int objsId){
        List<ObjsAttrs> ret = new LinkedList<ObjsAttrs>();
        EntityManager em = null;
        try {
            em = getEntityManager();
            Query qHbltVal = em.createNativeQuery("SELECT * FROM SPOPOFF.OBJS_ATTRS WHERE ATTR = 'ENFANTID' AND VAL = '?objsID'", ObjsAttrs.class);
            qHbltVal.setParameter("objsID", objsId);
            ret.addAll((List<ObjsAttrs>)qHbltVal.getResultList());
        } catch(Exception err) {
            String s = err.toString();
        }
        if(em!=null){
            em.close();
        }
        return ret;
    }
    /**
     * retrouve un seul attribut d'objs qui référence le lien vers un enfant de cet
     * objs
     * @param objsId l'identifiant de l'enfant
     * @param objsPar l'identifiant du parent
     * @param emm l'entity manager un autre
     * @return
     */
    public ObjsAttrs enfantObjs(int objsId, int objsPar, EntityManager emm){
        ObjsAttrs ret = null;
        try {
            Query qHbltVal = emm.createNativeQuery("SELECT * FROM SPOPOFF.OBJS_ATTRS WHERE ATTR = 'ENFANTID' AND VAL = ?objsID AND FKOBJS = ?objsPAR", ObjsAttrs.class);
            qHbltVal.setParameter("objsID", objsId);
            qHbltVal.setParameter("objsPAR", objsPar);
            ret = (ObjsAttrs) qHbltVal.getSingleResult();
        } catch(Exception err) {
            System.err.println("Erreur enfantObjs "+err.toString());
        }
        return ret;
    }

}
