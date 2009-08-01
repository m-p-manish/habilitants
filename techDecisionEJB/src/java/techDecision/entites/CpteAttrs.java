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
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe entité qui porte la liaison entre un compte et ses attibuts
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "CPTE_ATTRS")
@NamedQueries({
    @NamedQuery(name = "CpteAttrs.findAll", query = "SELECT c FROM CpteAttrs c"),
    @NamedQuery(name = "CpteAttrs.findByAttr", query = "SELECT c FROM CpteAttrs c WHERE c.attr = :attr"),
    @NamedQuery(name = "CpteAttrs.findByVal", query = "SELECT c FROM CpteAttrs c WHERE c.val = :val"),
    @NamedQuery(name = "CpteAttrs.findByPkattrCpte", query = "SELECT c FROM CpteAttrs c WHERE c.pkattrCpte = :pkattrCpte"),
    @NamedQuery(name = "CpteAttrs.findByFkCpte", query = "SELECT c FROM CpteAttrs c WHERE c.fkcpte = :fkcpte")
})
public class CpteAttrs extends Attributs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PKATTR_CPTE", nullable = false)
    private Integer pkattrCpte;
    @JoinColumn(name = "FKCPTE", referencedColumnName = "PKCPTE", nullable = false)
    @ManyToOne(optional = false)
    private Compte fkcpte;

    public CpteAttrs() {
        super();
    }

    public CpteAttrs(Integer pkattrCpte) {
        super();
        this.pkattrCpte = pkattrCpte;
    }

    public CpteAttrs(Integer pkattrCpte, String attr, String val) {
        super();
        this.pkattrCpte = pkattrCpte;
        setAttr(attr);
        setVal(val);
    }

    public Integer getPkattrCpte() {
        return pkattrCpte;
    }

    public void setPkattrCpte(Integer pkattrCpte) {
        this.pkattrCpte = pkattrCpte;
    }

    public Compte getFkcpte() {
        return fkcpte;
    }

    public void setFkcpte(Compte fkcpte) {
        this.fkcpte = fkcpte;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkattrCpte != null ? pkattrCpte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CpteAttrs)) {
            return false;
        }
        CpteAttrs other = (CpteAttrs) object;
        if ((this.pkattrCpte == null && other.pkattrCpte != null) || (this.pkattrCpte != null && !this.pkattrCpte.equals(other.pkattrCpte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkattrCpte=" + pkattrCpte + "fkcpte="+fkcpte.getPkcpte()+ " val="+super.getVal()+" attr="+super.getAttr();
    }
    /**
     * commande d'insertion SQL
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"CPTE_ATTRS (PKATTR_CPTE, FKCPTE, ATTR, VAL) VALUES ("+pkattrCpte+","+fkcpte.getPkcpte()+",'"+super.getAttr()+"','"+super.getVal()+"')";
    }

}
