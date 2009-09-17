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

package info.lespopoff;

import theCube.roleMining.IItemElec;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Set;
//import org.apache.log4j.Logger;
//import org.apache.log4j.Level;

/**
 * Classe parente qui contient les traitemts communs pour la comparaisons de deux comptes
 * suivant la méthode Electre
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
public abstract class SCClassement implements IComparator {
    public static Boolean bErr = false;
    public static String sErr = "Rien";
    public final String nomClassTalend = "theCube.roleMining.TalendConnection";
    public final String nomClassElectre = "theCube.electre.HashElectre";
    public Set lescriteres;
    public Object objCptes;//Comptes
    public Object objDb; //talendconn
    public Object objIdnts;//Identites
    public Object objScls;//CSurClassor
    public Method m1 = null;
    public Method m2 = null;
    public Method m12 = null;
    public Method m22 = null;
    public Method dec = null;
    public Class tal = null;
    //private static Logger loggg = null;
    private String decCrit = null;
    private int sommeStricte2 = 0;
    private int sommeStricte1 = 0;
    private int somme1 = 0;
    private int somme2 = 0;
    
/**
 * Constructeur de la classe qui initialise une fois les méthodes utilisées
 * dans les fonctions communes de comparaisons
 */
    public SCClassement(){
        //loggg = Logger.getLogger("info.lespopoff.SCClassement");
        System.out.println("**************** Initialisation SCClassement");
    }
/**
 * Fonction qui permet de signaler une erreur
 * 
 * @param mess Message transmis suite à une erreur
 */
    public static void setErreur(String mess) {
        bErr = true;
        sErr = mess;
    }
/**
 * Fonction qui compare deux comptes sur tous les critères spécifiés
 * dans la classe qui étend celle ci
 * @param compte1 premier compte à comparer
 * @param compte2 second compte à comparer
 * @return 1 en cas de succès et -2 en cas d'erreur
 */
    public int comparons(Object compte1, Object compte2) {
        Iterator it = lescriteres.iterator();
        ICritere crit = null;
        int i=0;
        decCrit = null;
        sommeStricte2 = 0;
        sommeStricte1 = 0;
        somme1 = 0;
        somme2 = 0;
        while (it.hasNext()) {
            crit = (ICritere) it.next();
            i++;
            decCrit = decCrit + "||Critere"+i+" ";
            System.out.println("Avant unCritere "+i);
            unCritere(crit, compte1, compte2, tal);
            if (bErr) {
                return -2;
            }
        }
        IItemElec i1 = (IItemElec)compte1;
        IItemElec i2 = (IItemElec)compte2;
        decCrit = decCrit+"/sommeStricte1="+sommeStricte1+" sommeStricte2="+sommeStricte2+" somme1="+somme1+" somme2="+somme2;
        try{
            dec.invoke(objScls, new Object[]{(Integer)i1.getId(), (Integer)i2.getId(), (String)decCrit});
        }catch(Exception err){
            bErr = true;
            sErr = "Erreur invocation méthode setDecisionCriteres "+err.toString();
            return -2;
        }
        System.out.println("Décision Critéres: "+decCrit);
        return 1;
    }
/**
 * Fonction qui fait la somme de tous les poids des critères
 * @return la somme des poids
 */
    public int totalPond() {
        Iterator it = lescriteres.iterator();
        ICritere crit = null;
        int iRet = 0;
        while (it.hasNext()) {
            crit = (ICritere) it.next();
            iRet = iRet + crit.getPonderation();
            if (bErr) {
                return -2;
            }
        }
        return iRet;
    }
/**
 * Fonction qui retourne une série des critères
 * @return une série de critères
 */
    public Set criteres() {
        return lescriteres;
    }
/**
 * Fonction qui retourne le texte de la dernière erreur signalée
 * @return un texte decrivant l'erreur
 */
    public String erreurTxt() {
        return sErr;
    }
/**
 * Fonction qui retourne le nombre de critères spécifiques
 * @return le nombre de critères
 */
    public int nbCriteres() {
        return lescriteres.size();
    }
/**
 * Fonction qui retourne le nombre de critères spécifiques
 * @param iCas 1 pour setSommeStricte1, 2 pour setFormuleSeuil1, 3 pour erreurTxt, 12 pour setSommeStricte2, 22 pour setFormuleSeuil2
 * @param cl la référence à la classe TalendConnection ou HashElectre
 * @return une référence sur une fonction 
 */
    public Method setComparatorMethod(int iCas, Class cl) {
        Method mthd = null;
        Class[] par = new Class[3];
        par[0] = Integer.TYPE;
        par[1] = Integer.TYPE;
        par[2] = Integer.TYPE;
        Class[] par2 = new Class[3];
        par2[0] = Integer.TYPE;
        par2[1] = Integer.TYPE;
        par2[2] = Double.TYPE;
        Class[] par3 = new Class[3];
        par3[0] = Integer.TYPE;
        par3[1] = Integer.TYPE;
        par3[2] = String.class;
        switch (iCas) {
            case 1:
                try {
                    mthd = cl.getMethod("setSommeStricte1", par);
                } catch (NoSuchMethodException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                } catch (SecurityException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                }
                break;
            case 2:
                try {
                    mthd = cl.getMethod("setFormuleSeuil1", par2);
                } catch (NoSuchMethodException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                } catch (SecurityException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                }
                break;
            case 3:
                try {
                    mthd = cl.getMethod("erreurTxt");
                } catch (NoSuchMethodException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                } catch (SecurityException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                }
                break;
            case 12:
                try {
                    mthd = cl.getMethod("setSommeStricte2", par);
                } catch (NoSuchMethodException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                } catch (SecurityException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                }
                break;
            case 22:
                try {
                    mthd = cl.getMethod("setFormuleSeuil2", par2);
                } catch (NoSuchMethodException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                } catch (SecurityException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                }
                break;
            case 23: //setDecisionCriteres
                try {
                    mthd = cl.getMethod("setDecisionCriteres", par3);
                } catch (NoSuchMethodException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                } catch (SecurityException ex) {
                    ClassementPerso.setErreur(ex.toString());
                    return null;
                }
                break;
        }
        return mthd;
    }
/**
 * Fonction qui retourne vrai si une erreur est signalée
 * @return vrai pour une erreur
 */
    public Boolean siErreur() {
        return bErr;
    }
/**
 * Fonction qui fait la comparaison entre deux comptes pour un critère donné
 * Le résultat du duel (le gain du poids du critère) est stocké dans 4 attributs
 * en fonction de la comparaison de la note au critère en cours<br>
 *    somme1 (cp1 >= cp2)   dans formuleSeuil1<br>
 *    sommeStricte1 (cp1 > cp2) dans sommeStricte1<br>
 *    somme2 (cp2 >= cp1) dans formuleSeuil2 <br>
 *    sommeStricte2 (cp2 > cp1) dans sommeStricte2<br>
 * Toutes ces valeurs sont stockées dans le compte de rang1 en relation avec le compte
 * de rang 1
 * @param crit le critère utilisé pour la comparaison
 * @param compte1 le premier compte à comparer
 * @param compte2 le second compte à comparer
 * @param tal une référence à la classe 
 */
    public void unCritere(ICritere crit, Object compte1, Object compte2, Class tal) {
        IItemElec cElec1 = null;
        IItemElec cElec2 = null;
        int poidsReel = crit.getPonderation();
        String sErrr = null;
        try{
            cElec1 = (IItemElec)compte1;
            cElec2 = (IItemElec)compte2;
        }catch(Exception err){
            bErr = true;
            sErr = "Erreur casting comptes "+err.toString();
            return;
        }
        //la note pour le premier critère
        Double vRepL = crit.getNoteCritere(compte1);
        if (vRepL == -1.0) {
            bErr = true;
            sErr = "Erreur de calcul de la note compte1 "+sErr;
            return;
        }
        //la note pour le deuxième critère
        Double vRepC = crit.getNoteCritere(compte2);
        if (vRepC == -1.0) {
            bErr = true;
            sErr = "Erreur de calcul de la note compte2 "+sErr;
            return;
        }
        //si cp1 >= cp2 donc sinon cp2 < cp1
        if (vRepL >= vRepC) {
            decCrit = decCrit+"/le compte cp1 gagne le poids ("+ poidsReel+") du critère avec une possibilité d'indifférence/";
            somme1 = somme1 + poidsReel;
        } else {
            decCrit = decCrit+"/sinon c'est cp2 qui gagne le poids ("+ poidsReel+") sans ambiguité/";
            sommeStricte2 = sommeStricte2 + poidsReel;
        }
        //si cp1 > cp2 sinon cp2 =< cp1
        if (vRepL > vRepC) {
            decCrit = decCrit+"/le compte cp1 gagne franchement le poids ("+ poidsReel+")/";
            sommeStricte1 = sommeStricte1 + poidsReel;
        } else {
            decCrit = decCrit+"/le compte cp2 gagne le poids ("+ poidsReel+") avec une possibilité d'indifférence/";
            somme2 = somme2 + poidsReel;
        }
        if(cElec1.getId()==0) sErrr="c1 Id null";
        if(cElec2.getId()==0) sErrr="c2 Id null / "+sErrr;
        if(sommeStricte1==0) sErrr="sommeStricte vide / "+sErrr;
        try {
            //setSommeStricte1
            m1.invoke(objScls, new Object[]{(Integer) cElec1.getId(), (Integer) cElec2.getId(), sommeStricte1});
        } catch (Exception err) {
            bErr = true;
            sErr = " + domElectre3 "+err.toString();//+" "+sErrr;
            return;
        }
        try {
            //setFormuleSeuil1
            m2.invoke(objScls, new Object[]{(Integer) cElec1.getId(), (Integer) cElec2.getId(), new Double(somme1)});
        } catch (Exception err) {
            bErr = true;
            sErr = " + domElectre4 ".concat(err.toString());
            return;
        }
        try {
            //setSommeStricte2
            m12.invoke(objScls, new Object[]{(Integer) cElec1.getId(), (Integer) cElec2.getId(), sommeStricte2});
        } catch (Exception err) {
            bErr = true;
            sErr = " + domElectre5 ".concat(err.toString());
            return;
        }
        try {
            //setFormuleSeuil2
            m22.invoke(objScls, new Object[]{(Integer) cElec1.getId(), (Integer) cElec2.getId(), new Double(somme2)});
        } catch (Exception err) {
            bErr = true;
            sErr = " + domElectre6 ".concat(err.toString());
            return;
        }
    }

}
