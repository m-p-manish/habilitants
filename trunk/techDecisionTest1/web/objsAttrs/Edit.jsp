<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing ObjsAttrs</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing ObjsAttrs</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkattrObjs:"/>
                    <h:outputText value="#{objsAttrs.objsAttrs.pkattrObjs}" title="PkattrObjs" />
                    <h:outputText value="Fkobjs:"/>
                    <h:selectOneMenu id="fkobjs" value="#{objsAttrs.objsAttrs.fkobjs}" title="Fkobjs" required="true" requiredMessage="The fkobjs field is required." >
                        <f:selectItems value="#{objsecu.objsecuItemsAvailableSelectOne}"/>
                    </h:selectOneMenu>
                    <h:outputText value="Attr:"/>
                    <h:inputText id="attr" value="#{objsAttrs.objsAttrs.attr}" title="Attr" required="true" requiredMessage="The attr field is required." />
                    <h:outputText value="Val:"/>
                    <h:inputText id="val" value="#{objsAttrs.objsAttrs.val}" title="Val" required="true" requiredMessage="The val field is required." />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{objsAttrs.edit}" value="Save">
                    <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs][objsAttrs.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{objsAttrs.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs][objsAttrs.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{objsAttrs.listSetup}" value="Show All ObjsAttrs Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
