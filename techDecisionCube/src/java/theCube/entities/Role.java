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
package theCube.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
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

/**
 * table des rôles pour Datamart et analyse
 * @author spopoff@rocketmail.com
 * @version 0.7
 */
@Entity
@Table(name = "ROLE", schema="SPOPOFF")
@NamedQueries({
    @NamedQuery(name = "Role.findAll", query = "SELECT r FROM Role r"),
    @NamedQuery(name = "Role.findByPkrole", query = "SELECT r FROM Role r WHERE r.pkrole = :pkrole"),
    @NamedQuery(name = "Role.findByName", query = "SELECT r FROM Role r WHERE r.name = :name"),
    @NamedQuery(name = "Role.findByHavechild", query = "SELECT r FROM Role r WHERE r.havechild = :havechild"),
    @NamedQuery(name = "Role.findByActive", query = "SELECT r FROM Role r WHERE r.active = :active")})
public class Role implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKROLE")
    private Integer pkrole;
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    @Basic(optional = false)
    @Column(name = "HAVECHILD")
    private short havechild;
    @Basic(optional = false)
    @Column(name = "TYPE")
    private short type;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private short active;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkparentrole")
    private List<Roleslink> roleslinkCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkchildrole")
    private List<Roleslink> roleslinkCollection1;

    @OneToMany
    @JoinTable(name="MEMBERS",
        joinColumns=@JoinColumn(name="FKROLE"),
        inverseJoinColumns=@JoinColumn(name="FKIDENTITY"))
    private Collection<Identite> collIdnt;

    @OneToMany
    @JoinTable(name="PERMISSIONS",
        joinColumns=@JoinColumn(name="FKROLE"),
        inverseJoinColumns=@JoinColumn(name="FKHABILITANT"))
    private Collection<Habilitant> collHblt;

    public Role() {
    }

    public Role(Integer pkrole) {
        this.pkrole = pkrole;
    }

    public Role(Integer pkrole, String name, short havechild, short active, short type) {
        this.pkrole = pkrole;
        this.name = name;
        this.havechild = havechild;
        this.active = active;
        this.type = type;
    }

    public Integer getPkrole() {
        return pkrole;
    }
    /**
     * Retourne le code unique du rôle
     * @return getId
     */
    public int getId() {
        return pkrole;
    }

    public void setPkrole(Integer pkrole) {
        this.pkrole = pkrole;
    }

    /**
     * retourne le code attribué au rôle
     * @return getDescription
     */
    public String getDescription() {
        return name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public short getType() {
        return type;
    }

    public void setType(short type) {
        this.type = type;
    }

    public Collection<Identite> getCollIdnt() {
        return collIdnt;
    }

    public void setCollIdnt(Collection<Identite> collIdnt) {
        this.collIdnt = collIdnt;
    }

    public Collection<Habilitant> getCollHblt() {
        return collHblt;
    }

    public void setCollHblt(Collection<Habilitant> collHblt) {
        this.collHblt = collHblt;
    }

    public List<Roleslink> getRoleslinkCollection() {
        return roleslinkCollection;
    }

    public void setRoleslinkCollection(List<Roleslink> roleslinkCollection) {
        this.roleslinkCollection = roleslinkCollection;
    }

    public List<Roleslink> getRoleslinkCollection1() {
        return roleslinkCollection1;
    }

    public void setRoleslinkCollection1(List<Roleslink> roleslinkCollection1) {
        this.roleslinkCollection1 = roleslinkCollection1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkrole != null ? pkrole.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Role)) {
            return false;
        }
        Role other = (Role) object;
        if ((this.pkrole == null && other.pkrole != null) || (this.pkrole != null && !this.pkrole.equals(other.pkrole))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkrole=" + pkrole + " name=" + name;
    }
    /**
     * commande d'insertion SQL
     * @param schemaa le schéma applicable ou rien
     * @return
     */
    public String sqlInsert(String schemaa){
        return "INSERT INTO "+schemaa+"ROLE (PKROLE, NAME, TYPE, HAVECHILD, ACTIVE) VALUES ("+pkrole+",'"+name+"',"+type+","+havechild+","+active+")";
    }
    /**
     * commande de mise à jour SQL
     * @param schemaa le schéma applicable ou rien
     * @return
     */
    public String sqlUpdate(String schemaa){
        return "UPDATE INTO "+schemaa+"ROLE (PKROLE, NAME, TYPE, HAVECHILD, ACTIVE) VALUES ("+pkrole+",'"+name+"',"+type+","+havechild+","+active+")";
    }

}
