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
 * table de liaison entre les identités et ses attributs
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
@Entity
@Table(name = "IDNT_ATTRS")
@NamedQueries({@NamedQuery(name = "IdntAttrs.findAll", query = "SELECT i FROM IdntAttrs i"), @NamedQuery(name = "IdntAttrs.findByAttr", query = "SELECT i FROM IdntAttrs i WHERE i.attr = :attr"), @NamedQuery(name = "IdntAttrs.findByVal", query = "SELECT i FROM IdntAttrs i WHERE i.val = :val"), @NamedQuery(name = "IdntAttrs.findByPkattrIdnt", query = "SELECT i FROM IdntAttrs i WHERE i.pkattrIdnt = :pkattrIdnt")})
public class IdntAttrs extends Attributs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PKATTR_IDNT", nullable = false)
    private Integer pkattrIdnt;
    @JoinColumn(name = "FKIDNT", referencedColumnName = "PKIDNT", nullable = false)
    @ManyToOne(optional = false)
    private Identite fkidnt;

    public IdntAttrs() {
        super();
    }

    public IdntAttrs(Integer pkattrIdnt) {
        super();
        this.pkattrIdnt = pkattrIdnt;
    }

    public IdntAttrs(Integer pkattrIdnt, String attrr, String vall) {
        super();
        this.pkattrIdnt = pkattrIdnt;
        setAttr(attrr);
        setVal(vall);
    }

    public Integer getPkattrIdnt() {
        return pkattrIdnt;
    }

    public void setPkattrIdnt(Integer pkattrIdnt) {
        this.pkattrIdnt = pkattrIdnt;
    }

    public Identite getFkidnt() {
        return fkidnt;
    }

    public void setFkidnt(Identite fkidnt) {
        this.fkidnt = fkidnt;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pkattrIdnt != null ? pkattrIdnt.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IdntAttrs)) {
            return false;
        }
        IdntAttrs other = (IdntAttrs) object;
        if ((this.pkattrIdnt == null && other.pkattrIdnt != null) || (this.pkattrIdnt != null && !this.pkattrIdnt.equals(other.pkattrIdnt))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkattrIdnt=" + pkattrIdnt + " fkidnt="+fkidnt.getPkidnt()+ " val="+super.getVal()+" attr="+super.getAttr();
    }
    /**
     * commande d'insertion SQL
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"IDNT_ATTRS (PKATTR_IDNT, FKIDNT, VAL, ATTR) VALUES ("+pkattrIdnt+","+fkidnt.getPkidnt()+",'"+super.getVal()+"','"+super.getAttr()+"')";
    }

}
