<%-- 
    Document   : dbase_error
    Created on : Aug 24, 2017, 7:45:12 AM
    Author     : dglunts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Runtime Error Page</title>
    </head>
    <body>
        <a href="<c:url value="/logout" />">Logout</a>        
        <h2>Runtime error occured!</h2>
        Message: <c:out value="${message}" />
        <br/>
        Specific Message: <c:out value="${s_message}" />
    </body>
</html>
