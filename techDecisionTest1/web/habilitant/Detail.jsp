<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Habilitant Detail</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Habilitant Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkhblt:"/>
                    <h:outputText value="#{habilitant.habilitant.pkhblt}" title="Pkhblt" />
                    <h:outputText value="Val:"/>
                    <h:outputText value="#{habilitant.habilitant.val}" title="Val" />
                    <h:outputText value="Type:"/>
                    <h:outputText value="#{habilitant.habilitant.type}" title="Type" />
                    <h:outputText value="ObjsHbltCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty habilitant.habilitant.objsHbltCollection}" value="(No Items)"/>
                        <h:dataTable value="#{habilitant.habilitant.objsHbltCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty habilitant.habilitant.objsHbltCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PkobjsHblt"/>
                                </f:facet>
                                <h:outputText value=" #{item.pkobjsHblt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkhblt"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkhblt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkobjs"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkobjs}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{objsHblt.detailSetup}">
                                    <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][habilitant.habilitant][habilitant.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="habilitant" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.HabilitantController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{objsHblt.editSetup}">
                                    <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][habilitant.habilitant][habilitant.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="habilitant" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.HabilitantController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{objsHblt.destroy}">
                                    <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][habilitant.habilitant][habilitant.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="habilitant" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.HabilitantController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{habilitant.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][habilitant.habilitant][habilitant.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{habilitant.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][habilitant.habilitant][habilitant.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{habilitant.createSetup}" value="New Habilitant" />
                <br />
                <h:commandLink action="#{habilitant.listSetup}" value="Show All Habilitant Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
