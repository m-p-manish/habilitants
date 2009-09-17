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

package theCube.roleMining;

import java.util.Collection;

/**
 * Interface minimale sur les comptes
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
public interface IComptes {
    /**
     * Fonction qui retourne le texte justifiant la dernière erreur
     * @return erreurTxt un texte
     */
    String erreurTxt();
    /**
     * Fonction pour connaitre le nombre de compte dans le container
     * @return getMaxCpte le nombre de compte du container
     */
    int getMaxCpte();

    /**
     * Transcription de Public Function nbreHblt(lTipHblt As Long, lUsrId As Long) As Long
     * permet de connaitre le nombre d'habilitant pour un compte donné et un type donné
     * @param typeHablt type d'habilitant 17, 18, 19
     * @param compteId la clé du compte
     * @return nbreHblt le nombre total d'habilitant
     */
    int nbreHblt(int typeHablt, int compteId);
    /**
     * Fonction qui signale la dernière erreur rencontrée
     * @return siErreur vrai si erreur
     */
    Boolean siErreur();
    /**
     * Retourne un compte Electre sur la base de son identifiant unique aléatoire
     * (un compte peut être aussi une identité)
     * @param id identitfiant de compte
     * @return trouveCompte un item electre
     */
    IItemElec trouveCompteElec(int id);
    /**
     * Ajoute le résultat d'un classement au compte
     * @param classe le classement obtenu qui s'ajoute aux précédents
     * @param iCpte le code du compte
     */
    void setClasse(int iCpte, String classe);

}
