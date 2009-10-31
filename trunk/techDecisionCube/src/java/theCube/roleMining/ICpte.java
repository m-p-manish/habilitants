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
import java.util.LinkedList;
/**
 * Interface pour fabriquer un compte
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public interface ICpte {
    /**
     * Retourne l'identifiant du compte
     * @return getId un entier
     */
    int getId();
    /**
     * Retourne l'identifiant externe du compte
     * @return getId2 un entier
     */
    Long getId2();
    /**
     * Retourne le lient vers l'identité
     * @return getName un code identité
     */
    String getName();
    /**
     * Retourne le profil c'est un habilitant à part
     * @return getProfil
     */
    String getProfil();
    /**
     * Retourne la liste des habilitants du compte
     * @return listHabilitants une liste
     */
    LinkedList<String> listHabilitants();
    /**
     * Ajoute un classement au compte
     * @param sVal le classement
     */
    void setClasse(String sVal);
    /**
     * Retourne un habilitant sur la base de son identifiant
     * @param un identifiant du compte
     * @return unHabilitant un habilitant de la liste
     */
    String unHabilitant(int un);

}