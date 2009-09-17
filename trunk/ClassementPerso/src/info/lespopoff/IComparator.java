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

package info.lespopoff;
import java.util.*;

/**
 *
 * Interface pour assurer la compatibilité entre le développement d'un choix de classement particulier
 * et son execution par le SurClassor
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
public interface IComparator {
     /**
     * Récupère les références des objets comptes identités et surclassement
     * @param comptes la ref sur les comptes
     * @param identites la ref sur les identites
     * @param surClassor la ref sur le surclassement
     */
   void init(Object comptes, Object identites, Object surClassor, Object talendConn);
    /**
     * Retourne le texte de la dernière erreur
     * @return erreurTxt Contient le texte en String
     */
    String erreurTxt();
    /**
     * Retourne le texte de la dernière erreur
     * @return siErreur retourne vrai si erreur
     */
    Boolean siErreur();
    /**
     * Retourne la somme des pondérations des critères
     * @return totalPond la pondération totale des critères
     */
    int totalPond();
    /**
     * Retourne le code du classement
     * @return getClassementCode retourne une chaine identifiant le classement
     */
    String getClassementCode();
    /**
     * Retourne la valeur du seuil de décision
     * @return seuiGlobal retourne le seuil entre 0.5 et 1
     */
    Double getSeuilGlobal();
     /**
     * Retourne le texte de la dernière erreur
     * @return comparons retourne 1 si supérieur, 0 si indifférent, 2 si incompatibles
     * @param compte1 un premier compte
     * @param compte2 un deuxième compte
     */
   int comparons(Object compte1, Object compte2);
    /**
     * Retourne le nombre de critère
     * @return nbCriteres entre 1 et n critères
     */
    int nbCriteres();
    /**
     * Retourne une liste de critère HashMap
     * @return criteres entre 1 et n critères dans HashMap
     */
    Set criteres();
    /**
     * Retourne ce sur quoi s'applique le comparateur multicritère
     * 1 = compte, 2 = identité
     * @return getType le type sur lequel s'applique le comparateur
     */
    int getType();
}
