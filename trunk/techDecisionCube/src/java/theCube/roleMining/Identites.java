/*
Copyright Stéphane Georges Popoff, (février - mai 2009)

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

package theCube.roleMining;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Iterator;
import java.util.LinkedList;
import org.apache.log4j.Level;
import theCube.entities.Identite;

/**
 * Collection d'identités
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
public class Identites extends theCube.jpa.Identite3 {

    //@PersistenceContext(unitName = "TechDecisionPU")
    protected static Collection<Identite> identites = null;
    private Boolean bErreur = false;
    private String sErreur = "";
    private IIdentite lastIdnt = null;
    /**
     * Constructeur qui initialise la liste des identités
     */
    public Identites() {
    	
//        identites = super.findIdentiteEntities();
//        TalendConnection.logg.log(Level.INFO,"Initialise les Identites");
    }
    public Boolean siErreur(){
        return bErreur;
    }
    public String erreurTxt(){
        return sErreur;
    }
  /**
 * Remonte une identité via son userId
 * permet de connaitre la fonction d'une identité
 * @param nom la clé du compte
 * @return trouveIdentite une identité
 */
   public Identite trouveIdentite(String nom) {
    	return super.findIdentite(nom);
    }
  /**
 * Remonte une identité via son identifiant unique
 * @param id la clé du compte( int)
 * @return trouveIdentite une identité
 */
   public Identite trouveIdentite(int id) {
       return super.findIdentite(id);
    }
 /**
 * permet de connaitre la fonction d'une identité à partir de son username
 * @param name la clé du compte
 * @return getFonction la fonction retounée
 */
    public String getFonction(String name){
        String sRep="";
        lastIdnt = null;
        try{
            lastIdnt = (IIdentite) super.findIdentite(name);
        } catch (Exception ex){
            TalendConnection.logg.log(Level.ERROR,"Dans getFonction " + ex.toString());
            sRep = "";
            return sRep;
        }
        sRep = lastIdnt.getFonction();
        return sRep;
    }
    /**
     * Ajoute un classement à un compte
     * @param iCpte identifiant du compte
     * @param classe le classement
     */
    public void setClasse(int iId, String classe) {
        Identite id = trouveIdentite(iId);
        id.setClasse(classe);
    }
    public Iterator<Identite> getIdentites(){
        return super.findIdentiteEntities().iterator();
    }
}
