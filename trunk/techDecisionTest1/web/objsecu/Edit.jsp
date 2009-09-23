<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsf/core" prefix="f" %>
<%@taglib uri="http://java.sun.com/jsf/html" prefix="h" %>
<f:view>
    <html>
        <head>
            <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
            <title>Editing Objsecu</title>
            <link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
        </head>
        <body>
            <h:panelGroup id="messagePanel" layout="block">
                <h:messages errorStyle="color: red" infoStyle="color: green" layout="table"/>
            </h:panelGroup>
            <h1>Editing Objsecu</h1>
            <h:form>
                <h:panelGrid columns="2">
                    <h:outputText value="Pkobjs:"/>
                    <h:outputText value="#{objsecu.objsecu.pkobjs}" title="Pkobjs" />
                    <h:outputText value="ObjsAttrsCollection:"/>
                    <h:selectManyListbox id="objsAttrsCollection" value="#{objsecu.objsecu.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].objsAttrsCollection}" title="ObjsAttrsCollection" size="6" converter="#{objsAttrs.converter}" >
                        <f:selectItems value="#{objsAttrs.objsAttrsItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="ObjsCpteCollection:"/>
                    <h:selectManyListbox id="objsCpteCollection" value="#{objsecu.objsecu.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].objsCpteCollection}" title="ObjsCpteCollection" size="6" converter="#{objsCpte.converter}" >
                        <f:selectItems value="#{objsCpte.objsCpteItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                    <h:outputText value="ObjsHbltCollection:"/>
                    <h:selectManyListbox id="objsHbltCollection" value="#{objsecu.objsecu.jsfcrud_transform[jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.collectionToArray][jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method.arrayToList].objsHbltCollection}" title="ObjsHbltCollection" size="6" converter="#{objsHblt.converter}" >
                        <f:selectItems value="#{objsHblt.objsHbltItemsAvailableSelectMany}"/>
                    </h:selectManyListbox>
                </h:panelGrid>
                <br />
                <h:commandLink action="#{objsecu.edit}" value="Save">
                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <br />
                <h:commandLink action="#{objsecu.detailSetup}" value="Show" immediate="true">
                    <f:param name="jsfcrud.currentObjsecu" value="#{jsfcrud_class['test1Jsf.util.JsfUtil'].jsfcrud_method['getAsConvertedString'][objsecu.objsecu][objsecu.converter].jsfcrud_invoke}"/>
                </h:commandLink>
                <br />
                <h:commandLink action="#{objsecu.listSetup}" value="Show All Objsecu Items" immediate="true"/>
                <br />
                <h:commandLink value="Index" action="welcome" immediate="true" />
            </h:form>
        </body>
    </html>
</f:view>
