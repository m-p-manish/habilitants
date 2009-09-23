<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Identite</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Identite</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkidnt:"/>
                    <h:outputText value="#{identite.identite.pkidnt}" title="Pkidnt" />
                    <h:outputText value="Username:"/>
                    <h:inputText id="username" value="#{identite.identite.username}" title="Username" required="true" requiredMessage="The username field is required." />
                    <h:outputText value="Fonction:"/>
                    <h:inputText id="fonction" value="#{identite.identite.fonction}" title="Fonction" required="true" requiredMessage="The fonction field is required." />
                    <h:outputText value="Département:"/>
                    <h:inputText id="departement" value="#{identite.identite.departement}" title="Departement" required="true" requiredMessage="The departement field is required." />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{identite.edit}" value="Save">
                    <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][identite.identite][identite.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{identite.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][identite.identite][identite.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{identite.listSetup}" value="Show All Identite Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
