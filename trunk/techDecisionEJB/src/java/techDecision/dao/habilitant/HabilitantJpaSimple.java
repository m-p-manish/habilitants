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
package techDecision.dao.habilitant;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Habilitant;
import techDecision.entites.ObjsHblt;
import techDecision.entites.CpteHblt;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class HabilitantJpaSimple {

    private EntityManager em = null;

    HabilitantJpaSimple(EntityManager emm) {
        em = emm;
    }

    public void create(Habilitant habilitant) throws PreexistingEntityException, RollbackFailureException, Exception {
        try {
            em.persist(habilitant);
        } catch (Exception ex) {
            if (findHabilitant(habilitant.getPkhblt()) != null) {
                throw new PreexistingEntityException("Habilitant " + habilitant + " already exists.", ex);
            }
            throw ex;
        } finally {
        }
    }
    /**
     * Fonction de modification qui ne permet que l'ajout et pas la suppression des
     * objets de sécurite lié
     * @param habilitant
     * @throws techDecision.dao.exceptions.IllegalOrphanException
     * @throws techDecision.dao.exceptions.NonexistentEntityException
     * @throws techDecision.dao.exceptions.RollbackFailureException
     * @throws java.lang.Exception
     */
    public void edit(Habilitant habilitant) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            //recupère la valeur persistée
            Habilitant persistentHabilitant = em.find(Habilitant.class, habilitant.getPkhblt());
            //récupère l'ancienne collection
            Collection<ObjsHblt> objsHbltCollectionOld = persistentHabilitant.getObjsHbltCollection();
            //récupère la nouvelle valeur de la collection
            Collection<ObjsHblt> objsHbltCollectionNew = habilitant.getObjsHbltCollection();
            //une liste vide
            List<String> illegalOrphanMessages = null;
            //pour tous les liens Objs de l'ancienne
            for (ObjsHblt objsHbltCollectionOldObjsHblt : objsHbltCollectionOld) {
                //si un ancien lien n'est pas dans la nouvelle collection
                if (!objsHbltCollectionNew.contains(objsHbltCollectionOldObjsHblt)) {
                    //alors si la collection des orphelins est vide on la fabrique
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    //on ajoute l'orphelin
                    illegalOrphanMessages.add("You must retain ObjsHblt " + objsHbltCollectionOldObjsHblt + " since its fkhblt field is not nullable.");
                    //non on le flingue !
                    em.remove(objsHbltCollectionOldObjsHblt);
                }
            }
            //si des orphelins on quitte
            if (illegalOrphanMessages != null) {
                //throw new IllegalOrphanException(illegalOrphanMessages);
            }
            //une collection neuve
            Collection<ObjsHblt> attachedObjsHbltCollectionNew = new ArrayList<ObjsHblt>();
            //on fabrique la liste des nouveaux liens
            for (ObjsHblt objsHbltCollectionNewObjsHbltToAttach : objsHbltCollectionNew) {
                objsHbltCollectionNewObjsHbltToAttach = em.getReference(objsHbltCollectionNewObjsHbltToAttach.getClass(), objsHbltCollectionNewObjsHbltToAttach.getPkobjsHblt());
                attachedObjsHbltCollectionNew.add(objsHbltCollectionNewObjsHbltToAttach);
            }
            //on met à jour la liste des nouveaux avec les nouveaux ?
            objsHbltCollectionNew = attachedObjsHbltCollectionNew;
            habilitant.setObjsHbltCollection(objsHbltCollectionNew);
            //on persiste
            habilitant = em.merge(habilitant);
            //pour tous les liens nouveaux et actuels
            for (ObjsHblt objsHbltCollectionNewObjsHblt : objsHbltCollectionNew) {
                //si l'ancienne collection ne contient pas un nouveau lien
                if (!objsHbltCollectionOld.contains(objsHbltCollectionNewObjsHblt)) {
                    //on fabrique un habilitant de secours
                    Habilitant oldFkhbltOfObjsHbltCollectionNewObjsHblt = objsHbltCollectionNewObjsHblt.getFkhblt();
                    //on met la référence de l'habilitant dans le lien
                    objsHbltCollectionNewObjsHblt.setFkhblt(habilitant);
                    //on persiste dans le même lien
                    objsHbltCollectionNewObjsHblt = em.merge(objsHbltCollectionNewObjsHblt);
                    //si l'habilitant de secours est non vide ET qu'il n'est pas égal à l'habilitant en cours
                    if (oldFkhbltOfObjsHbltCollectionNewObjsHblt != null && !oldFkhbltOfObjsHbltCollectionNewObjsHblt.equals(habilitant)) {
                        //alors on enlève le lien de l'habilitant de secours
                        oldFkhbltOfObjsHbltCollectionNewObjsHblt.getObjsHbltCollection().remove(objsHbltCollectionNewObjsHblt);
                        //on persiste
                        oldFkhbltOfObjsHbltCollectionNewObjsHblt = em.merge(oldFkhbltOfObjsHbltCollectionNewObjsHblt);
                    }
                }
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = habilitant.getPkhblt();
                if (findHabilitant(id) == null) {
                    throw new NonexistentEntityException("The habilitant with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            Habilitant habilitant;
            try {
                habilitant = em.getReference(Habilitant.class, id);
                habilitant.getPkhblt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The habilitant with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<ObjsHblt> objsHbltCollectionOrphanCheck = habilitant.getObjsHbltCollection();
            for (ObjsHblt objsHbltCollectionOrphanCheckObjsHblt : objsHbltCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Habilitant (" + habilitant + ") cannot be destroyed since the ObjsHblt " + objsHbltCollectionOrphanCheckObjsHblt + " in its objsHbltCollection field has a non-nullable fkhblt field.");
            }
/*            Collection<CpteHblt> cpteHbltCollectionOrphanCheck = habilitant.getCpteHbltCollection();
            for (CpteHblt cpteHbltCollectionOrphanCheckObjsHblt : cpteHbltCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Habilitant (" + habilitant + ") cannot be destroyed since the CpteHblt " + cpteHbltCollectionOrphanCheckObjsHblt + " in its cpteHbltCollection field has a non-nullable fkhblt field.");
            }
*/            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(habilitant);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<Habilitant> findHabilitantEntities() {
        return findHabilitantEntities(true, -1, -1);
    }

    public List<Habilitant> findHabilitantEntities(int maxResults, int firstResult) {
        return findHabilitantEntities(false, maxResults, firstResult);
    }

    public List<Habilitant> findHabilitantEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Habilitant as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public Habilitant findHabilitant(Integer id) {
        try {
            return em.find(Habilitant.class, id);
        } finally {
        }
    }

    public Habilitant findHabilitant(String sVal) {
        Habilitant ret = null;
        try {
            Query qHbltVal = em.createNamedQuery("Habilitant.findByVal");
            qHbltVal.setParameter("val", sVal);
            //Collection hblts = qHbltVal.getResultList();
            Collection results = qHbltVal.getResultList();
            if(results.size()==1){
                Iterator stIterator=results.iterator();
                while(stIterator.hasNext()){
                    ret = (Habilitant)stIterator.next();
                }
            }
        } catch(Exception err) {
            String s = err.toString();
        }
        return ret;
    }
    public Long getHabilitantCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Habilitant as o").getSingleResult()).longValue();
        } finally {
        }
    }
    public List<ObjsHblt> relatedObjs(int idHblt){
        List<ObjsHblt> ret = null;
        return ret;
    }
}
