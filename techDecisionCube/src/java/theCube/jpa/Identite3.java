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
package theCube.jpa;

import java.util.List;
import javax.persistence.Query;
import theCube.entities.Identite;
import java.util.Collection;
import java.util.Iterator;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import theCube.entities.Compte;
import theCube.entities.Habilitant;

/**
 * controller JPA qui ne prend pas en compte les collections liés à l'identité
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
public class Identite3 {
    @PersistenceContext(unitName="techDecisionCubePU")
    private EntityManager em;

    public Identite3(){
        //em = DataLoaderH2.getEm();
    }
    public Identite3(EntityManager emm) {
        em = emm;
    }
    public List<Identite> findIdentiteEntities() {
        return findIdentiteEntities(true, -1, -1);
    }

    public List<Identite> findIdentiteEntities(int maxResults, int firstResult) {
        return findIdentiteEntities(false, maxResults, firstResult);
    }

    private List<Identite> findIdentiteEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = em.createNativeQuery("SELECT * FROM SPOPOFF.IDENTITE",Identite.class);
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
    public int getIdentiteCount() {
        if(em==null) return 0;
        Query q = null;
        int ret = 0;
        Long ll = null;
        try {
            q = em.createNativeQuery("SELECT COUNT(*) FROM SPOPOFF.IDENTITE");
            ll =(Long) ((Vector) q.getSingleResult()).firstElement();
            ret = ll.intValue();
        }catch(Exception err){
            System.err.println("Erreur dans getIdentiteCount "+err.toString());
        } finally {
            return ret;
        }
    }
    /**
     * retourne la liste des comptes d'une identité
     * @param idIdnt
     * @return des comptes
     */
    public List<Compte> relatedCpte(int idIdnt){
        List<Compte> ret = null;
        try {
            Query q = em.createNativeQuery("SELECT PKCPTE FROM SPOPOFF.COMPTE, SPOPOFF.IDNT_CPTE WHERE PKCPTE = FKCPTE AND FKIDNT="+idIdnt+";", Compte.class);
            //ret = (ObjsAttrs) q.getSingleResult();
            ret = (List<Compte>) q.getResultList();
         }catch(NoResultException err){
            System.out.println("Pas trouvé identité="+idIdnt);
        }catch(NonUniqueResultException ex){
            System.err.println("trouvé plusieurs valeurs pour identité="+idIdnt);
            return null;
        }catch(oracle.toplink.essentials.exceptions.QueryException err){
            System.out.println("grosse erreur pour identité="+idIdnt+" "+err.toString());
        }catch(java.lang.IllegalStateException ex){
            System.err.println("Erreur sur le SQL pour le identité="+idIdnt);
        }
        return ret;
    }
    /**
     * retourne la liste des habilitants d'une identité, donc pour tous ses comptes
     * @param idIdnt
     * @return des habilitants
     */
    public List<Compte> relatedHblt(int idIdnt){
        List<Compte> ret = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM SPOPOFF.HABILITANT, SPOPOFF.CPTE_HBLT, SPOPOFF.IDNT_CPTE WHERE SPOPOFF.IDNT_CPTE.FKIDNT="+idIdnt+" AND SPOPOFF.IDNT_CPTE.FKCPTE = SPOPOFF.CPTE_HBLT.FKCPTE AND SPOPOFF.CPTE_HBLT.FKHBLT = SPOPOFF.HABILITANT.PKHBLT;", Habilitant.class);
            //ret = (ObjsAttrs) q.getSingleResult();
            ret = (List<Compte>) q.getResultList();
         }catch(NoResultException err){
            System.out.println("Pas trouvé habilitants="+idIdnt);
        }catch(oracle.toplink.essentials.exceptions.QueryException err){
            System.out.println("grosse erreur pour Habilitant="+idIdnt+" "+err.toString());
        }catch(java.lang.IllegalStateException ex){
            System.err.println("Erreur sur le SQL pour le identité="+idIdnt+ " "+ex.toString());
        }
        return ret;
    }

}
