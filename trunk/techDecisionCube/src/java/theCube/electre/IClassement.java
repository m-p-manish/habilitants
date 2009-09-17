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

package theCube.electre;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
public interface IClassement {
    /**
     * Fonction qui permet de connaitre le nombre maximum de classe obtenu par la dernière execution
     * @return maxClasses le nombre de classe remontées par la méthode
     */
    int maxClasses();
    /**
     * Fonction qui retourne le classement d'un compte ou une personne
     * @param iUse code d'un compte ou un utilisateur
     * @return getClassement La possition dans le classement de type lettre + chiffre
     */
    String getClassement(int iUse);
    /**
     * Procèdure finale à la fin des compararisons
     */
    void finDesBoucles();
    /**
     * Retourne le texte de la dernière erreur
     * @return siErreur retourne vrai si erreur
     */
    boolean siErreur();
    /**
     * Retourne le texte de la dernière erreur
     * @return erreurTxt Contient le texte en String
     */
    String erreurTxt();
    /**
     * Procèdure de fermeture et libération de la mémoire
     */
    void cloze();
    /**
     * Element de décision au niveau compte justifiant le classement du compte (cpx)
     * @param iCpt1 code du compte de rang 1
     * @param dec texte de la décision
     */
    void setDecisionFinale(int iCpt1, String dec);
    /**
     * Element de décision entre deux comptes sur une analyse en colonne (cp2 -> cp1x)
     * @param iCpt1 code du compte de rang 1
     */
    String getDecisionFinale(int iCpt1);
}
