/*
Copyright Stéphane Georges Popoff, (octobre - novembre 2009)

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
import techDecision.xacmlPdp.*;
import an.chopsticks.service.Attribute;
import an.chopsticks.service.AttributeType;
import an.chopsticks.service.AuthorizationResult;
import an.chopsticks.service.Context;
import an.chopsticks.service.Decision;
import an.chopsticks.service.DefaultContextImpl;
import an.chopsticks.service.Resource;
import an.chopsticks.service.Subject;
import an.chopsticks.service.AuthorizationFailedException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.josso.Lookup;
import org.josso.SecurityDomain;
import org.josso.gateway.Constants;
import org.josso.gateway.SSOGateway;
import org.josso.gateway.identity.exceptions.NoSuchDomainException;
import org.josso.gateway.session.SSOSession;
import org.josso.gateway.session.exceptions.NoSuchSessionException;
import org.josso.gateway.session.service.SSOSessionManager;
import org.josso.spring.SpringComponentKeeperImpl;
import org.springframework.context.ApplicationContext;

/**
 * Action module Srust pour évaluer une requête XACML
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
 public class PdpStrustDecision extends org.apache.struts.action.Action implements org.josso.gateway.signon.Constants {

        static final String USER = "j_username";
        static final String PASS = "j_password";
        static final String PARAM_MESSAGE = "an.Message";
        @Override
        public ActionForward execute(ActionMapping mapping,
                                     ActionForm form,
                                     HttpServletRequest request,
                                     HttpServletResponse response) {

       System.out.println("Traite l'uri XACML_URI !");
       ActionForward ret = null;
            //on test les requêtes XACML
       XaclmlAutorise xacml = null;
       SSOGateway g = getSSOGateway();
       SecurityDomain sd;
        try {
            sd = g.getSecurityDomain("josso");
        } catch (NoSuchDomainException ex) {
            System.err.println("trouve pas le domaine josso "+ex.toString());
            return mapping.findForward("xacml.erreur");
        }
        try {
            SpringComponentKeeperImpl cks = (SpringComponentKeeperImpl) Lookup.getInstance().getComponentKeeper();
            ApplicationContext appCtx = cks.getSpringContext();
            xacml = (XaclmlAutorise) appCtx.getBean("chopSticksXacml");
            if(xacml!=null){
                System.out.println("retrouvé le bean chopSticksXacml");
            }else{
                System.err.println("Le bean chopSticksXacml est vide !");
            }
        } catch (Exception exception) {
            System.err.println("Erreur récupération bean chopSticksXacml"+exception.toString());
        }
        if(xacml!=null){
            //on peut faire un contrôle de la requête pour savoir si elle contient du xacml
            String ress = request.getParameter("ress");
            String acte = request.getParameter("acte");
            System.out.println("Resource=" + ress +";Action=" + acte);
            Resource[] resources = new Resource[]{new Resource(ress)};
            //alors on valide
            try {
                an.chopsticks.service.Action action = new an.chopsticks.service.Action(acte);
                Context ctxe = createContextFromRequest(request);
                if(ctxe==null){
                    System.err.println("Erreur contexte Vide");
                    return mapping.findForward("xacml.erreur");
                }
                SSOSession sess = getJossoSession(request, sd);
                if(ctxe==null){
                    System.err.println("Erreur josso session Vide");
                    return mapping.findForward("xacml.erreur");
                }
                Subject subject = new Subject();
                subject.setSubjecName(sess.getUsername());
                Subject[] subjects = new Subject[]{subject};
                System.out.println("Subject=" + subject.getSubjectName() + ";Resource=" + ress +
                        ";Action=" + action.getActionName());
                AuthorizationResult result = null;
                result = doAuthorization(subjects, resources, action, ctxe, xacml);
                if(result==null){
                    System.err.println("Erreur resultat autorisation Vide");
                    return mapping.findForward("xacml.erreur");
                }
                Decision decision = result.getDecision();
                if(result==null){
                    System.err.println("Erreur decision Vide");
                    return mapping.findForward("xacml.erreur");
                }
                String msg = result.getMessage();
                Context responseCtx = result.getResponseContext();
                System.out.println("Decsion=" + decision + ";Subject=" + subject.getSubjectName() + ";Resource=" + ress +
                        ";Action=" + action.getActionName() +
                        (responseCtx == null ? "" : ";ResponseContext=[" + responseCtx + "]"));

                // Set the returned message to request
                if (msg != null) {
                    System.out.println("\n" + msg);
                    request.setAttribute(PARAM_MESSAGE, msg);
                }
                // Set the response context's attributes to request
                if (responseCtx != null) {
                    Collection<Attribute> attrs = responseCtx.getAllAttributes();
                    for (Attribute each : attrs) {
                        request.setAttribute(
                                each.getName(), each.isArray() ? each.getArrayValueAsString() : each.getValue());
                    }
                }

                if (decision == Decision.Permit) {
                    System.out.println("Resources '" + ress + "' was permit to be access.");
                    return mapping.findForward("xacml.autorise");
                }
                else {
                    String atzFailMsg = "Resources '" + ress + "' was not permit to be access.";
                    System.out.println(atzFailMsg);
                    request.setAttribute(PARAM_MESSAGE, atzFailMsg);
                    return mapping.findForward("xacml.interdit");
                }
            } catch (Exception ex) {
                System.err.println("Erreur dans test XACML "+ex.toString());
                return mapping.findForward("xacml.erreur");
            }
        }
       return mapping.findForward("xacml.autorise");
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
    protected boolean existJossoSession(HttpServletRequest request, SecurityDomain sd) throws Exception {
        String jossoSessionId = getJossoSessionId(request);

        if (jossoSessionId == null)
            return false;

        SSOSessionManager ssoSessionManager = sd.getSessionManager();

        try {
            SSOSession s = ssoSessionManager.getSession(jossoSessionId);
            if (s != null && s.isValid())
                return true;

        } catch (NoSuchSessionException nsse) {

            HttpSession ssn = request.getSession(true);
            // TODO : FIXME This component should not be boud to NTLM!
            /*
            if (ssn.getAttribute(NtlmProtocolHandler.NTLM_PASS_AUTHENTICATION) != null)
                ssn.removeAttribute(NtlmProtocolHandler.NTLM_PASS_AUTHENTICATION); */

            if (ssn.getAttribute( "ntlmHttpPa" ) != null)
                ssn.removeAttribute( "ntlmHttpPa" );
        }

        return false;
    }
    protected SSOSession getJossoSession(HttpServletRequest request, SecurityDomain sd) throws Exception {
        String jossoSessionId = getJossoSessionId(request);

        if (jossoSessionId == null)
            return null;

        SSOSessionManager ssoSessionManager = sd.getSessionManager();

        try {
            SSOSession s = ssoSessionManager.getSession(jossoSessionId);
            if (s != null && s.isValid())
                return s;

        } catch (NoSuchSessionException nsse) {

        }

        return null;
    }

    // ----------------------------------------------------- methods
    protected String getJossoSessionId(HttpServletRequest request) {
        Cookie c = getJossoCookie(request);
        if (c != null)
            return c.getValue();

        return null;
    }

    protected Cookie getJossoCookie(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies == null)
            return null;

        for (int i = 0; i < cookies.length; i++) {
            Cookie cookie = cookies[i];
            if (cookie.getName().equals(Constants.JOSSO_SINGLE_SIGN_ON_COOKIE)) {
                return cookie;
            }
        }
        return null;
    }
    /**
     * Fait un tableau de l'uri
     * @param uri
     * @return un tableau
     */
    private Resource[] processResource(String uri) {
        String splitter = "/";
        List<Resource> result = new ArrayList<Resource>();
        // Trim the ending "/"
        while (uri.endsWith(splitter)) {
            uri = uri.substring(0, uri.length() - 1);
        }
        if (uri.equals("")) {
            return new Resource[] {new Resource(splitter)};
        }

        result.add(new Resource(uri));
        int pos = uri.lastIndexOf(splitter);
        while (pos > 0) {
            uri = uri.substring(0, pos);
            result.add(new Resource(uri));
            pos = uri.lastIndexOf(splitter);
        }
        // Add the root finally.
        result.add(new Resource(splitter));
        return result.toArray(new Resource[0]);
    }
    @SuppressWarnings("unchecked")
    private Context createContextFromRequest(HttpServletRequest req) {
        Enumeration e = req.getParameterNames();
        if (e != null) {
            Context ctx = new DefaultContextImpl();
            while (e.hasMoreElements()) {
                String name = (String)e.nextElement();
                if (!name.equals(USER) && !name.equals(PASS)) {
                    try {
                        // Test if it is an valid URI, otherwise, we don't add it to context.
                        new URI(name);
                        ctx.setAttribute(new Attribute(name, AttributeType.String, req.getParameter(name)));
                    } catch (URISyntaxException e1) {
                        continue;
                    }
                }
            }
            return ctx;
        }
        return null;
    }
    private AuthorizationResult doAuthorization(Subject[] subjs, Resource[] reses, an.chopsticks.service.Action action, Context ctx, XaclmlAutorise xacml)
    throws AuthorizationFailedException {
        AuthorizationResult[] result = null;
        for (Resource res : reses) {
            result = xacml.getAtzSvc().authorize(subjs, new Resource[] {res}, action, ctx);
            if (result[0].getDecision() == Decision.Permit) {
                return result[0];
            }
        }
        return result == null ? null : result[0];
    }
}
