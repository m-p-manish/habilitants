<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing Habilitant Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing Habilitant Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Habilitant Items Found)<br />" rendered="#{habilitant.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{habilitant.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{habilitant.pagingInfo.firstItem + 1}..#{habilitant.pagingInfo.lastItem} of #{habilitant.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{habilitant.prev}" value="Previous #{habilitant.pagingInfo.batchSize}" rendered="#{habilitant.pagingInfo.firstItem >= habilitant.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{habilitant.next}" value="Next #{habilitant.pagingInfo.batchSize}" rendered="#{habilitant.pagingInfo.lastItem + habilitant.pagingInfo.batchSize <= habilitant.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{habilitant.next}" value="Remaining #{habilitant.pagingInfo.itemCount - habilitant.pagingInfo.lastItem}"
                                   rendered="#{habilitant.pagingInfo.lastItem < habilitant.pagingInfo.itemCount && habilitant.pagingInfo.lastItem + habilitant.pagingInfo.batchSize > habilitant.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{habilitant.habilitantItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pkhblt"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkhblt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Val"/>
                            </f:facet>
                            <h:outputText value=" #{item.val}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Type"/>
                            </f:facet>
                            <h:outputText value=" #{item.type}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{habilitant.detailSetup}">
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][habilitant.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{habilitant.editSetup}">
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][habilitant.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{habilitant.destroy}">
                                <f:param name="jsfcrud.currentHabilitant" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][habilitant.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{habilitant.createSetup}" value="New Habilitant"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
