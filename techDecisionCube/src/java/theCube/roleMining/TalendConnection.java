/*
Copyright Stéphane Georges Popoff, (février - juillet 2009)

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
import theCube.roleMining.Comptes.CpteElec;
//import theCube.roleMining.Comptes.Cpte;
import theCube.roleMining.DemiMatrice.Cpt1;
import theCube.roleMining.DemiMatrice.Cpt2;
import org.apache.log4j.Logger;
import org.apache.log4j.Level;
import org.apache.log4j.ConsoleAppender;
import java.util.*;
import theCube.electre.*;
import java.lang.reflect.Method;
import theCube.entities.Compte;

/**
 * Classe qui assure la communication avec le monde amont (Talend Studio)
 * et les composants internes de la solution.
 * En fait c'est cette classe qui fait le main et la distribution des travaux
 * @author spopoff@rocketmail.com
 * @version 0.8
 */
public class TalendConnection {
    private static Identites objUser = null;
    private static Comptes objCpte = null;
    private static Habilitants objTousHblt = null;
//    private static RolesApp objTousRol = null;
    private static Boolean bErr = false;
    private static String sErr = "";
    private Long lApp;
    private String sNomAppli;
    private String sEnviron;
    private Long lRoleId;
    private String sInTrans;
    private Long lEnv;
    private String sNomRole;
    private Long[] ltTrans;
    private Integer iPos;
    public static Logger logg = null;
    private String nomClassComparator = null;
    /**
     *
     * Constructeur de la classe pourrais être le nom de la classe mais
     * n'existait pas en VBA. Les paramètres ne servent à rien car la connexion
     * avec des bases de données serait fait dans l'amont avec Talend
     * Cette méthode permet l'initialisation des classes container d'identité,
     * de comptes, d'habilitant, de roles. On ouvre aussi un logger.
     * @param sConnDB string de connexion ADODB ne sert à rien
     * @param sUser compte pour une base de donnée ne sert à rien
     * @param sPass le password en clair ne sert à rien
     * @deprecated
     */
    public static void init(String sConnDB, String sUser, String sPass) {
        logg = Logger.getLogger("theCube.roleMining.TalendConnection");
        ConsoleAppender appender = (ConsoleAppender)  logg.getAppender("console");
        logg.addAppender(appender);
        logg.log(Level.INFO, "**************** Initialisation");
        try {
            objUser = new Identites();
        } catch (Exception ex) {
            logg.log(Level.ERROR, TalendConnection.class.getName() + " " + ex);
            bErr = true;
            sErr = ex.toString();
            return;
        }
        if (objUser.siErreur()){
            sErr = objUser.erreurTxt();
            logg.log(Level.ERROR, TalendConnection.class.getName() + " " + sErr);
            bErr = true;
            return;
        }
        try {
            objTousHblt = new Habilitants();
        } catch (Exception ex) {
            logg.log(Level.ERROR, TalendConnection.class.getName() + " " + ex);
            bErr = true;
            sErr = ex.toString();
            return;
        }
/*
        try {
            objTousRol = new RolesApp();
        } catch (Exception ex) {
            logg.log(Level.ERROR, TalendConnection.class.getName() + " " + ex);
            bErr = true;
            sErr = ex.toString();
            return;
        }

        try {
            objCpte = new Comptes(objTousHblt, objTousRol);
        } catch (Exception ex) {
            logg.log(Level.ERROR, TalendConnection.class.getName() + " " + ex);
            bErr = true;
            sErr = ex.toString();
            return;
        }
 */
        logg.log(Level.INFO, "******************** Demarre RoleEngine " + sConnDB + ": " + sUser + ": " + sPass);
    }
    /**
     *
     * Méthode d'aide à la décision Electre (version complete et autonome)
     * Contient une classe avec un container pour les données(HashElectre) et une autre (CSurClassor) qui utilise
     * cette classe et distribue des calculs en fonction du parcours des "comptes"
     * Une classe de comparaison est nécessaire pour fonctionner. Elle fabriquée suivant
     * un modèle imposer (Interface et Abstract) et elle utilise la "Reflexion" pour faire
     * des appels aux méthodes de HashElectre et CSurClassor tout comme CSurClassor le fait
     * (pourquoi faire simple quand on peut faire compliqué).
     * 
     * @param sFich un nom de fichier pour faire la sauvegarde du Dom qui servira dans les calculs
     * @param compPerso une classe spécifique qui contient les critères de cette comparaison
     * @param nomClass le nom de la classe précédente avec son package pour la reflexion
     * @deprecated
     */
   public void electre(String sFich, Object compPerso, String nomClass){
       CSurClassor objElectre = null;
       HashElectre objDom = new HashElectre();
       List cpts1 = null;
       CpteElec c1 = null;
       CpteElec c2 = null;
       int iRet = 0;
       String sErrr=null;
       Boolean bErrr=null;
       //classe qui gère la comparaison d'élement deux à deux (pas de différence entre
       // a comp b et b comp a et sans a comp a)
       DemiMatrice objMat = null;
       //on passe le nom de la classe de comparaison en plus de l'instance de l'objet
       nomClassComparator = nomClass;
       //on initialise la classe de comparaison et son container de données
       try {
            cpts1 = (LinkedList<IItemElec>)objCpte.getComptesElec();
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 1 " + sErr);
            return;
       }
       try {
            objMat = new DemiMatrice((LinkedList<IItemElec>)cpts1, sFich, true);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 2 " + sErr);
            return;
       }
       try {
            objDom.init(objMat, sFich);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 4 " + sErr);
            return;
       }
       try {
            objElectre = new CSurClassor(nomClassComparator,compPerso,objDom);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 3 " + sErr);
            return;
       }
       //on commence la réflexion sur la classe de comparaison
       Class cl = null;
       try {
            cl = Class.forName(nomClassComparator);
       } catch (ClassNotFoundException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A " + ex.toString());
            return;
       }
       //trois méthodes dont 2 qui de changent pas siErreur et erreurTxt
       Method msiR = null;
       Method mErr = null;
       Method mthd = null;
       try {
            msiR = cl.getMethod("siErreur");
       } catch (NoSuchMethodException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A01 " + ex.toString());
            return;
       } catch (SecurityException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A02 " + ex.toString());
            return;
       }
       //on vérifie si il y a des erreurs dans la classe de comparaison
       try{
            bErrr = (Boolean)msiR.invoke(compPerso);
       }catch(Exception errr){
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A03 " + errr.toString());
            return;
       }
        try {
            mErr = cl.getMethod("erreurTxt");
       } catch (NoSuchMethodException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A001 " + ex.toString());
            return;
       } catch (SecurityException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A002 " + ex.toString());
            return;
       }
       //si des erreurs on traite
       if(bErrr){
            try{
                sErrr = (String)mErr.invoke(compPerso);
            }catch(Exception errr){
                logg.log(Level.ERROR, CSurClassor.class.getName()+ " A003 " + errr.toString());
                return;
            }
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " inside avant" + sErrr);
            return;
       }
       Class[] par = new Class[4];
       par[0] = Object.class;
       par[1] = Object.class;
       par[2] = Object.class;
       par[3] = Object.class;
       //initialisation de la classe de comparaison, elle doit récupérer des informations
       //des comptes, identités, et de la classe de données Electre (et oui réflexion dans
       //les deux sens)
       try {
            mthd = cl.getMethod("init", par);
       } catch (NoSuchMethodException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A1 " + ex.toString());
            return;
       } catch (SecurityException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A2 " + ex.toString());
            return;
       }
       try{
            mthd.invoke(compPerso, new Object[]{(Object)objCpte, (Object)objUser, (Object)objDom, (Object)this});
       }catch(Exception err){
            logg.log(Level.ERROR, nomClassComparator + " invocation init " + err.toString()+" "+err.getCause());
            try {
                mthd = cl.getMethod("erreurTxt");
            } catch (NoSuchMethodException ex) {
                return;
            } catch (SecurityException ex) {
                return;
            }
            try{
                sErrr = (String)mthd.invoke(compPerso);
            }catch(Exception errr){
                return;
            }
            logg.log(Level.ERROR, nomClassComparator + " détail erreur précédente " +sErrr);
            return;
       }
       try{
            bErrr = (Boolean)msiR.invoke(compPerso);
       }catch(Exception errr){
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " A0003 " + errr.toString());
            return;
       }
       //si des erreurs on traite
       if(bErrr){
            try{
                sErrr = (String)mErr.invoke(compPerso);
            }catch(Exception errr){
                logg.log(Level.ERROR, CSurClassor.class.getName()+ " A0004 " + errr.toString());
                return;
            }
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " inside apres " + sErrr);
            return;
       }
       //on commence vraiment le boulot !
       par = new Class[2];
       par[0] = Object.class;
       par[1] = Object.class;
       mthd = null;
       try {
            mthd = cl.getMethod("comparons", par);
       } catch (NoSuchMethodException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " B " + ex.toString());
            return;
       } catch (SecurityException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " C " + ex.toString());
            return;
       }
       //en avant la musique un premier itérateur sur les comptes de rang1
       for(Cpt1 cc1: (List<Cpt1>)objMat.getListeC1()){
           //un deuxième sur les comptes de rang 2
           for(Cpt2 cc2 : (List<Cpt2>)cc1.getListeC2()){
               c1 = (CpteElec)objCpte.trouveCompteElec(cc1.getId());
               c2 = (CpteElec)objCpte.trouveCompteElec(cc2.getId());
               logg.log(Level.INFO, "comparons cpt1:" + c1.getId()+" et cpt2:"+ c2.getId());
               try {
                    iRet = (Integer) mthd.invoke(compPerso, new Object[]{(Object)c1, (Object)c2});
               } catch (Exception err) {
                    logg.log(Level.ERROR, CSurClassor.class.getName()+ " D " + err.toString());
                    return;
               }
               if(iRet==-2){
                   bErr = true;
                   try {
                        mthd = cl.getMethod("erreurTxt");
                   } catch (NoSuchMethodException ex) {
                        logg.log(Level.ERROR, CSurClassor.class.getName()+ " B " + ex.toString());
                        return;
                   } catch (SecurityException ex) {
                        logg.log(Level.ERROR, CSurClassor.class.getName()+ " C " + ex.toString());
                        return;
                   }
                   try {
                        sErr = (String) mthd.invoke(compPerso);
                   } catch (Exception err) {
                        logg.log(Level.ERROR, CSurClassor.class.getName()+ " D " + err.toString());
                        return;
                   }
                   logg.log(Level.ERROR, nomClassComparator + " E erreur dans comparons "+sErr);
                   return;
               }
           }
           logg.log(Level.INFO, "Fin de comparaison pour cpt1:" + c1.getId());
           try{
                objElectre.electreFinElmProd(c1.getId());
           }catch(Exception err){
               logg.log(Level.ERROR, CSurClassor.class.getName()+ " 4 " + err.toString());
               return;
           }
           if(objElectre.siErreur()){
               logg.log(Level.ERROR, CSurClassor.class.getName()+ " 5 " + objElectre.erreurTxt());
               return;
           }
           objElectre.clearErreur();
       }
       logg.log(Level.INFO, "Fin de toutes les comparaisons *****************");
       try{
           objElectre.finDesBoucles();
       }catch(Exception err){
           logg.log(Level.ERROR, CSurClassor.class.getName()+ " 6 " + err.toString());
           return;
       }
       if(objElectre.siErreur()){
           logg.log(Level.ERROR, CSurClassor.class.getName()+ " 7 " + objElectre.erreurTxt());
           return;
       }
       //procèdure de mise à jour des comptes ou identités
       //on commence par trouver le type de la comparaison
       int iType = 0;
       try {
            mthd = cl.getMethod("getType");
       } catch (NoSuchMethodException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 8 " + ex.toString());
            return;
       } catch (SecurityException ex) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 9 " + ex.toString());
            return;
       }
       try {
            iType = (Integer) mthd.invoke(compPerso);
       } catch (Exception err) {
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 10 " + err.toString());
            return;
       }
       for(Cpt1 cc1: (List<Cpt1>)objMat.getListeC1()){
           switch(iType){
               case 1: objCpte.setClasse(cc1.getId(), objElectre.getClassement(cc1.getId())); break;
               case 2: objUser.setClasse(cc1.getId(), objElectre.getClassement(cc1.getId())); break;
           }
       }
       //on sérialize et on quitte
       objElectre.clearErreur();
       try {
           objElectre.cloze();
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 8 " + sErr);
            return;
       }
       if(objElectre.siErreur()) {
           bErr = true;
            sErr = objElectre.erreurTxt();
            logg.log(Level.ERROR, CSurClassor.class.getName()+ " 9 " + sErr);
       }
       objElectre.clearErreur();
   }
   /**
   *
   * Retourne le nombre d'habilitant de type 17 (17=pas profil, pas CICS)
   * Prend cette information de la classe des habilitants
   * @return getMax17 le total des habilitants de type 17pour le dernier chargement
    * @deprecated
   */
   public int getMax17(){
       return objTousHblt.getMax17();
   }        
  /**
  *
  * Retourne le nombre d'habilitant de type 18 (Profil)
  * Prend cette information de la classe des habilitants
  * @return getMax18 le total des habilitants de type 18 pour le dernier chargement
   * @deprecated
  */
  public int getMax18(){
       return objTousHblt.getMax18();
   }        
   /**
   *
   * Retourne le nombre d'habilitant de type 19 (CICS)
   * Prend cette information de la classe des habilitants
   * @return getMax19 le total des habilitants de type 19 pour le dernier chargement
    * @deprecated
   */
   public int getMax19(){
       return objTousHblt.getMax19();
   }        
     /**
     *
     * Retourne le nombre de compte du dernier chargement
     * Prend cette information de la classe des habilitants
     * @return getMaxCpte le total des comptes pour le dernier chargement
      * @deprecated
     */
    public int getMaxCpte(){
        return objCpte.getMaxCpte();
    }
 /**
 *
 * Retourne le type d'un habilitant
 * Prend cette information de la classe des habilitants 19 = CICS, 18=Profil, 17=le reste
 * @param sNom un habilitant
 * @return typeH le type de l'habilitant
 */
   public static int typeH(String sNom){
        int iVal = 0;
        if(sNom.length()>=4) {
            if (sNom.substring(0, 4).equals("CICS")){ iVal=19;}
        }
        if(sNom.substring(0, 2).equals("P-")) {iVal=18;}
        if(iVal==0) {iVal=17;}
        return iVal;
    }
     /**
     *
     * Retourne une liste des comptes au format Electre (moins d'info)
     * @return getComptesElec une liste des comptes au format Electre
     */
    public static List<CpteElec> getComptesElec(){
        return objCpte.getComptesElec();
    }
     /**
     *
     * Retourne vrai si une erreur dans précédent traitement
     * @return siErreur vrai si erreur
     */
    public Boolean siErreur() {
        return bErr;
    }
     /**
     *
     * Retourne le texte de la dernière erreur
     * @return erreurTxt une erreur en texte
     */
   public String erreurTxt() {
        return sErr;
    }
     /**
     *
     * Retourne un chiffre aléatoire entre deux bornes
     * @param aStart borne basse
     * @param aEnd borne haute
     * @param aRandom un composant de génération aléatoire
     * @return randomInteger un chiffre aléatoire
     */
    private static int randomInteger(int aStart, int aEnd, Random aRandom){
        if ( aStart > aEnd ) {
          throw new IllegalArgumentException("Start cannot exceed End.");
        }
        //get the range, casting to long to avoid overflow problems
        long range = (long)aEnd - (long)aStart + 1;
        // compute a fraction of the range, 0 <= frac < range
        long fraction = (long)(range * aRandom.nextDouble());
        int randomNumber =  (int)(fraction + aStart); 
        return randomNumber;
    }
     /**
     *
     * Retourne un nombre aléatoire pas plus grand qu'un integer
     * @return Aleat un nombre aléatoire
     */
    public static int Aleat() {
//    Randomize
        int iAlea = 0;
        int iB = 0;
        String sT1 = "";
        String sT = "";
        try{
            iB = randomInteger(1,10,new Random());
            boolean bPaire = iB % 2 == 0;
            if(bPaire){
                sT1 = "" + randomInteger(10000,100000,new Random());
            } else {
                sT1 = "" + randomInteger(1000,10000,new Random());
            }
            iB = randomInteger(1,10,new Random());
            bPaire = iB % 2 == 0;
            if(bPaire){
                sT = "" + randomInteger(1000,10000,new Random());
            } else {
                sT = "" + randomInteger(100,1000,new Random());
            }
        }catch(Exception err){
            logg.log(Level.ERROR, TalendConnection.class.getName()+" Aleat marche pas "+err.toString());
            return -1;
        }
        try {
            iAlea = Integer.parseInt(sT1.concat(sT));
        } catch(Exception ex) {
            sT = "" + randomInteger(100,1000,new Random());
            iAlea = Integer.parseInt(sT1.concat(sT));
        }
        return iAlea;
    }
     /**
     * Transcription de Public Function nbreHblt(lTipHblt As Long, lUsrId As Long) As Long
     * permet de connaitre le nombre d'habilitant pour un compte donné et un type donné
     * @param typeHablt type d'habilitant 17, 18, 19
     * @param compteId la clé du compte
     * @return nbreHblt le nombre total d'habilitant
      * @deprecated
     */
    public int nbreHblt(int typeHablt, int compteId){
        int iRep = 0;
        iRep = objCpte.nbreHblt(typeHablt, compteId);
        return iRep;
    }
     /**
     * Transcription de Public Function getFonction(lId As Long) As String
     * permet de connaitre la fonction d'une identité
     * @param userId la clé du compte
     * @return getFonction la fonction retounée
     * @deprecated
     */
    public String getFonction(int cpteId){
        String sRep="";
        Compte c = objCpte.trouveCompte(cpteId);
        sRep = objUser.getFonction(c.getName());
        return sRep;
    }
     /**
     *
     * Retourne le nom de la classe qui sert à la méthode Electre
     * @return getNomClassComparator un nom de classe et son package
     * @deprecated
     */
    public String getNomClassComparator(){
        return nomClassComparator;
    }
}
