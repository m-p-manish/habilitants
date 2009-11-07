/*
Copyright Stéphane Georges Popoff, (novembre 2009)

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

package theCube.josso;

import java.io.IOException;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletResponse;
import org.josso.gl2.agent.FacesSSOAgent;
import org.apache.log4j.Logger;
import org.josso.agent.Lookup;

/**
 * Bean pour faces qui cause avec l'agent Josso
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class JossoInsideFaces {
    private static org.apache.log4j.Logger logg = Logger.getLogger(JossoInsideFaces.class);
    private String etatAgent = null;
    private String logoutUrl = null;

    public String getLogoutUrl() {
        return logoutUrl;
    }

    public void setLogoutUrl(String logoutUrl) {
        this.logoutUrl = logoutUrl;
    }

    public String getEtatAgent() {
        return etatAgent;
    }

    public void setEtatAgent(String etatAgent) {
        this.etatAgent = etatAgent;
    }

    /** Creates a new instance of JossoInsideFaces */
    public JossoInsideFaces() {
        FacesSSOAgent _agent = null;
        FacesContext facesContext = null;
        ExternalContext externalContext = null;
        //ce serait le bon endroit pour valider la présence de l'agent
        logg.info("** JossoInsideFaces Initialisation ...");
        etatAgent = "on cherche ...";
        if (_agent == null) {

            try {
                facesContext = FacesContext.getCurrentInstance();
                externalContext = facesContext.getExternalContext();
                Lookup lookup = Lookup.getInstance();
                lookup.init("josso-agent2-config.xml", externalContext.toString());
                _agent = (FacesSSOAgent) lookup.lookupSSOAgent();
                //_agent.setsCtx(externalContext);
                etatAgent = "démarré";
                logoutUrl = _agent.urlLogout();
                if(logoutUrl==null){
                    logg.info("** JossoInsideFaces trouve pas la valeur de logout url!");
                }else{
                    logg.info("** JossoInsideFaces logout url = "+logoutUrl);
                }
            } catch (Exception e) {
                logg.error("** JossoInsideFaces Erreur Initialisation du (debut) context=",e);
                etatAgent = "erreur";
            } finally{
                _agent = null;
            }
        }

        logg.info("** JossoInsideFaces Fin Initialisation du context="+externalContext.toString());
    }
    public String terminer(){
        FacesContext context = FacesContext.getCurrentInstance();
        ExternalContext external = context.getExternalContext();
        //HttpServletResponse response = (HttpServletResponse) external.getResponse();
        try {
            external.redirect(logoutUrl);
        } catch (IOException ex) {
            logg.error(JossoInsideFaces.class.getName(), ex);
            return "logout";
        }
        return null;
    }
}
