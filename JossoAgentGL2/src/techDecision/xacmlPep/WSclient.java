/*
Copyright Stéphane Georges Popoff, (décembre 2009)

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

package techDecision.xacmlPep;

import org.apache.xmlbeans.XmlObject;
import os.schema.context._0._2.xacml.tc.names.oasis.ActionType;
import os.schema.context._0._2.xacml.tc.names.oasis.AttributeType;
import os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType;
import os.schema.context._0._2.xacml.tc.names.oasis.DecisionType;
import os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument;
import os.schema.context._0._2.xacml.tc.names.oasis.RequestType;
import os.schema.context._0._2.xacml.tc.names.oasis.ResourceType;
import os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument;
import os.schema.context._0._2.xacml.tc.names.oasis.ResponseType;
import os.schema.context._0._2.xacml.tc.names.oasis.ResultType;
import os.schema.context._0._2.xacml.tc.names.oasis.SubjectType;

/**
 * classe qui porte le service PEP Decision et pose la question getDecision (opération soap)
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class WSclient implements WSclientInterface {
    PdpServiceSkeletonInterface xacmlWSpdp;
    String nom;

    public WSclient(){
        System.out.println("Initialisation WSclient pep autorise");
    }

    public void setXacmlWSpdp(PdpServiceSkeletonInterface xacmlWSpdp) {
        this.xacmlWSpdp = xacmlWSpdp;
        System.out.println("Récupère référence sur web service xacmlWSpdp");
    }

    public String simpleQuestionService(String user, String ress, String akte){
        String reponse = "Sans";
        try {
            RequestDocument req = RequestDocument.Factory.newInstance();
            RequestType rt = req.addNewRequest();
            SubjectType s = rt.addNewSubject();
            /*<Subject>
                <Attribute
                AttributeId="urn:oasis:names:tc:xacml:1.0:subject:subject-id"
                DataType="http://www.w3.org/2001/XMLSchema#string">
                    <AttributeValue>Julius Hibbert</AttributeValue>*/
            AttributeType nom = s.addNewAttribute();
            nom.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:subject-id");
            nom.setDataType("http://www.w3.org/2001/XMLSchema#string");
            //AttributeValueType nomV = nom.addNewAttributeValue();
            String xml = "<AttributeValue>"+user+"</AttributeValue>";
            XmlObject x = XmlObject.Factory.parse(xml);
            nom.set(x);
            /*<Resource>
                <Attribute
                AttributeId="urn:oasis:names:tc:xacml:1.0:resource:resource-id"
                DataType="http://www.w3.org/2001/XMLSchema#anyURI">
                    <AttributeValue>http://medico.com/record/patient/BartSimpson</AttributeValue>*/
            ResourceType r = rt.addNewResource();
            AttributeType home = r.addNewAttribute();
            nom.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:resource-id");
            nom.setDataType("http://www.w3.org/2001/XMLSchema#string");
            AttributeValueType homeV = home.addNewAttributeValue();
            xml = "<AttributeValue>"+ress+"</AttributeValue>";
            x = XmlObject.Factory.parse(xml);
            homeV.set(x);
            /*<Action>
                <Attribute
                AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id"
                DataType="http://www.w3.org/2001/XMLSchema#string">
                    <AttributeValue>read</AttributeValue>*/
            ActionType a = rt.addNewAction();
            AttributeType acte = a.addNewAttribute();
            acte.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:action-id");
            acte.setDataType("http://www.w3.org/2001/XMLSchema#string");
            AttributeValueType acteV = acte.addNewAttributeValue();
            xml = "<AttributeValue>"+akte+"</AttributeValue>";
            x = XmlObject.Factory.parse(xml);
            acteV.set(x);
            ResponseDocument rep = xacmlWSpdp.getDecision(req);
            if(rep==null){
                System.err.println("réponse vide "+rep.getClass().toString());
                reponse = "Vide";
            }else {
                System.out.println("résultat ="+rep.toString());
                ResponseType rpt = rep.getResponse();
                ResultType rat = rpt.getResultArray(0);
                if(rat.getDecision().equals(DecisionType.DENY)) reponse = "Refus" ;
                if(rat.getDecision().equals(DecisionType.INDETERMINATE)) reponse = "Indéterminé" ;
                if(rat.getDecision().equals(DecisionType.NOT_APPLICABLE)) reponse = "Pas applicable" ;
                if(rat.getDecision().equals(DecisionType.PERMIT)) reponse = "Permis" ;
            }

        }catch(Exception e){
            System.err.println("Erreur = "+e.toString());
        }

        return reponse;
    }

    public String getNomPdp() {
        return nom;
    }

    public void setNomPdp(String nom) {
        this.nom = nom;
    }
}
