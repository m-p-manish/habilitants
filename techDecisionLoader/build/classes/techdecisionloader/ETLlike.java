/*
Copyright Stéphane Georges Popoff, (mai - juillet 2009)

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
/**
 * Ce package contient les classes qui gèrent les données de base de l'outil a savoir
 * les comptes, les identités, les rôles, les habilitants
 */
package techdecisionloader;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import techDecision.dao.*;
import techDecision.entites.*;
import techDecision.dao.exceptions.TechDecisionErreurs;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Classe qui assure la communication avec le monde amont (Talend Studio)
 * et l'EJB numéro 1 qui est branché sur le repository du sytème de sécurité de l'entreprise.
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
public class ETLlike {
    //public static Logger logg = null;
    private Compte cpte = null;
    private Identite idnt  = null;
    private Habilitant hblt = null;
    private ITechDecisionDaoRemote dao = null;
    private InitialContext ctx = null;
    private final String ejbName = "ejbTechDecision";
    private MessageDigest empreinte = null;
    private String appKch = "";
    private int pkappKch = 0;
    private String idntKch = "";
    private int pkidntKch = 0;

    public void init(String sHost, String sPort) {
        //logg = Logger.getLogger("techdecisionloader.ETLlike");
        //ConsoleAppender appender = (ConsoleAppender)  logg.getAppender("console");
        //logg.addAppender(appender);
        //logg.log(Level.INFO, "**************** Initialisation");
        Properties props = new Properties();
        props.setProperty("java.naming.factory.initial",
                                 "com.sun.enterprise.naming.SerialInitContextFactory");
        props.setProperty("java.naming.factory.url.pkgs",
                                 "com.sun.enterprise.naming");
        props.setProperty("java.naming.factory.state",
                                 "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

        // optional.  Defaults to localhost.  Only needed if web server is running
        // on a different host than the appserver
        props.setProperty("org.omg.CORBA.ORBInitialHost", sHost);

        // optional.  Defaults to 3700.  Only needed if target orb port is not 3700.
        props.setProperty("org.omg.CORBA.ORBInitialPort", sPort);
        props.setProperty("java.naming.provider.url","iiop://"+sHost+":"+sPort);
        try {
            // TODO code application logic here
            ctx = new InitialContext(props);
        } catch (NamingException ex) {
            //logg.log(Level.ERROR, "Pas de connexion au serveur" + ex.toString());
            System.err.println("Pas de connexion au serveur" + ex.toString());
            System.exit(1);
        }
        try {
            dao = (ITechDecisionDaoRemote) ctx.lookup(ejbName);
        } catch (NamingException ex) {
            //logg.log(Level.ERROR, "Pas trouver l ejb dans serveur " + ex.toString());
            System.err.println("Pas trouver l ejb dans serveur " + ex.toString());
            System.exit(2);
        }
        try{
            dao.init();
        }catch(Exception err){
            //logg.log(Level.ERROR, "Echec initialisation ejb " + err.toString());
            System.err.println("Echec initialisation ejb " + err.toString());
            System.exit(3);
        }
        try {
            empreinte = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException ex) {
            System.err.println(ex.toString());
        }
    }
    /**
     *
     * Fontion pour ajouter une identité
     * @param nom son nom en fait sa clé unique on s'en servira pour la liaison avec les comptes
     * @param dept un département une organisation
     * @param fonction un métier une activité
     */
    public void ajouteUtilisateur(String nom, String dept, String fonction){
        try{
            idnt = new Identite(aleat(),nom, fonction, dept);
            dao.createIdentite(idnt);
        }catch(TechDecisionErreurs err){
            //logg.log(Level.ERROR, "Erreur sur ajout identite de " + nom  +" " + err.toString());
            System.err.println("Erreur sur ajout identite de " + nom  +" " + err.toString());
        }

    }
    /**
     * Lier deux applications au sens hiérarchique si nom1=nom2 alors application
     * racine (APPROOT=VRAI), sinon APPPAR = pkObjs de l'appli parente
     * @param nom1 nom de l'appli1 (enfant)
     * @param nom2 nom de l'appli2 ou appli1 (parent)
     */
    public void lierAppli(String nom1, String nom2){
         Integer a1 = 0;
         Integer a2 = 0;
         int l = nom1.length();
         if(l>150) l=150;
         empreinte.reset();
         empreinte.update(nom1.getBytes());
         byte[] hash = empreinte.digest();
         int l2 = nom2.length();
         if(l2>150) l=150;
         empreinte.reset();
         empreinte.update(nom2.getBytes());
         byte[] hash2 = empreinte.digest();
         try{
            a1 = dao.getPkAppli(hashToString(hash).substring(0,10));
            if(a1==0) return;
            if(!nom1.equals(nom2)){
                a2 = dao.getPkAppli(hashToString(hash2).substring(0,10));
                if(a2==0) return;
            }
            if(a2==0){ //si application maitre
                System.out.println("appli root="+nom1.substring(0, l));
                dao.ajouteAttr(3, "APPROOT", "VRAI", a1);
            }else{ //sinon application1 dépendante de application2
                System.out.println("appli parente="+nom2.substring(0, l2)+" pour"+nom1.substring(0, l));
                dao.ajouteAttr(3, "APPPAR", ""+a2, a1);
            }
        }catch(TechDecisionErreurs err){
            //logg.log(Level.ERROR, "Erreur sur ajout identite de " + nom  +" " + err.toString());
            System.err.println("Erreur sur liaison appli nom1=" + nom1  +" nom2=" + nom2 + " " + err.toString());
        }
    }
    /**
     * Ajoute une application dans un objet de sécurité
     * @param nom nom de l'appli
     * @param type de l'appli
     */
    public void ajouteAppli(String nom, int type){
         int a = aleat();
         int l = nom.length();
         if(l>150) l=150;
         empreinte.reset();
         empreinte.update(nom.getBytes());
         byte[] hash = empreinte.digest();
         try{
            if(dao.existAppli(hashToString(hash).substring(0,10))) return;
            Objsecu objs = new Objsecu(a);
            dao.createObjetSecu(objs);
            System.out.println("nom appli="+nom.substring(0, l));
            dao.ajouteAttr(3, "NOMAPP", nom.substring(0, l), a);
            dao.ajouteAttr(3, "APPHASH", hashToString(hash).substring(0, 10), a);
            dao.ajouteAttr(3, "TYPEAPP", ""+type, a);
        }catch(TechDecisionErreurs err){
            //logg.log(Level.ERROR, "Erreur sur ajout identite de " + nom  +" " + err.toString());
            System.err.println("Erreur sur ajout appli id=" + a  +" " + err.toString());
        }
    }
    /**
     * Fonction qui ajoute un chef à une identité
     * @param idnt username de l'identité
     * @param chef username du chef
     */
    public void ajouteChef(String idnt, String chef){
        try{
            int idU = dao.getIdIdnt(idnt);
            int idC = dao.getIdIdnt(chef);
            dao.ajouteAttr(2,"CHEF",""+idC,idU);
        }catch(TechDecisionErreurs err){
            //logg.log(Level.ERROR, "Erreur sur ajout identite de " + nom  +" " + err.toString());
            System.err.println("Erreur sur ajout chef=" + chef  +" à user="+idnt+ " " + err.toString());
        }catch(Exception err){
            //logg.log(Level.ERROR, "Erreur sur ajout identite de " + nom  +" " + err.toString());
            System.err.println("Erreur sur ajout chef=" + chef  +" à user="+idnt+ " " + err.toString());
        }
    }
    /**
     * Fonction d'ajout d'attribut à un 1=compte, une 2=identité, un 3=objet de sécurité
     * @param iType 1=compte, une 2=identité, un 3=objet de sécurité
     * @param sAtt attribut
     * @param sVal valeur de l'attribut
     * @param id le username
     */
    public void ajouteAttr(int iType, String sAtt, String sVal, String sId){
        switch(iType){
            case 1: //compte
                try{
                    int id = new Integer(sId);
                    if(id==-1){
                        return;
                    }
                    dao.ajouteAttr(1, sAtt, sVal, id);
                }catch(TechDecisionErreurs err){
                    System.err.println("Erreur sur ajout attribut compte pour=" + sId  +" " + err.toString());
                }catch(Exception err){
                    System.err.println("Erreur sur ajout attribut compte pour=" + sId  +" " + err.toString());
                }
                break;
            case 2: //identité
                try{
                    int id = dao.getIdIdnt(sId);
                    if(id==-1){
                        return;
                    }
                    dao.ajouteAttr(2, sAtt, sVal, id);
                }catch(TechDecisionErreurs err){
                    System.err.println("Erreur sur ajout attribut identité pour=" + sId  +" " + err.toString());
                }catch(Exception err){
                    System.err.println("Erreur sur ajout attribut identité pour=" + sId  +" " + err.toString());
                }

                break;
            case 3: //obj secu
                try{
                    int id = new Integer(sId);
                    if(id==-1){
                        return;
                    }
                    dao.ajouteAttr(3, sAtt, sVal, id);
                }catch(TechDecisionErreurs err){
                    System.err.println("Erreur sur ajout attribut obj secu pour=" + sId  +" " + err.toString());
                }catch(Exception err){
                    System.err.println("Erreur sur ajout attribut obj secu pour=" + sId  +" " + err.toString());
                }
                break;
        }

    }
    /**
     * Ajoute un habilitant avec l'attribut de lien et sa référence à l'application
     * @param pkHblt code de l'habilitant
     * @param sHblt valeur de l'habilitant
     * @param appli référence de l'application (objet sécurité) 
     */
    public void ajouteHabilitant(String sHblt, String appli, int type){
       Integer a1 = 0;
       int a = aleat();
       Habilitant hh = null;
       empreinte.reset();
       empreinte.update(appli.getBytes());
       byte[] hash = empreinte.digest();
       try{
           hh = dao.findHabilitantByVal(sHblt);
           if(hh==null){
                System.out.println("new habilitant="+a);
                hblt = new Habilitant(a, sHblt, type);
                dao.createHabilitant(hblt);
           }else{
               a = hh.getPkhblt();
                System.out.println("old habilitant="+a);
           }
           if(appli.equals(appKch)){
               a1 = pkappKch;
           }else{
               a1 = dao.getPkAppli(hashToString(hash).substring(0,10));
               appKch = appli;
               pkappKch = a1;
           }
           if(!dao.existHblt4Objs(a, a1)){
               dao.createObjsHblt(aleat(), a1, a);
           }
        }catch(TechDecisionErreurs err){
            System.err.println("Erreur sur createObjsHblt pour=" + sHblt  +" " + err.toString());
       }catch(Exception err){
           System.err.println("Erreur sur ajout habilitant pour=" + sHblt  +" " + err.toString());
       }
    }
    /**
     * Ajoute un compte sa référence à une application, un libellé, un attribut de domaine lotus
     * le cas échéant
     * @param compte
     * @param cpteNom
     * @param appli
     * @param lotus
     */
    public void ajouteCpteAppli(String compte, String cpteNom, String appli, String lotus){
        int idC = aleat();
        Integer a1 = 0;
        empreinte.reset();
        empreinte.update(appli.getBytes());
        byte[] hash = empreinte.digest();
        try{
           cpte = new Compte(idC);
           dao.createCompte(cpte);
           if(appli.equals(appKch)){
               a1 = pkappKch;
           }else{
               a1 = dao.getPkAppli(hashToString(hash).substring(0,10));
               appKch = appli;
               pkappKch = a1;
           }
           dao.lierCpteObjs(aleat(), idC, a1);
           ajouteAttr(1, "NOMCPTE", cpteNom, ""+idC);
           if(lotus!=null){
               ajouteAttr(1, "LOTUS", lotus, ""+idC);
           }
        }catch(Exception err){
               System.err.println("Erreur ajout compte + lien appli" + err.toString());
        }
    }
    /**
     * ajoute un attribut UIDCPTE au compte trouvé par NOMCPTE
     * @param compte UIDCPTE
     * @param cpteNom NOMCPTE
     */
    public void corrigeCpte(String compte, String cpteNom){
        int c = 0;
        try{
           c = dao.getIdCpte(cpteNom);
           if(c!=0){
              ajouteAttr(1, "UIDCPTE", compte, ""+c);
           }
        }catch(Exception err){
               System.err.println("Erreur ajout compte + lien appli" + err.toString());
        }
    }
    /**
     * fonction de mise à jour des comptes (ajoute le cas échéant)
     * @param compte
     * @param cpteNom
     * @param appli
     * @param lotus
     */
    public void mergeCpteAppli(String compte, String cpteNom, String appli, String lotus){
        Integer c = null;
        Integer a1 = null;
        if(compte==null||appli==null){
            System.err.println("UID du compte ou nom appli vide !!!");
            return;
        }
        try{
           //recherche sur uid du compte attribut UIDCPTE
           try{
               c = dao.getIdCpteUid(compte);
               if(c!=null){
                  return;
               }
           }catch(Exception err){
               System.err.println("Erreur merge compte + lien appli sur recherche compte="+compte+ " " + err.toString());
           }
           System.out.println("ajouter compte si trouve appli uid="+compte);
           int idC = aleat();
           empreinte.reset();
           empreinte.update(appli.getBytes());
           byte[] hash = empreinte.digest();
           if(appli.equals(appKch)){
               a1 = pkappKch;
           }else{
               a1 = dao.getPkAppli(hashToString(hash).substring(0,10));
               if(a1==null){
                   System.out.println("pas trouvé hash="+hashToString(hash).substring(0,10)+" pour appli="+appli);
                   return;
               }
               appKch = appli;
               pkappKch = a1;
           }
           cpte = new Compte(idC);
           try {
               dao.createCompte(cpte);
           //si aleat pas bon on essaye un nouveau tirage
           } catch (TechDecisionErreurs ex) {
               if(ex.getCode()==1){
                    idC += 1;
                    cpte = new Compte(idC);
                    try{
                        dao.createCompte(cpte);
                    }catch(TechDecisionErreurs err){
                        System.err.println("Erreur merge compte + lien appli deuxième chance compte="+compte+" appli="+appli+ " " + err.toString());
                        return;
                    }
               }else{
                   System.err.println("Erreur merge compte + lien appli sauver compte="+compte+" appli="+appli+ " " + ex.toString());
                   return;
               }
           }
           if(cpteNom!=null){
               ajouteAttr(1, "NOMCPTE", cpteNom, ""+idC);
           }
           ajouteAttr(1, "UIDCPTE", compte, ""+idC);
           if(lotus!=null){
               ajouteAttr(1, "LOTUS", lotus, ""+idC);
           }
           dao.lierCpteObjs(aleat(), idC, a1);
        }catch(Exception err){
               System.err.println("Erreur merge compte + lien appli compte="+compte+" appli="+appli+ " " + err.toString());
        }
    }
    /**
     *
     * Fonction pour ajouter un compte
     * @param nom le nom de l'identité en relation avec le compte
     * @param userId le code du compte qui vient de l'externe on ne l'utilise pas vraiment
     * @param profil le nom d'un regroupement d'habilitants
     * @param habilitants une liste d'habilitants joint par +
     */
    public void ajouteCompte(String nom, int userId, String profil, String habilitants){
        String[][] stHblt = new String[50][2];
        int i = 0;
        int iRet = 0;
        int iD = 0;
        String sErr = "";
        String sHblt = "";
        StringTokenizer st = new StringTokenizer(habilitants,"+");
        while ( st.hasMoreTokens() ) {
            sHblt = st.nextToken();
            if(i<50){
                if(!sHblt.equals(null)){
                    stHblt[i][0] = sHblt.toString();
                    try {
                        iRet = dao.getIdHblt(sHblt.toString());
                        if(iRet==0){
                            iRet = aleat();
                            hblt = new Habilitant(iRet,sHblt.toString());
                            hblt.setType(typeH(sHblt.toString()));
                            dao.createHabilitant(hblt);
                        }
                        stHblt[i][1] = ""+iRet;
                    }catch(TechDecisionErreurs err){
                        sErr = "Erreur interne sur ajout habilitant: " + sHblt + err.toString();
                        //logg.log(Level.ERROR, sErr);
                        System.err.println(sErr);
                        break;
                    } catch (Exception ex) {
                        sErr = "Erreur générale sur ajout habilitant: " + sHblt+ " " + ex.toString();
                        //logg.log(Level.ERROR, sErr);
                        System.err.println(sErr);
                        break;
                    }
                }
                i++;
            } else {
                sErr = "Trop d'habilitants > 50";
                //logg.log(Level.ERROR, sErr + " habilitant oublié:" + sHblt);
                System.err.println(sErr + " habilitant oublié:" + sHblt);
            }
        }
        //ajoute le compte
        try {
            iD = aleat();
            cpte = new Compte(iD, userId);
            dao.createCompte(cpte);
            dao.ajouteAttr(1, "profil", profil, iD);
            //dao.ajouteAttr(1, "habilitants", habilitants, iD);
            //logg.log(Level.INFO, "ajouté compte " + userId);
            System.out.println("ajouté compte " + userId);
        } catch (Exception ex) {
            sErr = ex.toString();
            //logg.log(Level.ERROR, sErr);
            System.err.println(sErr);
            return;
        }
        //trouve identité et fait la liaison
        try {
            int iDi = dao.getIdIdnt(nom);
            cpte = new Compte(iD, userId);
            dao.lierIdntCpte(iDi, iD);
            //logg.log(Level.INFO, "lié compte / identite (" + iD+ "/" + iDi+")");
            System.out.println("lié compte / identite (" + iD+ "/" + iDi+")");
        } catch (Exception ex) {
            sErr = ex.toString();
            //logg.log(Level.ERROR, sErr);
            System.err.println(sErr);
            return;
        }
        //fait liaison entre habilitant et compte
        try{
            for(int j=0;!(stHblt[j][1]==null);j++){
                if(!stHblt[j][1].equals("")){
                    dao.lierCpteHblt(iD, new Integer(stHblt[j][1]));
                }else{
                    //logg.log(Level.ERROR, " tableau habilitant1 vide j="+j);
                    System.err.println(" tableau habilitant1 vide j="+j);
                }
            }
        }catch(Exception err){
            //logg.log(Level.ERROR, " Id cpte vers Hblt " + err.toString());
            System.err.println(" Id cpte vers Hblt " + err.toString());
        }
        //logg.log(Level.INFO, "lié compte / habilitants");
        System.out.println("lié compte / habilitants");
    }
    /**
     * associe un compte et une identité
     * @param idnt
     * @param cpte
     */
    public void lierIdntCpte(String idnt, String cpte){
        try{
            Integer c = null;
            Integer iDi = null;
            if(idnt.equals(idntKch)){
                iDi = pkidntKch;
            }else{
                iDi = dao.getIdIdnt(idnt);
                pkidntKch = iDi;
                idntKch = idnt;
            }
            c = dao.getIdCpteUid(cpte);
            if(c==null) return;
            if(iDi==null) return;
            if(c==0||iDi==-1){
                System.err.println("Erreur liaison idnt=" + iDi +" cpte="+c+" user="+idnt+" uid="+cpte);
            }else{
                try{
                    dao.lierIdntCpte(iDi, c);
                    System.out.println("lié compte / identité user="+idnt+" uid="+cpte);
                }catch(TechDecisionErreurs exx){
                    System.err.println("Erreur liaison sauver user="+idnt+" uid="+cpte+" "+exx.toString());
                }
            }
        }catch(Exception err){
            System.err.println("Erreur liaison idnt / cpte " + err.toString());
        }
    }
    /**
     * Dédoblonne les liaisons entre compte et identité un seul identifiant de compte
     * pour une seule identité
     * @param idnt identifiant unique d'identité
     * @param cpte identifiant unique de compte pour cette identité
     */
    public void corrigeIdntCpte(String idnt, String cpte){
        try{
            Integer c = null;
            Integer iDi = null;
            try{
                c = dao.getIdCpteUid(cpte);
                iDi = dao.getIdIdnt(idnt);
            }catch(Exception err){
                System.out.println("Info corrigeIdntCpte pas trouvé user="+idnt+" ou uid="+cpte);
                return;
            }
            if(c==0||iDi==-1){
                System.out.println("Info pas de liaison pas trouvé idnt=" + iDi +" cpte="+c+" user="+idnt+" uid="+cpte);
            }else{
                dao.corrigeCpteIdnt(iDi, c);
            }
        }catch(Exception err){
            System.err.println("Erreur correction liaison idnt / cpte " + err.toString());
        }
    }
    /**
     * ajoute la liaison entre un compte et un habilitant
     * @param c
     * @param h
     */
    public void ajouteCpteHblt(String c, String h){
        dao.ajouteCpteHblt(c, h);
    }
     /**
     *
     * Retourne un nombre aléatoire pas plus grand qu'un integer
     * @return Aleat un nombre aléatoire
     */
    private int aleat() {
//    Randomize
        int iAlea = 0;
        int iB = 0;
        String sT1 = "";
        String sT = "";
        Random generator = new Random();
        try{
            iB = randomInteger(1,10,generator);
            boolean bPaire = iB % 2 == 0;
            if(bPaire){
                sT1 = "" + randomInteger(10000,100000,new Random());
            } else {
                sT1 = "" + randomInteger(100,10000,new Random());
            }
            iB = randomInteger(1,10,new Random());
            bPaire = iB % 2 == 0;
            if(bPaire){
                sT = "" + randomInteger(1000,10000,new Random());
            } else {
                sT = "" + randomInteger(100,10000,new Random());
            }
        }catch(Exception err){
            //logg.log(Level.ERROR, " Aleat marche pas "+err.toString());
            System.err.println(" Aleat marche pas "+err.toString());
            return -1;
        }
        try {
            iAlea = Integer.parseInt(sT1.concat(sT));
        } catch(Exception ex) {
            sT = "" + randomInteger(100,10000,new Random());
            iAlea = Integer.parseInt(sT1.concat(sT));
        }
        return iAlea;
    }
      /**
     *
     * Retourne un chiffre aléatoire entre deux bornes
     * @param aStart borne basse
     * @param aEnd borne haute
     * @param aRandom un composant de génération aléatoire
     * @return randomInteger un chiffre aléatoire
     */
    private int randomInteger(int aStart, int aEnd, Random aRandom){
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
   private int typeH(String sNom){
        int iVal = 0;
        if(sNom.length()>=4) {
            if (sNom.substring(0, 4).equals("CICS")){ iVal=19;}
        }
        if(sNom.substring(0, 2).equals("P-")) {iVal=18;}
        if(iVal==0) {iVal=17;}
        return iVal;
    }
   public List<Identite> getAllIdentite(){
       List<Identite> ret = null;
       try{
            ret = dao.getAllIdentite();
       }catch(Exception err){
           //logg.log(Level.ERROR, " liste des identites " + err.toString());
           System.err.println(" liste des identites " + err.toString());
       }finally{
           return ret;
       }
   }
       private String hashToString(byte[] hash) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < hash.length; i++) {
            int v = hash[i] & 0xFF;
            if(v < 16) {
                sb.append("0");
            }
            sb.append(Integer.toString(v, 16));
        }
        return sb.toString();
    }

}
