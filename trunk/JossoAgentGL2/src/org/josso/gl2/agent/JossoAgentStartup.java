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

package org.josso.gl2.agent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletContextEvent;
import org.josso.agent.Lookup;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Initialisation de 'agent sur le startup de l'application
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class JossoAgentStartup implements ServletContextListener {
    private FacesSSOAgent _agent;
    private static final Log logg = LogFactory.getLog(JossoAgentStartup.class);

    public void contextInitialized(ServletContextEvent ctx) {
        logg.info("** JossoAgentStartup Initialisation du context="+ctx.getServletContext().getContextPath());
        if (_agent == null) {

            try {

                Lookup lookup = Lookup.getInstance();
                lookup.init("josso-agent2-config.xml", ctx.getServletContext().getContextPath());
                _agent = (FacesSSOAgent) lookup.lookupSSOAgent();
                _agent.start();
                _agent.setDebug(1);
                _agent.setsCtx(ctx.getServletContext());
            } catch (Exception e) {
                logg.error("** JossoAgentStartup Erreur Initialisation du (debut) context=",e);
            }
        }

        logg.info("** Fin Initialisation du context="+ctx.getServletContext().getContextPath());
    }

    public void contextDestroyed(ServletContextEvent arg0) {
        _agent = null;
    }

}
