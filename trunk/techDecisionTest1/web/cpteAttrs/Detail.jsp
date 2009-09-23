<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>CpteAttrs Detail</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>CpteAttrs Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkattrCpte:"/>
                    <h:outputText value="#{cpteAttrs.cpteAttrs.pkattrCpte}" title="PkattrCpte" />
                    <h:outputText value="Fkcpte:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{cpteAttrs.cpteAttrs.fkcpte}"/>
                        <h:panelGroup rendered="#{cpteAttrs.cpteAttrs.fkcpte != null}">
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Attr:"/>
                    <h:outputText value="#{cpteAttrs.cpteAttrs.attr}" title="Attr" />
                    <h:outputText value="Val:"/>
                    <h:outputText value="#{cpteAttrs.cpteAttrs.val}" title="Val" />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{cpteAttrs.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentCpteAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteAttrs.cpteAttrs][cpteAttrs.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{cpteAttrs.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentCpteAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][cpteAttrs.cpteAttrs][cpteAttrs.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{cpteAttrs.createSetup}" value="New CpteAttrs" />
                <br />
                <h:commandLink action="#{cpteAttrs.listSetup}" value="Show All CpteAttrs Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
