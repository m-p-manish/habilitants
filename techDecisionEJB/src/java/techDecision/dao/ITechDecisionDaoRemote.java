/*
Copyright Stéphane Georges Popoff, (février - juin 2009)

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
import javax.ejb.Remote;
import techDecision.dao.exceptions.TechDecisionErreurs;
import techDecision.entites.*;
import java.util.List;

/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@Remote
public interface ITechDecisionDaoRemote extends ITechDecisionDao {

    public Boolean existAppli(String nom);

    public void init();

    public void createCompte(Compte cpte) throws TechDecisionErreurs;

    void createIdentite(Identite idnt) throws TechDecisionErreurs;

    void lierIdntCpte(int idIdnt, int idCpte) throws TechDecisionErreurs;

    void createHabilitant(Habilitant hblt);

    void createObjetSecu(Objsecu objObjS);

    void lierCpteHblt(int idCpte, int idHblt) throws TechDecisionErreurs;

    void ajouteAttr(int iType, String sAttr, String sVal, int id) throws TechDecisionErreurs;

    int getIdHblt(String sVal);

    int getIdIdnt(String userName) throws TechDecisionErreurs;

    List listIdentite();

    void modifierIdentite(Identite idnt);

    List<Identite> getAllIdentite() throws TechDecisionErreurs;

    Integer getPkAppli(String sNom);

    Habilitant findHabilitantByVal(String sVal);

    Boolean existHblt4Objs(int idCpte, int idObjs);

    void createObjsHblt(int pk, int idObjs, int idHblt) throws TechDecisionErreurs;

    void lierCpteObjs(int pk, int cpte, int objs);

    int getIdCpte(String nomCpte);

    Integer getIdCpteUid(String sUid);

    Integer trouveIdntCpte(int idnt, int cpte);

    void corrigeCpteIdnt(int idnt, int cpte);

    void ajouteCpteHblt(String cpte, String hblt);
}
