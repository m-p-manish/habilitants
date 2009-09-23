<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>CpteHblt Detail</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>CpteHblt Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkcpteHblt:"/>
                    <h:outputText value="#{cpteHblt.cpteHblt.pkcpteHblt}" title="PkcpteHblt" />
                    <h:outputText value="Fkcpte:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{cpteHblt.cpteHblt.fkcpte}"/>
                        <h:panelGroup rendered="#{cpteHblt.cpteHblt.fkcpte != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{compte.detailSetup}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt.fkcpte][compte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cpteHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CpteHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{compte.editSetup}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt.fkcpte][compte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cpteHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CpteHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{compte.destroy}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt.fkcpte][compte.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cpteHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CpteHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Fkhblt:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{cpteHblt.cpteHblt.fkhblt}"/>
                        <h:panelGroup rendered="#{cpteHblt.cpteHblt.fkhblt != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{habilitant.detailSetup}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt.fkhblt][habilitant.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cpteHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CpteHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{habilitant.editSetup}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt.fkhblt][habilitant.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cpteHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CpteHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{habilitant.destroy}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt.fkhblt][habilitant.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="cpteHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.CpteHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{cpteHblt.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{cpteHblt.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{cpteHblt.createSetup}" value="New CpteHblt" />
                <br />
                <h:commandLink action="#{cpteHblt.listSetup}" value="Show All CpteHblt Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
