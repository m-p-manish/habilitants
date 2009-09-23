<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing CpteHblt Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing CpteHblt Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No CpteHblt Items Found)<br />" rendered="#{cpteHblt.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{cpteHblt.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{cpteHblt.pagingInfo.firstItem + 1}..#{cpteHblt.pagingInfo.lastItem} of #{cpteHblt.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cpteHblt.prev}" value="Previous #{cpteHblt.pagingInfo.batchSize}" rendered="#{cpteHblt.pagingInfo.firstItem >= cpteHblt.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{cpteHblt.next}" value="Next #{cpteHblt.pagingInfo.batchSize}" rendered="#{cpteHblt.pagingInfo.lastItem + cpteHblt.pagingInfo.batchSize <= cpteHblt.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cpteHblt.next}" value="Remaining #{cpteHblt.pagingInfo.itemCount - cpteHblt.pagingInfo.lastItem}"
                                   rendered="#{cpteHblt.pagingInfo.lastItem < cpteHblt.pagingInfo.itemCount && cpteHblt.pagingInfo.lastItem + cpteHblt.pagingInfo.batchSize > cpteHblt.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{cpteHblt.cpteHbltItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PkcpteHblt"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkcpteHblt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fkcpte"/>
                            </f:facet>
                            <h:outputText value=" #{item.fkcpte}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fkhblt"/>
                            </f:facet>
                            <h:outputText value=" #{item.fkhblt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{cpteHblt.detailSetup}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteHblt.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cpteHblt.editSetup}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteHblt.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cpteHblt.destroy}">
                                <f:param name="jsfcrud.currentCpteHblt" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteHblt.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{cpteHblt.createSetup}" value="New CpteHblt"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
