<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>ObjsAttrs Detail</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>ObjsAttrs Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkattrObjs:"/>
                    <h:outputText value="#{objsAttrs.objsAttrs.pkattrObjs}" title="PkattrObjs" />
                    <h:outputText value="Fkobjs:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{objsAttrs.objsAttrs.fkobjs}"/>
                        <h:panelGroup rendered="#{objsAttrs.objsAttrs.fkobjs != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{objsecu.detailSetup}">
                                <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs][objsAttrs.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsAttrs"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsAttrsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{objsecu.editSetup}">
                                <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs][objsAttrs.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsAttrs"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsAttrsController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{objsecu.destroy}">
                                <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs][objsAttrs.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsAttrs"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsAttrsController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Attr:"/>
                    <h:outputText value="#{objsAttrs.objsAttrs.attr}" title="Attr" />
                    <h:outputText value="Val:"/>
                    <h:outputText value="#{objsAttrs.objsAttrs.val}" title="Val" />
                </h:panelGrid>
                <br />
                <h:commandLink action="#{objsAttrs.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs][objsAttrs.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{objsAttrs.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsAttrs.objsAttrs][objsAttrs.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{objsAttrs.createSetup}" value="New ObjsAttrs" />
                <br />
                <h:commandLink action="#{objsAttrs.listSetup}" value="Show All ObjsAttrs Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
