<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New CpteAttrs</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New CpteAttrs</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{cpteAttrs.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Fkcpte:"/>
                    <h:selectOneMenu id="fkcpte" value="#{cpteAttrs.cpteAttrs.fkcpte}" title="Fkcpte" required="true" requiredMessage="The fkcpte field is required." >
                        <f:selectItems value="#{compte.compteItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Attr:"/>
                    <h:inputText id="attr" value="#{cpteAttrs.cpteAttrs.attr}" title="Attr" required="true" requiredMessage="The attr field is required." />
                    <h:outputText value="Val:"/>
                    <h:inputText id="val" value="#{cpteAttrs.cpteAttrs.val}" title="Val" required="true" requiredMessage="The val field is required." />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{cpteAttrs.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{cpteAttrs.listSetup}" value="Show All CpteAttrs Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
