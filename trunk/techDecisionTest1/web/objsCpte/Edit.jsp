<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing ObjsCpte</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing ObjsCpte</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkobjsCpte:"/>
                    <h:outputText value="#{objsCpte.objsCpte.pkobjsCpte}" title="PkobjsCpte" />
                    <h:outputText value="Fkcpte:"/>
                    <h:selectOneMenu id="fkcpte" value="#{objsCpte.objsCpte.fkcpte}" title="Fkcpte" required="true" requiredMessage="The fkcpte field is required." >
                        <f:selectItems value="#{compte.compteItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Fkobjs:"/>
                    <h:selectOneMenu id="fkobjs" value="#{objsCpte.objsCpte.fkobjs}" title="Fkobjs" required="true" requiredMessage="The fkobjs field is required." >
                        <f:selectItems value="#{objsecu.objsecuItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{objsCpte.edit}" value="Save">
                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{objsCpte.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsCpte.objsCpte][objsCpte.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{objsCpte.listSetup}" value="Show All ObjsCpte Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
