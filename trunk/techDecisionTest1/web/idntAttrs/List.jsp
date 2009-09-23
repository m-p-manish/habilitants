<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Listing IdntAttrs Items</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Listing IdntAttrs Items</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No IdntAttrs Items Found)<br />" rendered="#{idntAttrs.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{idntAttrs.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{idntAttrs.pagingInfo.firstItem + 1}..#{idntAttrs.pagingInfo.lastItem} of #{idntAttrs.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{idntAttrs.prev}" value="Previous #{idntAttrs.pagingInfo.batchSize}" rendered="#{idntAttrs.pagingInfo.firstItem >= idntAttrs.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{idntAttrs.next}" value="Next #{idntAttrs.pagingInfo.batchSize}" rendered="#{idntAttrs.pagingInfo.lastItem + idntAttrs.pagingInfo.batchSize <= idntAttrs.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{idntAttrs.next}" value="Remaining #{idntAttrs.pagingInfo.itemCount - idntAttrs.pagingInfo.lastItem}"
                                   rendered="#{idntAttrs.pagingInfo.lastItem < idntAttrs.pagingInfo.itemCount && idntAttrs.pagingInfo.lastItem + idntAttrs.pagingInfo.batchSize > idntAttrs.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{idntAttrs.idntAttrsItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="PkattrIdnt"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkattrIdnt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Fkidnt"/>
                            </f:facet>
                            <h:outputText value=" #{item.fkidnt}"/>
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
                            <h:commandLink value="Show" action="#{idntAttrs.detailSetup}">
                                <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{idntAttrs.editSetup}">
                                <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{idntAttrs.destroy}">
                                <f:param name="jsfcrud.currentIdntAttrs" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][idntAttrs.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{idntAttrs.createSetup}" value="New IdntAttrs"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
