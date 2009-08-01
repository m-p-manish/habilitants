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
 * Classe entité qui porte les comptes
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "COMPTE")
@NamedQueries({@NamedQuery(name = "Compte.findAll", query = "SELECT c FROM Compte c"), @NamedQuery(name = "Compte.findByPkcpte", query = "SELECT c FROM Compte c WHERE c.pkcpte = :pkcpte"), @NamedQuery(name = "Compte.findByPkcpte2", query = "SELECT c FROM Compte c WHERE c.pkcpte2 = :pkcpte2")})
public class Compte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PKCPTE", nullable = false)
    private Integer pkcpte;
    @Basic(optional = false)
    @Column(name = "PKCPTE2", nullable = false)
    private int pkcpte2;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkcpte")
    private Collection<ObjsCpte> objsCpteCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkcpte")
    private Collection<CpteAttrs> cpteAttrsCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fkcpte")
    private Collection<IdntCpte> idntCpteCollection;

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

    public Collection<ObjsCpte> getObjsCpteCollection() {
        return objsCpteCollection;
    }

    public void setObjsCpteCollection(Collection<ObjsCpte> objsCpteCollection) {
        this.objsCpteCollection = objsCpteCollection;
    }

    public Collection<CpteAttrs> getCpteAttrsCollection() {
        return cpteAttrsCollection;
    }

    public void setCpteAttrsCollection(Collection<CpteAttrs> cpteAttrsCollection) {
        this.cpteAttrsCollection = cpteAttrsCollection;
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
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"COMPTE (PKCPTE, PKCPTE2) VALUES ("+pkcpte+","+pkcpte2+")";
    }

}
