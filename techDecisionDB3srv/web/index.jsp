<%-- 
    Document   : index
    Created on : 12 mai 2009, 22:34:12
    Author     : spopoff
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>techDecision DB3 server Datamart</title>
    </head>
    <body>
        <h1>The in-memory techDecision Datamart server (based on H2)</h1>
        <a href="<%=request.getContextPath()%>/console">Lien vers la console H2</a><br/>
        <a href="<%=request.getContextPath()%>/buildDatabase">Lien vers initialisation des tables</a>
    </body>
</html>
