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
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
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
 * Classe entité qui porte les comptes en liaison avec H2 et toutes les méthodes
 * pour Electre et analyse de rôle
 * @author spopoff@rocketmail.com
 * @version 0.8
 */
@Entity
@Table(name = "COMPTE", schema="SPOPOFF")
@NamedQueries({
    @NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"),
    @NamedQuery(name = "Compte.findByPkcpte", query = "SELECT c FROM Compte c WHERE c.pkcpte = :pkcpte"),
    @NamedQuery(name = "Compte.findByPkcpte2", query = "SELECT c FROM Compte c WHERE c.pkcpte2 = :pkcpte2")})
public class Compte implements Serializable, theCube.roleMining.ICpte {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKCPTE", nullable = false)
    private Integer pkcpte;
    @Basic(optional = false)
    @Column(name = "PKCPTE2", nullable = false)
    private int pkcpte2;
    @OneToMany
    @JoinTable(name="SPOPOFF.OBJS_CPTE",
        joinColumns=@JoinColumn(name="FKCPTE"),
        inverseJoinColumns=@JoinColumn(name="FKOBJS"))
    private Collection<Objsecu> objsColl;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkcpte")
    private Collection<CpteAttrs> cpteAttrsCollection;

    @OneToMany
    @JoinTable(name="SPOPOFF.IDNT_CPTE",
        joinColumns=@JoinColumn(name="FKCPTE"),
        inverseJoinColumns=@JoinColumn(name="FKIDNT"))
    private Collection<Identite> idntColl;

    @OneToMany
    @JoinTable(name="SPOPOFF.CPTE_HBLT",
        joinColumns=@JoinColumn(name="FKCPTE"),
        inverseJoinColumns=@JoinColumn(name="FKHBLT"))
    private Collection<Habilitant> hbltColl;

    public Collection<Identite> getIdntColl() {
        return idntColl;
    }

    public void setIdntColl(Collection<Identite> idntColl) {
        this.idntColl = idntColl;
    }

    public Collection<Objsecu> getObjsColl() {
        return objsColl;
    }

    public void setObjsColl(Collection<Objsecu> objsColl) {
        this.objsColl = objsColl;
    }

    public Collection<Habilitant> getHbltColl() {
        return hbltColl;
    }

    public void setHbltColl(Collection<Habilitant> hbltColl) {
        this.hbltColl = hbltColl;
    }

    public Compte() {
    }

    public Compte(Integer pkcpte) {
        this.pkcpte = pkcpte;
    }

    public Compte(Integer pkcpte, int pkcpte2) {
        this.pkcpte = pkcpte;
        this.pkcpte2 = pkcpte2;
    }

    public Integer getPkcpte() {
        return pkcpte;
    }

    public void setPkcpte(Integer pkcpte) {
        this.pkcpte = pkcpte;
    }

    public int getPkcpte2() {
        return pkcpte2;
    }

    public void setPkcpte2(int pkcpte2) {
        this.pkcpte2 = pkcpte2;
    }

    public Collection<CpteAttrs> getCpteAttrsCollection() {
        return cpteAttrsCollection;
    }

    public void setCpteAttrsCollection(Collection<CpteAttrs> cpteAttrsCollection) {
        this.cpteAttrsCollection = cpteAttrsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkcpte != null ? pkcpte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Compte)) {
            return false;
        }
        Compte other = (Compte) object;
        if ((this.pkcpte == null && other.pkcpte != null) || (this.pkcpte != null && !this.pkcpte.equals(other.pkcpte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkcpte=" + pkcpte + " pkcpte2=" + pkcpte2;
    }
    /**
     * commande d'insertion SQL
     * @param schemaa le schéma applicable ou rien
     * @return
     */
    public String sqlInsert(String schemaa){
        return "INSERT INTO "+schemaa+"COMPTE (PKCPTE, PKCPTE2) VALUES ("+pkcpte+","+pkcpte2+")";
    }
    /**
     * commande de mise à jour SQL
     * @param schemaa le schéma applicable ou rien
     * @return
     */
    public String sqlUpdate(String schemaa){
        return "UPDATE INTO "+schemaa+"COMPTE (PKCPTE, PKCPTE2) VALUES ("+pkcpte+","+pkcpte2+")";
    }
    /**
     * Retourne l'identifiant du compte
     * @return getId un entier
     */
    public int getId() {
        return pkcpte;
    }
    /**
     * Retourne l'identifiant externe du compte
     * @return getId2 un entier
     */
    public Long getId2() {
        return new Long(pkcpte2);
    }
    /**
     * Retourne le lien vers l'identité
     * @return getName un code identité
     */
    public String getName() {
        return "RIEN";
    }
    /**
     * retourne l'habilitant de type 18 (profil)
     * @return
     */
    public String getProfil() {
        String profil = "";
        try{
            for(Habilitant h: hbltColl){
                if(h.getType()==18){
                    profil = h.getVal();
                    break;
                }
            }
        }catch(Exception err){
            System.err.println("Erreur sur liste habilitant "+err.toString());
        }
        return profil;
    }
    /**
     * Retourne la liste des habilitants du compte
     * @return listHabilitants une liste
     */
    public LinkedList<String> listHabilitants() {
        LinkedList<String> ret = new LinkedList<String>();
        //System.out.println("Info Compte.listHabilitants Liste des habilitants en mode texte dans une liste");
        for(Habilitant h: hbltColl){
            ret.add(h.getVal());
        }
        Collections.sort(ret);
        return ret;
    }
    /**
     * stocke le classement du compte pour une comparaison electre
     * si la collection des attributs est vide (improbable) on ajoute
     * si la collection des attributs contient CLASST on met à jour la valeur
     * sinon on fabrique l'attribut et on l'ajoute à la collection
     * @param desc
     */
    public void setClasse(String classement){
        if(cpteAttrsCollection==null){
            System.out.println("Collection attributs vide bizarre ?");
            cpteAttrsCollection = new LinkedList<CpteAttrs>();
            CpteAttrs oaa = new CpteAttrs(new Integer(TalendConnection.Aleat()),"CLASST", classement);
            oaa.setFkcpte(this);
            cpteAttrsCollection.add(oaa);
        }else{
            for(CpteAttrs oa: cpteAttrsCollection){
                if(oa.getAttr().equals("CLASST")){
                    System.out.println("Ecrase dernier classement");
                    oa.setVal(classement);
                    return;
                }
            }
            System.out.println("Ajoute classement");
            CpteAttrs oaa = new CpteAttrs(new Integer(TalendConnection.Aleat()),"CLASST", classement);
            oaa.setFkcpte(this);
            cpteAttrsCollection.add(oaa);
        }
    }
    /**
     * Retourne un habilitant sur la base de son identifiant
     * @param un identifiant du compte
     * @return unHabilitant un habilitant de la liste
     */
    public String unHabilitant(int un) {
        String ret  = "";
        for(Habilitant h : hbltColl){
            if(h.getPkhblt()==un){
                ret = h.getVal();
                break;
            }
        }
        return ret;
    }
    public class trierHblt implements Comparator {

        public int compare(Object o1, Object o2) {
            Habilitant h1 = (Habilitant) o1;
            Habilitant h2 = (Habilitant) o2;
            return h1.getVal().compareTo(h2.getVal());
        }

    }

}
