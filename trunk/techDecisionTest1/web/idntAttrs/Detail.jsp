<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>IdntAttrs Detail</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>IdntAttrs Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkattrIdnt:"/>
                    <h:outputText value="#{idntAttrs.idntAttrs.pkattrIdnt}" title="PkattrIdnt" />
                    <h:outputText value="Fkidnt:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{idntAttrs.idntAttrs.fkidnt}"/>
                        <h:panelGroup rendered="#{idntAttrs.idntAttrs.fkidnt != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{identite.detailSetup}">
                                <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs][idntAttrs.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs.fkidnt][identite.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="idntAttrs"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.IdntAttrsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{identite.editSetup}">
                                <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs][idntAttrs.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs.fkidnt][identite.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="idntAttrs"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.IdntAttrsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{identite.destroy}">
                                <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs][idntAttrs.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs.fkidnt][identite.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="idntAttrs"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.IdntAttrsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Attr:"/>
                    <h:outputText value="#{idntAttrs.idntAttrs.attr}" title="Attr" />
                    <h:outputText value="Val:"/>
                    <h:outputText value="#{idntAttrs.idntAttrs.val}" title="Val" />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{idntAttrs.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs][idntAttrs.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{idntAttrs.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][idntAttrs.idntAttrs][idntAttrs.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{idntAttrs.createSetup}" value="New IdntAttrs" />
                <br />
                <h:commandLink action="#{idntAttrs.listSetup}" value="Show All IdntAttrs Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
