<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Identite Detail</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Détail d'une identité qui fonctionne</h1>
            En effet on ne charge pas les liaisons sur les attributs, les objets de sécurité, les habilitants<br/><br/>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkidnt:"/>
                    <h:outputText value="#{identite.identite.pkidnt}" title="Pkidnt" />
                    <h:outputText value="Username:"/>
                    <h:outputText value="#{identite.identite.username}" title="Username" />
                    <h:outputText value="Fonction:"/>
                    <h:outputText value="#{identite.identite.fonction}" title="Fonction" />
                    <h:outputText value="Departement:"/>
                    <h:outputText value="#{identite.identite.departement}" title="Departement" />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{identite.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][identite.identite][identite.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{identite.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][identite.identite][identite.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{identite.createSetup}" value="New Identite" />
                <br />
                <h:commandLink action="#{identite.listSetup}" value="Show All Identite Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
