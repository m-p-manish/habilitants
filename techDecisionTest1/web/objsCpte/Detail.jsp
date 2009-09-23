<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Liaison Objet de sécurité et Compte</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Liaison Objet de sécurité et Compte</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkobjsCpte:"/>
                    <h:outputText value="#{objsCpte.objsCpte.pkobjsCpte}" title="PkobjsCpte" />
                    <h:outputText value="Fkcpte:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{objsCpte.objsCpte.fkcpte}"/>
                        <h:panelGroup rendered="#{objsCpte.objsCpte.fkcpte != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{compte.detailSetup}">
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte.fkcpte][compte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsCpte"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsCpteController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{compte.editSetup}">
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte.fkcpte][compte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsCpte"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsCpteController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{compte.destroy}">
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte.fkcpte][compte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsCpte"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsCpteController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Fkobjs:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{objsCpte.objsCpte.fkobjs}"/>
                        <h:panelGroup rendered="#{objsCpte.objsCpte.fkobjs != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{objsecu.detailSetup}">
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsCpte"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsCpteController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{objsecu.editSetup}">
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsCpte"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsCpteController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{objsecu.destroy}">
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsCpte"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsCpteController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{objsCpte.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{objsCpte.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{objsCpte.createSetup}" value="New ObjsCpte" />
                <br />
                <h:commandLink action="#{objsCpte.listSetup}" value="Show All ObjsCpte Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
