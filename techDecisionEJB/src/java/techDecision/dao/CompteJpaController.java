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

import techDecision.dao.compte.ICompteDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Compte;
import techDecision.entites.ObjsCpte;
import java.util.ArrayList;
import java.util.Collection;
import techDecision.entites.CpteAttrs;
import techDecision.entites.IdntCpte;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CompteJpaController implements ICompteDao {

    private EntityManager em = null;

    CompteJpaController(EntityManager emm) {
        em = emm;
    }

    public void create(Compte compte) throws PreexistingEntityException, RollbackFailureException, Exception {
        if (compte.getObjsCpteCollection() == null) {
            compte.setObjsCpteCollection(new ArrayList<ObjsCpte>());
        }
        if (compte.getCpteAttrsCollection() == null) {
            compte.setCpteAttrsCollection(new ArrayList<CpteAttrs>());
        }
        if (compte.getIdntCpteCollection() == null) {
            compte.setIdntCpteCollection(new ArrayList<IdntCpte>());
        }
        try {
            Collection<ObjsCpte> attachedObjsCpteCollection = new ArrayList<ObjsCpte>();
            for (ObjsCpte objsCpteCollectionObjsCpteToAttach : compte.getObjsCpteCollection()) {
                objsCpteCollectionObjsCpteToAttach = em.getReference(objsCpteCollectionObjsCpteToAttach.getClass(), objsCpteCollectionObjsCpteToAttach.getPkobjsCpte());
                attachedObjsCpteCollection.add(objsCpteCollectionObjsCpteToAttach);
            }
            compte.setObjsCpteCollection(attachedObjsCpteCollection);
            Collection<CpteAttrs> attachedCpteAttrsCollection = new ArrayList<CpteAttrs>();
            for (CpteAttrs cpteAttrsCollectionCpteAttrsToAttach : compte.getCpteAttrsCollection()) {
                cpteAttrsCollectionCpteAttrsToAttach = em.getReference(cpteAttrsCollectionCpteAttrsToAttach.getClass(), cpteAttrsCollectionCpteAttrsToAttach.getPkattrCpte());
                attachedCpteAttrsCollection.add(cpteAttrsCollectionCpteAttrsToAttach);
            }
            compte.setCpteAttrsCollection(attachedCpteAttrsCollection);
            Collection<IdntCpte> attachedIdntCpteCollection = new ArrayList<IdntCpte>();
            for (IdntCpte idntCpteCollectionIdntCpteToAttach : compte.getIdntCpteCollection()) {
                idntCpteCollectionIdntCpteToAttach = em.getReference(idntCpteCollectionIdntCpteToAttach.getClass(), idntCpteCollectionIdntCpteToAttach.getPkidntCpte());
                attachedIdntCpteCollection.add(idntCpteCollectionIdntCpteToAttach);
            }
            compte.setIdntCpteCollection(attachedIdntCpteCollection);
            if (findCompte(compte.getPkcpte()) != null) {
                throw new PreexistingEntityException("Compte " + compte + " already exists.");
            }
            em.persist(compte);
            for (ObjsCpte objsCpteCollectionObjsCpte : compte.getObjsCpteCollection()) {
                Compte oldFkcpteOfObjsCpteCollectionObjsCpte = objsCpteCollectionObjsCpte.getFkcpte();
                objsCpteCollectionObjsCpte.setFkcpte(compte);
                objsCpteCollectionObjsCpte = em.merge(objsCpteCollectionObjsCpte);
                if (oldFkcpteOfObjsCpteCollectionObjsCpte != null) {
                    oldFkcpteOfObjsCpteCollectionObjsCpte.getObjsCpteCollection().remove(objsCpteCollectionObjsCpte);
                    oldFkcpteOfObjsCpteCollectionObjsCpte = em.merge(oldFkcpteOfObjsCpteCollectionObjsCpte);
                }
            }
            for (CpteAttrs cpteAttrsCollectionCpteAttrs : compte.getCpteAttrsCollection()) {
                Compte oldFkcpteOfCpteAttrsCollectionCpteAttrs = cpteAttrsCollectionCpteAttrs.getFkcpte();
                cpteAttrsCollectionCpteAttrs.setFkcpte(compte);
                cpteAttrsCollectionCpteAttrs = em.merge(cpteAttrsCollectionCpteAttrs);
                if (oldFkcpteOfCpteAttrsCollectionCpteAttrs != null) {
                    oldFkcpteOfCpteAttrsCollectionCpteAttrs.getCpteAttrsCollection().remove(cpteAttrsCollectionCpteAttrs);
                    oldFkcpteOfCpteAttrsCollectionCpteAttrs = em.merge(oldFkcpteOfCpteAttrsCollectionCpteAttrs);
                }
            }
            for (IdntCpte idntCpteCollectionIdntCpte : compte.getIdntCpteCollection()) {
                Compte oldFkcpteOfIdntCpteCollectionIdntCpte = idntCpteCollectionIdntCpte.getFkcpte();
                idntCpteCollectionIdntCpte.setFkcpte(compte);
                idntCpteCollectionIdntCpte = em.merge(idntCpteCollectionIdntCpte);
                if (oldFkcpteOfIdntCpteCollectionIdntCpte != null) {
                    oldFkcpteOfIdntCpteCollectionIdntCpte.getIdntCpteCollection().remove(idntCpteCollectionIdntCpte);
                    oldFkcpteOfIdntCpteCollectionIdntCpte = em.merge(oldFkcpteOfIdntCpteCollectionIdntCpte);
                }
            }
        } catch (Exception ex) {
            if (findCompte(compte.getPkcpte()) != null) {
                throw new PreexistingEntityException("Compte " + compte + " already exists.", ex);
            }
            throw ex;
        } finally {
        }
    }

    public void edit(Compte compte) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Compte persistentCompte = em.find(Compte.class, compte.getPkcpte());
            Collection<ObjsCpte> objsCpteCollectionOld = persistentCompte.getObjsCpteCollection();
            Collection<ObjsCpte> objsCpteCollectionNew = compte.getObjsCpteCollection();
            Collection<CpteAttrs> cpteAttrsCollectionOld = persistentCompte.getCpteAttrsCollection();
            Collection<CpteAttrs> cpteAttrsCollectionNew = compte.getCpteAttrsCollection();
            Collection<IdntCpte> idntCpteCollectionOld = persistentCompte.getIdntCpteCollection();
            Collection<IdntCpte> idntCpteCollectionNew = compte.getIdntCpteCollection();
            List<String> illegalOrphanMessages = null;
            for (ObjsCpte objsCpteCollectionOldObjsCpte : objsCpteCollectionOld) {
                if (!objsCpteCollectionNew.contains(objsCpteCollectionOldObjsCpte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain ObjsCpte " + objsCpteCollectionOldObjsCpte + " since its fkcpte field is not nullable.");
                }
            }
            for (CpteAttrs cpteAttrsCollectionOldCpteAttrs : cpteAttrsCollectionOld) {
                if (!cpteAttrsCollectionNew.contains(cpteAttrsCollectionOldCpteAttrs)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain CpteAttrs " + cpteAttrsCollectionOldCpteAttrs + " since its fkcpte field is not nullable.");
                }
            }
            for (IdntCpte idntCpteCollectionOldIdntCpte : idntCpteCollectionOld) {
                if (!idntCpteCollectionNew.contains(idntCpteCollectionOldIdntCpte)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain IdntCpte " + idntCpteCollectionOldIdntCpte + " since its fkcpte field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<ObjsCpte> attachedObjsCpteCollectionNew = new ArrayList<ObjsCpte>();
            for (ObjsCpte objsCpteCollectionNewObjsCpteToAttach : objsCpteCollectionNew) {
                objsCpteCollectionNewObjsCpteToAttach = em.getReference(objsCpteCollectionNewObjsCpteToAttach.getClass(), objsCpteCollectionNewObjsCpteToAttach.getPkobjsCpte());
                attachedObjsCpteCollectionNew.add(objsCpteCollectionNewObjsCpteToAttach);
            }
            objsCpteCollectionNew = attachedObjsCpteCollectionNew;
            compte.setObjsCpteCollection(objsCpteCollectionNew);
            Collection<CpteAttrs> attachedCpteAttrsCollectionNew = new ArrayList<CpteAttrs>();
            for (CpteAttrs cpteAttrsCollectionNewCpteAttrsToAttach : cpteAttrsCollectionNew) {
                cpteAttrsCollectionNewCpteAttrsToAttach = em.getReference(cpteAttrsCollectionNewCpteAttrsToAttach.getClass(), cpteAttrsCollectionNewCpteAttrsToAttach.getPkattrCpte());
                attachedCpteAttrsCollectionNew.add(cpteAttrsCollectionNewCpteAttrsToAttach);
            }
            cpteAttrsCollectionNew = attachedCpteAttrsCollectionNew;
            compte.setCpteAttrsCollection(cpteAttrsCollectionNew);
            Collection<IdntCpte> attachedIdntCpteCollectionNew = new ArrayList<IdntCpte>();
            for (IdntCpte idntCpteCollectionNewIdntCpteToAttach : idntCpteCollectionNew) {
                idntCpteCollectionNewIdntCpteToAttach = em.getReference(idntCpteCollectionNewIdntCpteToAttach.getClass(), idntCpteCollectionNewIdntCpteToAttach.getPkidntCpte());
                attachedIdntCpteCollectionNew.add(idntCpteCollectionNewIdntCpteToAttach);
            }
            idntCpteCollectionNew = attachedIdntCpteCollectionNew;
            compte.setIdntCpteCollection(idntCpteCollectionNew);
            compte = em.merge(compte);
            for (ObjsCpte objsCpteCollectionNewObjsCpte : objsCpteCollectionNew) {
                if (!objsCpteCollectionOld.contains(objsCpteCollectionNewObjsCpte)) {
                    Compte oldFkcpteOfObjsCpteCollectionNewObjsCpte = objsCpteCollectionNewObjsCpte.getFkcpte();
                    objsCpteCollectionNewObjsCpte.setFkcpte(compte);
                    objsCpteCollectionNewObjsCpte = em.merge(objsCpteCollectionNewObjsCpte);
                    if (oldFkcpteOfObjsCpteCollectionNewObjsCpte != null && !oldFkcpteOfObjsCpteCollectionNewObjsCpte.equals(compte)) {
                        oldFkcpteOfObjsCpteCollectionNewObjsCpte.getObjsCpteCollection().remove(objsCpteCollectionNewObjsCpte);
                        oldFkcpteOfObjsCpteCollectionNewObjsCpte = em.merge(oldFkcpteOfObjsCpteCollectionNewObjsCpte);
                    }
                }
            }
            for (CpteAttrs cpteAttrsCollectionNewCpteAttrs : cpteAttrsCollectionNew) {
                if (!cpteAttrsCollectionOld.contains(cpteAttrsCollectionNewCpteAttrs)) {
                    Compte oldFkcpteOfCpteAttrsCollectionNewCpteAttrs = cpteAttrsCollectionNewCpteAttrs.getFkcpte();
                    cpteAttrsCollectionNewCpteAttrs.setFkcpte(compte);
                    cpteAttrsCollectionNewCpteAttrs = em.merge(cpteAttrsCollectionNewCpteAttrs);
                    if (oldFkcpteOfCpteAttrsCollectionNewCpteAttrs != null && !oldFkcpteOfCpteAttrsCollectionNewCpteAttrs.equals(compte)) {
                        oldFkcpteOfCpteAttrsCollectionNewCpteAttrs.getCpteAttrsCollection().remove(cpteAttrsCollectionNewCpteAttrs);
                        oldFkcpteOfCpteAttrsCollectionNewCpteAttrs = em.merge(oldFkcpteOfCpteAttrsCollectionNewCpteAttrs);
                    }
                }
            }
            for (IdntCpte idntCpteCollectionNewIdntCpte : idntCpteCollectionNew) {
                if (!idntCpteCollectionOld.contains(idntCpteCollectionNewIdntCpte)) {
                    Compte oldFkcpteOfIdntCpteCollectionNewIdntCpte = idntCpteCollectionNewIdntCpte.getFkcpte();
                    idntCpteCollectionNewIdntCpte.setFkcpte(compte);
                    idntCpteCollectionNewIdntCpte = em.merge(idntCpteCollectionNewIdntCpte);
                    if (oldFkcpteOfIdntCpteCollectionNewIdntCpte != null && !oldFkcpteOfIdntCpteCollectionNewIdntCpte.equals(compte)) {
                        oldFkcpteOfIdntCpteCollectionNewIdntCpte.getIdntCpteCollection().remove(idntCpteCollectionNewIdntCpte);
                        oldFkcpteOfIdntCpteCollectionNewIdntCpte = em.merge(oldFkcpteOfIdntCpteCollectionNewIdntCpte);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = compte.getPkcpte();
                if (findCompte(id) == null) {
                    throw new NonexistentEntityException("The compte with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Compte compte;
            try {
                compte = em.getReference(Compte.class, id);
                compte.getPkcpte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The compte with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ObjsCpte> objsCpteCollectionOrphanCheck = compte.getObjsCpteCollection();
            for (ObjsCpte objsCpteCollectionOrphanCheckObjsCpte : objsCpteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compte (" + compte + ") cannot be destroyed since the ObjsCpte " + objsCpteCollectionOrphanCheckObjsCpte + " in its objsCpteCollection field has a non-nullable fkcpte field.");
            }
            Collection<CpteAttrs> cpteAttrsCollectionOrphanCheck = compte.getCpteAttrsCollection();
            for (CpteAttrs cpteAttrsCollectionOrphanCheckCpteAttrs : cpteAttrsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compte (" + compte + ") cannot be destroyed since the CpteAttrs " + cpteAttrsCollectionOrphanCheckCpteAttrs + " in its cpteAttrsCollection field has a non-nullable fkcpte field.");
            }
            Collection<IdntCpte> idntCpteCollectionOrphanCheck = compte.getIdntCpteCollection();
            for (IdntCpte idntCpteCollectionOrphanCheckIdntCpte : idntCpteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Compte (" + compte + ") cannot be destroyed since the IdntCpte " + idntCpteCollectionOrphanCheckIdntCpte + " in its idntCpteCollection field has a non-nullable fkcpte field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(compte);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<Compte> findCompteEntities() {
        return findCompteEntities(true, -1, -1);
    }

    public List<Compte> findCompteEntities(int maxResults, int firstResult) {
        return findCompteEntities(false, maxResults, firstResult);
    }

    private List<Compte> findCompteEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Compte as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public Compte findCompte(Integer id) {
        try {
            return em.find(Compte.class, id);
        } finally {
        }
    }

    public Long getCompteCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Compte as o").getSingleResult()).longValue();
        } finally {
        }
    }

}
