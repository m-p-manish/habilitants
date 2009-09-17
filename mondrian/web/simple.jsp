<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- 
    Document   : simple
    Created on : 22 juil. 2009, 08:38:24
    Author     : spopoff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Simple page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
    </body>
<c:if test="query==null">
</c:if>
<%=query%>
</html>
