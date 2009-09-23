<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Identite</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Identite</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{identite.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkidnt:"/>
                    <h:inputText id="pkidnt" value="#{identite.identite.pkidnt}" title="Pkidnt" required="true" requiredMessage="The pkidnt field is required." />
                    <h:outputText value="Username:"/>
                    <h:inputText id="username" value="#{identite.identite.username}" title="Username" required="true" requiredMessage="The username field is required." />
                    <h:outputText value="Fonction:"/>
                    <h:inputText id="fonction" value="#{identite.identite.fonction}" title="Fonction" required="true" requiredMessage="The fonction field is required." />
                    <h:outputText value="Departement:"/>
                    <h:inputText id="departement" value="#{identite.identite.departement}" title="Departement" required="true" requiredMessage="The departement field is required." />
                    <h:outputText value="IdntAttrsCollection:"/>
                    <h:selectManyListbox id="idntAttrsCollection" value="#{identite.identite.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].idntAttrsCollection}" title="IdntAttrsCollection" size="6" converter="#{idntAttrs.converter}" >
                        <f:selectItems value="#{idntAttrs.idntAttrsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="IdntCpteCollection:"/>
                    <h:selectManyListbox id="idntCpteCollection" value="#{identite.identite.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].idntCpteCollection}" title="IdntCpteCollection" size="6" converter="#{idntCpte.converter}" >
                        <f:selectItems value="#{idntCpte.idntCpteItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{identite.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{identite.listSetup}" value="Show All Identite Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
