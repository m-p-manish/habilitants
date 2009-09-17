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
 * Classe portant la liste des comptes de rang 2 ou ligne pour Electre
 * On fait référence ici à une matrice en deux dimensions avec laquelle on
 * compare tous les comptes mais une seule fois, donc une demie matrice.
 * En consèquence tous les comptes sont représenté sur le rang 1 mais sur le 
 * rang 2 (voir ICompte2) on ne trouvera que ceux qui ne sont pas encore comparé
 * Pour faire clair si j'ai 4 comptes A B C D j'aurais cp1 A et cp2 ABCD, puis 
 * cpt1 B cpt2 BCD, puis cpt1 C cpt2 CD, enfin cpt1 D cpt2 D.
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public interface ICompte2 {
    /**
     * Un de compte que l'on utilise pas
     * @return getCpteId un code pour rien
     */
    Long getCpteId();
    /**
     * Fonction pour lire l'attribut csFse1 = "FormuleSeuil1" (valeur du poids obtenu dans un duel ou cp1 >= cp2)
     * Cette valeur sera transformée en indice en la divisant par la somme des poids
     * @return getFormuleSeuil1 la valeur numérique
     */
    Double getFormuleSeuil1();
    /**
     * Fonction pour lire l'attribut csFse2 = "FormuleSeuil2" (valeur du poids obtenu dans un duel ou cp2 =< cp1)
     * Cette valeur sera transformée en indice en la divisant par la somme des poids
     * @return getFormuleSeuil2 la valeur numérique
     */
    Double getFormuleSeuil2();
    /**
     * Fonction pour lire l'attribut csFS1 = "FormuleSuperieur1" 1 indique que cp2 a gagné sur cp1;
     * 0 indique que cp2 à perduu contre cp1
     * @return getFormuleSuperieur1 1 ou 0
     */
    int getFormuleSuperieur1();
    /**
     * Fonction pour lire l'attribut csFS2 = "FormuleSuperieur2" 1 indique que cp1 à gagné sur cp2;
     * 0 indique que cp1 a perdu contre cp2
     * @return getFormuleSuperieur2 1 ou 0
     */
    int getFormuleSuperieur2();
    /**
     * L'identifiant unique du champ
     * @return getId Le code unique
     */
    int getId();
    /**
     * Fonction pour lire l'attribut csSS1 = "SommeStricte1" qui totalise tous les poids obtenus
     * dans le cas (cp1 > cp2)
     * @return getSommeStricte1 la somme des poids
     */
    int getSommeStricte1();
    /**
     * Fonction pour lire l'attribut csSS2 = "SommeStricte2" qui totalise tous les poids obtenus
     * dans le cas (cp2 > cp1)
     * @return getSommeStricte2 la somme des poids
     */
    int getSommeStricte2();
    /**
     * Fonction qui retourne le résultat du test suivant: si résultat de (cp2>=cp1) dépasse seuil de décision ET que
     * résultat de (cp2 > cp1) est plus grand ou égal aux résultat de (cp1>cp2)
     * Si 1 cp2 est meilleur que cp1 sinon ce n'est pas le cas
     * @return getTabConcordanceBas 1 ou 0
     */
    int getTabConcordanceBas();
    /**
     * Fonction qui retourne le résultat du test suivant: si résultat de (cp1>=cp2) dépasse seuil de décision ET que
     * résultat de (cp1 > cp2) est plus grand ou égale à résultat de (cp2 > cp1)
     * Si 1 cp1 est meilleur que cp2 sinon ce n'est pas le cas
     * @return getTabConcordanceHaut 1 ou 0
     */
    int getTabConcordanceHaut();
    /**
     * Retourne l'uid de l'utilisateur porteur du compte
     * @return getUidL une référence à l'utilisateur
     */
    String getUid();
    /**
     * Fonction pour écrire l'attribut csFse1 = "FormuleSeuil1" (valeur du poids obtenu dans un duel ou cp1 >= cp2)
     * Cette valeur sera transformée en indice en la divisant par la somme des poids
     * @param iVal la valeur numérique
     */
    void setFormuleSeuil1(Double iVal);
    /**
     * Fonction pour écrire l'attribut csFse2 = "FormuleSeuil2" (valeur du poids obtenu dans un duel ou cp2 =< cp1)
     * Cette valeur sera transformée en indice en la divisant par la somme des poids
     * @param iVal la valeur numérique
     */
    void setFormuleSeuil2(Double iVal);
    /**
     * Fonction pour écrire l'attribut csFS1 = "FormuleSuperieur1" 1 indique que cp2 a gagné sur cp1;
     * 0 indique que cp2 à perdu contre cp1
     * @param iVal 1 ou 0
     */
    void setFormuleSuperieur1(int iVal);
    /**
     * Fonction pour écrire l'attribut csFS2 = "FormuleSuperieur2" 1 indique que cp1 à gagné sur cp2;
     * 0 indique que cp1 a perdu contre cp2
     * @param iVal 1 ou 0
     */
    void setFormuleSuperieur2(int iVal);
    /**
     * Fonction pour écrire l'attribut csSS1 = "SommeStricte1" qui totalise tous les poids obtenus
     * dans le cas (cp1 > cp2)
     * @param iVal la somme des poids
     */
    void setSommeStricte1(int iVal);
    /**
     * Fonction pour écrire l'attribut csSS2 = "SommeStricte2" qui totalise tous les poids obtenus
     * dans le cas (cp2 > cp1)
     * @param iVal la somme des poids
     */
    void setSommeStricte2(int iVal);
    /**
     * Fonction qui écrit le résultat du test suivant: si résultat de (cp1>=cp2) dépasse seuil de décision ET que
     * résultat de (cp1 > cp2) est plus grand ou égale à résultat de (cp2 > cp1)
     * Si 1 cp1 est meilleur que cp2 sinon ce n'est pas le cas
     * @param iVal 1 ou 0
     */
    void setTabConcordanceBas(int iVal);
    /**
     * Fonction qui écrit le résultat du test suivant: si résultat de (cp1>=cp2) dépasse seuil de décision ET que
     * résultat de (cp1 > cp2) est plus grand ou égale à résultat de (cp2 > cp1)
     * Si 1 cp1 est meilleur que cp2 sinon ce n'est pas le cas
     * @param iVal 1 ou 0
     */
    void setTabConcordanceHaut(int iVal);
}
