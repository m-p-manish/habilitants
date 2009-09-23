/*
Copyright Stéphane Georges Popoff, (avril - juillet 2009)

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
import javax.naming.InitialContext;
import techDecision.dao.objshblt.IObjsHbltDaoRemote;
import techDecision.dao.objshblt.IObjsHbltDao;
import techDecision.dao.exceptions.IllegalOrphanException;
import techDecision.dao.exceptions.NonexistentEntityException;
import techDecision.dao.exceptions.PreexistingEntityException;
import techDecision.dao.exceptions.RollbackFailureException;
import techDecision.entites.ObjsHblt;
/**
 * Classe vers un ejb d'association des objets de sécurité et des hanilitants
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
public class ObjsHbltEjb implements IObjsHbltDao {
    //@EJB(name="ejb_identite")
    IObjsHbltDaoRemote dao;
    /**
     * Fonction d'initialisation de la classe et donc de l'ejb
     * Injection directe ne fonctionne pas alors classique appel au context
     */
    public ObjsHbltEjb(){
        try {
            InitialContext ic = new InitialContext();
            dao = (IObjsHbltDaoRemote) ic.lookup("ejbObjsHblt");
            dao.init();
        } catch (Exception ex) {
            System.err.println("Erreur constructeur ObjsHbltEjb "+ex.toString());
        }
    }
    /**
     * Fonction de création et donc permanence d'une identite
     * @param objshblt une classe contenant le objshblt
     * @throws techDecision.dao.exceptions.PreexistingEntityException en cas de doublon
     * @throws techDecision.dao.exceptions.RollbackFailureException si erreur er rollback foire
     * @throws java.lang.Exception au cas ou
     */
    public void create(ObjsHblt objshblt) throws PreexistingEntityException, RollbackFailureException, Exception {
        dao.create(objshblt);
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
     * @param objshblt
     * @throws techDecision.dao.exceptions.IllegalOrphanException
     * @throws techDecision.dao.exceptions.NonexistentEntityException
     * @throws techDecision.dao.exceptions.RollbackFailureException
     * @throws java.lang.Exception
     */
    public void edit(ObjsHblt objshblt) throws IllegalOrphanException, NonexistentEntityException, RollbackFailureException, Exception {
        dao.edit(objshblt);
    }
    /**
     * On cherche et trouve sur l'ID
     * @param id
     * @return ObjsHblt une des association objet secu habilitant ou null
     */
    public ObjsHblt findObjsHblt(Integer id) {
        return dao.findObjsHblt(id);
    }
    /**
     *
     * @return List<ObjsHblt> des des associations objet secu habilitant
     */
    public List<ObjsHblt> findObjsHbltEntities() {
        return dao.findObjsHbltEntities();
    }
    /**
     * euh ?
     * @param maxResults
     * @param firstResult
     * @return List<ObjsHblt> des associations objet secu habilitant ou null
     */
    public List<ObjsHblt> findObjsHbltEntities(int maxResults, int firstResult) {
        return dao.findObjsHbltEntities(maxResults, firstResult);
    }
    /**
     * le nombre de objshblt
     * @return int un nombre 
     */
    public Long getObjsHbltCount() {
        return dao.getObjsHbltCount();
    }
    /**
     * Fonction qui retourne la liste des objets de sécurité d'un habilitant
     * @param idHblt un identifiant d'habilitant
     * @return List<ObjsHblt> une liste
     */
    public List<ObjsHblt> relatedObjs(int idHblt){
        return dao.relatedObjs(idHblt);
    }
    /**
     * Fonction qui retourne la liste des habilitants d'un objet de sécurité
     * @param idObjs un identifiant d'objet de sécurité
     * @return List<ObjsHblt> une liste
     */
    public List<ObjsHblt> relatedHblt(int idObjs){
        return dao.relatedHblt(idObjs);
    }

}
