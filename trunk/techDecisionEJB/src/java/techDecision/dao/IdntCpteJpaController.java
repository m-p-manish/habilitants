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
import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Compte;
import techDecision.entites.Identite;
import techDecision.entites.IdntCpte;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;

/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class IdntCpteJpaController {

    private EntityManager em = null;

    IdntCpteJpaController(EntityManager emm) {
        em = emm;
    }
    public void create(IdntCpte idntCpte) throws RollbackFailureException, Exception {
        try {
            Compte fkcpte = idntCpte.getFkcpte();
            if (fkcpte != null) {
                fkcpte = em.getReference(fkcpte.getClass(), fkcpte.getPkcpte());
                idntCpte.setFkcpte(fkcpte);
            }
            Identite fkidnt = idntCpte.getFkidnt();
            if (fkidnt != null) {
                fkidnt = em.getReference(fkidnt.getClass(), fkidnt.getPkidnt());
                idntCpte.setFkidnt(fkidnt);
            }
            em.persist(idntCpte);
            if (fkcpte != null) {
                fkcpte.getIdntCpteCollection().add(idntCpte);
                fkcpte = em.merge(fkcpte);
            }
            if (fkidnt != null) {
                fkidnt.getIdntCpteCollection().add(idntCpte);
                fkidnt = em.merge(fkidnt);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(IdntCpte idntCpte) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            IdntCpte persistentIdntCpte = em.find(IdntCpte.class, idntCpte.getPkidntCpte());
            Compte fkcpteOld = persistentIdntCpte.getFkcpte();
            Compte fkcpteNew = idntCpte.getFkcpte();
            Identite fkidntOld = persistentIdntCpte.getFkidnt();
            Identite fkidntNew = idntCpte.getFkidnt();
            if (fkcpteNew != null) {
                fkcpteNew = em.getReference(fkcpteNew.getClass(), fkcpteNew.getPkcpte());
                idntCpte.setFkcpte(fkcpteNew);
            }
            if (fkidntNew != null) {
                fkidntNew = em.getReference(fkidntNew.getClass(), fkidntNew.getPkidnt());
                idntCpte.setFkidnt(fkidntNew);
            }
            idntCpte = em.merge(idntCpte);
            if (fkcpteOld != null && !fkcpteOld.equals(fkcpteNew)) {
                fkcpteOld.getIdntCpteCollection().remove(idntCpte);
                fkcpteOld = em.merge(fkcpteOld);
            }
            if (fkcpteNew != null && !fkcpteNew.equals(fkcpteOld)) {
                fkcpteNew.getIdntCpteCollection().add(idntCpte);
                fkcpteNew = em.merge(fkcpteNew);
            }
            if (fkidntOld != null && !fkidntOld.equals(fkidntNew)) {
                fkidntOld.getIdntCpteCollection().remove(idntCpte);
                fkidntOld = em.merge(fkidntOld);
            }
            if (fkidntNew != null && !fkidntNew.equals(fkidntOld)) {
                fkidntNew.getIdntCpteCollection().add(idntCpte);
                fkidntNew = em.merge(fkidntNew);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = idntCpte.getPkidntCpte();
                if (findIdntCpte(id) == null) {
                    throw new NonexistentEntityException("The idntCpte with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            IdntCpte idntCpte;
            try {
                idntCpte = em.getReference(IdntCpte.class, id);
                idntCpte.getPkidntCpte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The idntCpte with id " + id + " no longer exists.", enfe);
            }
            Compte fkcpte = idntCpte.getFkcpte();
            if (fkcpte != null) {
                fkcpte.getIdntCpteCollection().remove(idntCpte);
                fkcpte = em.merge(fkcpte);
            }
            Identite fkidnt = idntCpte.getFkidnt();
            if (fkidnt != null) {
                fkidnt.getIdntCpteCollection().remove(idntCpte);
                fkidnt = em.merge(fkidnt);
            }
            em.remove(idntCpte);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<IdntCpte> findIdntCpteEntities() {
        return findIdntCpteEntities(true, -1, -1);
    }

    public List<IdntCpte> findIdntCpteEntities(int maxResults, int firstResult) {
        return findIdntCpteEntities(false, maxResults, firstResult);
    }

    private List<IdntCpte> findIdntCpteEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from IdntCpte as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public IdntCpte findIdntCpte(Integer id) {
        try {
            return em.find(IdntCpte.class, id);
        } finally {
        }
    }

    public Long getIdntCpteCount() {
        try {
            return ((Long) em.createQuery("select count(o) from IdntCpte as o").getSingleResult()).longValue();
        } finally {
        }
    }
    /**
     * recherche et retourne la clé de l'enregistrement qui lie identité et compte
     * @param idnt
     * @param cpte
     * @return clé si trouvé un seul, 0 si erreur, -1 si plusieurs
     */
    public Integer trouveIdntCpte(int idnt, int cpte) {
        Integer ret = null;
        IdntCpte ic = null;
        try {
            Query q = em.createQuery("select object(o) from IdntCpte as o where o.fkidnt = :fkidnt and o.fkcpte = :fkcpte");
            q.setParameter("fkidnt", em.getReference(Identite.class, idnt));
            q.setParameter("fkcpte", em.getReference(Compte.class, cpte));
            ic = (IdntCpte) q.getSingleResult();
        }catch(NonUniqueResultException ex){
            System.out.println("Info trouveIdntCpte plusieurs pour idnt="+idnt+" cpte="+cpte);
            return new Integer(-1);
        }catch(NoResultException exr){
            System.out.println("Info trouveIdntCpte rien pour idnt="+idnt+" cpte="+cpte);
        } catch(Exception err) {
            System.err.println("Erreur trouveIdntCpte pour idnt="+idnt+" cpte="+cpte+" "+err.toString());
        }
        if(ic!=null){
            ret = ic.getPkidntCpte();
        }
        return ret;
    }
    public void delDoublonAddOne(int idnt, int cpte){
        Query q = null;
        IdntCpte ic = null;
        Compte c = null;
        Identite i = null;
        try{
            i = em.getReference(Identite.class, idnt);
            c = em.getReference(Compte.class, cpte);
            q = em.createQuery("DELETE FROM IdntCpte where fkidnt = :fkidnt and fkcpte = :fkcpte");
            q.setParameter("fkidnt", i);
            q.setParameter("fkcpte", c);
            q.executeUpdate();
            ic = new IdntCpte();
            ic.setFkcpte(c);
            ic.setFkidnt(i);
            em.persist(ic);
        }catch(Exception err){
            System.err.println("Erreur delDoublonAddOne "+err.toString());
        }
    }
    public void truncate(){
        try {
            Query q = em.createNativeQuery("truncate IDNT_CPTE");
            IdntCpte o = (IdntCpte) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("On s'en fout de l'erreur truncate IDNT_CPTE");
        }
    }

}
