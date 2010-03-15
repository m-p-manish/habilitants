/*
Copyright Stéphane Georges Popoff, (février 2009 - mars 2010)

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
package techDecision.dao;

import java.util.List;
//import javax.annotation.Resource;
import javax.persistence.EntityManager;
//import javax.persistence.EntityManagerFactory;
//import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
//import javax.transaction.UserTransaction;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.ObjsAttrs;
import techDecision.entites.Objsecu;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ObjsAttrsJpaController {

    private EntityManager em = null;

    ObjsAttrsJpaController(EntityManager emm) {
        em = emm;
    }

    public void create(ObjsAttrs objsAttrs) throws RollbackFailureException, Exception {
        try {
            Objsecu fkobjs = objsAttrs.getFkobjs();
            if (fkobjs != null) {
                fkobjs = em.getReference(fkobjs.getClass(), fkobjs.getPkobjs());
                objsAttrs.setFkobjs(fkobjs);
            }
            em.persist(objsAttrs);
            if (fkobjs != null) {
                fkobjs.getObjsAttrsCollection().add(objsAttrs);
                fkobjs = em.merge(fkobjs);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(ObjsAttrs objsAttrs) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            ObjsAttrs persistentObjsAttrs = em.find(ObjsAttrs.class, objsAttrs.getPkattrObjs());
            Objsecu fkobjsOld = persistentObjsAttrs.getFkobjs();
            Objsecu fkobjsNew = objsAttrs.getFkobjs();
            if (fkobjsNew != null) {
                fkobjsNew = em.getReference(fkobjsNew.getClass(), fkobjsNew.getPkobjs());
                objsAttrs.setFkobjs(fkobjsNew);
            }
            objsAttrs = em.merge(objsAttrs);
            if (fkobjsOld != null && !fkobjsOld.equals(fkobjsNew)) {
                fkobjsOld.getObjsAttrsCollection().remove(objsAttrs);
                fkobjsOld = em.merge(fkobjsOld);
            }
            if (fkobjsNew != null && !fkobjsNew.equals(fkobjsOld)) {
                fkobjsNew.getObjsAttrsCollection().add(objsAttrs);
                fkobjsNew = em.merge(fkobjsNew);
            }
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
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
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
            em.remove(objsAttrs);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<ObjsAttrs> findObjsAttrsEntities() {
        return findObjsAttrsEntities(true, -1, -1);
    }

    public List<ObjsAttrs> findObjsAttrsEntities(int maxResults, int firstResult) {
        return findObjsAttrsEntities(false, maxResults, firstResult);
    }

    private List<ObjsAttrs> findObjsAttrsEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from ObjsAttrs as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public ObjsAttrs findObjsAttrs(Integer id) {
        try {
            return em.find(ObjsAttrs.class, id);
        } finally {
        }
    }

    public Long getObjsAttrsCount() {
        try {
            return ((Long) em.createQuery("select count(o) from ObjsAttrs as o").getSingleResult()).longValue();
        } finally {
        }
    }
    /**
     * Vérifie la présence d'une application sur son hash
     * @param sNom
     * @return vrai=présent, faux=absent
     */
    public Boolean existNomAppli(String sNom){
        Object o = null;
        try{
            Query q = em.createNamedQuery("ObjsAttrs.findAppli");
            q.setParameter("val", sNom);
            o = q.getSingleResult();
        }catch(Exception err){
            System.out.println("existe pas appli="+sNom);
            return false;
        }
        if(o==null){
            return false;
        }
        return true;
    }
    /**
     * retourne la clé d'une application depuis son hash
     * @param sNom
     * @return pkobjs clé de l'application
     */
    public Integer getPkAppli(String sNom){
        ObjsAttrs o = null;
        try{
            Query q = em.createNamedQuery("ObjsAttrs.findAppli");
            q.setParameter("val", sNom);
            o = (ObjsAttrs) q.getSingleResult();
        }catch(Exception err){
            System.out.println("pas trouvé appli="+sNom+" "+err.toString());
            return null;
        }
        if(o==null){
            return null;
        }
        return o.getFkobjs().getPkobjs();
    }
    public void truncate(){
        try {
            Query q = em.createNativeQuery("truncate OBJS_ATTRS");
            ObjsAttrs o = (ObjsAttrs) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("On s'en fout de l'erreur truncate OBJS_ATTRS");
        }
    }
}
