<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Liste des comptes (Show2)</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Liste des comptes</h1>
            <h:outputText value="Show et Show2 font la même chose"/><br/><br/>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Compte Items Found)<br />" rendered="#{compte.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{compte.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{compte.pagingInfo.firstItem + 1}..#{compte.pagingInfo.lastItem} of #{compte.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{compte.prev}" value="Previous #{compte.pagingInfo.batchSize}" rendered="#{compte.pagingInfo.firstItem >= compte.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{compte.next}" value="Next #{compte.pagingInfo.batchSize}" rendered="#{compte.pagingInfo.lastItem + compte.pagingInfo.batchSize <= compte.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{compte.next}" value="Remaining #{compte.pagingInfo.itemCount - compte.pagingInfo.lastItem}"
                                   rendered="#{compte.pagingInfo.lastItem < compte.pagingInfo.itemCount && compte.pagingInfo.lastItem + compte.pagingInfo.batchSize > compte.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{compte.compteItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pkcpte"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkcpte}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pkcpte2"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkcpte2}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText escape="false" value="&nbsp;"/>
                            </f:facet>
                            <h:commandLink value="Show" action="#{compte.detailSetup}">
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][compte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Edit" action="#{compte.editSetup}">
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][compte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Destroy" action="#{compte.destroy}">
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][compte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                            <h:outputText value=" "/>
                            <h:commandLink value="Show2" action="#{compte.detailSetup2}">
                                <f:param name="jsfcrud.currentCompte" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][item][compte.converter].jsfcrud_invoke}"/>
                            </h:commandLink>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink action="#{compte.createSetup}" value="New Compte"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
                
            </h:form>
        </body>
    </html>
</f:view>
