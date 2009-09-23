<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing CpteHblt</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing CpteHblt</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkcpteHblt:"/>
                    <h:outputText value="#{cpteHblt.cpteHblt.pkcpteHblt}" title="PkcpteHblt" />
                    <h:outputText value="Fkcpte:"/>
                    <h:selectOneMenu id="fkcpte" value="#{cpteHblt.cpteHblt.fkcpte}" title="Fkcpte" required="true" requiredMessage="The fkcpte field is required." >
                        <f:selectItems value="#{compte.compteItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Fkhblt:"/>
                    <h:selectOneMenu id="fkhblt" value="#{cpteHblt.cpteHblt.fkhblt}" title="Fkhblt" required="true" requiredMessage="The fkhblt field is required." >
                        <f:selectItems value="#{habilitant.habilitantItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{cpteHblt.edit}" value="Save">
                    <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{cpteHblt.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteHblt.cpteHblt][cpteHblt.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{cpteHblt.listSetup}" value="Show All CpteHblt Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
