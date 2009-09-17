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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * table liaison / hiérarcie des rôles
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "ROLESLINK")
@NamedQueries({
    @NamedQuery(name = "Roleslink.findAll", query = "SELECT r FROM Roleslink r"),
    @NamedQuery(name = "Roleslink.findByPkrolelink", query = "SELECT r FROM Roleslink r WHERE r.pkrolelink = :pkrolelink"),
    @NamedQuery(name = "Roleslink.findByActive", query = "SELECT r FROM Roleslink r WHERE r.active = :active")})
public class Roleslink implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKROLELINK")
    private Integer pkrolelink;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private short active;
    @JoinColumn(name = "FKPARENTROLE", referencedColumnName = "PKROLE")
    @ManyToOne(optional = false)
    private Role fkparentrole;
    @JoinColumn(name = "FKCHILDROLE", referencedColumnName = "PKROLE")
    @ManyToOne(optional = false)
    private Role fkchildrole;

    public Roleslink() {
    }

    public Roleslink(Integer pkrolelink) {
        this.pkrolelink = pkrolelink;
    }

    public Roleslink(Integer pkrolelink, short active) {
        this.pkrolelink = pkrolelink;
        this.active = active;
    }

    public Integer getPkrolelink() {
        return pkrolelink;
    }

    public void setPkrolelink(Integer pkrolelink) {
        this.pkrolelink = pkrolelink;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public Role getFkparentrole() {
        return fkparentrole;
    }

    public void setFkparentrole(Role fkparentrole) {
        this.fkparentrole = fkparentrole;
    }

    public Role getFkchildrole() {
        return fkchildrole;
    }

    public void setFkchildrole(Role fkchildrole) {
        this.fkchildrole = fkchildrole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkrolelink != null ? pkrolelink.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Roleslink)) {
            return false;
        }
        Roleslink other = (Roleslink) object;
        if ((this.pkrolelink == null && other.pkrolelink != null) || (this.pkrolelink != null && !this.pkrolelink.equals(other.pkrolelink))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkrolelink=" + pkrolelink + " fkparentrole="+fkparentrole.getPkrole()+" fkchildrole="+fkchildrole.getPkrole();
    }
    /**
     * commande d'insertion SQL
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"ROLESLINK (PKROLELINK, ACTIVE, FKPARENTROLE, FKCHILDROLE) VALUES ("+pkrolelink+","+active+","+fkparentrole.getPkrole()+","+fkchildrole.getPkrole()+")";
    }

}
