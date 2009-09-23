<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing CpteAttrs Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing CpteAttrs Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No CpteAttrs Items Found)<br />" rendered="#{cpteAttrs.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{cpteAttrs.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{cpteAttrs.pagingInfo.firstItem + 1}..#{cpteAttrs.pagingInfo.lastItem} of #{cpteAttrs.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cpteAttrs.prev}" value="Previous #{cpteAttrs.pagingInfo.batchSize}" rendered="#{cpteAttrs.pagingInfo.firstItem >= cpteAttrs.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{cpteAttrs.next}" value="Next #{cpteAttrs.pagingInfo.batchSize}" rendered="#{cpteAttrs.pagingInfo.lastItem + cpteAttrs.pagingInfo.batchSize <= cpteAttrs.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{cpteAttrs.next}" value="Remaining #{cpteAttrs.pagingInfo.itemCount - cpteAttrs.pagingInfo.lastItem}"
                                   rendered="#{cpteAttrs.pagingInfo.lastItem < cpteAttrs.pagingInfo.itemCount && cpteAttrs.pagingInfo.lastItem + cpteAttrs.pagingInfo.batchSize > cpteAttrs.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{cpteAttrs.cpteAttrsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PkattrCpte"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkattrCpte}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fkcpte"/>
                            </f:facet>
                            <h:outputText value=" #{item.fkcpte}"/>
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
                            <h:commandLink value="Show" action="#{cpteAttrs.detailSetup}">
                                <f:param name="jsfcrud.currentCpteAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{cpteAttrs.editSetup}">
                                <f:param name="jsfcrud.currentCpteAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{cpteAttrs.destroy}">
                                <f:param name="jsfcrud.currentCpteAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][cpteAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{cpteAttrs.createSetup}" value="New CpteAttrs"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
