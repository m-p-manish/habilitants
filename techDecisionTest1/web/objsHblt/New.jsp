<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>New ObjsHblt</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>New ObjsHblt</h1>
            <h:form>
                <h:inputHidden id="validateCreateField" validator="#{objsHblt.validateCreate}" value="value"/>
                <h:panelGrid columns="2">
                    <h:outputText value="Fkhblt:"/>
                    <h:selectOneMenu id="fkhblt" value="#{objsHblt.objsHblt.fkhblt}" title="Fkhblt" required="true" requiredMessage="The fkhblt field is required." >
                        <f:selectItems value="#{habilitant.habilitantItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Fkobjs:"/>
                    <h:selectOneMenu id="fkobjs" value="#{objsHblt.objsHblt.fkobjs}" title="Fkobjs" required="true" requiredMessage="The fkobjs field is required." >
                        <f:selectItems value="#{objsecu.objsecuItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{objsHblt.create}" value="Create"/>
                <br />
                <br />
                <h:commandLink action="#{objsHblt.listSetup}" value="Show All ObjsHblt Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
