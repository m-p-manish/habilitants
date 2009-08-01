/*
Copyright Stéphane Georges Popoff, (février - juin 2009)

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
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.ObjsAttrs;
import java.util.ArrayList;
import java.util.Collection;
import techDecision.entites.ObjsCpte;
import techDecision.entites.ObjsHblt;
import techDecision.entites.Objsecu;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class ObjsecuJpaController {

    private EntityManager em = null;

    ObjsecuJpaController(EntityManager emm) {
        em = emm;
    }

    public void create(Objsecu objsecu) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (objsecu.getObjsAttrsCollection() == null) {
            objsecu.setObjsAttrsCollection(new ArrayList<ObjsAttrs>());
        }
        if (objsecu.getObjsCpteCollection() == null) {
            objsecu.setObjsCpteCollection(new ArrayList<ObjsCpte>());
        }
        if (objsecu.getObjsHbltCollection() == null) {
            objsecu.setObjsHbltCollection(new ArrayList<ObjsHblt>());
        }
        try {
            Collection<ObjsAttrs> attachedObjsAttrsCollection = new ArrayList<ObjsAttrs>();
            for (ObjsAttrs objsAttrsCollectionObjsAttrsToAttach : objsecu.getObjsAttrsCollection()) {
                objsAttrsCollectionObjsAttrsToAttach = em.getReference(objsAttrsCollectionObjsAttrsToAttach.getClass(), objsAttrsCollectionObjsAttrsToAttach.getPkattrObjs());
                attachedObjsAttrsCollection.add(objsAttrsCollectionObjsAttrsToAttach);
            }
            objsecu.setObjsAttrsCollection(attachedObjsAttrsCollection);
            Collection<ObjsCpte> attachedObjsCpteCollection = new ArrayList<ObjsCpte>();
            for (ObjsCpte objsCpteCollectionObjsCpteToAttach : objsecu.getObjsCpteCollection()) {
                objsCpteCollectionObjsCpteToAttach = em.getReference(objsCpteCollectionObjsCpteToAttach.getClass(), objsCpteCollectionObjsCpteToAttach.getPkobjsCpte());
                attachedObjsCpteCollection.add(objsCpteCollectionObjsCpteToAttach);
            }
            objsecu.setObjsCpteCollection(attachedObjsCpteCollection);
            Collection<ObjsHblt> attachedObjsHbltCollection = new ArrayList<ObjsHblt>();
            for (ObjsHblt objsHbltCollectionObjsHbltToAttach : objsecu.getObjsHbltCollection()) {
                objsHbltCollectionObjsHbltToAttach = em.getReference(objsHbltCollectionObjsHbltToAttach.getClass(), objsHbltCollectionObjsHbltToAttach.getPkobjsHblt());
                attachedObjsHbltCollection.add(objsHbltCollectionObjsHbltToAttach);
            }
            objsecu.setObjsHbltCollection(attachedObjsHbltCollection);
            em.persist(objsecu);
            for (ObjsAttrs objsAttrsCollectionObjsAttrs : objsecu.getObjsAttrsCollection()) {
                Objsecu oldFkobjsOfObjsAttrsCollectionObjsAttrs = objsAttrsCollectionObjsAttrs.getFkobjs();
                objsAttrsCollectionObjsAttrs.setFkobjs(objsecu);
                objsAttrsCollectionObjsAttrs = em.merge(objsAttrsCollectionObjsAttrs);
                if (oldFkobjsOfObjsAttrsCollectionObjsAttrs != null) {
                    oldFkobjsOfObjsAttrsCollectionObjsAttrs.getObjsAttrsCollection().remove(objsAttrsCollectionObjsAttrs);
                    oldFkobjsOfObjsAttrsCollectionObjsAttrs = em.merge(oldFkobjsOfObjsAttrsCollectionObjsAttrs);
                }
            }
            for (ObjsCpte objsCpteCollectionObjsCpte : objsecu.getObjsCpteCollection()) {
                Objsecu oldFkobjsOfObjsCpteCollectionObjsCpte = objsCpteCollectionObjsCpte.getFkobjs();
                objsCpteCollectionObjsCpte.setFkobjs(objsecu);
                objsCpteCollectionObjsCpte = em.merge(objsCpteCollectionObjsCpte);
                if (oldFkobjsOfObjsCpteCollectionObjsCpte != null) {
                    oldFkobjsOfObjsCpteCollectionObjsCpte.getObjsCpteCollection().remove(objsCpteCollectionObjsCpte);
                    oldFkobjsOfObjsCpteCollectionObjsCpte = em.merge(oldFkobjsOfObjsCpteCollectionObjsCpte);
                }
            }
            for (ObjsHblt objsHbltCollectionObjsHblt : objsecu.getObjsHbltCollection()) {
                Objsecu oldFkobjsOfObjsHbltCollectionObjsHblt = objsHbltCollectionObjsHblt.getFkobjs();
                objsHbltCollectionObjsHblt.setFkobjs(objsecu);
                objsHbltCollectionObjsHblt = em.merge(objsHbltCollectionObjsHblt);
                if (oldFkobjsOfObjsHbltCollectionObjsHblt != null) {
                    oldFkobjsOfObjsHbltCollectionObjsHblt.getObjsHbltCollection().remove(objsHbltCollectionObjsHblt);
                    oldFkobjsOfObjsHbltCollectionObjsHblt = em.merge(oldFkobjsOfObjsHbltCollectionObjsHblt);
                }
            }
        } catch (Exception ex) {
            if (findObjsecu(objsecu.getPkobjs()) != null) {
                throw new PreexistingEntityException("Objsecu " + objsecu + " already exists.", ex);
            }
            throw ex;
        } finally {
        }
    }

    public void edit(Objsecu objsecu) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Objsecu persistentObjsecu = em.find(Objsecu.class, objsecu.getPkobjs());
            Collection<ObjsAttrs> objsAttrsCollectionOld = persistentObjsecu.getObjsAttrsCollection();
            Collection<ObjsAttrs> objsAttrsCollectionNew = objsecu.getObjsAttrsCollection();
            Collection<ObjsCpte> objsCpteCollectionOld = persistentObjsecu.getObjsCpteCollection();
            Collection<ObjsCpte> objsCpteCollectionNew = objsecu.getObjsCpteCollection();
            Collection<ObjsHblt> objsHbltCollectionOld = persistentObjsecu.getObjsHbltCollection();
            Collection<ObjsHblt> objsHbltCollectionNew = objsecu.getObjsHbltCollection();
            List<String> illegalOrphanMessages = null;
            for (ObjsAttrs objsAttrsCollectionOldObjsAttrs : objsAttrsCollectionOld) {
                if (!objsAttrsCollectionNew.contains(objsAttrsCollectionOldObjsAttrs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ObjsAttrs " + objsAttrsCollectionOldObjsAttrs + " since its fkobjs field is not nullable.");
                }
            }
            for (ObjsCpte objsCpteCollectionOldObjsCpte : objsCpteCollectionOld) {
                if (!objsCpteCollectionNew.contains(objsCpteCollectionOldObjsCpte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ObjsCpte " + objsCpteCollectionOldObjsCpte + " since its fkobjs field is not nullable.");
                }
            }
            for (ObjsHblt objsHbltCollectionOldObjsHblt : objsHbltCollectionOld) {
                if (!objsHbltCollectionNew.contains(objsHbltCollectionOldObjsHblt)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ObjsHblt " + objsHbltCollectionOldObjsHblt + " since its fkobjs field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ObjsAttrs> attachedObjsAttrsCollectionNew = new ArrayList<ObjsAttrs>();
            for (ObjsAttrs objsAttrsCollectionNewObjsAttrsToAttach : objsAttrsCollectionNew) {
                objsAttrsCollectionNewObjsAttrsToAttach = em.getReference(objsAttrsCollectionNewObjsAttrsToAttach.getClass(), objsAttrsCollectionNewObjsAttrsToAttach.getPkattrObjs());
                attachedObjsAttrsCollectionNew.add(objsAttrsCollectionNewObjsAttrsToAttach);
            }
            objsAttrsCollectionNew = attachedObjsAttrsCollectionNew;
            objsecu.setObjsAttrsCollection(objsAttrsCollectionNew);
            Collection<ObjsCpte> attachedObjsCpteCollectionNew = new ArrayList<ObjsCpte>();
            for (ObjsCpte objsCpteCollectionNewObjsCpteToAttach : objsCpteCollectionNew) {
                objsCpteCollectionNewObjsCpteToAttach = em.getReference(objsCpteCollectionNewObjsCpteToAttach.getClass(), objsCpteCollectionNewObjsCpteToAttach.getPkobjsCpte());
                attachedObjsCpteCollectionNew.add(objsCpteCollectionNewObjsCpteToAttach);
            }
            objsCpteCollectionNew = attachedObjsCpteCollectionNew;
            objsecu.setObjsCpteCollection(objsCpteCollectionNew);
            Collection<ObjsHblt> attachedObjsHbltCollectionNew = new ArrayList<ObjsHblt>();
            for (ObjsHblt objsHbltCollectionNewObjsHbltToAttach : objsHbltCollectionNew) {
                objsHbltCollectionNewObjsHbltToAttach = em.getReference(objsHbltCollectionNewObjsHbltToAttach.getClass(), objsHbltCollectionNewObjsHbltToAttach.getPkobjsHblt());
                attachedObjsHbltCollectionNew.add(objsHbltCollectionNewObjsHbltToAttach);
            }
            objsHbltCollectionNew = attachedObjsHbltCollectionNew;
            objsecu.setObjsHbltCollection(objsHbltCollectionNew);
            objsecu = em.merge(objsecu);
            for (ObjsAttrs objsAttrsCollectionNewObjsAttrs : objsAttrsCollectionNew) {
                if (!objsAttrsCollectionOld.contains(objsAttrsCollectionNewObjsAttrs)) {
                    Objsecu oldFkobjsOfObjsAttrsCollectionNewObjsAttrs = objsAttrsCollectionNewObjsAttrs.getFkobjs();
                    objsAttrsCollectionNewObjsAttrs.setFkobjs(objsecu);
                    objsAttrsCollectionNewObjsAttrs = em.merge(objsAttrsCollectionNewObjsAttrs);
                    if (oldFkobjsOfObjsAttrsCollectionNewObjsAttrs != null && !oldFkobjsOfObjsAttrsCollectionNewObjsAttrs.equals(objsecu)) {
                        oldFkobjsOfObjsAttrsCollectionNewObjsAttrs.getObjsAttrsCollection().remove(objsAttrsCollectionNewObjsAttrs);
                        oldFkobjsOfObjsAttrsCollectionNewObjsAttrs = em.merge(oldFkobjsOfObjsAttrsCollectionNewObjsAttrs);
                    }
                }
            }
            for (ObjsCpte objsCpteCollectionNewObjsCpte : objsCpteCollectionNew) {
                if (!objsCpteCollectionOld.contains(objsCpteCollectionNewObjsCpte)) {
                    Objsecu oldFkobjsOfObjsCpteCollectionNewObjsCpte = objsCpteCollectionNewObjsCpte.getFkobjs();
                    objsCpteCollectionNewObjsCpte.setFkobjs(objsecu);
                    objsCpteCollectionNewObjsCpte = em.merge(objsCpteCollectionNewObjsCpte);
                    if (oldFkobjsOfObjsCpteCollectionNewObjsCpte != null && !oldFkobjsOfObjsCpteCollectionNewObjsCpte.equals(objsecu)) {
                        oldFkobjsOfObjsCpteCollectionNewObjsCpte.getObjsCpteCollection().remove(objsCpteCollectionNewObjsCpte);
                        oldFkobjsOfObjsCpteCollectionNewObjsCpte = em.merge(oldFkobjsOfObjsCpteCollectionNewObjsCpte);
                    }
                }
            }
            for (ObjsHblt objsHbltCollectionNewObjsHblt : objsHbltCollectionNew) {
                if (!objsHbltCollectionOld.contains(objsHbltCollectionNewObjsHblt)) {
                    Objsecu oldFkobjsOfObjsHbltCollectionNewObjsHblt = objsHbltCollectionNewObjsHblt.getFkobjs();
                    objsHbltCollectionNewObjsHblt.setFkobjs(objsecu);
                    objsHbltCollectionNewObjsHblt = em.merge(objsHbltCollectionNewObjsHblt);
                    if (oldFkobjsOfObjsHbltCollectionNewObjsHblt != null && !oldFkobjsOfObjsHbltCollectionNewObjsHblt.equals(objsecu)) {
                        oldFkobjsOfObjsHbltCollectionNewObjsHblt.getObjsHbltCollection().remove(objsHbltCollectionNewObjsHblt);
                        oldFkobjsOfObjsHbltCollectionNewObjsHblt = em.merge(oldFkobjsOfObjsHbltCollectionNewObjsHblt);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = objsecu.getPkobjs();
                if (findObjsecu(id) == null) {
                    throw new NonexistentEntityException("The objsecu with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Objsecu objsecu;
            try {
                objsecu = em.getReference(Objsecu.class, id);
                objsecu.getPkobjs();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objsecu with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ObjsAttrs> objsAttrsCollectionOrphanCheck = objsecu.getObjsAttrsCollection();
            for (ObjsAttrs objsAttrsCollectionOrphanCheckObjsAttrs : objsAttrsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objsecu (" + objsecu + ") cannot be destroyed since the ObjsAttrs " + objsAttrsCollectionOrphanCheckObjsAttrs + " in its objsAttrsCollection field has a non-nullable fkobjs field.");
            }
            Collection<ObjsCpte> objsCpteCollectionOrphanCheck = objsecu.getObjsCpteCollection();
            for (ObjsCpte objsCpteCollectionOrphanCheckObjsCpte : objsCpteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objsecu (" + objsecu + ") cannot be destroyed since the ObjsCpte " + objsCpteCollectionOrphanCheckObjsCpte + " in its objsCpteCollection field has a non-nullable fkobjs field.");
            }
            Collection<ObjsHblt> objsHbltCollectionOrphanCheck = objsecu.getObjsHbltCollection();
            for (ObjsHblt objsHbltCollectionOrphanCheckObjsHblt : objsHbltCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Objsecu (" + objsecu + ") cannot be destroyed since the ObjsHblt " + objsHbltCollectionOrphanCheckObjsHblt + " in its objsHbltCollection field has a non-nullable fkobjs field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(objsecu);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<Objsecu> findObjsecuEntities() {
        return findObjsecuEntities(true, -1, -1);
    }

    public List<Objsecu> findObjsecuEntities(int maxResults, int firstResult) {
        return findObjsecuEntities(false, maxResults, firstResult);
    }

    private List<Objsecu> findObjsecuEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Objsecu as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public Objsecu findObjsecu(Integer id) {
        try {
            return em.find(Objsecu.class, id);
        } finally {
        }
    }

    public Long getObjsecuCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Objsecu as o").getSingleResult()).longValue();
        } finally {
        }
    }

}
