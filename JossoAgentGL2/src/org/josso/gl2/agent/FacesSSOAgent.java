/*
Copyright Stéphane Georges Popoff, (octobre 2009)

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

import org.josso.gl2.agent.jaas.JAASHelper;
import java.security.Principal;
import java.util.Map;
import javax.faces.context.FacesContext;
import javax.servlet.ServletRequest;
import org.apache.catalina.Context;
import org.josso.agent.SSOAgentRequest;
import org.josso.agent.http.HttpSSOAgent;
import org.josso.agent.http.HttpSSOAgentRequest;
import javax.security.auth.Subject;
import javax.servlet.ServletResponse;
import org.josso.agent.http.MutableHttpServletRequestImpl;

/**
 * Agent JOSSO pour Faces
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class FacesSSOAgent extends HttpSSOAgent {
    private static final String AUTH_TYPE_INFO_KEY = "javax.servlet.http.authType";


    @Override
    public void start() {
        super.start();
        System.out.println("****Started FacesSSOAgent !");
   }
    @Override
    protected Principal authenticate(SSOAgentRequest request) {
        if (debug==1)
            System.out.println("Attempting SSO Session authentication FacesSSOAgent.authenticate ");
        try{
            HttpSSOAgentRequest r = (HttpSSOAgentRequest) request;
            JAASHelper jaasHelper = new JAASHelper();
            /*Context c = r.getContext();

            if (debug==1)
            System.out.println("contexte CatalinaSSOAgentRequest="+c.toString());*/
            FacesContext fCtx = super.getLeFacesContext(super.getReq(), super.getRep());
            //Context ctx = (Context) fCtx.getExternalContext().getContext();

            Subject s = null;
            Principal p = null;
            if (debug > 0)
                System.out.println("Using JAASHelper SSOSID : " + r.getSessionId()+" assertId="+r.getAssertionId());
            //authenticate(user, password)
            // If JAAS authentication failed
            if (!jaasHelper.authentif(r.getSessionId(), r.getAssertionId())) {
                return null;
            }
            else {
                System.out.println("Info FacesSSOAgent on recheche subjet");
                s = jaasHelper.getSubject();
                if(s==null){
                    System.err.println("Erreur FacesSSOAgent le subject est vide !");
                    return null;
                }
                System.out.println("Info FacesSSOAgent on recheche principal");
                p = jaasHelper.getPrincipal();
                if(p==null){
                    System.err.println("Erreur FacesSSOAgent le principal est vide !");
                    return null;
                }
                System.out.println("Info FacesSSOAgent on ajoute la clé du système d'authentification");
                try {
                    MutableHttpServletRequestImpl modifReq = new MutableHttpServletRequestImpl(r.getRequest());
                    modifReq.addHeader(AUTH_TYPE_INFO_KEY, "jossoRealm");
                } catch (Exception e) {
                    System.err.println("Erreur FacesSSOAgent arrive pas ajouter clé "+e.toString());
                }
                /*System.out.println("Info FacesSSOAgent on recheche sessionMap");
                Map sm = fCtx.getExternalContext().getSessionMap();
                if(sm==null){
                System.err.println("Erreur FacesSSOAgent le sessionmap est vide !");
                return null;
                }
                // Put the authenticated subject in the session.
                System.out.println("Info FacesSSOAgent on ajoute subjet à la session s="+s.toString()+" sm="+sm.toString());
                sm.put("JAASSubject", s);*/
            }

            if (debug > 0){
                System.out.println("Received Subject : " + s + "[" + ( s != null ? s.getClass().getName() : "<null>" ) +"]");
                //System.out.println("Info FacesSSOAgent on cherche le premier principal");
                System.out.println("Received Principal : " + p + "[" + ( p != null ? p.getClass().getName() : "<null>" ) +"]");
            }
            return p;
        }catch(Exception err){
            System.err.println("Erreur FacesSSOAgent.authenticate="+err.toString());
            return null;
        }
    }

}
