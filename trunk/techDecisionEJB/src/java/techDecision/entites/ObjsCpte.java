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
/**
 * Ce package contient les classes qui gèrent les données de base de l'outil a savoir
 * les comptes, les identités, les rôles, les habilitants
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
import javax.persistence.OneToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * Classe entité qui porte la table de liaison entre un objet de sécurité et un compte
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "OBJS_CPTE")
@NamedQueries({
    @NamedQuery(name = "ObjsCpte.findAll", query = "SELECT o FROM ObjsCpte o"),
    @NamedQuery(name = "ObjsCpte.findByPkobjsCpte", query = "SELECT o FROM ObjsCpte o WHERE o.pkobjsCpte = :pkobjsCpte"),
    @NamedQuery(name = "ObjsCpte.findByPkobjs", query = "SELECT o FROM ObjsCpte o WHERE o.fkobjs = :fkobjs"),
    @NamedQuery(name = "ObjsCpte.findByPkcpte", query = "SELECT o FROM ObjsCpte o WHERE o.fkcpte = :fkcpte")
})
public class ObjsCpte implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PKOBJS_CPTE", nullable = false)
    private Integer pkobjsCpte;
    @JoinColumn(name = "FKCPTE", referencedColumnName = "PKCPTE", nullable = false)
    @OneToOne(targetEntity=techDecision.entites.Compte.class, optional = false)
    private Compte fkcpte;
    @JoinColumn(name = "FKOBJS", referencedColumnName = "PKOBJS", nullable = false)
    @OneToOne(targetEntity=techDecision.entites.Objsecu.class, optional = false)
    private Objsecu fkobjs;

    public ObjsCpte() {
    }

    public ObjsCpte(Integer pkobjsCpte) {
        this.pkobjsCpte = pkobjsCpte;
    }

    public Integer getPkobjsCpte() {
        return pkobjsCpte;
    }

    public void setPkobjsCpte(Integer pkobjsCpte) {
        this.pkobjsCpte = pkobjsCpte;
    }

    public Compte getFkcpte() {
        return fkcpte;
    }

    public void setFkcpte(Compte fkcpte) {
        this.fkcpte = fkcpte;
    }

    public Objsecu getFkobjs() {
        return fkobjs;
    }

    public void setFkobjs(Objsecu fkobjs) {
        this.fkobjs = fkobjs;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkobjsCpte != null ? pkobjsCpte.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjsCpte)) {
            return false;
        }
        ObjsCpte other = (ObjsCpte) object;
        if ((this.pkobjsCpte == null && other.pkobjsCpte != null) || (this.pkobjsCpte != null && !this.pkobjsCpte.equals(other.pkobjsCpte))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fkobjs=" + fkobjs.getPkobjs() + " fkcpte=" + fkcpte.getPkcpte();
    }
    /**
     * commande d'insertion SQL
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"OBJS_CPTE (PKOBJS_CPTE, FKCPTE, FKOBJS) VALUES ("+pkobjsCpte+","+fkcpte.getPkcpte()+","+fkobjs.getPkobjs()+")";
    }

}
