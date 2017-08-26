<%-- 
    Document   : login
    Created on : Aug 19, 2017, 6:54:56 PM
    Author     : dglunts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"  %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">        

        <script src="<c:url value='/resources/js/perfekt.js' />">        
        </script>             
        

        <link rel="stylesheet" type="text/css" 
            href="<c:url value='/resources/css/perfekt.css' />" 
            media="screen"/>        

        <title>Login page</title>
    </head>
    
    <body>
        <a href="<c:url value="/logout" />">Logout</a>
        <h2>Login with username and password</h2>
        
        <c:set var="servletCtx" value="${pageContext.servletContext.contextPath}" />        
        <form action="${servletCtx}/login" method="POST">
        <div>
            <c:if test="${param.error != null}">       
		<p class="error">
			Invalid username and password.
		</p>
            </c:if>
            <c:if test="${param.logout != null}">      
		<p class="message">
			You have been logged out.
		</p>
            </c:if>            
            <table>
                <tr>
                    <td>User Name :</td>
                    <td><input type="text" name="username" id=username" tabindex="1"/></td>
                </tr>
                <tr>
                    <td>Password :</td>
                    <td><input type="password" name="password" id="password" tabindex="2"/></td>
                </tr>
                <tr>
                    <td><input type="submit" value="Login"/></td>
                </tr>
            </table> 
            <input type="hidden" name="${_csrf.parameterName}"
		value="${_csrf.token}"/>            
        </div>    
        </form>  
        <button onclick="history.back()">&lt;&lt;Back</button>
    </body>
</html>
