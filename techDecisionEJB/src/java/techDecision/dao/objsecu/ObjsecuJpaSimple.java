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
package techDecision.dao.objsecu;

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
public class ObjsecuJpaSimple {

    private EntityManager em = null;

    ObjsecuJpaSimple(EntityManager emm) {
        em = emm;
    }

    public void create(Objsecu objsecu) throws PreexistingEntityException, RollbackFailureException, Exception {
        try {
            em.persist(objsecu);
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
            objsecu = em.merge(objsecu);
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
    public void truncate(){
        try {
            Query q = em.createNativeQuery("truncate OBJSECU");
            Objsecu o = (Objsecu) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("On s'en fout de l'erreur truncate OBJSECU");
        }
    }

}
