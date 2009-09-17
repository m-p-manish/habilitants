/*
Copyright Stéphane Georges Popoff, (mai - juin 2009)

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
import java.util.Vector;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import theCube.entities.Compte;
import theCube.entities.Habilitant;

/**
 * controller JPA qui ne prend traite la table des comptes pour datamart H2
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
public class Compte3 {
    @PersistenceContext(unitName="techDecisionCubePU")
    private EntityManager em;

    public List<Compte> findCompteEntities() {
        System.out.println("Retrouve tous les comptes de H2");
        return findCompteEntities(true, -1, -1);
    }

    public List<Compte> findCompteEntities(int maxResults, int firstResult) {
        return findCompteEntities(false, maxResults, firstResult);
    }

    private List<Compte> findCompteEntities(boolean all, int maxResults, int firstResult) {
        try {
            Query q = (Query) em.createNativeQuery("SELECT * FROM SPOPOFF.COMPTE", Compte.class);
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

    public int getCompteCount() {
        if(em==null) return 0;
        Query q = null;
        int ret = 0;
        Long ll = null;
        try {
            q = em.createNativeQuery("SELECT COUNT(*) FROM SPOPOFF.COMPTE");
            ll =(Long) ((Vector) q.getSingleResult()).firstElement();
            ret = ll.intValue();
        }catch(Exception err){
            System.err.println("Erreur dans getIdentiteCount "+err.toString());
        } finally {
            return ret;
        }
    }
    /**
     * Fonction pour vérifier la présence des comptes pour un objet de securité donné
     * utilise la table de liaison objet de sécurité / compte
     * @param iC1 id compte 1
     * @param iC2 id Compte 2
     * @return trouveComptes si 1 =trouvé compte 1, si 3 =trouvé compte 2, si 4 =trouvé les deux, si 0 rien trouvé, si -1 erreur
     */
    public int trouveComptes(int iC1, int iC2, int pkid){
        int iRes = 0;
        Compte ret = null;
        try {
            Query q = em.createNativeQuery("SELECT DISTINCT PKCPTE FROM SPOPOFF.COMPTE, SPOPOFF.OBJS_CPTE WHERE PKCPTE = FKCPTE AND FKCPTE="+iC1+" AND FKOBJS="+pkid+";", Compte.class);
            //ret = (ObjsAttrs) q.getSingleResult();
            ret = (Compte) q.getSingleResult();
            //compte 1 trouvé
            iRes = 1;
        }catch(NoResultException err){
            System.out.println("Pas trouvé compte1="+iC1);
        }catch(NonUniqueResultException ex){
            System.err.println("trouvé plusieurs valeurs pour compte1="+iC1);
            return -1;
        }catch(oracle.toplink.essentials.exceptions.QueryException err){
            System.out.println("grosse erreur pour compte1="+iC1+" "+err.toString());
        }catch(java.lang.IllegalStateException ex){
            System.err.println("Erreur sur le SQL pour le compte1="+iC1);
        }
/*
        if(ret!=null){
            iRes = 1;
        }
 */
        ret = null;
        try {
            Query q = em.createNativeQuery("SELECT DISTINCT PKCPTE FROM SPOPOFF.COMPTE, SPOPOFF.OBJS_CPTE WHERE PKCPTE = FKCPTE AND FKCPTE="+iC2+" AND FKOBJS="+pkid+";", Compte.class);
            ret = (Compte) q.getSingleResult();
            //soit 3 (compte 2 trouvé) soit 4 (trouvé les deux)
            iRes += 3;
        }catch(NoResultException err){
            System.out.println("Pas trouvé compte2="+iC2);
        }catch(NonUniqueResultException ex){
            System.err.println("trouvé plusieurs valeurs pour compte2="+iC2);
            return -1;
        }catch(oracle.toplink.essentials.exceptions.QueryException err){
            System.out.println("grosse erreur pour compte2="+iC2+" "+err.toString());
        }catch(java.lang.IllegalStateException ex){
            System.err.println("trouveComptes Erreur sur le SQL pour le compte2="+iC2+" "+ex.toString());
        }
/*
        if(ret!=null){
            iRes += 3;
        }
 */
        //if(iRes == 0 ) iRes = 4;
        try{
            em.close();
        }catch(Exception err){
            System.err.println("trouveComptes Erreur sur fermeture entityMngr "+err.toString());
        }
        return iRes;
    }
    /**
     * retrouve les habilitants d'un compte
     * @param idCpte
     * @return
     */
    public List<Habilitant> relatedHblt(int idCpte){
        List<Habilitant> ret = null;
        try {
            Query q = em.createNativeQuery("SELECT * FROM SPOPOFF.HABILITANT, SPOPOFF.CPTE_HBLT WHERE PKHBLT = FKHBLT AND FKCPTE="+idCpte+";", Habilitant.class);
            //ret = (ObjsAttrs) q.getSingleResult();
            ret = (List<Habilitant>) q.getResultList();
         }catch(NoResultException err){
            System.out.println("Pas trouvé compte="+idCpte);
        }catch(NonUniqueResultException ex){
            System.err.println("trouvé plusieurs valeurs pour compte="+idCpte);
            return null;
        }catch(oracle.toplink.essentials.exceptions.QueryException err){
            System.out.println("grosse erreur pour compte="+idCpte+" "+err.toString());
        }catch(java.lang.IllegalStateException ex){
            System.err.println("Erreur sur le SQL pour le compte="+idCpte);
        }
        return ret;
    }
}
