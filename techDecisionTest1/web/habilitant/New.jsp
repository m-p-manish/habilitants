<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New Habilitant</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New Habilitant</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{habilitant.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkhblt:"/>
                    <h:inputText id="pkhblt" value="#{habilitant.habilitant.pkhblt}" title="Pkhblt" required="true" requiredMessage="The pkhblt field is required." />
                    <h:outputText value="Val:"/>
                    <h:inputText id="val" value="#{habilitant.habilitant.val}" title="Val" required="true" requiredMessage="The val field is required." />
                    <h:outputText value="Type:"/>
                    <h:inputText id="type" value="#{habilitant.habilitant.type}" title="Type" required="true" requiredMessage="The type field is required." />
                    <h:outputText value="ObjsHbltCollection:"/>
                    <h:selectManyListbox id="objsHbltCollection" value="#{habilitant.habilitant.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].objsHbltCollection}" title="ObjsHbltCollection" size="6" converter="#{objsHblt.converter}" >
                        <f:selectItems value="#{objsHblt.objsHbltItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{habilitant.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{habilitant.listSetup}" value="Show All Habilitant Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
