<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Détail de la liaison Identité & Compte</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Liaison des comptes et des identités</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkidntCpte:"/>
                    <h:outputText value="#{idntCpte.idntCpte.pkidntCpte}" title="PkidntCpte" />
                    <h:outputText value="Fkcpte:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{idntCpte.idntCpte.fkcpte}"/>
                    </h:panelGroup>
                    <h:outputText value="Fkidnt:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{idntCpte.idntCpte.fkidnt}"/>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{idntCpte.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntCpte.idntCpte][idntCpte.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{idntCpte.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntCpte.idntCpte][idntCpte.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{idntCpte.createSetup}" value="New IdntCpte" />
                <br />
                <h:commandLink action="#{idntCpte.listSetup}" value="Show All IdntCpte Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
