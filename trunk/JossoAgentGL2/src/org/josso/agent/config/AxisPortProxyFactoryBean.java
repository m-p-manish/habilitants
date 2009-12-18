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

package org.josso.agent.config;

import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.encoding.TypeMapping;
import javax.xml.rpc.encoding.TypeMappingRegistry;
import org.apache.axis.encoding.ser.BeanDeserializerFactory;
import org.apache.axis.encoding.ser.BeanSerializerFactory;
import org.springframework.remoting.jaxrpc.JaxRpcPortProxyFactoryBean;
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
import os.schema.context._0._2.xacml.tc.names.oasis.StatusCodeType;

/**
 * Enregistrement programmatique des serialiseur/désérialiseur pour axis/beans
 * @author spopoff@rocketmail.com
 * @version 0.1
 */
public class AxisPortProxyFactoryBean extends JaxRpcPortProxyFactoryBean {

    @Override
    protected void postProcessJaxRpcService(Service service) {
        TypeMappingRegistry registry = service.getTypeMappingRegistry();
        TypeMapping mapping = registry.createTypeMapping();
        registerBeanMapping(mapping, RequestDocument.class, "RequestDocument");         //1
        registerBeanMapping(mapping, ResponseDocument.class, "ResponseDocument");       //2
        registerBeanMapping(mapping, ActionType.class, "ActionType");                   //3
        registerBeanMapping(mapping, AttributeType.class, "AttributeType");             //4
        registerBeanMapping(mapping, AttributeValueType.class, "AttributeValueType");   //5
        registerBeanMapping(mapping, DecisionType.class, "DecisionType");               //6
        registerBeanMapping(mapping, RequestType.class, "RequestType");                 //7
        registerBeanMapping(mapping, ResourceType.class, "ResourceType");               //8
        registerBeanMapping(mapping, ResponseType.class, "ResponseType");               //9
        registerBeanMapping(mapping, ResultType.class, "ResultType");                   //10
        registerBeanMapping(mapping, SubjectType.class, "SubjectType");                 //11
        registerBeanMapping(mapping, StatusCodeType.class, "StatusCodeType");           //12
        registry.register("http://schemas.xmlsoap.org/soap/encoding/", mapping);
    }

    protected void registerBeanMapping(TypeMapping mapping, Class type, String name) {
        QName qName = new QName("http://localhost:8082/josso/services/xacmlWSpdp", name);
        mapping.register(type, qName,
                new BeanSerializerFactory(type, qName),
                new BeanDeserializerFactory(type, qName));
    }
}