<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New IdntAttrs</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New IdntAttrs</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{idntAttrs.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Fkidnt:"/>
                    <h:selectOneMenu id="fkidnt" value="#{idntAttrs.idntAttrs.fkidnt}" title="Fkidnt" required="true" requiredMessage="The fkidnt field is required." >
                        <f:selectItems value="#{identite.identiteItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Attr:"/>
                    <h:inputText id="attr" value="#{idntAttrs.idntAttrs.attr}" title="Attr" required="true" requiredMessage="The attr field is required." />
                    <h:outputText value="Val:"/>
                    <h:inputText id="val" value="#{idntAttrs.idntAttrs.val}" title="Val" required="true" requiredMessage="The val field is required." />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{idntAttrs.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{idntAttrs.listSetup}" value="Show All IdntAttrs Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
