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
package daoEjb;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.InitialContext;
import techDecision.dao.compte.ICompteDaoRemote;
import techDecision.dao.compte.ICompteDao;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Compte;
/**
 * Classe vers un ejb compte
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class CompteEjb implements ICompteDao {
    //@EJB(name="ejb_identite")
    ICompteDaoRemote dao;
    /**
     * Fonction d'initialisation de la classe et donc de l'ejb
     * Injection directe ne fonctionne pas alors classique appel au context
     */
    public CompteEjb(){
        try {
            InitialContext ic = new InitialContext();
            dao = (ICompteDaoRemote) ic.lookup("ejbCompte");
            dao.init();
        } catch (Exception ex) {
            Logger.getLogger(IdentiteEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Fonction de création et donc permanence d'un Compte
     * @param compte une classe contenant le compte
     * @throws techDecision.dao.exceptions.PreexistingEntityException en cas de doublon
     * @throws techDecision.dao.exceptions.RollbackFailureException si erreur er rollback foire
     * @throws java.lang.Exception au cas ou
     */
    public void create(Compte compte) throws PreexistingEntityException, RollbackFailureException, Exception {
        dao.create(compte);
    }
    /**
     * On flingue un Compte
     * @param id
     * @throws techDecision.dao.exceptions.IllegalOrphanException
     * @throws techDecision.dao.exceptions.NonexistentEntityException
     * @throws techDecision.dao.exceptions.RollbackFailureException
     * @throws java.lang.Exception
     */
    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        dao.destroy(id);
    }
    /**
     * On change un Compte
     * @param compte
     * @throws techDecision.dao.exceptions.IllegalOrphanException
     * @throws techDecision.dao.exceptions.NonexistentEntityException
     * @throws techDecision.dao.exceptions.RollbackFailureException
     * @throws java.lang.Exception
     */
    public void edit(Compte compte) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        dao.edit(compte);
    }
    /**
     * On cherche et trouve sur l'ID
     * @param id
     * @return Compte un Compte ou null
     */
    public Compte findCompte(Integer id) {
        return dao.findCompte(id);
    }
    /**
     * la liste de tous les comptes
     * @return List<Compte> des comptes
     */
    public List<Compte> findCompteEntities() {
        return dao.findCompteEntities();
    }
    /**
     * euh ?
     * @param maxResults
     * @param firstResult
     * @return List<Compte> une liste de compte ou null
     */
    public List<Compte> findCompteEntities(int maxResults, int firstResult) {
        return dao.findCompteEntities(maxResults, firstResult);
    }
    /**
     * le nombre de compte
     * @return int un nombre 
     */
    public Long getCompteCount() {
        return dao.getCompteCount();
    }
}
