/*
Copyright Stéphane Georges Popoff, (février - juin 2009)

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
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.2
 */
@MappedSuperclass
public class Attributs implements Serializable {
    @Basic(optional = false)
    @Column(name = "ATTR", nullable = false, length = 10)
    private String attr;
    @Basic(optional = false)
    @Column(name = "VAL", nullable = false, length = 150)
    private String val;
    public Attributs(){
        super();
    }
    public String getAttr() {
        return attr;
    }

    public void setAttr(String attr) {
        int l = attr.length();
        if(l>9) l=9;
        this.attr = attr.substring(0,l);
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        int l = val.length();
        if(l>149) l=149;
        this.val = val.substring(0,l);
    }
    @Override
    public String toString(){
        return "["+attr+"="+val+"]";
    }

}
