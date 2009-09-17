<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Liste des rôles</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionCube/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Les rôles</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Role Items Found)<br />" rendered="#{role.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{role.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{role.pagingInfo.firstItem + 1}..#{role.pagingInfo.lastItem} of #{role.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{role.prev}" value="Previous #{role.pagingInfo.batchSize}" rendered="#{role.pagingInfo.firstItem >= role.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{role.next}" value="Next #{role.pagingInfo.batchSize}" rendered="#{role.pagingInfo.lastItem + role.pagingInfo.batchSize <= role.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{role.next}" value="Remaining #{role.pagingInfo.itemCount - role.pagingInfo.lastItem}"
                                   rendered="#{role.pagingInfo.lastItem < role.pagingInfo.itemCount && role.pagingInfo.lastItem + role.pagingInfo.batchSize > role.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{role.roleItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pkrole"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkrole}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Name"/>
                            </f:facet>
                            <h:outputText value=" #{item.name}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Havechild"/>
                            </f:facet>
                            <h:outputText value=" #{item.havechild}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Active"/>
                            </f:facet>
                            <h:outputText value=" #{item.active}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Type"/>
                            </f:facet>
                            <h:outputText value=" #{item.type}"/>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink value="Sélecteur" action="welcome" immediate="true" />
                <br/>
                <h:inputTextarea id="textArea" rows="200" cols="150"  value="#{roleMinerBean.rapportRole}" />
                
            </h:form>
        </body>
    </html>
</f:view>
