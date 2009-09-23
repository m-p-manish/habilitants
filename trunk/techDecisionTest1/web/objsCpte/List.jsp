<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing ObjsCpte Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing ObjsCpte Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No ObjsCpte Items Found)<br />" rendered="#{objsCpte.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{objsCpte.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{objsCpte.pagingInfo.firstItem + 1}..#{objsCpte.pagingInfo.lastItem} of #{objsCpte.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsCpte.prev}" value="Previous #{objsCpte.pagingInfo.batchSize}" rendered="#{objsCpte.pagingInfo.firstItem >= objsCpte.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{objsCpte.next}" value="Next #{objsCpte.pagingInfo.batchSize}" rendered="#{objsCpte.pagingInfo.lastItem + objsCpte.pagingInfo.batchSize <= objsCpte.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsCpte.next}" value="Remaining #{objsCpte.pagingInfo.itemCount - objsCpte.pagingInfo.lastItem}"
                                   rendered="#{objsCpte.pagingInfo.lastItem < objsCpte.pagingInfo.itemCount && objsCpte.pagingInfo.lastItem + objsCpte.pagingInfo.batchSize > objsCpte.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{objsCpte.objsCpteItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
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
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{objsCpte.editSetup}">
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{objsCpte.destroy}">
                                <f:param name="jsfcrud.currentObjsCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][objsCpte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{objsCpte.createSetup}" value="New ObjsCpte"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
