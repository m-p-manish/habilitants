<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>techDecision level 3</title>
        <link rel="stylesheet" type="text/css" href="/techDecisionCube/faces/jsfcrud.css" />
    </head>
    <body>
        <h1><h:outputText value="The Cube v0.8" /></h1>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Initialisation du Datamart par filtrage:</h1>
            <h1>Les identités</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:inputText value="#{database3.selectIdnt}" required="true" size="100"/>
                <h:commandButton action="#{database3.gotoIdentite}" value="Select & Insert !" >
                    <f:param name="filtre" value="#{database3.selectIdnt}"/>
                </h:commandButton>
            <h:commandLink value="  Voir la liste des identités" action="identite_list" immediate="true" />
            </h:form>
            <h1>Analyse des profils applicatifs</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:inputText id="fichier" value="#{roleMinerBean.fichier}" required="true" size="50"/>
                <h:inputText id="seuilHbilitant" value="#{roleMinerBean.seuilHbilitant}" required="true" size="2"/>
                <h:commandButton action="#{roleMinerBean.rolesApplicatifs}" value="Analyse !" >
                    <f:param name="fichier" value="#{roleMinerBean.fichier}"/>
                    <f:param name="seuilHbilitant" value="#{roleMinerBean.seuilHbilitant}"/>
                </h:commandButton>
            </h:form>
            <h1>Comparaison des compte via Electre</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:inputText id="fichier" value="#{ElectreBean.fichier}" required="true" size="50"/>
                <h:inputText id="nomClass" value="#{ElectreBean.nomClass}" required="true" size="50"/>
                <h:commandButton action="#{ElectreBean.electreCpte}" value="Compare !" >
                    <f:param name="fichier" value="#{ElectreBean.fichier}"/>
                    <f:param name="nomClass" value="#{ElectreBean.nomClass}"/>
                </h:commandButton>
            <h:commandLink value="  Voir la liste des classements" action="cpteattrs_list" immediate="true" />
            </h:form>
            <h1>Analyse des Rôles</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:inputText id="fichier" value="#{roleMinerBean.fichier2}" required="true" size="50"/>
                <h:outputText value="Seuil Hblt:" />
                <h:inputText id="seuilHabilitant" value="#{roleMinerBean.seuilHbilitant2}" required="true" size="2"/>
                <h:outputText value="Seuil Idnt:" />
                <h:inputText id="seuilIdentite" value="#{roleMinerBean.seuilIdentite}" required="true" size="2"/>
                <h:commandButton action="#{roleMinerBean.newRoles}" value="Analyse !" >
                    <f:param name="fichier" value="#{roleMinerBean.fichier2}"/>
                    <f:param name="seuilHabilitant" value="#{roleMinerBean.seuilHbilitant2}"/>
                    <f:param name="seuilIdentite" value="#{roleMinerBean.seuilIdentite}"/>
                </h:commandButton>
            <h:commandLink value="  Voir la liste des rôles" action="role_list" immediate="true" />
            </h:form>
            <h:form styleClass="jsfcrud_list_form">
                <h:commandButton id="stopperRole" action="#{roleMinerBean.stopperRoles}" value="Stop !" actionListener="#{roleBuilder.stopper}" >
                </h:commandButton>
            </h:form><br/>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText value="Etat Agent Josso: " /><h:outputText value="#{jossoInsideFaces.etatAgent}   " /><h:commandButton value=" Logout !" action="#{jossoInsideFaces.terminer}" />
            </h:form><br/>
         <br/>
         <a href="../../techDecisionDB3srv/console/">Lien vers la console H2</a><br/>
         <a href="../../techDecisionDB3srv/buildDatabase/">Lien vers la page de construction de la base</a>
    </body>
</html>
</f:view>
