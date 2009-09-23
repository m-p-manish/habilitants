<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing IdntAttrs</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing IdntAttrs</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkattrIdnt:"/>
                    <h:outputText value="#{idntAttrs.idntAttrs.pkattrIdnt}" title="PkattrIdnt" />
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
                <h:commandLink action="#{idntAttrs.edit}" value="Save">
                    <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs][idntAttrs.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{idntAttrs.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs][idntAttrs.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{idntAttrs.listSetup}" value="Show All IdntAttrs Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
