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

package techDecision.xacmlPdp;

import an.chopsticks.service.AuditService;
import an.chopsticks.service.AuthorizationService;
import an.chopsticks.service.ServiceManager;
import an.log.LogFactory;
import an.log.Logger;
import java.io.InputStream;
import org.josso.gateway.identity.service.SSOIdentityManagerImpl;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
/**
 * @org.apache.xbean.XBean element="protocol-handler"
 * Beans qui charge la gestion des autorisations xacml
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class XaclmlAutorise implements ApplicationContextAware {
    private Logger logger = null;
    private ServiceManager mgr;
    private AuthorizationService atzSvc;
    private String configFile;
    private SSOIdentityManagerImpl identityManager;
    private ApplicationContext ctxSpring = null;
    AuditService adtSvc;

    public AuditService getAdtSvc() {
        return adtSvc;
    }

    public SSOIdentityManagerImpl getIdentityManager() {
        if(identityManager==null) identityManager = (SSOIdentityManagerImpl) ctxSpring.getBean("josso-identity-manager");
        return identityManager;
    }

    public String getConfigFile() {
        return configFile;
    }

    public void setConfigFile(String configFile) {
        this.configFile = configFile;
    }

    public AuthorizationService getAtzSvc() {
        return atzSvc;
    }

    public XaclmlAutorise(String conFfile) throws Exception {
        System.out.println("Constructeur XaclmlAutorise !"+conFfile);
        String anconfigFile = conFfile;
        configFile = conFfile;
        try {
            System.out.println("config ="+anconfigFile);
            InputStream in = getClass().getResourceAsStream(anconfigFile);
            if(in==null){
                System.err.println("pas trouvé config dans ressource ="+anconfigFile);
                throw new Exception("pas trouvé config dans ressource");
            }
            mgr = ServiceManager.getInstance(anconfigFile, in);
            //System.out.println("logger !");
            //logger = LogFactory.getLogger();
            System.out.println("authorize !");
            atzSvc = (AuthorizationService)mgr.getService(AuthorizationService.class);
            adtSvc = (AuditService)mgr.getService(AuditService.class);
            //atnSvc = (AuthenticationService)mgr.getService(AuthenticationService.class);
            /*System.out.println("session !");
            sessionMgr = SessionManager.getInstance(mgr);*/
            /*if(ctxSpring!=null){
            mgr.setCtxSpring(ctxSpring);
            } else {
            System.err.println("Le context Spring est vide !");
            }*/
            System.out.println("The protection has been enabled");
        } catch (Exception e) {
            String msg = "Error occurs while initialize services, the protection has been disabled "+e.toString();
            if (logger != null) {
                logger.error(msg, e);
            }
            throw new Exception(msg, e);
        }


    }

    public void setApplicationContext(ApplicationContext arg0) throws BeansException {
        ctxSpring = arg0;
    }
}
