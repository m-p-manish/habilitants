/*
Copyright Stéphane Georges Popoff, (février 2009)

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
import techDecision.dao.exceptions.TechDecisionErreurs;
import techDecision.dao.identite.IIdentiteDaoRemote;
import techDecision.dao.identite.IIdentiteDao;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.Identite;
/**
 * Classe vers un ejb identite
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class IdentiteEjb implements IIdentiteDao {
    //@EJB(name="ejb_identite")
    IIdentiteDaoRemote dao;
    /**
     * Fonction d'initialisation de la classe et donc de l'ejb
     * Injection directe ne fonctionne pas alors classique appel au context
     */
    public IdentiteEjb(){
        try {
            InitialContext ic = new InitialContext();
            dao = (IIdentiteDaoRemote) ic.lookup("ejbIdentite");
            dao.init();
        } catch (Exception ex) {
            Logger.getLogger(IdentiteEjb.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    /**
     * Fonction de création et donc permanence d'une identite
     * @param identite une classe contenant l'identité
     * @throws techDecision.dao.exceptions.PreexistingEntityException en cas de doublon
     * @throws techDecision.dao.exceptions.RollbackFailureException si erreur er rollback foire
     * @throws java.lang.Exception au cas ou
     */
    public void create(Identite identite) throws PreexistingEntityException, RollbackFailureException, Exception {
        dao.create(identite);
    }
    /**
     * On flingue
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
     * On change
     * @param identite
     * @throws techDecision.dao.exceptions.IllegalOrphanException
     * @throws techDecision.dao.exceptions.NonexistentEntityException
     * @throws techDecision.dao.exceptions.RollbackFailureException
     * @throws java.lang.Exception
     */
    public void edit(Identite identite) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        dao.edit(identite);
    }
    /**
     * On cherche et trouve sur l'ID
     * @param id
     * @return Identite une identité ou null
     */
    public Identite findIdentite(Integer id) {
        return dao.findIdentite(id);
    }
    /**
     * On cherche et trouve sur le userId
     * @param nom
     * @return Identite une identité ou null
     */
    public Identite findIdentite(String nom) {
        return dao.findIdentite(nom);
    }

    public List<Identite> findIdentiteEntities() {
        return dao.findIdentiteEntities();
    }
    /**
     * euh ?
     * @param maxResults
     * @param firstResult
     * @return Identite une identité ou null
     */
    public List<Identite> findIdentiteEntities(int maxResults, int firstResult) {
        return dao.findIdentiteEntities(maxResults, firstResult);
    }
    /**
     * le nombre d'identité
     * @return int un nombre d'identité
     */
    public Long getIdentiteCount() {
        return dao.getIdentiteCount();
    }

    public List<Identite> findIdentiteWhere(String sWhere) throws TechDecisionErreurs {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
