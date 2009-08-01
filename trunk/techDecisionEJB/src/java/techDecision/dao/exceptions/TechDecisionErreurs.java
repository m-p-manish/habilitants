/*
Copyright Stéphane Georges Popoff, (février -  juillet 2009)

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
package techDecision.dao.exceptions;
import javax.ejb.ApplicationException;

/**
 *
 * @author spopoff@rocketmail.com
 * @version 0.3
 */
@ApplicationException(rollback=true)
public class TechDecisionErreurs extends RuntimeException {
    private static final long serialVersionUID = 1L;
    private int code = 0;
    public TechDecisionErreurs(){
        super();
        System.err.println("Nouvelle erreur TechDecisionErreurs");
    }
    public TechDecisionErreurs(String message){
        super(message);
        System.err.println("Nouvelle erreur TechDecisionErreurs message="+message);
    }
    public TechDecisionErreurs(String message, Throwable cause){
        super(message, cause);
        System.err.println("Nouvelle erreur TechDecisionErreurs avec cause message="+message);
    }
    public TechDecisionErreurs(Throwable cause){
        super(cause);
        System.err.println("Nouvelle erreur TechDecisionErreurs avec cause ");
    }
    public TechDecisionErreurs(String message, int code){
        super(message);
        setCode(code);
        System.err.println("Nouvelle erreur TechDecisionErreurs avec code="+code);
    }
    public TechDecisionErreurs(Throwable cause, int code){
        super(cause);
        setCode(code);
    }
    public TechDecisionErreurs(String message, Throwable cause, int code){
        super(message, cause);
        setCode(code);
        System.err.println("Nouvelle erreur TechDecisionErreurs avec cause et code="+code);
    }
    public void setCode(int val){
        code = val;
    }
    public int getCode(){
        return code;
    }
    @Override
    public String toString(){
        return super.toString() + " code="+code;
    }
}
