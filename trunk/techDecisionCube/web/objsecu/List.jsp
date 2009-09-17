<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Liste des objets de sécurité</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionCube/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Les Objets de sécurité dont les profils applicatifs</h1>
            <h:form styleClass="jsfcrud_list_form">
                <h:outputText escape="false" value="(No Objsecu Items Found)<br />" rendered="#{objsecu.pagingInfo.itemCount == 0}" />
                <h:panelGroup rendered="#{objsecu.pagingInfo.itemCount > 0}">
                    <h:outputText value="Item #{objsecu.pagingInfo.firstItem + 1}..#{objsecu.pagingInfo.lastItem} of #{objsecu.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsecu.prev}" value="Previous #{objsecu.pagingInfo.batchSize}" rendered="#{objsecu.pagingInfo.firstItem >= objsecu.pagingInfo.batchSize}"/>&nbsp;
                    <h:commandLink action="#{objsecu.next}" value="Next #{objsecu.pagingInfo.batchSize}" rendered="#{objsecu.pagingInfo.lastItem + objsecu.pagingInfo.batchSize <= objsecu.pagingInfo.itemCount}"/>&nbsp;
                    <h:commandLink action="#{objsecu.next}" value="Remaining #{objsecu.pagingInfo.itemCount - objsecu.pagingInfo.lastItem}"
                                   rendered="#{objsecu.pagingInfo.lastItem < objsecu.pagingInfo.itemCount && objsecu.pagingInfo.lastItem + objsecu.pagingInfo.batchSize > objsecu.pagingInfo.itemCount}"/>
                    <h:dataTable value="#{objsecu.objsecuItems}" var="item" border="0" cellpadding="2" cellspacing="0" rowClasses="jsfcrud_odd_row,jsfcrud_even_row" rules="all" style="border:solid 1px">
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Pkobjs"/>
                            </f:facet>
                            <h:outputText value=" #{item.pkobjs}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="NbreHblt"/>
                            </f:facet>
                            <h:outputText value=" #{item.nbreHblt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="NbreCpt"/>
                            </f:facet>
                            <h:outputText value=" #{item.nbreCpt}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Enfant"/>
                            </f:facet>
                            <h:outputText value=" #{item.enfant}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Sommet"/>
                            </f:facet>
                            <h:outputText value=" #{item.sommet}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Parent"/>
                            </f:facet>
                            <h:outputText value=" #{item.parent}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Position"/>
                            </f:facet>
                            <h:outputText value=" #{item.position}"/>
                        </h:column>
                        <h:column>
                            <f:facet name="header">
                                <h:outputText value="Description"/>
                            </f:facet>
                            <h:outputText value=" #{item.description}"/>
                        </h:column>
                    </h:dataTable>
                </h:panelGroup>
                <br />
                <h:commandLink value="Sélecteur" action="welcome" immediate="true" />
                <br/>
                <h:inputTextarea id="textArea" rows="200" cols="150"  value="#{objSecusBean.rapportObjs}" />
                
            </h:form>
        </body>
    </html>
</f:view>
