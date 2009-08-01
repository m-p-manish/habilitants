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
 * Classe entité qui porte la table de liaison entre un objet de sécurité et ses attributs
 * @author spopoff@rocketmail.com
 * @version 0.6
 */
@Entity
@Table(name = "OBJS_ATTRS")
@NamedQueries({
    @NamedQuery(name = "ObjsAttrs.findAll", query = "SELECT o FROM ObjsAttrs o"),
    @NamedQuery(name = "ObjsAttrs.findByAttr", query = "SELECT o FROM ObjsAttrs o WHERE o.attr = :attr"),
    @NamedQuery(name = "ObjsAttrs.findByVal", query = "SELECT o FROM ObjsAttrs o WHERE o.val = :val"),
    @NamedQuery(name = "ObjsAttrs.findByPkattrObjs", query = "SELECT o FROM ObjsAttrs o WHERE o.pkattrObjs = :pkattrObjs"),
    @NamedQuery(name = "ObjsAttrs.findAppli", query = "SELECT o FROM ObjsAttrs o WHERE o.attr = 'APPHASH' and o.val = :val")})
public class ObjsAttrs extends Attributs implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PKATTR_OBJS", nullable = false)
    private Integer pkattrObjs;
    @JoinColumn(name = "FKOBJS", referencedColumnName = "PKOBJS", nullable = false)
    @ManyToOne(optional = false)
    private Objsecu fkobjs;

    public ObjsAttrs() {
        super();
    }

    public ObjsAttrs(Integer pkattrObjs) {
       super();
       this.pkattrObjs = pkattrObjs;
    }

    public ObjsAttrs(Integer pkattrObjs, String attr, String val) {
        super();
        this.pkattrObjs = pkattrObjs;
        setAttr(attr);
        setVal(val);
    }

    public Integer getPkattrObjs() {
        return pkattrObjs;
    }

    public void setPkattrObjs(Integer pkattrObjs) {
        this.pkattrObjs = pkattrObjs;
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
        hash += (pkattrObjs != null ? pkattrObjs.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ObjsAttrs)) {
            return false;
        }
        ObjsAttrs other = (ObjsAttrs) object;
        if ((this.pkattrObjs == null && other.pkattrObjs != null) || (this.pkattrObjs != null && !this.pkattrObjs.equals(other.pkattrObjs))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "pkattrObjs=" + pkattrObjs + " fkobjs="+fkobjs.getPkobjs()+ " val="+super.getVal()+" attr="+super.getAttr();
    }
    /**
     * commande d'insertion SQL
     * @return
     */
    public String sqlInsert(String schemaa){
        return "MERGE INTO "+schemaa+"OBJS_ATTRS (PKATTR_OBJS, FKOBJS, VAL, ATTR) VALUES ("+pkattrObjs+","+fkobjs.getPkobjs()+",'"+super.getVal()+"','"+super.getAttr()+"')";
    }


}
