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
 * table habilitant d'un rôle
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "PERMISSIONS")
@NamedQueries({
    @NamedQuery(name = "Permissions.findAll", query = "SELECT p FROM Permissions p"),
    @NamedQuery(name = "Permissions.findByPkpermissions", query = "SELECT p FROM Permissions p WHERE p.pkpermissions = :pkpermissions"),
    @NamedQuery(name = "Permissions.findByActive", query = "SELECT p FROM Permissions p WHERE p.active = :active"),
    @NamedQuery(name = "Permissions.findByFkhabilitant", query = "SELECT p FROM Permissions p WHERE p.fkhabilitant = :fkhabilitant")})
public class Permissions implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKPERMISSIONS")
    private Integer pkpermissions;
    @Basic(optional = false)
    @Column(name = "ACTIVE")
    private short active;
    @Basic(optional = false)
    @Column(name = "FKHABILITANT")
    private int fkhabilitant;
    @JoinColumn(name = "FKROLE", referencedColumnName = "PKROLE")
    @ManyToOne(optional = false)
    private Role fkrole;

    public Permissions() {
    }

    public Permissions(Integer pkpermissions) {
        this.pkpermissions = pkpermissions;
    }

    public Permissions(Integer pkpermissions, short active, int fkhabilitant) {
        this.pkpermissions = pkpermissions;
        this.active = active;
        this.fkhabilitant = fkhabilitant;
    }

    public Integer getPkpermissions() {
        return pkpermissions;
    }

    public void setPkpermissions(Integer pkpermissions) {
        this.pkpermissions = pkpermissions;
    }

    public short getActive() {
        return active;
    }

    public void setActive(short active) {
        this.active = active;
    }

    public int getFkhabilitant() {
        return fkhabilitant;
    }

    public void setFkhabilitant(int fhhabilitant) {
        this.fkhabilitant = fhhabilitant;
    }

    public Role getFkrole() {
        return fkrole;
    }

    public void setFkrole(Role fkrole) {
        this.fkrole = fkrole;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkpermissions != null ? pkpermissions.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Permissions)) {
            return false;
        }
        Permissions other = (Permissions) object;
        if ((this.pkpermissions == null && other.pkpermissions != null) || (this.pkpermissions != null && !this.pkpermissions.equals(other.pkpermissions))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkpermissions=" + pkpermissions + " fkhabilitant="+fkhabilitant+" fkrole="+fkrole.getPkrole();
    }
    /**
     * commande d'insertion SQL
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"PERMISSIONS (PKPERMISSIONS, ACTIVE, FKHABILITANT, FKROLE) VALUES ("+pkpermissions+","+active+","+","+fkhabilitant+","+fkrole.getPkrole()+")";
    }

}
