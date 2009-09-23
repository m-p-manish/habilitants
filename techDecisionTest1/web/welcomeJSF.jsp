<%@page contentType="text/html"%>
<%@page pageEncoding="UTF-8"%>

<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<%--
    This file is an entry point for JavaServer Faces application.
--%>
<f:view>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Visualisateur de l'EJB 1</title>
<link rel="stylesheet" type="text/css" href="/techDecisionTest1/faces/jsfcrud.css" />
    </head>
    <body>
<h:form>
<h1><h:outputText value="Données de sécurité niveau 1" /></h1>
<h:outputText value="Peut être que certains liens Edit/Show/Remove ne fonctionne pas" />
<br/><br/>
<h:commandLink action="#{identite.listSetup}" value="Liste des identités"/><br/>
<UL><LI><h:commandLink action="#{idntAttrs.listSetup}" value="Attributs des identités"/></LI>
<LI><h:commandLink action="#{idntCpte.listSetup}" value="Liste des comptes et des identités"/></LI></UL>
<br/>
<h:commandLink action="#{compte.listSetup}" value="Liste des comptes"/>
<UL><LI><h:commandLink action="#{cpteAttrs.listSetup}" value="Attributs des comptes"/></LI>
<LI><h:commandLink action="#{cpteHblt.listSetup}" value="Liste des comptes et des habilitants"/></LI></UL>
<br/>
<h:commandLink action="#{habilitant.listSetup}" value="Liste des habilitants"/>
<br/>
<br/>
<h:commandLink action="#{objsecu.listSetup}" value="Liste des objets de sécurités"/>
<UL><LI><h:commandLink action="#{objsAttrs.listSetup}" value="Attributs des objets de sécurité"/></LI>
<LI><h:commandLink action="#{objsHblt.listSetup}" value="Liste des objets de sécurités et des habilitants"/></LI>
<LI><h:commandLink action="#{objsCpte.listSetup}" value="Liste des objets de sécurités et des comptes"/></LI></UL>
</h:form>
<br/>
<h:form>
<br/>Détail d'un objet de sécurité: <h:inputText id="pkobjs" value="#{objsecu.pkobjs}" required="true" size="10"/>
<h:commandButton action="#{objsecu.detailObjs}" value="Détail" />
</h:form>

    </body>
</html>
</f:view>
