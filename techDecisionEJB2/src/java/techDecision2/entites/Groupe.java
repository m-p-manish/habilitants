/*
Copyright Stéphane Georges Popoff, (mai 2009)

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
package techDecision2.entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * table des groupes
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "GROUPE")
@NamedQueries({
    @NamedQuery(name = "Groupe.findAll", query = "SELECT g FROM Groupe g"),
    @NamedQuery(name = "Groupe.findByPkgroupe", query = "SELECT g FROM Groupe g WHERE g.pkgroupe = :pkgroupe"),
    @NamedQuery(name = "Groupe.findByName", query = "SELECT g FROM Groupe g WHERE g.name = :name"),
    @NamedQuery(name = "Groupe.findByType", query = "SELECT g FROM Groupe g WHERE g.type = :type"),
    @NamedQuery(name = "Groupe.findByHavechild", query = "SELECT g FROM Groupe g WHERE g.havechild = :havechild"),
    @NamedQuery(name = "Groupe.findByActive", query = "SELECT g FROM Groupe g WHERE g.active = :active")})
public class Groupe implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKGROUPE")
    private Integer pkgroupe;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private short type;
    @Basic(optional = false)
    @Column(name = "HAVECHILD")
    private short havechild;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private short active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkgroupe")
    private List<Memberof> memberofCollection1;
    @OneToMany
    private List<Groupe> lesGroupes;

    public Groupe() {
    }

    public Groupe(Integer pkgroupe) {
        this.pkgroupe = pkgroupe;
    }

    public Groupe(Integer pkgroupe, String name, short type, short havechild, short active) {
        this.pkgroupe = pkgroupe;
        this.name = name;
        this.type = type;
        this.havechild = havechild;
        this.active = active;
    }

    public Integer getPkgroupe() {
        return pkgroupe;
    }

    public void setPkgroupe(Integer pkgroupe) {
        this.pkgroupe = pkgroupe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public short getHavechild() {
        return havechild;
    }

    public void setHavechild(short havechild) {
        this.havechild = havechild;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public List<Memberof> getMemberofCollection1() {
        return memberofCollection1;
    }

    public void setMemberofCollection1(List<Memberof> memberofCollection1) {
        this.memberofCollection1 = memberofCollection1;
    }

    public List<Groupe> getLesGroupes() {
        return lesGroupes;
    }

    public void setLesGroupes(List<Groupe> lesGroupes) {
        this.lesGroupes = lesGroupes;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkgroupe != null ? pkgroupe.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Groupe)) {
            return false;
        }
        Groupe other = (Groupe) object;
        if ((this.pkgroupe == null && other.pkgroupe != null) || (this.pkgroupe != null && !this.pkgroupe.equals(other.pkgroupe))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkgroupe=" + pkgroupe+" name="+name;
    }
    /**
     * commande d'insertion SQL
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"GROUPE (PKGROUPE, NAME, TYPE, HAVECHILD, ACTIVE) VALUES ("+pkgroupe+",'"+name+"',"+type+","+havechild+","+active+")";
    }

}
