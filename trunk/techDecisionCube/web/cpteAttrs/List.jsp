<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Liste des attributs de compte</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionCube/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Les attributs de compte de type classement</h1>
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
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink value="Sélecteur" action="welcome" immediate="true" />
                <br/>
                <h:inputTextarea id="textArea" rows="200" cols="150"  value="#{ElectreBean.rapportCpte}" />
                
            </h:form>
        </body>
    </html>
</f:view>
