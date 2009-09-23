<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Objsecu Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Objsecu Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Objsecu Items Found)<br />" rendered="#{objsecu.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{objsecu.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{objsecu.pagingInfo.firstItem + 1}..#{objsecu.pagingInfo.lastItem} of #{objsecu.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsecu.prev}" value="Previous #{objsecu.pagingInfo.batchSize}" rendered="#{objsecu.pagingInfo.firstItem >= objsecu.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{objsecu.next}" value="Next #{objsecu.pagingInfo.batchSize}" rendered="#{objsecu.pagingInfo.lastItem + objsecu.pagingInfo.batchSize <= objsecu.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsecu.next}" value="Remaining #{objsecu.pagingInfo.itemCount - objsecu.pagingInfo.lastItem}"
                                   rendered="#{objsecu.pagingInfo.lastItem < objsecu.pagingInfo.itemCount && objsecu.pagingInfo.lastItem + objsecu.pagingInfo.batchSize > objsecu.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{objsecu.objsecuItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pkobjs"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkobjs}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{objsecu.detailSetup}">
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsecu.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{objsecu.editSetup}">
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsecu.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{objsecu.destroy}">
                                <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsecu.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{objsecu.createSetup}" value="New Objsecu"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
