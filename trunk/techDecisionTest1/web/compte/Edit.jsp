<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Compte</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Compte</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkcpte:"/>
                    <h:outputText value="#{compte.compte.pkcpte}" title="Pkcpte" />
                    <h:outputText value="Pkcpte2:"/>
                    <h:inputText id="pkcpte2" value="#{compte.compte.pkcpte2}" title="Pkcpte2" required="true" requiredMessage="The pkcpte2 field is required." />
                    <h:outputText value="ObjsCpteCollection:"/>
                    <h:selectManyListbox id="objsCpteCollection" value="#{compte.compte.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].objsCpteCollection}" title="ObjsCpteCollection" size="6" converter="#{objsCpte.converter}" >
                        <f:selectItems value="#{objsCpte.objsCpteItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="CpteAttrsCollection:"/>
                    <h:selectManyListbox id="cpteAttrsCollection" value="#{compte.compte.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].cpteAttrsCollection}" title="CpteAttrsCollection" size="6" converter="#{cpteAttrs.converter}" >
                        <f:selectItems value="#{cpteAttrs.cpteAttrsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="IdntCpteCollection:"/>
                    <h:selectManyListbox id="idntCpteCollection" value="#{compte.compte.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].idntCpteCollection}" title="IdntCpteCollection" size="6" converter="#{idntCpte.converter}" >
                        <f:selectItems value="#{idntCpte.idntCpteItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{compte.edit}" value="Save">
                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{compte.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][compte.compte][compte.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{compte.listSetup}" value="Show All Compte Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
