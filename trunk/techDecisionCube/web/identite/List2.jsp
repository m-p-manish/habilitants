<%@taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : List2
    Created on : 10 mai 2009, 00:25:41
    Author     : spopoff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Identités</title>
    </head>
    <body>
        <sql:query var="result" dataSource="amemTechDecision">
            SELECT PKIDNT, USERNAME FROM SPOPOFF.IDENTITE
        </sql:query>

        <table border="1">
        <!-- column headers -->
        <tr>
        <c:forEach var="columnName" items="${result.columnNames}">
        <th><c:out value="${columnName}"/></th>
        </c:forEach>
        </tr>
        <!-- column data -->
        <c:forEach var="row" items="${result.rowsByIndex}">
        <tr>
        <c:forEach var="column" items="${row}">
        <td><c:out value="${column}"/></td>
        </c:forEach>
        </tr>
        </c:forEach>
        </table>
    </body>
</html>
