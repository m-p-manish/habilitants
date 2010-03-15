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
package techDecision.dao;

import java.util.List;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Habilitant;
import techDecision.entites.ObjsHblt;
import techDecision.entites.Objsecu;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ObjsHbltJpaController {

    private EntityManager em = null;

    ObjsHbltJpaController(EntityManager emm) {
        em = emm;
    }
    public void create(ObjsHblt objsHblt) throws Exception {
        try {
            em.persist(objsHblt);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void create(int pk, int idObjs, int idHblt) throws Exception {
        try {
            ObjsHblt objsHblt = new ObjsHblt(pk);
            System.out.println("fait objshblt");
            Objsecu objs = em.getReference(Objsecu.class, idObjs);
            System.out.println("trouvé objs");
            Habilitant hblt = em.getReference(Habilitant.class, idHblt);
            System.out.println("trouvé hblt");
            objsHblt.setFkhblt(hblt);
            System.out.println("lié hblt");
            objsHblt.setFkobjs(objs);
            System.out.println("lié objs");
            em.persist(objsHblt);
            System.out.println("persisté objshblt");
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(ObjsHblt objsHblt) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            ObjsHblt persistentObjsHblt = em.find(ObjsHblt.class, objsHblt.getPkobjsHblt());
            Habilitant fkhbltOld = persistentObjsHblt.getFkhblt();
            Habilitant fkhbltNew = objsHblt.getFkhblt();
            Objsecu fkobjsOld = persistentObjsHblt.getFkobjs();
            Objsecu fkobjsNew = objsHblt.getFkobjs();
            if (fkhbltNew != null) {
                fkhbltNew = em.getReference(fkhbltNew.getClass(), fkhbltNew.getPkhblt());
                objsHblt.setFkhblt(fkhbltNew);
            }
            if (fkobjsNew != null) {
                fkobjsNew = em.getReference(fkobjsNew.getClass(), fkobjsNew.getPkobjs());
                objsHblt.setFkobjs(fkobjsNew);
            }
            objsHblt = em.merge(objsHblt);
            if (fkhbltOld != null && !fkhbltOld.equals(fkhbltNew)) {
                fkhbltOld.getObjsHbltCollection().remove(objsHblt);
                fkhbltOld = em.merge(fkhbltOld);
            }
            if (fkhbltNew != null && !fkhbltNew.equals(fkhbltOld)) {
                fkhbltNew.getObjsHbltCollection().add(objsHblt);
                fkhbltNew = em.merge(fkhbltNew);
            }
            if (fkobjsOld != null && !fkobjsOld.equals(fkobjsNew)) {
                fkobjsOld.getObjsHbltCollection().remove(objsHblt);
                fkobjsOld = em.merge(fkobjsOld);
            }
            if (fkobjsNew != null && !fkobjsNew.equals(fkobjsOld)) {
                fkobjsNew.getObjsHbltCollection().add(objsHblt);
                fkobjsNew = em.merge(fkobjsNew);
            }
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
            Habilitant fkhblt = objsHblt.getFkhblt();
            if (fkhblt != null) {
                fkhblt.getObjsHbltCollection().remove(objsHblt);
                fkhblt = em.merge(fkhblt);
            }
            Objsecu fkobjs = objsHblt.getFkobjs();
            if (fkobjs != null) {
                fkobjs.getObjsHbltCollection().remove(objsHblt);
                fkobjs = em.merge(fkobjs);
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
    public Boolean existHblt4Objs(Integer idCpte, Integer idObjs){
        Boolean ret  = false;
        Object r = null;
        try {
            Query q = em.createQuery("select object(o) from ObjsHblt as o where fkcpte = :fkcpte and fkobjs = :fkobjs");
            q.setParameter("fkcpte", idCpte);
            q.setParameter("fkobjs", idObjs);
            r = q.getSingleResult();
        } catch(Exception err) {
            return ret;
        }
        if(r!=null){
            ret = true;
        }
        return ret;
    }
    public void truncate(){
        try {
            Query q = em.createNativeQuery("truncate OBJS_HBLT");
            ObjsHblt o = (ObjsHblt) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("On s'en fout de l'erreur truncate OBJS_HBLT");
        }
    }

}
