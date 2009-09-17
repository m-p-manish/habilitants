/*
Copyright Stéphane Georges Popoff, (juin - juillet 2009)

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

import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import theCube.roleMining.Comptes;
import theCube.roleMining.Comptes.CpteElec;
import theCube.roleMining.DemiMatrice;
import theCube.roleMining.IItemElec;
import javax.faces.context.FacesContext;
import javax.persistence.Query;
import theCube.entities.Compte;
import theCube.entities.CpteAttrs;
import theCube.jsf.util.JsfUtil;
import theCube.roleMining.DemiMatrice.Cpt1;
import theCube.roleMining.DemiMatrice.Cpt2;
import theCube.roleMining.Habilitants;
import theCube.roleMining.Identites;

/**
 * Bean JSF dédié à la comparaison suivant la méthode Electre
 * @author spopoff@rocketmail.com
 * @version 0.4
 */

public class ElectreBean {
    @PersistenceUnit(unitName = "techDecisionCubePU")
    private EntityManagerFactory emf = null;
    private String nomClassComparator = null;
    private static Boolean bErr = false;
    private static String sErr = "";
    private Comptes jpaCptes = null;
    private Identites objUser = null;
    private String fichier = "/home/spopoff/electreCpte.txt";
    private String nomClass = "info.lespopoff.ClassementPerso";
    private Habilitants jpaHblts = null;
    private SauverWillie beansHelp = null;
    private String rapportCpte = "";

    /** Creates a new instance of ElectreBean */
    public ElectreBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaCptes = (Comptes) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "comptesBean");
        objUser = (Identites) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "identitesBean");
        jpaHblts = (Habilitants) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "habilitantsBean");
        beansHelp = (SauverWillie) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "helpBean");
        jpaCptes.setObjHblts(jpaHblts);
        System.out.println("Initialisation ElectreBean");
        //facesContext.
    }
    public String getRapportCpte() {
        return rapportCpte;
    }

    public void setRapportCpte(String rapportCpte) {
        this.rapportCpte = rapportCpte;
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }

    public String getNomClass() {
        return nomClass;
    }

    public void setNomClass(String nomClass) {
        this.nomClass = nomClass;
    }
    /**
     * Lancement d'une comparaison sur les comptes
     * @param sFich
     * @param nomClass
     * @return renvoit vers la page d'attribut
     */
    public String electreCpte(){
       EntityManager em = getEntityManager();
       CSurClassor objElectre = null; //classe de méthodes haut niveau
       HashElectre objDom = new HashElectre(); //classe de données et méthodes bas niveau
       List cpts1 = null;
       CpteElec c1 = null;
       CpteElec c2 = null;
       int iRet = 0;
       String sErrr=null;
       Boolean bErrr=null;
       //instanciation de la classe de comparaison externe
       Object compPerso = null;
       try
       {
            compPerso = Class.forName (nomClass).newInstance();
       }catch (ClassNotFoundException e){
            System.out.println("## Class not found");
       }catch (InstantiationException e){
            System.out.println("## Class erreur instantiation#");
       }catch (IllegalAccessException e){
            System.out.println("## Erreur Sécurité #");
       }

       //classe qui gère la comparaison d'élement deux à deux (pas de différence entre
       // a comp b et b comp a et sans a comp a)
       DemiMatrice objMat = null;
       //on passe le nom de la classe de comparaison en plus de l'instance de l'objet
       nomClassComparator = nomClass;
       //on initialise la classe de comparaison et son container de données
       try {
            cpts1 = (LinkedList<IItemElec>)jpaCptes.getComptesElec(true);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            System.err.println( " 0 " + sErr);
            JsfUtil.addErrorMessage(sErr);
            return "";
       }
       try {
            objMat = new DemiMatrice((LinkedList<IItemElec>)cpts1, fichier, true);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            System.err.println( " 2 " + sErr);
            JsfUtil.addErrorMessage(sErr);
            return "";
       }
       try {
            objDom.init(objMat, fichier);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            System.err.println(" 4 " + sErr);
            JsfUtil.addErrorMessage(sErr);
            return "";
       }
       try {
            objElectre = new CSurClassor(nomClass, compPerso, objDom);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            System.err.println(" 3 " + sErr);
            JsfUtil.addErrorMessage(sErr);
            return "";
       }
       //on commence la réflexion sur la classe de comparaison
       Class cl = null;
       try {
            cl = Class.forName(nomClassComparator);
       } catch (ClassNotFoundException ex) {
            System.err.println(" A " + ex.toString());
            JsfUtil.addErrorMessage(" A " + ex.toString());
            return "";
       }
       //trois méthodes dont 2 qui ne changent pas siErreur et erreurTxt
       Method msiR = null;
       Method mErr = null;
       Method mthd = null;
       try {
            msiR = cl.getMethod("siErreur");
       } catch (NoSuchMethodException ex) {
            System.err.println( " A01 " + ex.toString());
            JsfUtil.addErrorMessage(" A01 " + ex.toString());
            return "";
       } catch (SecurityException ex) {
            System.err.println( " A02 " + ex.toString());
            JsfUtil.addErrorMessage(" A02 " + ex.toString());
            return "";
       }
       //on vérifie si il y a des erreurs dans la classe de comparaison
       try{
            bErrr = (Boolean)msiR.invoke(compPerso);
       }catch(Exception errr){
            System.err.println(" A03 " + errr.toString());
            JsfUtil.addErrorMessage(" A03 " + errr.toString());
            return "";
       }
        try {
            mErr = cl.getMethod("erreurTxt");
       } catch (NoSuchMethodException ex) {
            System.err.println(" A001 " + ex.toString());
            JsfUtil.addErrorMessage(" A001 " + ex.toString());
            return "";
       } catch (SecurityException ex) {
            System.err.println(" A002 " + ex.toString());
            JsfUtil.addErrorMessage(" A002 " + ex.toString());
            return "";
       }
       //si des erreurs on traite
       if(bErrr){
            try{
                sErrr = (String)mErr.invoke(compPerso);
            }catch(Exception errr){
                System.err.println(" A003 " + errr.toString());
                JsfUtil.addErrorMessage(" A003 " + errr.toString());
                return "";
            }
            System.err.println(" inside avant " + sErrr);
            JsfUtil.addErrorMessage(" inside avant " + sErrr);
            return "";
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
            System.err.println(" A1 " + ex.toString());
            JsfUtil.addErrorMessage(" A1 " + ex.toString());
            return "";
       } catch (SecurityException ex) {
            System.err.println(" A2 " + ex.toString());
            JsfUtil.addErrorMessage(" A2 " + ex.toString());
            return "";
       }
       try{
            mthd.invoke(compPerso, new Object[]{(Object)jpaCptes, (Object)objUser, (Object)objDom, (Object)this});
       }catch(Exception err){
            System.err.println(nomClassComparator + " invocation init " + err.toString()+" "+err.getCause());
            try {
                mthd = cl.getMethod("erreurTxt");
            } catch (NoSuchMethodException ex) {
                System.err.println( nomClassComparator + " méthode erreurTxt existe pas ");
                JsfUtil.addErrorMessage(nomClassComparator + " méthode erreurTxt existe pas ");
                return "";
            } catch (SecurityException ex) {
                System.err.println( nomClassComparator + " sécurité erreur sur erreurTxt");
                JsfUtil.addErrorMessage(nomClassComparator + " sécurité erreur sur erreurTxt");
                return "";
            }
            try{
                sErrr = (String)mthd.invoke(compPerso);
            }catch(Exception errr){
                System.err.println( nomClassComparator + " Erreur " +errr.toString());
                JsfUtil.addErrorMessage(nomClassComparator + " Erreur " +errr.toString());
                return "";
            }
            System.err.println( nomClassComparator + " détail erreur précédente " +sErrr);
            JsfUtil.addErrorMessage(nomClassComparator + " détail erreur précédente " +sErrr);
            return "";
       }
       try{
            bErrr = (Boolean)msiR.invoke(compPerso);
       }catch(Exception errr){
            System.err.println(" A0003 " + errr.toString());
            JsfUtil.addErrorMessage(" A0003 " + errr.toString());
            return "";
       }
       //si des erreurs on traite
       if(bErrr){
            try{
                sErrr = (String)mErr.invoke(compPerso);
            }catch(Exception errr){
                System.err.println(" A0004 " + errr.toString());
                JsfUtil.addErrorMessage(" A0004 " + errr.toString());
                return "";
            }
            System.err.println(" inside apres " + sErrr);
            JsfUtil.addErrorMessage(" inside apres " + sErrr);
            return "";
       }
       //on commence vraiment le boulot !
       par = new Class[2];
       par[0] = Object.class;
       par[1] = Object.class;
       mthd = null;
       try {
            mthd = cl.getMethod("comparons", par);
       } catch (NoSuchMethodException ex) {
            System.err.println(" B " + ex.toString());
            JsfUtil.addErrorMessage(" B " + ex.toString());
            return "";
       } catch (SecurityException ex) {
            System.err.println(" C " + ex.toString());
            JsfUtil.addErrorMessage(" C " + ex.toString());
            return "";
       }
       //en avant la musique un premier itérateur sur les comptes de rang1
       for(Cpt1 cc1: (List<Cpt1>)objMat.getListeC1()){
           //un deuxième sur les comptes de rang 2
           for(Cpt2 cc2 : (List<Cpt2>)cc1.getListeC2()){
               c1 = (CpteElec)jpaCptes.trouveCompteElec(cc1.getId());
               c2 = (CpteElec)jpaCptes.trouveCompteElec(cc2.getId());
               System.out.println("comparons cpt1:" + c1.getId()+" et cpt2:"+ c2.getId());
               try {
                    iRet = (Integer) mthd.invoke(compPerso, new Object[]{(Object)c1, (Object)c2});
               } catch (Exception err) {
                    System.err.println(" D " + err.toString());
                    JsfUtil.addErrorMessage(" D " + err.toString());
                    return "";
               }
               if(iRet==-2){
                   bErr = true;
                   try {
                        mthd = cl.getMethod("erreurTxt");
                   } catch (NoSuchMethodException ex) {
                        System.err.println(" B " + ex.toString());
                        JsfUtil.addErrorMessage(" B " + ex.toString());
                        return "";
                   } catch (SecurityException ex) {
                        System.err.println( " C " + ex.toString());
                        JsfUtil.addErrorMessage(" C " + ex.toString());
                        return "";
                   }
                   try {
                        sErr = (String) mthd.invoke(compPerso);
                   } catch (Exception err) {
                        System.err.println(" D " + err.toString());
                        JsfUtil.addErrorMessage(" D " + err.toString());
                        return "";
                   }
                   System.err.println( nomClassComparator + " E erreur dans comparons "+sErr);
                    JsfUtil.addErrorMessage(nomClassComparator + " E erreur dans comparons "+sErr);
                    return "";
               }
           }
           System.out.println( "Fin de comparaison pour cpt1:" + c1.getId());
           try{
                objElectre.electreFinElmProd(c1.getId());
           }catch(Exception err){
               System.err.println( " 4 " + err.toString());
                JsfUtil.addErrorMessage(" 4 " + err.toString());
                return "";
           }
           if(objElectre.siErreur()){
               System.err.println(" 5 " + objElectre.erreurTxt());
                JsfUtil.addErrorMessage(" 5 " +  objElectre.erreurTxt());
                return "";
           }
           objElectre.clearErreur();
       }
       System.out.println("Fin de toutes les comparaisons *****************");
       try{
           objElectre.finDesBoucles();
       }catch(Exception err){
           System.err.println(" 6 " + err.toString());
            JsfUtil.addErrorMessage(" 6 " + err.toString());
            return "";
       }
       if(objElectre.siErreur()){
           System.err.println(" 7 " + objElectre.erreurTxt());
            JsfUtil.addErrorMessage(" 7 " + objElectre.erreurTxt());
            return "";
       }
       //procèdure de mise à jour des comptes ou identités
       //on commence par trouver le type de la comparaison
       int iType = 0;
       try {
            mthd = cl.getMethod("getType");
       } catch (NoSuchMethodException ex) {
            System.err.println(" 8 " + ex.toString());
            JsfUtil.addErrorMessage(" 8 " + ex.toString());
            return "";
       } catch (SecurityException ex) {
            System.err.println(" 9 " + ex.toString());
            JsfUtil.addErrorMessage(" 9 " + ex.toString());
            return "";
       }
       try {
            iType = (Integer) mthd.invoke(compPerso);
       } catch (Exception err) {
            System.err.println(" 10 " + err.toString());
            JsfUtil.addErrorMessage(" 10 " + err.toString());
            return "";
       }
       System.out.println("Persistence *****************");
       for(Cpt1 cc1: (List<Cpt1>)objMat.getListeC1()){
           switch(iType){
               case 1:
                   //Compte ccc = em.getReference(Compte.class, cc1.getId());
                   try{
                       //beansHelp.setClasse(objElectre.getClassement(cc1.getId()),cc1.getId());
                       beansHelp.ajouteAttrCpte("CLASST", objElectre.getClassement(cc1.getId()), cc1.getId());
                       break;
                  }catch(Exception err){
                       System.err.println( " erreur dans persistence "+err.toString());
                       objElectre.cloze();
                       em.close();
                        JsfUtil.addErrorMessage(" erreur dans persistence ");
                        return "";
                   }
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
            System.err.println( " 8 " + sErr);
            JsfUtil.addErrorMessage(" 8 " + sErr);
            return "";
       }
       rapportCpte = objDom.getRapport();
       if(objElectre.siErreur()) {
            bErr = true;
            sErr = objElectre.erreurTxt();
            System.err.println( " 9 " + sErr);
       }
       objElectre.clearErreur();
       //em.flush();
       em.close();
       return "cpteattrs_list";

    }
    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }
    public List<CpteAttrs> findCpteAttrsEntities() {
        return findCpteAttrsEntities(true, -1, -1);
    }
    public List<CpteAttrs> findCpteAttrsEntities(String attribut) {
        return findCpteAttrsEntities2(true, -1, -1, attribut);
    }

    public List<CpteAttrs> findCpteAttrsEntities(int maxResults, int firstResult) {
        return findCpteAttrsEntities(false, maxResults, firstResult);
    }

    public List<CpteAttrs> findCpteAttrsEntities(int maxResults, int firstResult, String attribut) {
        return findCpteAttrsEntities2(false, maxResults, firstResult, attribut);
    }

    private List<CpteAttrs> findCpteAttrsEntities2(boolean all, int maxResults, int firstResult, String attribut) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CpteAttrs as o where o.attr=:attr");
            q.setParameter("attr", attribut);
            System.out.println("Info retrouve CpteAttrs pour attribut="+attribut);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }
    private List<CpteAttrs> findCpteAttrsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select object(o) from CpteAttrs as o");
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public CpteAttrs findCpteAttrs(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(CpteAttrs.class, id);
        } finally {
            em.close();
        }
    }

    public int getCpteAttrsCount() {
        EntityManager em = getEntityManager();
        try {
            return ((Long) em.createQuery("select count(o) from CpteAttrs as o").getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    public int getCpteAttrsCount(String attribut) {
        EntityManager em = getEntityManager();
        try {
            Query q = em.createQuery("select count(o) from CpteAttrs as o where o.attr=:attr");
            q.setParameter("attr", attribut);
            System.out.println("Info compte CpteAttrs pour attribut="+attribut);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
