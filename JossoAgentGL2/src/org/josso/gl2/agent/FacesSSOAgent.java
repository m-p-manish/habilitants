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

package org.josso.gl2.agent;

//import com.sun.enterprise.security.auth.realm.User;
import org.josso.gl2.agent.jaas.JAASHelper;
import java.security.Principal;
//import java.util.Map;
//import javax.faces.context.FacesContext;
//import javax.servlet.ServletRequest;
//import org.apache.catalina.Context;
import java.util.ArrayList;
import java.util.List;
import org.josso.agent.SSOAgentRequest;
import org.josso.agent.http.HttpSSOAgent;
import org.josso.agent.http.HttpSSOAgentRequest;
import javax.security.auth.Subject;
//import javax.servlet.ServletResponse;
//import org.josso.agent.SingleSignOnEntry;
//import org.josso.agent.http.MutableHttpServletRequestImpl;

import org.apache.catalina.Container;
import org.apache.catalina.Context;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * Agent JOSSO pour Faces et autre servlet
 * @author spopoff@rocketmail.com
 * @version 0.4
 */
public class FacesSSOAgent extends HttpSSOAgent {
    private static final String AUTH_TYPE_INFO_KEY = "javax.servlet.http.authType";
    private JAASHelper jaasHelper;
    private List<String> ssoId = null;
    private List<String[]> groups = null;
    private Container _container;
    private Boolean bStarted = false;
    //private int debug = 1;
    private static final Log logg = LogFactory.getLog(FacesSSOAgent.class);


    public FacesSSOAgent() {
        super();
    }


    public FacesSSOAgent(Container container) {
        super();
        log("*** INFO FacesSSOAgent Création et Mise à jour du container!");
        _container  = container;
        // Add context config as partner app ...
        if (_container instanceof Context) {
            Context context = (Context) _container;
            _cfg.addSSOPartnerApp(context.getPublicId(), null, context.getPath(), null, null);
        }

    }
    @Override
    public void start() {
        if (bStarted){
            log("****INFO Already Started FacesSSOAgent");
            return;
        }
        super.start();
        ssoId = new ArrayList<String>();
        groups = new ArrayList<String[]>();
         if (_container instanceof Context) {
            Context context = (Context) _container;
            _cfg.addSSOPartnerApp(context.getPublicId(), null, context.getPath(), null, null);
        }
        log("****INFO Started FacesSSOAgent once !");
        bStarted = true;
    }
    @Override
    protected Principal authenticate(SSOAgentRequest request) {
        if (debug==1)
            log("Attempting SSO Session authentication FacesSSOAgent.authenticate ");
        try{
            HttpSSOAgentRequest r = (HttpSSOAgentRequest) request;
            jaasHelper = new JAASHelper();

            Subject s = null;
            Principal p = null;
            if (debug > 0)
                log("Using JAASHelper SSOSID : " + r.getSessionId()+" assertId="+r.getAssertionId());
            //authenticate(user, password)
            // If JAAS authentication failed
            if (!jaasHelper.authentif(r.getSessionId(), r.getAssertionId())) {
                return null;
            }
            else {
                log("Info FacesSSOAgent on recheche subjet");
                s = jaasHelper.getSubject();
                if(s==null){
                    System.err.println("Erreur FacesSSOAgent le subject est vide !");
                    return null;
                }
                log("Info FacesSSOAgent on recheche principal");
                p = jaasHelper.getPrincipal();
                if(p==null){
                    System.err.println("Erreur FacesSSOAgent le principal est vide !");
                    return null;
                }
                /*log("Info FacesSSOAgent on ajoute la clé du système d'authentification");
                try {
                MutableHttpServletRequestImpl modifReq = new MutableHttpServletRequestImpl(r.getRequest());
                modifReq.addHeader(AUTH_TYPE_INFO_KEY, "JOSSO");
                } catch (Exception e) {
                System.err.println("Erreur FacesSSOAgent arrive pas ajouter clé "+e.toString());
                }*/            }

            if (debug > 0){
                log("Received Subject : " + s + "[" + ( s != null ? s.getClass().getName() : "<null>" ) +"]");
                //log("Info FacesSSOAgent on cherche le premier principal");
                log("Received Principal : " + p + "[" + ( p != null ? p.getClass().getName() : "<null>" ) +"]");
            }
            return p;
        }catch(Exception err){
            System.err.println("Erreur FacesSSOAgent.authenticate="+err.toString());
            return null;
        }
    }
    public void setInfoUserGroups(String[] grp){
        List<String[]> group2 = new ArrayList<String[]>();
        group2.addAll(groups);
        for(String[] g : group2){
            if(g[0].equals(grp[0])){
                groups.remove(g);
            }
            break;
        }
       groups.add(grp);
    }
    public String[] getInfoUserGroups(String nom){
        String[] ret = null;
        for(String[] g : groups){
            if(g[0].equals(nom)){
                log("Info FacesSSOAgent trouvé user="+nom+" groupe taille="+g.length);
                ret = new String[g.length-1];
                for(int i=1;i<g.length;i++){
                    ret[i-1] = g[i];
                }
                break;
            }
        }
        return ret;
    }
    public void addEntrySSOIDsuccessed(String ssoid){
        log("Info FacesSSOAgent ajoute ssoid="+ssoid);
        ssoId.add(ssoid);
    }
    public Boolean isSSOIDloged(String ssoid){
        Boolean ret = false;
        if(ssoid!=null){
            for(String s : ssoId){
                if(s.equals(ssoid)){
                    ret = true;
                    log("Info FacesSSOAgent trouvé ssoid="+ssoid);
                    break;
                }
            }
        }
        if(!ret) log("Info FacesSSOAgent pas trouvé ssoid="+ssoid);
        return ret;
    }
    /**
     * Sets the Catalina Context to be used by the authenticator.
     *
     * @param container
     */
    public void setCatalinaContainer(Container container) {
        log("****Mise à jour du container CatalinaSSOAgent !");
        _container = container;

    }
    public Container getCatalinaContainer(){
        return _container;
    }
    public String urlLogout(){
        return super.urlLogout1();
    }

    protected void log2(String message) {
        if(_container != null){
            org.apache.catalina.Logger logger = _container.getLogger();
            if (logger != null){
                logger.log(this.toString() + ": " + message);
            }else{
                logg.info(this.toString() + ": " + message);
            }
        } else {
            logg.info(this.toString() + ": " + message);
        }
    }

    protected void log2(String message, Throwable throwable) {
        if(_container != null){
            org.apache.catalina.Logger logger = _container.getLogger();
            if (logger != null){
                logger.log(this.toString() + ": " + message, throwable);
            }else{
                logg.info(this.toString() + ": " + message);
            }
        } else {
            logg.info(this.toString() + ": " + message);
        }
    }
    @Override
    protected void log(String message) {
        logg.info(this.toString() + ": " + message);
    }

    @Override
    protected void log(String message, Throwable throwable) {
        logg.error(this.toString() + ": " + message, throwable);
    }

    /**
     * Return a String rendering of this object.
     */
    @Override
    public String toString() {

        StringBuffer sb = new StringBuffer("FacesSSOAgent[");
        sb.append(_container != null ? _container.getName() : "");
        sb.append("]");
        return (sb.toString());

    }
}
