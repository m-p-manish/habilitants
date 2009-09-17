/*
Copyright Stéphane Georges Popoff, (juin 2009)

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

import java.util.List;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.transaction.UserTransaction;
import techDecision.entites.ObjsCpte;
import theCube.jpa.exceptions.NonexistentEntityException;
import theCube.jpa.exceptions.RollbackFailureException;

/**
 * Classe entité qui gère la liaison objet de séurité / compte
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class ObjsCpteJpaController {
    @Resource
    private UserTransaction utx = null;
    @PersistenceUnit(unitName = "techDecisionCubePU")
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            ObjsCpte objsCpte;
            try {
                objsCpte = em.getReference(ObjsCpte.class, id);
                objsCpte.getPkobjsCpte();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The objsCpte with id " + id + " no longer exists.", enfe);
            }
            em.remove(objsCpte);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }
    /**
     * retrouve un compte d'un objet de sécurité
     * @param id
     * @return
     */
    public ObjsCpte findObjsCpte(Integer idObjs, Integer idCpte) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createNativeQuery("SELECT * SPOPOFF.OBJS_CPTE WHERE FKOBJS=?idObjs AND FKCPTE=?idCpte",ObjsCpte.class);
            q.setParameter("idObjs", idObjs);
            q.setParameter("idCpte", idCpte);
            return (ObjsCpte) q.getSingleResult();
        } finally {
            em.close();
        }
    }

}
