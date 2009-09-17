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

import java.util.*;
import theCube.roleMining.IItemElec;
import java.lang.reflect.*;
/**
 * une classe perso qui implémente trois critères mais 10 ce serait pareil
 * le boulot est fait dans la SuperClass SSClassement
 * @author spopoff
 * @version 0.3
 */
public class ClassementPerso extends SCClassement{
    private Method mnb = null;
    private Method mfn = null;
    private Class taal = null;
    private Class elec = null;
    private Object objDbb = null;
    private Class cptes = null;
    private Class idnts = null;
    private Object icptes = null;
    private Object iidnts = null;
    /**
     * Constructeur qui appelle le constructeur parent
     */
    public ClassementPerso(){
         super();
         if(SCClassement.bErr) SCClassement.sErr = "une erreur dans constructeur parent" ;
    }
    /**
     * Procèdure pour initialiser les critères spécifiques apporté par cette classe
     * @param comptes la référence sur la collection des comptes
     * @param identites la référence sur la collection des identités
     * @param surClassor la référence sur l'objet qui fait le traitement Electre
     */
    public void init(Object comptes, Object identites, Object surClassor, Object talendConn) {
        super.objCptes = comptes;
        icptes = comptes;
        //cptes = super.objCptes;
        super.objIdnts = identites;
        iidnts = identites;
        super.objScls = surClassor;
        //super.objDb = talendConn;
        try {
            super.tal = Class.forName(super.nomClassTalend);
        } catch (ClassNotFoundException ex) {
            SCClassement.bErr = true;
            SCClassement.sErr = "Invocation de TalendConnection echoue "+ex.toString();
            return;
        }
        try {
            cptes = Class.forName("theCube.roleMining.Comptes");
        } catch (ClassNotFoundException ex) {
            SCClassement.bErr = true;
            SCClassement.sErr = "Invocation de Comptes echoue "+ex.toString();
            return;
        }
        try {
            idnts = Class.forName("theCube.roleMining.Identites");
        } catch (ClassNotFoundException ex) {
            SCClassement.bErr = true;
            SCClassement.sErr = "Invocation de Identites echoue "+ex.toString();
            return;
        }
        taal = super.tal;
        objDbb = super.objDb;
        try {
            elec = Class.forName(super.nomClassElectre);
        } catch (ClassNotFoundException ex) {
            SCClassement.bErr = true;
            SCClassement.sErr = "Invocation de Electre echoue "+ex.toString();
            return;
        }
        super.lescriteres = new LinkedHashSet();
        try{
            super.lescriteres.add(new Critere1());
        }catch(Exception errr){
            SCClassement.bErr = true;
            SCClassement.sErr = "erreur instanciation critère1 "+errr.toString();
            return;
        }
        try{
            super.lescriteres.add(new Critere2());
        }catch(Exception errr){
            SCClassement.bErr = true;
            SCClassement.sErr = "erreur instanciation critère2 "+errr.toString();
            return;
        }
        try{
            super.lescriteres.add(new Critere3());
        }catch(Exception errr){
            SCClassement.bErr = true;
            SCClassement.sErr = "erreur instanciation critère3 "+errr.toString();
            return;
        }
        try{
            super.m1 = super.setComparatorMethod(1, elec);
            super.m2 = super.setComparatorMethod(2, elec);
            super.m12 = super.setComparatorMethod(12, elec);
            super.m22 = super.setComparatorMethod(22, elec);
            super.dec = super.setComparatorMethod(23, elec);
        }catch(Exception err){
            SCClassement.bErr = true;
            SCClassement.sErr = "Erreur invocation methodes "+err.toString();
            return;
        }
        if(super.m1==null || super.m2==null){
            SCClassement.bErr = true;
            SCClassement.sErr = "Erreur invocation methodes "+sErr;
            return;
        }
        Class[] par = new Class[2];
        par[0] = Integer.TYPE;
        par[1] = Integer.TYPE;
        try {
            mnb = cptes.getMethod("nbreHblt", par);
        } catch (NoSuchMethodException ex) {
            ClassementPerso.setErreur("Erreur invocation critere1b " + ex.toString());
            return;
        } catch (SecurityException ex) {
            ClassementPerso.setErreur("Erreur invocation critere1c " + ex.toString());
            return;
        }
        par = new Class[1];
        par[0] = Integer.TYPE;
        try {
            mfn = cptes.getMethod("getFonction", par);
        } catch (NoSuchMethodException ex) {
            ClassementPerso.setErreur("Erreur invocation critere3 " + ex.toString());
            return;
        } catch (SecurityException ex) {
            ClassementPerso.setErreur("Erreur invocation critere3 " + ex.toString());
            return;
        }
    }
    /**
     * Fonction qui retourne le seuil de décision
     * @return vrai pour une erreur
     */
    public Double getSeuilGlobal(){
        return 0.65;
    }
    /**
     * Fonction qui retourne le code du classement
     * @return un texte
     */
    public String getClassementCode() {
        return "A_";
    }
    /**
     * Comparateur pour des comptes
     */
    public int getType() {
        return 1;
    }
    /**
     * Critère numéro 1
     */
    public class Critere1 implements ICritere{
        private Map lesreponses = new HashMap();
        private Double iRep;
        private IItemElec cElec;
        private int iMax=0;
        private String sErr = null;
        private Integer iRet=null;
        /**
         * constructeur du Critère numéro 1 initialise les références aux méthodes de TalendConnection
         */
        public Critere1(){
            Method mthd = null;
            try {
                mthd = cptes.getMethod("getMax19");
            } catch (NoSuchMethodException ex) {
                ClassementPerso.setErreur("Erreur constructeur critere1 " + ex.toString());
                return;
            } catch (SecurityException ex) {
                ClassementPerso.setErreur("Erreur constructeur critere1 " + ex.toString());
                return;
            }
            try {
                iMax = (Integer) mthd.invoke(icptes);
            } catch (Exception err) {
                ClassementPerso.setErreur("Erreur constructeur critere1 " + err.toString());
                return;
            }
        }
        /**
         * Fonction qui retourne la note obtenue par le compte ce qui est la fonction principale du critère
         * @param compte une compte
         * @return une note
         */
        public Double getNoteCritere(Object compte) {
                cElec = (IItemElec) compte;
                Integer cpteId = cElec.getId();
                if (lesreponses.get(cpteId) == null) {
                    try {
                        iRet = (Integer) mnb.invoke(icptes, new Object[]{(Integer)19, (Integer) cpteId});
                    } catch (Exception err) {
                        ClassementPerso.setErreur("Erreur invocation critere1d " + err.toString());
                        return -1.0;
                    }
                    iRep = new Double(iRet);
                    lesreponses.put(cpteId, (Double)iRep/iMax);
                } else {
                    iRep = (Double) lesreponses.get(cpteId);
                }
                return iRep;
        }
        /**
         * Fonction qui retourne la pondération relative du critère
         * @return un poids
         */
        public int getPonderation() {
            return 40;
        }
        /**
         * Fonction qui retourne le texte de la dernière erreur signalée
         * @return un poids
         */
        public String erreurTxt() {
            return sErr;
        }
        
    }
    /**
     * Critère numéro 2
     */
    public class Critere2 implements ICritere{
        private Map lesreponses = new HashMap();
        private Double iRep;
        private IItemElec cElec;
        private int iMax=0;
        private Integer iRet=0; 
        /**
         * constructeur du Critère numéro 2 initialise les références aux méthodes de TalendConnection
         */
        public Critere2(){
            Method mthd = null;
            try {
                mthd = cptes.getMethod("getMax18");
            } catch (NoSuchMethodException ex) {
                ClassementPerso.setErreur("Erreur Construction critere21 " + ex.toString());
                return;
            } catch (SecurityException ex) {
                ClassementPerso.setErreur("Erreur Construction critere21 " + ex.toString());
                return;
            }
            try {
                iMax = (Integer) mthd.invoke(icptes);
            } catch (Exception err) {
                ClassementPerso.setErreur("Erreur Construction critere23 " + err.toString());
                return;
            }
        }
        public Double getNoteCritere(Object compte) {
            cElec = (IItemElec) compte;
            Integer cpteId = cElec.getId();
            if(lesreponses.get(cElec.getId())==null){
                try{
                iRet = (Integer)mnb.invoke(icptes, new Object[]{18, (Integer)cElec.getId()});
                }catch(Exception err){
                    ClassementPerso.setErreur("Erreur invocation critere2 " + err.toString());
                    return -1.0;
                }
                iRep = new Double(iRet);
                lesreponses.put(cpteId, (Double)iRep/iMax);
            }else{
                iRep = (Double)lesreponses.get(cpteId);
            }
            return iRep;
        }

        public int getPonderation() {
            return 40;
        }
        public String erreurTxt() {
            return sErr;
        }
    }
    /**
     * Critère numéro 3
     */
    public class Critere3 implements ICritere{
        private Map lesreponses = new HashMap();
        private Double iRep;
        private IItemElec cElec;

        public Double getNoteCritere(Object compte) {
            String sRep = null;
            cElec = (IItemElec) compte;
            Integer cpteId = cElec.getId();
            if(lesreponses.get(cElec.getId())==null){
                try{
                sRep = (String)mfn.invoke(icptes, new Object[]{(Integer)cElec.getId()});
                }catch(Exception err){
                    ClassementPerso.setErreur("Erreur invocation critere3 " + err.toString());
                    return 0.0;
                }
                if(sRep.equals("ASS")){
                    iRep = 1.0;
                }else{
                    iRep = 0.0;
                }
                lesreponses.put(cpteId, iRep);
            }else{
                iRep = (Double)lesreponses.get(cpteId);
            }
            return iRep;
        }

        public int getPonderation() {
            return 30;
        }
        public String erreurTxt() {
            return sErr;
        }
    }

}
