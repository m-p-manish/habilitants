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
package techDecision.entites;

import java.io.Serializable;
import java.util.Collection;
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
 * Classe entité qui porte la table identité
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "IDENTITE")
@NamedQueries({
    @NamedQuery(name = "Identite.findAll", query = "SELECT i FROM Identite i"),
    @NamedQuery(name = "Identite.findByPkidnt", query = "SELECT i FROM Identite i WHERE i.pkidnt = :pkidnt"),
    @NamedQuery(name = "Identite.findByUsername", query = "SELECT i FROM Identite i WHERE i.username = :username"),
    @NamedQuery(name = "Identite.findByFonction", query = "SELECT i FROM Identite i WHERE i.fonction = :fonction"),
    @NamedQuery(name = "Identite.findByDepartement", query = "SELECT i FROM Identite i WHERE i.departement = :departement")
})
public class Identite implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKIDNT", nullable = false)
    private Integer pkidnt;
    @Basic(optional = false)
    @Column(name = "USERNAME", nullable = false, length = 30)
    private String username;
    @Basic(optional = false)
    @Column(name = "FONCTION", nullable = false, length = 30)
    private String fonction;
    @Basic(optional = false)
    @Column(name = "DEPARTEMENT", nullable = false, length = 30)
    private String departement;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidnt")
    private Collection<IdntAttrs> idntAttrsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkidnt")
    private Collection<IdntCpte> idntCpteCollection;

    public Identite() {
    }

    public Identite(Integer pkidnt) {
        this.pkidnt = pkidnt;
    }

    public Identite(Integer pkidnt, String username, String fonction, String departement) {
        this.pkidnt = pkidnt;
        this.username = username;
        this.fonction = fonction;
        this.departement = departement;
    }

    public Integer getPkidnt() {
        return pkidnt;
    }

    public void setPkidnt(Integer pkidnt) {
        this.pkidnt = pkidnt;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getDepartement() {
        return departement;
    }

    public void setDepartement(String departement) {
        this.departement = departement;
    }

    public Collection<IdntAttrs> getIdntAttrsCollection() {
        return idntAttrsCollection;
    }

    public void setIdntAttrsCollection(Collection<IdntAttrs> idntAttrsCollection) {
        this.idntAttrsCollection = idntAttrsCollection;
    }

    public Collection<IdntCpte> getIdntCpteCollection() {
        return idntCpteCollection;
    }

    public void setIdntCpteCollection(Collection<IdntCpte> idntCpteCollection) {
        this.idntCpteCollection = idntCpteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkidnt != null ? pkidnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Identite)) {
            return false;
        }
        Identite other = (Identite) object;
        if ((this.pkidnt == null && other.pkidnt != null) || (this.pkidnt != null && !this.pkidnt.equals(other.pkidnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkidnt=" + pkidnt + " username="+username;
    }
    /**
     * donne la commande d'insertion SQL de l'objet
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"IDENTITE (PKIDNT, USERNAME, DEPARTEMENT, FONCTION) VALUES("+pkidnt+",'"+username+"','"+departement+"','"+fonction+"')";
    }
}
