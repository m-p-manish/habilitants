/*
Copyright Stéphane Georges Popoff, (février - mars 2010)

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
import techDecision.entites.CpteAttrs;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
@TransactionAttribute(TransactionAttributeType.REQUIRED)
public class CpteAttrsJpaController {

    private EntityManager em = null;

    CpteAttrsJpaController(EntityManager emm) {
        em = emm;
    }

    public void create(CpteAttrs cpteAttrs) throws RollbackFailureException, Exception {
        try {
            Compte fkcpte = cpteAttrs.getFkcpte();
            if (fkcpte != null) {
                fkcpte = em.getReference(fkcpte.getClass(), fkcpte.getPkcpte());
                cpteAttrs.setFkcpte(fkcpte);
            }
            em.persist(cpteAttrs);
            if (fkcpte != null) {
                fkcpte.getCpteAttrsCollection().add(cpteAttrs);
                fkcpte = em.merge(fkcpte);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public void edit(CpteAttrs cpteAttrs) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            CpteAttrs persistentCpteAttrs = em.find(CpteAttrs.class, cpteAttrs.getPkattrCpte());
            Compte fkcpteOld = persistentCpteAttrs.getFkcpte();
            Compte fkcpteNew = cpteAttrs.getFkcpte();
            if (fkcpteNew != null) {
                fkcpteNew = em.getReference(fkcpteNew.getClass(), fkcpteNew.getPkcpte());
                cpteAttrs.setFkcpte(fkcpteNew);
            }
            cpteAttrs = em.merge(cpteAttrs);
            if (fkcpteOld != null && !fkcpteOld.equals(fkcpteNew)) {
                fkcpteOld.getCpteAttrsCollection().remove(cpteAttrs);
                fkcpteOld = em.merge(fkcpteOld);
            }
            if (fkcpteNew != null && !fkcpteNew.equals(fkcpteOld)) {
                fkcpteNew.getCpteAttrsCollection().add(cpteAttrs);
                fkcpteNew = em.merge(fkcpteNew);
            }
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = cpteAttrs.getPkattrCpte();
                if (findCpteAttrs(id) == null) {
                    throw new NonexistentEntityException("The cpteAttrs with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        try {
            CpteAttrs cpteAttrs;
            try {
                cpteAttrs = em.getReference(CpteAttrs.class, id);
                cpteAttrs.getPkattrCpte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The cpteAttrs with id " + id + " no longer exists.", enfe);
            }
            Compte fkcpte = cpteAttrs.getFkcpte();
            if (fkcpte != null) {
                fkcpte.getCpteAttrsCollection().remove(cpteAttrs);
                fkcpte = em.merge(fkcpte);
            }
            em.remove(cpteAttrs);
        } catch (Exception ex) {
            throw ex;
        } finally {
        }
    }

    public List<CpteAttrs> findCpteAttrsEntities() {
        return findCpteAttrsEntities(true, -1, -1);
    }

    public List<CpteAttrs> findCpteAttrsEntities(int maxResults, int firstResult) {
        return findCpteAttrsEntities(false, maxResults, firstResult);
    }

    private List<CpteAttrs> findCpteAttrsEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createQuery("select object(o) from CpteAttrs as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
        }
    }

    public CpteAttrs findCpteAttrs(Integer id) {
        try {
            return em.find(CpteAttrs.class, id);
        } finally {
        }
    }

    public Long getCpteAttrsCount() {
        try {
            return ((Long) em.createQuery("select count(o) from CpteAttrs as o").getSingleResult()).longValue();
        } finally {
        }
    }
    public Integer getId4NomCpte(String val){
        Integer i = 0;
        try {
            Query q = em.createQuery("select object(o) from CpteAttrs as o where o.attr='NOMCPTE' and o.val = :val");
            q.setParameter("val", val);
            CpteAttrs ca = (CpteAttrs) q.getSingleResult();
            i = ca.getFkcpte().getPkcpte();
        } catch(Exception err) {
            System.err.println("Erreur cherche cpte sur nomcpte val="+val+" "+err.toString());
        }
        return i;
    }
    public Integer getId4UidCpte(String val){
        Integer i = null;
        try {
            Query q = em.createQuery("select object(o) from CpteAttrs as o where o.attr='UIDCPTE' and o.val = :val");
            q.setParameter("val", val);
            CpteAttrs ca = (CpteAttrs) q.getSingleResult();
            i = ca.getFkcpte().getPkcpte();
        } catch(Exception err) {
            System.err.println("Erreur cherche cpte sur uidcpte val="+val+" "+err.toString());
        }
        return i;
    }
    public void truncate(){
        try {
            Query q = em.createNativeQuery("truncate CPTE_ATTRS");
            CpteAttrs o = (CpteAttrs) q.getSingleResult();
        } catch (Exception e) {
            System.err.println("On s'en fout de l'erreur truncate CPTE_ATTRS");
        }
    }

}
