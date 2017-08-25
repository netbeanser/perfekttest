<%-- 
    Document   : admin
    Created on : Aug 22, 2017, 2:13:50 AM
    Author     : dglunts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/perfekt.css' />" >                       
        <title>Admin Page</title>
    </head>
    <body>
        <a href="<c:url value="/logout" />">Logout</a>
        <h2>Admin Page</h2>

        <h3>You're Admin? Really?</h3>
        <h3>No kidding? </h3>
        <h3>H-m-m-m... Well...</h3>
        <h3>Put out more flags!!! </h3>          

        <button onclick="window.location.href='<c:url value="/UnderConstruction" />'">Add book</button>
        <button onclick="window.location.href='<c:url value="/registration" />'">Add user</button>
        <button onclick="window.location.href='<c:url value="/userlist" />'">List users</button>
    </body>
</html>
