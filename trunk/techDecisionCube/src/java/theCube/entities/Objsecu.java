/*
Copyright Stéphane Georges Popoff, (mai - juin 2009)

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
package theCube.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import theCube.roleMining.TalendConnection;

/**
 * Classe entité qui porte la référence d'un objet de sécurité ou sécurisé
 * En particulier porte la notion de rôle applicatif ou profil applicatif
 * @author spopoff@rocketmail.com
 * @version 0.6
 */
@Entity
@Table(name = "OBJSECU", schema="SPOPOFF")
@NamedQueries({
    @NamedQuery(name = "Objsecu.findAll", query = "SELECT o FROM Objsecu o"),
    @NamedQuery(name = "Objsecu.findByPkobjs", query = "SELECT o FROM Objsecu o WHERE o.pkobjs = :pkobjs")})
public class Objsecu implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKOBJS", nullable = false)
    private Integer pkobjs;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkobjs")
    private Collection<ObjsAttrs> objsAttrsCollection;

    @OneToMany
    @JoinTable(name="SPOPOFF.OBJS_CPTE",
        joinColumns=@JoinColumn(name="FKOBJS"),
        inverseJoinColumns=@JoinColumn(name="FKCPTE"))
    private Collection<Compte> cpteColl;

    @OneToMany
    @JoinTable(name="SPOPOFF.OBJS_HBLT",
        joinColumns=@JoinColumn(name="FKOBJS"),
        inverseJoinColumns=@JoinColumn(name="FKHBLT"))
    private Collection<Habilitant> hbltColl;

    public Collection<Compte> getCpteColl() {
        return cpteColl;
    }

    public void setCpteColl(Collection<Compte> cpteColl) {
        this.cpteColl = cpteColl;
    }

    public Collection<Habilitant> getHbltColl() {
        return hbltColl;
    }

    public String getHabilitants(){
        String ret = "";
        ArrayList<String> ll = new ArrayList<String>();
        for(Habilitant h: hbltColl){
            ll.add(h.getVal());
        }
        Collections.sort(ll);
        return ll.toString();
    }
    public void setHbltColl(Collection<Habilitant> hbltColl) {
        this.hbltColl = hbltColl;
    }

    public Objsecu() {
    }

    public Objsecu(Integer pkobjs) {
        this.pkobjs = pkobjs;
    }

    public Integer getPkobjs() {
        return pkobjs;
    }

    public void setPkobjs(Integer pkobjs) {
        this.pkobjs = pkobjs;
    }

    public Collection<ObjsAttrs> getObjsAttrsCollection() {
        return objsAttrsCollection;
    }

    public void setObjsAttrsCollection(Collection<ObjsAttrs> objsAttrsCollection) {
        this.objsAttrsCollection = objsAttrsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkobjs != null ? pkobjs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Objsecu)) {
            return false;
        }
        Objsecu other = (Objsecu) object;
        if ((this.pkobjs == null && other.pkobjs != null) || (this.pkobjs != null && !this.pkobjs.equals(other.pkobjs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkobjs=" + pkobjs;
    }
    /**
     * commande d'insertion SQL
     * @param schemaa le schéma applicable ou rien
     * @return
     */
    public String sqlInsert(String schemaa){
        return "INSERT INTO "+schemaa+"OBJSECU (PKOBJS) VALUES ("+pkobjs+")";
    }
    /**
     * commande d'insertion SQL
     * @param schemaa le schéma applicable ou rien
     * @return
     */
    public String sqlUpdate(String schemaa){
        return "UPDATE INTO "+schemaa+"OBJSECU (PKOBJS) VALUES ("+pkobjs+")";
    }
    /**
     * retourne la taille de la collection d'habilitant
     * @return
     */
    public int getNbreHblt(){
        int ret = 0;
        ret = hbltColl.size();
        return ret;
    }
    /**
     * retourne la taille de la collection de compte
     * @return
     */
    public int getNbreCpt(){
        int ret = 0;
        ret = cpteColl.size();
        return ret;
    }
    public int getId(){
        return pkobjs;
    }
    /**
     * Procèdure pour rechercher les rôles dans les rôles. Attntion cette procèdure
     * trouve des liaisons et établis des raccourcis entre plusieurs niveau en effet
     * si un rôle à comme habilitant AB alors il est parent de ABC mais aussi ABCD. La
     * procèdure elague va permettre de couper la liaison entre AB et ABC.
     * @param autre un autre rôle
     */
    public void cousin(Objsecu autre){
        int iComm = 0;
        String inter = "";
        for(Habilitant hh: hbltColl){
            String h = hh.getVal();
            for(Habilitant hhhh: autre.getHbltColl()){
                String hhh = hhhh.getVal();
                if(h.equals(hhh)){
                    iComm++;
                    inter += " ";
                    inter += hhh;
                    break;
                }
            }
        }
        if(hbltColl.size()==iComm && autre.getHbltColl().size()>hbltColl.size()){
            //déprovisionne le rôle en cours car en face c'est un sous rôle
            //c'est à dire un rôle avec une racine commune et plusieurs
            //habilitant en plus, il est plus spécifique donc on garde le provisioning
            //Le rôle en cours représnte la racine
            System.out.println("habilitant en commun="+iComm+" val="+inter+" autre(plus long)"+autre.getHabilitants());
            deprovisionne(autre);
            System.out.println("Objs="+autre.getPkobjs()+" est un enfant");
            autre.setEnfant();
            setSommet();
            setFilius(""+autre.getId());
            System.out.println("Objs="+pkobjs+" est un sommet");
            //listeR.add(autre);
        }
    }
    /**
     * Marque un rôle comme enfant
     * si la collection des attributs est vide (improbable) on ajoute
     * si la collection des attributs contient ENFANTID pour la même valeur on ne fait rien
     * sinon on fabrique l'attribut et on l'ajoute à la collection
     */
    public void setFilius(String id){
        if(objsAttrsCollection==null){
            objsAttrsCollection = new LinkedList<ObjsAttrs>();
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"ENFANTID", id);
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }else{
            for(ObjsAttrs oa: objsAttrsCollection){
                if(oa.getAttr().equals("ENFANTRAP")){
                    if(oa.getVal().equals(id)) return;
                }
            }
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"ENFANTID", id);
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }
    }
    /**
     * Marque un rôle comme enfant
     * si la collection des attributs est vide (improbable) on ajoute
     * si la collection des attributs contient ENFANTRAP on met à jour la valeur
     * sinon on fabrique l'attribut et on l'ajoute à la collection
     */
    public void setEnfant(){
        if(objsAttrsCollection==null){
            objsAttrsCollection = new LinkedList<ObjsAttrs>();
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"ENFANTRAP", "VRAI");
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }else{
            for(ObjsAttrs oa: objsAttrsCollection){
                if(oa.getAttr().equals("ENFANTRAP")){
                    oa.setVal("VRAI");
                    return;
                }
            }
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"ENFANTRAP", "VRAI");
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }
    }
    /**
     * Marque un rôle le premier parent
     * si la collection des attributs est vide (improbable) on ajoute
     * si la collection des attributs contient SOMMETRAP on met à jour la valeur
     * sinon on fabrique l'attribut et on l'ajoute à la collection
     */
    public void setSommet(){
        if(objsAttrsCollection==null){
            objsAttrsCollection = new LinkedList<ObjsAttrs>();
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"SOMMETRAP", "VRAI");
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }else{
            for(ObjsAttrs oa: objsAttrsCollection){
                if(oa.getAttr().equals("SOMMETRAP")){
                    oa.setVal("VRAI");
                    return;
                }
            }
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"SOMMETRAP", "VRAI");
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }
    }
    /**
     * retour vrai si roleApp (objsec) est un enfant d'un autre (parent)
     * @return vrai ou faux
     */
    public Boolean getEnfant(){
        Boolean enfant = false;
        if(objsAttrsCollection==null){
            System.out.println("getEnfant Pas normal pas d'attribut!");
            return enfant;
        }
         for(ObjsAttrs oa: objsAttrsCollection){
            if(oa.getAttr().equals("ENFANTRAP") && oa.getVal().equals("VRAI")){
                enfant = true;
                break;
            }
        }
        return enfant;
    }
    /**
     * retourne vrai si le roleApp est le premier parent
     * @return
     */
    public Boolean getSommet(){
        Boolean sommet = false;
        if(objsAttrsCollection==null){
            System.out.println("getSommet Pas normal pas d'attribut!");
            return sommet;
        }
         for(ObjsAttrs oa: objsAttrsCollection){
            if(oa.getAttr().equals("SOMMETRAP") && oa.getVal().equals("VRAI")){
                sommet = true;
                break;
            }
        }
        return sommet;
    }
    /**
     * Procèdure pour retirer les comptes qui sont consommé par un rôle plus
     * spécifique, donc autre est un enfant du rôle actuel
     * @param autre le rôle plus spécifique car enfant
     */
    public void deprovisionne(Objsecu autre){
        for(Compte c : autre.getCpteColl()){
            Boolean ret = cpteColl.remove(c);
            System.out.println("Deprovisionne1 cpte="+c.getId()+" de objs="+pkobjs+" nb cpte="+cpteColl.size()+" retour="+ret);
        }
/*
        for(Compte c : cpteColl){
            System.out.println("moi cpte="+c.getId()+" de objs="+pkobjs+" nb cpte="+cpteColl.size());
        }
*/
/*
        Boolean bRet = false;
        List listeClone = new LinkedList();
        for(Compte i: cpteColl){
            listeClone.add(i);
        }
        for(Iterator it = listeClone.iterator(); it.hasNext();){
            Compte c = (Compte)it.next();
            for(Iterator itt = autre.getCpteColl().iterator(); itt.hasNext();){
                Compte cc = (Compte)itt.next();
                if(c.equals(cc)){
                    try{
                        bRet = cpteColl.remove(c);
                        System.out.println("Deprovisionne cpte="+c.getId()+" de objs="+pkobjs);
                    }catch(ConcurrentModificationException err){
                        System.err.println("même pas mal "+err.toString());
                    }
                    if(!bRet){
                        //bErreur = true;
                        System.err.println("N'arrive pas à supprimer un compte qui doit être présent cpt="+c);
                    }
                    break;
                }
            }
        }
 */
    }
    /**
     * retourne le parent le plus éloigné du roleApp
     * @return
     */
    public String getParent() {
        String parent = "";
        if(objsAttrsCollection==null){
            return parent;
        }
         for(ObjsAttrs oa: objsAttrsCollection){
            if(oa.getAttr().equals("PARENTRAP")){
                if(parent.compareTo(oa.getVal())<0) parent = oa.getVal();
            }
        }
       return parent;
    }
    /**
     * Indique le parent du roleApp (attribut de l'objsecu)
     * si la collection des attributs est vide (improbable) on ajoute
     * si la collection des attributs contient PARENTRAP on met à jour la valeur
     * sinon on fabrique l'attribut et on l'ajoute à la collection
     * @param parent
     */
    public void setParent(String parent) {
        if(objsAttrsCollection==null){
            objsAttrsCollection = new LinkedList<ObjsAttrs>();
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"PARENTRAP", parent);
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }else{
            for(ObjsAttrs oa: objsAttrsCollection){
                if(oa.getAttr().equals("PARENTRAP")){
                    oa.setVal(parent);
                    return;
                }
            }
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"PARENTRAP", parent);
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }
    }
    /**
     * Donne la position hiérarchique la plus éloignée du rôle dans l'aborescence
     * @return getPosition un indice de type x.x.x
     */
    public String getPosition(){
         String position = "";
         if(objsAttrsCollection==null){
            return position;
         }
         for(ObjsAttrs oa: objsAttrsCollection){
            if(oa.getAttr().equals("POSRAP")){
                //si position est avant oa.getval alors on prend se sernier
                if(position.compareTo(oa.getVal())<0) position = oa.getVal();
                //break;
            }
        }
       return position;
    }
    /**
     * stocke la position hiérarchique du roleApp
     * si la collection des attributs est vide (improbable) on ajoute
     * si la collection des attributs contient POSRAP on met à jour la valeur
     * sinon on fabrique l'attribut et on l'ajoute à la collection
     * @param pos indice de type x.x.x
     */
    public void setPosition(String pos){
        if(objsAttrsCollection==null){
            objsAttrsCollection = new LinkedList<ObjsAttrs>();
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"POSRAP", pos);
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }else{
            for(ObjsAttrs oa: objsAttrsCollection){
                if(oa.getAttr().equals("POSRAP")){
                    oa.setVal(pos);
                    return;
                }
            }
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"POSRAP", pos);
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }
    }
    /**
     * Donne le nom du rôle App
     * @return nom
     */
    public String getDescription(){
         String desc = "";
         if(objsAttrsCollection==null){
            return desc;
         }
         for(ObjsAttrs oa: objsAttrsCollection){
            if(oa.getAttr().equals("DESCRAP")){
                desc = oa.getVal();
                break;
            }
        }
       return desc;
    }
    /**
     * stocke le nom du roleApp
     * si la collection des attributs est vide (improbable) on ajoute
     * si la collection des attributs contient DESCRAP on met à jour la valeur
     * sinon on fabrique l'attribut et on l'ajoute à la collection
     * @param desc
     */
    public void setDescription(String desc){
        if(objsAttrsCollection==null){
            objsAttrsCollection = new LinkedList<ObjsAttrs>();
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"DESCRAP", desc);
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }else{
            for(ObjsAttrs oa: objsAttrsCollection){
                if(oa.getAttr().equals("DESCRAP")){
                    oa.setVal(desc);
                    return;
                }
            }
            ObjsAttrs oaa = new ObjsAttrs(new Integer(TalendConnection.Aleat()),"DESCRAP", desc);
            oaa.setFkobjs(this);
            objsAttrsCollection.add(oaa);
        }
    }
}
