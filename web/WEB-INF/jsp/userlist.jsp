<%-- 
    Document   : userlist
    Created on : Aug 23, 2017, 12:24:40 PM
    Author     : dglunts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/perfekt.css' />" >               
        <title>User List</title>
    </head>
    <body>        
        <a href="<c:url value="/logout" />">Logout</a>
        <h3>Что, одминко, заскучал?</h3>
        <table>
            <tr>            
                <th>UserId</th>
                <th>User Name</th>
                <th>Role</th>
                <th>Password</th>
            </tr>
            <c:forEach items="${userList}" var="user" varStatus="theCount" >
               <c:choose>
                    <c:when test="${(theCount.index % 2 eq 0)}">
                        <tr class="oddrow">
                    </c:when>        
                    <c:otherwise>
                        <tr class="evenrow">    
                    </c:otherwise>
                </c:choose>                         
                <td>${user.user_id}</td>
                <td>${user.username}</td>
                <td>${user.rolename}</td>
                <td>Одминко,это нельзя показывать! Ишь!</td>
                        </tr>
            </c:forEach>
        </table>
   
        <button onclick="history.back()">&lt;&lt;Back</button>
    </body>
</html>
