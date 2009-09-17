<%-- 
    Document   : clearCache
    Created on : 26 juil. 2009, 15:14:28
    Author     : spopoff
--%>

<%@ page session="true" contentType="text/html; charset=ISO-8859-1" %>
<%@ taglib uri="http://www.tonbeller.com/jpivot" prefix="jp" %>
<%@ taglib uri="http://www.tonbeller.com/wcf" prefix="wcf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>

<%
String rep = "";
java.util.Iterator<mondrian.rolap.RolapSchema> schemaIterator =  mondrian.rolap.RolapSchema.getRolapSchemas();
while(schemaIterator.hasNext()){
    mondrian.rolap.RolapSchema schema = schemaIterator.next();
    rep+="Schema="+schema.getName()+"<br/>";
    mondrian.olap.CacheControl cacheControl = schema.getInternalConnection().getCacheControl(null);
    for (mondrian.olap.Cube cube : schema.getCubes()) {
        rep+="   cube="+cube.getName()+"<br/>";
        cacheControl.flush(cacheControl.createMeasuresRegion(cube));
    }
    cacheControl.flushSchema(schema);
    cacheControl.flushSchemaCache();
    session.invalidate();
    //mondrian.rolap.CachePool.instance().flush();
}


%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vide cache Mondrian</title>
    </head>
    <body>
        <h1>Nettoyé</h1>
        <p><%=rep%>
        <a href="index.jsp">back to index</a>
    </body>
</html>
