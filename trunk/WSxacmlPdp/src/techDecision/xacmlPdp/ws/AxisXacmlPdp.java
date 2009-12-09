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

package techDecision.xacmlPdp.ws;

import an.chopsticks.service.Action;
import an.chopsticks.service.AuthorizationFailedException;
import an.chopsticks.service.AuthorizationResult;
import an.chopsticks.service.AuthorizationService;
import an.chopsticks.service.Context;
import an.chopsticks.service.Decision;
import an.chopsticks.service.Resource;
import an.chopsticks.service.Subject;
import org.apache.xmlbeans.XmlBeans;
import org.josso.Lookup;
import org.josso.spring.SpringComponentKeeperImpl;
import techDecision.xacmlPdp.XaclmlAutorise;
import org.springframework.context.ApplicationContext;
import os.schema.context._0._2.xacml.tc.names.oasis.ActionType;
import os.schema.context._0._2.xacml.tc.names.oasis.DecisionType;
import os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument;
import os.schema.context._0._2.xacml.tc.names.oasis.RequestType;
import os.schema.context._0._2.xacml.tc.names.oasis.ResourceType;
import os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument;
import os.schema.context._0._2.xacml.tc.names.oasis.ResponseType;
import os.schema.context._0._2.xacml.tc.names.oasis.ResultType;
import os.schema.context._0._2.xacml.tc.names.oasis.SubjectType;
import os.schema.context._0._2.xacml.tc.names.oasis.impl.StatusCodeTypeImpl;
/**
 * classe qui porte le service PDP Decision et répond à getDecision (opération soap)
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class AxisXacmlPdp implements PdpServiceSkeletonInterface {
    private XaclmlAutorise xacml = null;

    private AuthorizationService atzSvc;
    private Subject[] subjects;
    private Resource[] resources;
    private Action action;
    private Context context;
    private Boolean init = false;

    public AxisXacmlPdp(){
       System.out.println("initialise web Service PDP autorise !");
            //on test les requêtes XACML
        try {
            SpringComponentKeeperImpl cks = (SpringComponentKeeperImpl) Lookup.getInstance().getComponentKeeper();
            ApplicationContext appCtx = cks.getSpringContext();
            xacml = (XaclmlAutorise) appCtx.getBean("chopSticksXacml");
            if(xacml!=null){
                System.out.println("retrouvé le bean chopSticksXacml");
            }else{
                System.err.println("Le bean chopSticksXacml est vide !");
                return;
            }
        } catch (Exception exception) {
            System.err.println("Erreur récupération bean chopSticksXacml "+exception.toString());
            return;
        }
        if(xacml!=null){
            atzSvc=xacml.getAthzSvc();
            init = true;
        }
    }
    /*public void setAtzSvc(AuthorizationService atzSvc) {
    this.atzSvc = atzSvc;
    }*/
    public ResponseDocument getDecision(RequestDocument request) {
        ResponseDocument ret = null;
        if(!init){
            System.err.println("Erreur getDecision initialisation service échouée");
            try {
                //throw org.apache.axis2.AxisFault.makeFault(e);
                ret = ResponseDocument.Factory.newInstance();
                ResponseType rep = ret.addNewResponse();
                ResultType rest = rep.addNewResult();
                rest.setDecision(DecisionType.NOT_APPLICABLE);
                System.out.println("Réponse sur erreur="+ret.toString());
                return ret;
            } catch (Exception ex) {
                System.err.println("Erreur getDecision fabrication réponse erreur init "+ex.toString());
            }
        }
        RequestType req = request.getRequest();
        ActionType acte = req.getAction();
        /*<Action>
            <Attribute
                  AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id"
                  DataType="http://www.w3.org/2001/XMLSchema#string">
                <AttributeValue>read</AttributeValue>
        */
        action = new Action(acte.getAttributeArray(0).getAttributeValueArray(0).toString());
        ResourceType[] ress = req.getResourceArray();
        /*<Resource>
            <Attribute
            AttributeId="urn:oasis:names:tc:xacml:1.0:resource:resource-id"
            DataType="http://www.w3.org/2001/XMLSchema#anyURI">
                <AttributeValue>http://medico.com/record/patient/BartSimpson</AttributeValue>*/
        resources = new Resource[ress.length];
        int i = 0;
        for(ResourceType res : ress){
            resources[i] = new Resource(res.getAttributeArray(0).getAttributeValueArray(0).toString());
            i++;
        }
        /*<Subject>
            <Attribute
            AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id"
            DataType="http://www.w3.org/2001/XMLSchema#string">
                <AttributeValue>Julius Hibbert</AttributeValue>
         */
        SubjectType[] sbjs = req.getSubjectArray();
        subjects = new Subject[sbjs.length];
        i = 0;
        for(SubjectType sbj : sbjs){
            Subject s = new Subject();
            s.setSubjecName(sbj.getAttributeArray(0).getAttributeValueArray(0).toString());
            subjects[i] = s;
            i++;
        }
        /*<Response
            xmlns="urn:oasis:names:tc:xacml:1.0:context"
            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
            xsi:schemaLocation="urn:oasis:names:tc:xacml:1.0:context
            cs-xacml-schema-context-01.xsd">
            <Result>
                <Decision>Permit</Decision>
                <Status>
                    <StatusCode
                    Value="urn:oasis:names:tc:xacml:1.0:status:ok"/>
                </Status>*/
        AuthorizationResult[] aresss = null;
        ResponseType rep = ret.addNewResponse();
        try {
            aresss = atzSvc.authorize(subjects, resources, action, context);
            Decision dec = aresss[0].getDecision();
            ResultType rest = rep.addNewResult();
            if(dec==Decision.Permit) rest.setDecision(DecisionType.PERMIT);
            if(dec==Decision.Deny) rest.setDecision(DecisionType.DENY);
            if(dec==Decision.Indeterminate) rest.setDecision(DecisionType.INDETERMINATE);
            if(dec==Decision.NotApplicable) rest.setDecision(DecisionType.NOT_APPLICABLE);
            rest.addNewStatus();
            StatusCodeTypeImpl ssc = new StatusCodeTypeImpl(XmlBeans.NO_TYPE);
            ssc.setValue(Constants.STATUS_OK);
            rest.getStatus().setStatusCode(ssc);
        } catch (AuthorizationFailedException ex) {
            System.err.println("Erreur sur décision "+ex.toString());
            ResultType rest = rep.addNewResult();
            rest.setDecision(DecisionType.INDETERMINATE);
            rest.addNewStatus();
            StatusCodeTypeImpl ssc = new StatusCodeTypeImpl(XmlBeans.NO_TYPE);
            ssc.setValue(Constants.STATUS_PROCESSING_ERROR);
            rest.getStatus().setStatusCode(ssc);
        }
        return ret;
    }

}
