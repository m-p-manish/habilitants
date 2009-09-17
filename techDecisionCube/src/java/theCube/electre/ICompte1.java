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
import java.util.List;

/**
 * Classe portant la liste des comptes de rang 1 ou colonne pour Electre
 * On fait référence ici à une matrice en deux dimensions avec laquelle on
 * compare tous les comptes mais une seule fois, donc une demie matrice.
 * En consèquence tous les comptes sont représenté sur le rang 1 mais sur le 
 * rang 2 (voir ICompte2) on ne trouvera que ceux qui ne sont pas encore comparé
 * Pour faire clair si j'ai 4 comptes A B C D j'aurais cp1 A et cp2 ABCD, puis 
 * cpt1 B cpt2 BCD, puis cpt1 C cpt2 CD, enfin cpt1 D cpt2 D.
 * @author spopoff
 */
public interface ICompte1 {
    /**
     * Un de compte que l'on utilise pas
     * @return getCpteId un code pour rien
     */
    Long getCpteId();
    /**
     * Les comptes sous les premiers
     * @return itLesComptes2 la liste des comptes de deuxième rang ou ligne
     */
    List itLesComptes2();
    /**
     * Méthode pour ajouter un compte de rang 2 ou colonne
     * @param id l'identifiant unique du compte
     * @param uid l'identifiant de l'utilisateur
     * @return addCompte2 un compte2
     */
    Object addCompte2(int id, String uid);
    /**
     * Le champ d'annotation du compte pour l'incomparabilité
     * @return getDescription remplie si incomparabilité
     */
    String getDescription();
    /**
     * L'indicateur d'incomparabilité
     * @return getFlagProdIncomp vrai si incomparable
     */
    Boolean getFlagProdIncomp();
    /**
     * L'identifiant unique du champ
     * @return getId Le code unique
     */
    int getId();
    /**
     * Le code de la classe (une lettre un chiffre) obtenu via Electre
     * @return getLeClassement une classe
     */
    String getLeClassement();
    /**
     * Sans usage pour Electre, ce n'est pas une moyenne !
     * @return getNote marche pas
     */
    Double getNote();
    /**
     * Classement final
     * @return getSoustrait une classe
     */
    int getSoustrait();
    /**
     * Classement final
     * @return getTabConcordanceClass une classe
     */
    int getTabConcordanceClass();
    /**
     * Somme des indicateurs favorables à un compte dans une analyse en colonne
     * @return getTabConcordanceSomC une classe
     */
    int getTabConcordanceSomC();
    /**
     * Somme des indicateurs favorables à un compte dans une analyse en ligne
     * @return getTabConcordanceSomL une classe
     */
    int getTabConcordanceSomL();
    /**
     * Retourne l'uid de l'utilisateur porteur du compte
     * @return getUidL une référence à l'utilisateur
     */
    String getUid();
    /**
     * Le champ d'annotation du compte pour l'incomparabilité
     * @param sVal le texte justifiant l'incomparabilité
     */
    void setDescription(String sVal);
    /**
     * met à jour l'incicateur d'incomparabilité
     * @param bVal vrai si incomparable
     */
    void setFlagProdIncomp(Boolean bVal);
    /**
     * met à jour le classement du compte
     * @param sVal une lettre et un chiffre
     */
    void setLeClassement(String sVal);
    /**
     * Sans usage pour Electre, ce n'est pas une moyenne !
     * @param dVal marche pas
     */
    void setNote(Double dVal);
    /**
     * met à jour le classement final
     * @param iVal classement de 1(mieux classé) vers n (mal classé)
     */
    void setSoustrait(int iVal);
    /**
     * met à jour le classement final
     * @param iVal classement de 1(mieux classé) vers n (mal classé)
     */
    void setTabConcordanceClass(int iVal);
    /**
     * met à jour la somme des indicateurs favorables à un compte dans une analyse en colonne
     * @param iVal la valeur entière à mettre à jour
     */
    void setTabConcordanceSomC(int iVal);
    /**
     * met à jour la somme des indicateurs favorables à un compte dans une analyse en ligne
     * @param iVal la valeur entière à mettre à jour
     */
    void setTabConcordanceSomL(int iVal);
    /**
     * Element de décision au niveau compte justifiant le classement du compte (cp1x)
     * @param dec texte de la décision
     */
    void setDecisionFinale(String dec);
    /**
     * Element de décision au niveau compte justifiant le classement du compte (cp1x)
     * @return getDecisionFinale texte de la décision
     */
    String getDecisionFinale();
}
