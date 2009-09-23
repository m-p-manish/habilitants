<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Identite Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Identite Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Identite Items Found)<br />" rendered="#{identite.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{identite.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{identite.pagingInfo.firstItem + 1}..#{identite.pagingInfo.lastItem} of #{identite.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{identite.prev}" value="Previous #{identite.pagingInfo.batchSize}" rendered="#{identite.pagingInfo.firstItem >= identite.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{identite.next}" value="Next #{identite.pagingInfo.batchSize}" rendered="#{identite.pagingInfo.lastItem + identite.pagingInfo.batchSize <= identite.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{identite.next}" value="Remaining #{identite.pagingInfo.itemCount - identite.pagingInfo.lastItem}"
                                   rendered="#{identite.pagingInfo.lastItem < identite.pagingInfo.itemCount && identite.pagingInfo.lastItem + identite.pagingInfo.batchSize > identite.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{identite.identiteItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pkidnt"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkidnt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Username"/>
                            </f:facet>
                            <h:outputText value=" #{item.username}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fonction"/>
                            </f:facet>
                            <h:outputText value=" #{item.fonction}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Departement"/>
                            </f:facet>
                            <h:outputText value=" #{item.departement}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{identite.detailSetup}">
                                <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][identite.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{identite.editSetup}">
                                <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][identite.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{identite.destroy}">
                                <f:param name="jsfcrud.currentIdentite" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][identite.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{identite.createSetup}" value="New Identite"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
