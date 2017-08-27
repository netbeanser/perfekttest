<%-- 
    Document   : registration
    Created on : Aug 22, 2017, 3:36:11 AM
    Author     : dglunts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
--%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">          
        <link rel="stylesheet" href="<c:url value='/resources/css/perfekt.css' />" >       
        <script  src='<c:url value="/resources/js/perfekt.js" />'></script>             
        <title>Registration page</title>
    </head>
    <body>
        <a href="<c:url value="/logout" />">Logout</a>
        <h2>Enter username and password</h2>

        <c:set var="servletCtx" value="${pageContext.servletContext.contextPath}" />        
        <c:if test="${error ne null}">
            <p class="error">
                <c:out value="${error}" />
            </p>
        </c:if>

        <form action="${servletCtx}/registration" method="POST"
              onsubmit="return checkUserDataValid(document.forms[0]);">
        <div>
          
            <table>
                <tr>
                    <td>User Name :</td>
                    <td><input type="text" name="username" id="username"/></td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><input type="password" name="password" id="password" /></td>
                </tr>
                <tr>
                    <td>Email :</td>
                    <td><input type="text" name="email" id="email" /></td>
                </tr>                
                <tr>
                    <td colspan="2"><input type="submit" value="Create new account"/></td>
                </tr>
                
            </table> 
            <input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}"/>            
        </div>    
        </form>    
    </body>
</html>