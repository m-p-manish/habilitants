/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package wsxacmlpdpclient;

import org.apache.xmlbeans.XmlObject;
import org.apache.xmlbeans.impl.values.JavaStringHolder;
import os.schema.context._0._2.xacml.tc.names.oasis.ActionType;
import os.schema.context._0._2.xacml.tc.names.oasis.AttributeType;
import os.schema.context._0._2.xacml.tc.names.oasis.AttributeValueType;
import os.schema.context._0._2.xacml.tc.names.oasis.RequestDocument;
import os.schema.context._0._2.xacml.tc.names.oasis.RequestType;
import os.schema.context._0._2.xacml.tc.names.oasis.ResourceType;
import os.schema.context._0._2.xacml.tc.names.oasis.ResponseDocument;
import os.schema.context._0._2.xacml.tc.names.oasis.ResponseType;
import os.schema.context._0._2.xacml.tc.names.oasis.SubjectType;
import techDecision.ws.client.PdpServiceStub;

/**
 *
 * @author spopoff
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.out.println("test");
        PdpServiceStub stub = null;
        try {
            stub = new PdpServiceStub("http://localhost:8082/josso/services/PdpService");
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
            String xml = "<AttributeValue>spo</AttributeValue>";
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
            xml = "<AttributeValue>maison</AttributeValue>";
            x = XmlObject.Factory.parse(xml);
            homeV.set(x);
            /*<Action>
                <Attribute
                AttributeId="urn:oasis:names:tc:xacml:1.0:action:action-id"
                DataType="http://www.w3.org/2001/XMLSchema#string">
                    <AttributeValue>read</AttributeValue>*/
            ActionType a = rt.addNewAction();
            AttributeType acte = a.addNewAttribute();
            nom.setAttributeId("urn:oasis:names:tc:xacml:1.0:subject:action-id");
            nom.setDataType("http://www.w3.org/2001/XMLSchema#string");
            AttributeValueType acteV = home.addNewAttributeValue();
            xml = "<AttributeValue>entrer</AttributeValue>";
            x = XmlObject.Factory.parse(xml);
            acteV.set(x);
            ResponseDocument rep = stub.getDecision(req);
            if(rep==null){
                System.err.println("réponse vide "+rep.getClass().toString());
            }else {
                System.out.println("résultat ="+rep.toString());
            }
            
        }catch(Exception e){
            System.err.println("Erreur = "+e.toString());
        }

   }

}
