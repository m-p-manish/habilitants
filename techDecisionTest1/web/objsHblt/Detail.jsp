<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>ObjsHblt Detail</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>ObjsHblt Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="PkobjsHblt:"/>
                    <h:outputText value="#{objsHblt.objsHblt.pkobjsHblt}" title="PkobjsHblt" />
                    <h:outputText value="Fkhblt:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{objsHblt.objsHblt.fkhblt}"/>
                        <h:panelGroup rendered="#{objsHblt.objsHblt.fkhblt != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{habilitant.detailSetup}">
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt.fkhblt][habilitant.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{habilitant.editSetup}">
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt.fkhblt][habilitant.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{habilitant.destroy}">
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt.fkhblt][habilitant.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                    <h:outputText value="Fkobjs:"/>
                    <h:panelGroup>
                        <h:outputText value=" #{objsHblt.objsHblt.fkobjs}"/>
                        <h:panelGroup rendered="#{objsHblt.objsHblt.fkobjs != null}">
                            <h:outputText value=" ("/>
                            <h:commandLink value="Show" action="#{objsecu.detailSetup}">
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{objsecu.editSetup}">
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{objsecu.destroy}">
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt.fkobjs][objsecu.converter].jsfcrud_invoke}"/>
                                <f:param name="jsfcrud.relatedController" value="objsHblt"/>
                                <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsHbltController"/>
                            </h:commandLink>
                            <h:outputText value=" )"/>
                        </h:panelGroup>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{objsHblt.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{objsHblt.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsHblt.objsHblt][objsHblt.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{objsHblt.createSetup}" value="New ObjsHblt" />
                <br />
                <h:commandLink action="#{objsHblt.listSetup}" value="Show All ObjsHblt Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
