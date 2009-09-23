<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing IdntCpte</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing IdntCpte</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkidntCpte:"/>
                    <h:outputText value="#{idntCpte.idntCpte.pkidntCpte}" title="PkidntCpte" />
                    <h:outputText value="Fkcpte:"/>
                    <h:selectOneMenu id="fkcpte" value="#{idntCpte.idntCpte.fkcpte}" title="Fkcpte" required="true" requiredMessage="The fkcpte field is required." >
                        <f:selectItems value="#{compte.compteItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Fkidnt:"/>
                    <h:selectOneMenu id="fkidnt" value="#{idntCpte.idntCpte.fkidnt}" title="Fkidnt" required="true" requiredMessage="The fkidnt field is required." >
                        <f:selectItems value="#{identite.identiteItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{idntCpte.edit}" value="Save">
                    <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntCpte.idntCpte][idntCpte.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{idntCpte.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntCpte.idntCpte][idntCpte.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{idntCpte.listSetup}" value="Show All IdntCpte Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
