<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Objsecu Detail</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Objsecu Detail</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkobjs:"/>
                    <h:outputText value="#{objsecu.objsecu.pkobjs}" title="Pkobjs" />
                    <h:outputText value="ObjsAttrsCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty objsecu.objsecu.objsAttrsCollection}" value="(No Items)"/>
                        <h:dataTable value="#{objsecu.objsecu.objsAttrsCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty objsecu.objsecu.objsAttrsCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PkattrObjs"/>
                                </f:facet>
                                <h:outputText value=" #{item.pkattrObjs}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkobjs"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkobjs}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Attr"/>
                                </f:facet>
                                <h:outputText value=" #{item.attr}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Val"/>
                                </f:facet>
                                <h:outputText value=" #{item.val}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{objsAttrs.detailSetup}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsAttrs.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{objsAttrs.editSetup}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsAttrs.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{objsAttrs.destroy}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsAttrs.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="ObjsCpteCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty objsecu.objsecu.objsCpteCollection}" value="(No Items)"/>
                        <h:dataTable value="#{objsecu.objsecu.objsCpteCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty objsecu.objsecu.objsCpteCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PkobjsCpte"/>
                                </f:facet>
                                <h:outputText value=" #{item.pkobjsCpte}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkcpte"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkcpte}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkobjs"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkobjs}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{objsCpte.detailSetup}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{objsCpte.editSetup}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{objsCpte.destroy}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                    <h:outputText value="ObjsHbltCollection:" />
                    <h:panelGroup>
                        <h:outputText rendered="#{empty objsecu.objsecu.objsHbltCollection}" value="(No Items)"/>
                        <h:dataTable value="#{objsecu.objsecu.objsHbltCollection}" var="item" 
                                     border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px" 
                                     rendered="#{not empty objsecu.objsecu.objsHbltCollection}">
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="PkobjsHblt"/>
                                </f:facet>
                                <h:outputText value=" #{item.pkobjsHblt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkhblt"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkhblt}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText value="Fkobjs"/>
                                </f:facet>
                                <h:outputText value=" #{item.fkobjs}"/>
                            </h:column>
                            <h:column>
                                <f:facet name="header">
                                    <h:outputText escape="false" value="&nbsp;"/>
                                </f:facet>
                                <h:commandLink value="Show" action="#{objsHblt.detailSetup}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Edit" action="#{objsHblt.editSetup}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                                <h:outputText value=" "/>
                                <h:commandLink value="Destroy" action="#{objsHblt.destroy}">
                                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                                    <f:param name="jsfcrud.relatedController" value="objsecu" />
                                    <f:param name="jsfcrud.relatedControllerType" value="test1Jsf.ObjsecuController" />
                                </h:commandLink>
                            </h:column>
                        </h:dataTable>
                    </h:panelGroup>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{objsecu.destroy}" value="Destroy">
                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{objsecu.editSetup}" value="Edit">
                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}" />
                </h:commandLink>
                <br />
                <h:commandLink action="#{objsecu.createSetup}" value="New Objsecu" />
                <br />
                <h:commandLink action="#{objsecu.listSetup}" value="Show All Objsecu Items"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
