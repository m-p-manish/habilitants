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
package theCube.jsf;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.LinkedList;
import java.util.List;
import javax.faces.context.FacesContext;
import theCube.roleMining.Comptes;
import theCube.roleMining.Identites;
import theCube.roleMining.Habilitants;
import theCube.jpa.ObjsAttrsJ3;
import theCube.jpa.IdntAttrs3;
import theCube.jpa.Role3;
import theCube.roleMining.DemiMatrice;
import theCube.roleMining.IItemElec;
import theCube.roleMining.ObjSecus;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import theCube.entities.Habilitant;
import theCube.entities.Identite;
import theCube.jpa.ObjsCpteJpaController;
import theCube.jsf.util.JsfUtil;
import theCube.newRoleMiner.RoleBuilder;
import theCube.newRoleMiner.RoleBuilder.Idnt1;
import theCube.newRoleMiner.RoleBuilder.RolLink;

/**
 * Bean JSF dédié à la recherche et mise en forme des rôles applicatifs
 * @author spopoff@rocketmail.com
 * @version 0.5
 */
public class roleMinerBean {

    private Identites jpaIdnt3 = null;
    private Comptes jpaCptes = null;
    private Habilitants jpaHblts = null;
    private ObjSecus jpaRoles = null;
    private IdntAttrs3 jpaIdntAttrs = null;
    private Role3 jpaTheRole = null;
    private Boolean bErr = false;
    private String sErr = "";
    private ObjsAttrsJ3 jpaObjsAttrs = null;
    private final String dec = "--";
    private String fichier = "/home/spopoff/roleApp.txt";
    private String fichier2 = "/home/spopoff/newRole.txt";
    private ObjsCpteJpaController jpaObjsCpte = null;
    private UniqueArrayList unic = null;
    private int seuilHbilitant = 1;
    private int seuilHbilitant2 = 5;
    private int seuilIdentite = 10;
    private String rapportRole = "";
    private Boolean stopRoles = false;
    private RoleBuilder objRol = null;


   public roleMinerBean() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        jpaIdnt3 = (Identites) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "identitesBean");
        //IdntAttrs3 jpaIdntAttrs = (IdntAttrs3) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "idntAttrs3Jpa");
        jpaCptes = (Comptes) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "comptesBean");
        jpaHblts = (Habilitants) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "habilitantsBean");
        jpaRoles = (ObjSecus) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objSecusBean");
        jpaObjsAttrs = (ObjsAttrsJ3) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsAttrsBean");
        jpaObjsCpte = (ObjsCpteJpaController) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objsCpteBean");
        jpaRoles.setCptes(jpaCptes);
        jpaRoles.setObjsAttrs(jpaObjsAttrs);
        jpaRoles.setObjsCpte(jpaObjsCpte);
        jpaRoles.setJpaHblts(jpaHblts);
        jpaTheRole = (Role3) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "objRole");
        objRol = (RoleBuilder) facesContext.getApplication().getELResolver().getValue(facesContext.getELContext(), null, "roleBuilder");
       System.out.println("Fin initialisation roleMinerBean !");
    }
    /**
     * procèdure qui calcul des rôles sur la base des identités et des habilitants
     * présent dans la base encours et renvoit vers la page des rôles
     */
    public String newRoles(){
       List<Identite> listI = null;
       String ret = "";
       stopRoles = true;
       System.out.println("***** Commence roles ! *****");
       try{
            listI = jpaIdnt3.findIdentiteEntities();
            if(listI==null){
                System.err.println("Erreur newRoles init RoleBuilder identités vide ");
            }else{
                System.out.println("Info newRoles nb Idnt="+listI.size()+" fichier="+fichier2);
            }
            objRol.init(listI, fichier2);
       }catch(Exception err){
           System.err.println("Erreur newRoles init RoleBuilder " + err.toString());
           stopRoles = false;
           return "";
       }
       System.out.println("Info newRoles set seuil="+seuilHbilitant2);
       objRol.setSeuilHblt(seuilHbilitant2);
       objRol.setSeuilIdnt(seuilIdentite);
       try{
           for(Iterator iti = objRol.getListeI1().iterator(); iti.hasNext();){
               Idnt1 ii = (Idnt1) iti.next();
               //System.out.println("Info newRoles Idnt="+ii.getId()+" size="+objRol.getListeI1().size());
               for(Iterator ith =  jpaIdnt3.relatedHblt(ii.getId()).iterator(); ith.hasNext();){
                   Habilitant h = (Habilitant) ith.next();
                   //System.out.println("Info newRoles hblt="+h.getPkhblt()+" idnt="+ii.getId()+" hbltVal="+h.getVal());
                   objRol.addHbltIdnt(ii.getId(), h.getPkhblt(), h.getVal(), objRol.hacherMenu(h.getVal()));
               }
           }
       }catch(Exception err){
           System.err.println("Erreur newRoles init habilitant " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles init habilitant " );
           stopRoles = false;
           return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       try{
            objRol.premierTour();
       }catch(Exception err){
           System.err.println("Erreur newRoles première passe " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles première passe ");
           stopRoles = false;
           return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       if(objRol.getRoles().size()==0){
           System.err.println("Info newRoles pas trouvé de rôle");
           JsfUtil.addErrorMessage("Info newRoles pas trouvé de rôle !");
           stopRoles = false;
           return "";
       }
       try{
            objRol.supprimerPetitRole();
       }catch(Exception err){
           System.err.println("Erreur newRoles supprimer role " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles supprimer rôles ");
           stopRoles = false;
           return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       if(objRol.getRoles().size()==0){
           System.err.println("Info newRoles pas de rôle avec suffisamment d'identité");
           JsfUtil.addErrorMessage("Info newRoles pas de rôle avec suffisamment d'identité !");
           stopRoles = false;
           return "";
       }
       try{
            objRol.trierOrdre1();
       }catch(Exception err){
           System.err.println("Erreur newRoles tri 1 " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles tri 1 ");
           stopRoles = false;
           return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       try{
            objRol.trierOrdre2();
       }catch(Exception err){
           System.err.println("Erreur newRoles tri 2 " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles tri 2 ");
           stopRoles = false;
           return "";
       }
       try{
            objRol.hierarchisons(false);
       }catch(Exception err){
           System.err.println("Erreur newRoles hiérarchie 1 " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles hiérarchie 1 ");
           stopRoles = false;
           return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       try{
            objRol.trierOrdre3();
       }catch(Exception err){
           System.err.println("Erreur newRoles tri 3 " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles tri 3 ");
           stopRoles = false;
           return "";
       }
       for(RolLink rl: objRol.getRolLink()){
           System.out.println("Info RoleBuider hiérarchie parent="+rl.getParent()+" enfant="+rl.getEnfant());
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       try{
            objRol.simplifionsHiera();
       }catch(Exception err){
           System.err.println("Erreur newRoles simplifionsHiera " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles simplifionsHiera ");
           stopRoles = false;
           return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       try{
            ret = objRol.hierarchisons(true);
            rapportRole = ret;
       }catch(Exception err){
           System.err.println("Erreur newRoles hiérarchie 2 " + err.toString());
           JsfUtil.addErrorMessage("Erreur newRoles hiérarchie 2 ");
           stopRoles = false;
           return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       rapportRole+=objRol.rapport();
       System.out.println("***** Info newRoles enregistrement dans fichier de synthèse "+fichier2);
       try{
           cloze(fichier2, false, ret);
        }catch(Exception err){
            System.err.println("Erreur newRoles sauver fichier 1 " + err.toString());
            JsfUtil.addErrorMessage("Erreur newRoles sauver fichier 1 ");
            stopRoles = false;
            return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       System.out.println("***** Info newRoles persistence des rôles nb="+objRol.getRoles().size());
       try{
           jpaTheRole.persisteNewRole(objRol);
       }catch(Exception err){
            System.err.println("Erreur newRoles persister Rôles " + err.toString());
            JsfUtil.addErrorMessage("Erreur newRoles persister Rôles ");
            stopRoles = false;
            return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       System.out.println("***** Info newRoles persistence de la hiérarchie rôles nb="+objRol.getRolLink().size());
       try{
           jpaTheRole.persisteNewRoleHiera(objRol);
       }catch(Exception err){
            System.err.println("Erreur newRoles persister Hiérarchie " + err.toString());
            JsfUtil.addErrorMessage("Erreur newRoles persister Hiérarchie ");
            stopRoles = false;
            return "";
       }
       if(!stopRoles){
           JsfUtil.addErrorMessage("Info newRoles stopper !" );
           return "";
       }
       try{
           jpaTheRole.cloze(fichier2, true);
       }catch(Exception err){
            System.err.println("Erreur newRoles sauver fichier 2 " + err.toString());
            JsfUtil.addErrorMessage("Erreur newRoles sauver fichier 2 ");
            stopRoles = false;
            return "";
       }
       stopRoles = false;
       return "role_list";
    }
    public void stopperRoles(){
        stopRoles = false;
        if(objRol!=null){
            objRol.setStop(true);
        }
    }
    public Boolean getStopRoles() {
        return stopRoles;
    }

    public void setStopRoles(Boolean stopRoles) {
        this.stopRoles = stopRoles;
    }

    public int getSeuilIdentite() {
        return seuilIdentite;
    }

    public void setSeuilIdentite(int seuilIdentite) {
        this.seuilIdentite = seuilIdentite;
    }

    public String getRapportRole() {
        return rapportRole;
    }
    public void setRapportRole(String val) {
        rapportRole = val;
    }

    /**
    * Procèdure qui calcul des rôles applicatifs sur la base des comptes
    * de la collection en cours
    */
   public String rolesApplicatifs(){
       List cpts1 = null;
       DemiMatrice objMat = null;

       System.out.println("***** Commence Profils Applicatifs ! *****");
       try {
            cpts1 = (LinkedList<IItemElec>)jpaCptes.getComptesElec(false);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            System.err.println(roleMinerBean.class.getName()+ " 0 " + sErr);
            JsfUtil.addErrorMessage(sErr);
            return "";
       }
       System.out.println("***** Fabrique la DemiMatrice !");
       try {
            objMat = new DemiMatrice((LinkedList<IItemElec>)cpts1, fichier, false);
       } catch (Exception ex) {
            bErr = true;
            sErr = ex.toString() ;
            System.err.println(roleMinerBean.class.getName()+ " 1 " + sErr);
            JsfUtil.addErrorMessage(sErr);
            return "";
       }
       // une boucle pour mettre en forme la hiérarchie
       System.out.println("***** une boucle pour mettre en forme la hiérarchie");
       String hierar = jpaRoles.simplifyHiera(fichier, objMat, seuilHbilitant);
       //on sauve les données pour contrôle
       System.out.println("***** enregistrement dans fichier de synthèse "+fichier);
       try{
           cloze(fichier, false, hierar);
           //jpaRoles.cloze(fichier, false, prem);
       }catch(Exception err){
            bErr = true;
            sErr = err.toString() ;
            System.err.println(roleMinerBean.class.getName()+ " 7 " + sErr);
            JsfUtil.addErrorMessage(sErr);
            return "";
       }
       try{
           jpaRoles.cloze(fichier, true);
       }catch(Exception err){
            bErr = true;
            sErr = err.toString() ;
            System.err.println(roleMinerBean.class.getName()+ " 8 " + sErr);
            JsfUtil.addErrorMessage(sErr);
            return "";
       }
       return "objsecu_list";
    }
    /**
     * Procèdure de sérialisation des rôles applicatifs
     * @param sFile le chemin complet vers un fichier
     * @param bAppend si true on ajoute au fichier, sinon un tout neuf
     * @throws java.io.IOException
     */
    public void cloze(String sFile, Boolean bAppend, String hiera) throws IOException{
        String str="**** Habilitants ****\n";
        Writer output = null;
        try{
           for(Habilitant h: jpaHblts.findHabilitantEntities()){
                str+=h.toString()+" ";
           }
           str+="\n**** Hiérarchie de rôles ou d'objet de sécurité ****\n"+hiera;
           System.out.println("Serialization terminée");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            output = new BufferedWriter(new FileWriter(sFile, bAppend));
              //FileWriter always assumes default encoding is OK!
            output.write(str);
        } catch (IOException ex) {
            System.err.println("Erreur "+ex.toString());
        } finally {
            output.close();
        }
        System.out.println("Recherche des rôles applicatifs terminée");
    }

    public String getFichier() {
        return fichier;
    }

    public void setFichier(String fichier) {
        this.fichier = fichier;
    }
    public String getFichier2() {
        return fichier2;
    }

    public void setFichier2(String fichier) {
        this.fichier2 = fichier;
    }
    public int getSeuilHbilitant() {
        return seuilHbilitant;
    }

    public void setSeuilHbilitant(int seuilHbilitant) {
        this.seuilHbilitant = seuilHbilitant;
    }
    public int getSeuilHbilitant2() {
        return seuilHbilitant2;
    }

    public void setSeuilHbilitant2(int seuilHbilitant2) {
        this.seuilHbilitant2 = seuilHbilitant2;
    }

    public class UniqueArrayList extends ArrayList {
        /**
         * Only add the object if there is not
         * another copy of it in the list
         */
        private int lastEqual;

        public int getLastEqual() {
            return lastEqual+1;
        }
        @Override
        public boolean add(Object obj) {
            for (int i = 0; i < size(); i++) {
                if (obj.equals(get(i))) {
                    lastEqual = i;
                    return false;
                }
            }
            return super.add(obj);
        }

        @Override
        public boolean addAll(Collection c) {
            boolean result = true;
            for (Object t : c) {
                if (!add(t)) {
                    result = false;
                }
            }
            return result;
        }
    }
}
