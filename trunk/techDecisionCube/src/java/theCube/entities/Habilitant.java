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
import javax.persistence.Basic;
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
 * Classe entité qui porte les habilitants
 * @author spopoff@rocketmail.com
 * @version 0.7
 */
@Entity
@Table(name = "HABILITANT", schema="SPOPOFF")
@NamedQueries({
    @NamedQuery(name = "Habilitant.findAll", query = "SELECT h FROM Habilitant h"),
    @NamedQuery(name = "Habilitant.findByPkhblt", query = "SELECT h FROM Habilitant h WHERE h.pkhblt = :pkhblt"),
    @NamedQuery(name = "Habilitant.findByVal", query = "SELECT h FROM Habilitant h WHERE h.val = :val")})
public class Habilitant implements Serializable, theCube.roleMining.IHabilitant {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKHBLT", nullable = false)
    private Integer pkhblt;
    @Basic(optional = false)
    @Column(name = "VAL", nullable = false, length = 100)
    private String val;
    @Basic(optional = false)
    @Column(name = "TYPE", nullable = true)
    private int type;

    @OneToMany
    @JoinTable(name="SPOPOFF.OBJS_HBLT",
        joinColumns=@JoinColumn(name="FKHBLT"),
        inverseJoinColumns=@JoinColumn(name="FKOBJS"))
    private Collection<Objsecu> objsColl;

    @OneToMany
    @JoinTable(name="SPOPOFF.CPTE_HBLT",
        joinColumns=@JoinColumn(name="FKHBLT"),
        inverseJoinColumns=@JoinColumn(name="FKCPTE"))
    private Collection<Compte> cpteColl;

    public Collection<Compte> getCpteColl() {
        return cpteColl;
    }

    public void setCpteColl(Collection<Compte> cpteColl) {
        this.cpteColl = cpteColl;
    }

    public Collection<Objsecu> getObjsColl() {
        return objsColl;
    }

    public void setObjsColl(Collection<Objsecu> objsColl) {
        this.objsColl = objsColl;
    }

    public Habilitant() {
    }

    public Habilitant(Integer pkhblt) {
        this.pkhblt = pkhblt;
    }

    public Habilitant(Integer pkhblt, String val) {
        this.pkhblt = pkhblt;
        this.val = val;
    }

    public Habilitant(Integer pkhblt, String val, int type) {
        this.pkhblt = pkhblt;
        this.val = val;
        this.type = type;
    }
    public Integer getPkhblt() {
        return pkhblt;
    }

    public void setPkhblt(Integer pkhblt) {
        this.pkhblt = pkhblt;
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    public int getType() {
        return type;
    }

    public void setType(int val) {
        this.type = val;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkhblt != null ? pkhblt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Habilitant)) {
            return false;
        }
        Habilitant other = (Habilitant) object;
        if ((this.pkhblt == null && other.pkhblt != null) || (this.pkhblt != null && !this.pkhblt.equals(other.pkhblt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkhblt=" + pkhblt + " val="+val+" type"+type;
    }
    /**
     * donne la commande d'insertion SQL de l'objet
     * @return
     */
    public String sqlInsert(String schemaa){
        return "INSERT INTO "+schemaa+"HABILITANT (PKHBLT, VAL, TYPE) VALUES("+pkhblt+",'"+val+"',"+type+")";
    }
    /**
     * donne la commande de mise à jour SQL de l'objet
     * @return
     */
    public String sqlUpdate(String schemaa){
        return "UPDATE INTO "+schemaa+"HABILITANT (PKHBLT, VAL, TYPE) VALUES("+pkhblt+",'"+val+"',"+type+")";
    }

    public int getId() {
        return pkhblt;
    }

    public String getName() {
        return val;
    }

}
