/*
Copyright Stéphane Georges Popoff, (novembre - decembre 2009)

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

package techDecision.xacmlPDPurl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.josso.Lookup;
import org.josso.SecurityDomain;
import org.josso.gateway.SSOContext;
import org.josso.gateway.SSOGateway;
import org.josso.spring.SpringComponentKeeperImpl;
import org.springframework.context.ApplicationContext;
import techDecision.xacmlPdp.XaclmlAutorise;


/**
 * Classe qui relance le PDP autorisations xacml pour Strust
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class PdpAutoriseReload extends org.apache.struts.action.Action implements org.josso.gateway.signon.Constants {

    @Override
    public ActionForward execute(ActionMapping mapping,
                                 ActionForm form,
                                 HttpServletRequest request,
                                 HttpServletResponse response) {

       System.out.println("Traite l'uri XACML_URI restart !");
            //on test les requêtes XACML
       XaclmlAutorise xacml = null;
       SSOGateway g = null;
       SSOContext s = null;
       SecurityDomain sd;
        try {
            g = getSSOGateway();
            s = g.prepareDefaultSSOContext();
            sd = s.getSecurityDomain();
        } catch (Exception ex) {
            System.err.println("trouve pas le domaine josso "+ex.toString());
            return null;
        }
       if (sd==null){
            System.err.println("Le domaine josso est vide");
            return null;
       }
        try {
            SpringComponentKeeperImpl cks = (SpringComponentKeeperImpl) Lookup.getInstance().getComponentKeeper();
            ApplicationContext appCtx = cks.getSpringContext();
            xacml = (XaclmlAutorise) appCtx.getBean("chopSticksXacml");
            if(xacml!=null){
                System.out.println("retrouvé le bean chopSticksXacml");
            }else{
                System.err.println("Le bean chopSticksXacml est vide !");
                return null;
            }
        } catch (Exception exception) {
            System.err.println("Erreur récupération bean chopSticksXacml"+exception.toString());
            return null;
        }


        if(xacml!=null){
            System.out.println("recharge les policies !");
            //String ret = null;
            try {
                xacml.reloadPolicies();
            } catch (Exception e) {
                System.err.println("Erreur sur rechargement "+e.toString());
            }
            System.out.println("Policies rechargées");
        }
       return mapping.findForward("xacml.reload");
    }
    protected SSOGateway getSSOGateway() {

        SSOGateway g = (SSOGateway) getServlet().getServletContext().getAttribute(KEY_JOSSO_GATEWAY);

        if (g == null) {

            try {
                g = Lookup.getInstance().lookupSSOGateway();
                getServlet().getServletContext().setAttribute(KEY_JOSSO_GATEWAY, g);
            } catch (Exception e) {
                System.err.println("Cannot get Gateway instance " + e.getMessage());
            }
        }
        return g;
    }
}
