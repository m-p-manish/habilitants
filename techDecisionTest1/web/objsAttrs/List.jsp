<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing ObjsAttrs Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing ObjsAttrs Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No ObjsAttrs Items Found)<br />" rendered="#{objsAttrs.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{objsAttrs.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{objsAttrs.pagingInfo.firstItem + 1}..#{objsAttrs.pagingInfo.lastItem} of #{objsAttrs.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsAttrs.prev}" value="Previous #{objsAttrs.pagingInfo.batchSize}" rendered="#{objsAttrs.pagingInfo.firstItem >= objsAttrs.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{objsAttrs.next}" value="Next #{objsAttrs.pagingInfo.batchSize}" rendered="#{objsAttrs.pagingInfo.lastItem + objsAttrs.pagingInfo.batchSize <= objsAttrs.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsAttrs.next}" value="Remaining #{objsAttrs.pagingInfo.itemCount - objsAttrs.pagingInfo.lastItem}"
                                   rendered="#{objsAttrs.pagingInfo.lastItem < objsAttrs.pagingInfo.itemCount && objsAttrs.pagingInfo.lastItem + objsAttrs.pagingInfo.batchSize > objsAttrs.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{objsAttrs.objsAttrsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{objsAttrs.editSetup}">
                                <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{objsAttrs.destroy}">
                                <f:param name="jsfcrud.currentObjsAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{objsAttrs.createSetup}" value="New ObjsAttrs"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
