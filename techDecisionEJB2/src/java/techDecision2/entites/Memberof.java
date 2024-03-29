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
 * table du contenu des groupes (groupe et/ou identité)
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "MEMBEROF")
@NamedQueries({
    @NamedQuery(name = "Memberof.findAll", query = "SELECT m FROM Memberof m"),
    @NamedQuery(name = "Memberof.findByPkmemberof", query = "SELECT m FROM Memberof m WHERE m.pkmemberof = :pkmemberof"),
    @NamedQuery(name = "Memberof.findByFkidentity", query = "SELECT m FROM Memberof m WHERE m.fkidentity = :fkidentity")})
public class Memberof implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKMEMBEROF")
    private Integer pkmemberof;
    @Column(name = "FKIDENTITY")
    private Integer fkidentity;
    @JoinColumn(name = "FKGROUPENESTED", referencedColumnName = "PKGROUPE")
    @ManyToOne
    private Groupe fkgroupenested;
    @JoinColumn(name = "FKGROUPE", referencedColumnName = "PKGROUPE")
    @ManyToOne(optional = false)
    private Groupe fkgroupe;

    public Memberof() {
    }

    public Memberof(Integer pkmemberof) {
        this.pkmemberof = pkmemberof;
    }

    public Integer getPkmemberof() {
        return pkmemberof;
    }

    public void setPkmemberof(Integer pkmemberof) {
        this.pkmemberof = pkmemberof;
    }

    public Integer getFkidentity() {
        return fkidentity;
    }

    public void setFkidentity(Integer fkidentity) {
        this.fkidentity = fkidentity;
    }

    public Groupe getFkgroupenested() {
        return fkgroupenested;
    }

    public void setFkgroupenested(Groupe fkgroupenested) {
        this.fkgroupenested = fkgroupenested;
    }

    public Groupe getFkgroupe() {
        return fkgroupe;
    }

    public void setFkgroupe(Groupe fkgroupe) {
        this.fkgroupe = fkgroupe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkmemberof != null ? pkmemberof.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Memberof)) {
            return false;
        }
        Memberof other = (Memberof) object;
        if ((this.pkmemberof == null && other.pkmemberof != null) || (this.pkmemberof != null && !this.pkmemberof.equals(other.pkmemberof))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkmemberof=" + pkmemberof + " fkidentity="+fkidentity+" fkgroupe="+fkgroupe.getPkgroupe()+" fkgroupenested="+fkgroupenested.getPkgroupe();
    }
    /**
     * commande d'insertion SQL
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"MEMBEROF (PKMEMBEROF, FKIDENTITY, FKGROUPE, FKGROUPENESTED) VALUES ("+pkmemberof+","+fkidentity+","+fkgroupe.getPkgroupe()+","+fkgroupenested.getPkgroupe()+")";
    }

}
