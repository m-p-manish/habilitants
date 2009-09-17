<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<jp:mondrianQuery id="query01" dataSource="jdbc/memTechDecision" catalogUri="/WEB-INF/queries/techDecision1.xml">
select {[Measures].[nbre Idnt], [Measures].[nbre Cpte]} ON columns,
  {[Comptes].[Tous les Comptes]} ON rows
from [CompteIdentiteDept]
</jp:mondrianQuery>

<c:set var="title01" scope="session" value="Nbre compte et identité par département"/>
