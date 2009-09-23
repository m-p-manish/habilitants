<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing ObjsHblt Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing ObjsHblt Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No ObjsHblt Items Found)<br />" rendered="#{objsHblt.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{objsHblt.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{objsHblt.pagingInfo.firstItem + 1}..#{objsHblt.pagingInfo.lastItem} of #{objsHblt.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsHblt.prev}" value="Previous #{objsHblt.pagingInfo.batchSize}" rendered="#{objsHblt.pagingInfo.firstItem >= objsHblt.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{objsHblt.next}" value="Next #{objsHblt.pagingInfo.batchSize}" rendered="#{objsHblt.pagingInfo.lastItem + objsHblt.pagingInfo.batchSize <= objsHblt.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsHblt.next}" value="Remaining #{objsHblt.pagingInfo.itemCount - objsHblt.pagingInfo.lastItem}"
                                   rendered="#{objsHblt.pagingInfo.lastItem < objsHblt.pagingInfo.itemCount && objsHblt.pagingInfo.lastItem + objsHblt.pagingInfo.batchSize > objsHblt.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{objsHblt.objsHbltItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{objsHblt.editSetup}">
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{objsHblt.destroy}">
                                <f:param name="jsfcrud.currentObjsHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsHblt.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{objsHblt.createSetup}" value="New ObjsHblt"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
