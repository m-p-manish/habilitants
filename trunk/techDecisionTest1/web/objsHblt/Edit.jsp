<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing ObjsHblt</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing ObjsHblt</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkobjsHblt:"/>
                    <h:outputText value="#{objsHblt.objsHblt.pkobjsHblt}" title="PkobjsHblt" />
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
                <h:commandLink action="#{objsHblt.edit}" value="Save">
                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{objsHblt.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{objsHblt.listSetup}" value="Show All ObjsHblt Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
