/*
Copyright Stéphane Georges Popoff, (mai 2009)

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
package techDecision.dao.identite;

import techDecision.dao.identite.IIdentiteDao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Identite;
import techDecision.entites.IdntAttrs;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import techDecision.entites.IdntCpte;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import techDecision.dao.exceptions.TechDecisionErreurs;

/**
 * controller JPA qui ne prend pas en compte les collections liés à l'identité
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IdentiteJpaSimple implements IIdentiteDao {
    private EntityManager em = null;

    IdentiteJpaSimple(EntityManager emm) {
        em = emm;
    }

    public void create(Identite identite) throws PreexistingEntityException, RollbackFailureException, Exception {
        try {
            em.persist(identite);
        } catch (Exception ex) {
            if (findIdentite(identite.getPkidnt()) != null) {
                throw new PreexistingEntityException("Identite " + identite + " already exists.", ex);
            }
            throw ex;
        } finally {
        }
    }

    public void edit(Identite identite) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            identite = em.merge(identite);
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = identite.getPkidnt();
                if (findIdentite(id) == null) {
                    throw new NonexistentEntityException("The identite with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        try {
            Identite identite;
            try {
                identite = em.getReference(Identite.class, id);
                identite.getPkidnt();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The identite with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<IdntAttrs> idntAttrsCollectionOrphanCheck = identite.getIdntAttrsCollection();
            for (IdntAttrs idntAttrsCollectionOrphanCheckIdntAttrs : idntAttrsCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Identite (" + identite + ") cannot be destroyed since the IdntAttrs " + idntAttrsCollectionOrphanCheckIdntAttrs + " in its idntAttrsCollection field has a non-nullable fkidnt field.");
            }
            Collection<IdntCpte> idntCpteCollectionOrphanCheck = identite.getIdntCpteCollection();
            for (IdntCpte idntCpteCollectionOrphanCheckIdntCpte : idntCpteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Identite (" + identite + ") cannot be destroyed since the IdntCpte " + idntCpteCollectionOrphanCheckIdntCpte + " in its idntCpteCollection field has a non-nullable fkidnt field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(identite);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<Identite> findIdentiteEntities() {
        return findIdentiteEntities(true, -1, -1);
    }

    public List<Identite> findIdentiteEntities(int maxResults, int firstResult) {
        return findIdentiteEntities(false, maxResults, firstResult);
    }

    private List<Identite> findIdentiteEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from Identite as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public Identite findIdentite(Integer id) {
        try {
            return em.find(Identite.class, id);
        } finally {
        }
    }

    public Identite findIdentite(String nom){
        Identite ret = null;
        try {
            Query qHbltVal = em.createNamedQuery("Identite.findByUsername");
            qHbltVal.setParameter("username", nom);
            //Collection hblts = qHbltVal.getResultList();
            Collection results = qHbltVal.getResultList();
            if(results.size()==1){
                Iterator stIterator=results.iterator();
                while(stIterator.hasNext()){
                    ret = (Identite)stIterator.next();
                }
            }
        } catch(Exception err) {
        }
        return ret;
    }
    public Long getIdentiteCount() {
        try {
            return ((Long) em.createQuery("select count(o) from Identite as o").getSingleResult()).longValue();
        } finally {
        }
    }
    public List<Identite> findIdentiteWhere(String sWhere) throws TechDecisionErreurs {
        List<Identite> ret = null;
        if(sWhere==null){
            System.err.println("Erreur findIdentiteWhere sWhere est vide!");
            return null;
        }else{
            System.out.println("Info findIdentiteWhere sWhere="+sWhere);
        }
        try {
            Query qHbltVal = em.createNativeQuery(sWhere, Identite.class);
            //qHbltVal.setParameter("cpteID", cpteId);
            ret = qHbltVal.getResultList();
        } catch(Exception err) {
            throw new TechDecisionErreurs(err,4556);
        }
        return ret;
    }

}
