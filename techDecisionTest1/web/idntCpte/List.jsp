<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing IdntCpte Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing IdntCpte Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No IdntCpte Items Found)<br />" rendered="#{idntCpte.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{idntCpte.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{idntCpte.pagingInfo.firstItem + 1}..#{idntCpte.pagingInfo.lastItem} of #{idntCpte.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{idntCpte.prev}" value="Previous #{idntCpte.pagingInfo.batchSize}" rendered="#{idntCpte.pagingInfo.firstItem >= idntCpte.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{idntCpte.next}" value="Next #{idntCpte.pagingInfo.batchSize}" rendered="#{idntCpte.pagingInfo.lastItem + idntCpte.pagingInfo.batchSize <= idntCpte.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{idntCpte.next}" value="Remaining #{idntCpte.pagingInfo.itemCount - idntCpte.pagingInfo.lastItem}"
                                   rendered="#{idntCpte.pagingInfo.lastItem < idntCpte.pagingInfo.itemCount && idntCpte.pagingInfo.lastItem + idntCpte.pagingInfo.batchSize > idntCpte.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{idntCpte.idntCpteItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PkidntCpte"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkidntCpte}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fkcpte"/>
                            </f:facet>
                            <h:outputText value=" #{item.fkcpte}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fkidnt"/>
                            </f:facet>
                            <h:outputText value=" #{item.fkidnt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{idntCpte.detailSetup}">
                                <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntCpte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{idntCpte.editSetup}">
                                <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntCpte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{idntCpte.destroy}">
                                <f:param name="jsfcrud.currentIdntCpte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntCpte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{idntCpte.createSetup}" value="New IdntCpte"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
