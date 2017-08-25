<%-- 
    Document   : home
    Created on : Aug 19, 2017, 6:36:34 PM
    Author     : dglunts
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <c:set var="ctxPatth" value="${pageContext.request.contextPath}" />
        Context Path ctxPath: <c:out value="${ctxPath}" /> 
<c:set var="serCtx" value="${pageContext.servletContext.contextPath}" />        
Servlet Context <c:out value="${serCtx}" />
        
        <h2>Hi jobs@perfekt.software!</h2>
        <p>
            This is very limited test app aimed to demonstrate some aspects of,
            first of all, spring-security.
            As a matter of fact, it turned the most complex part of the app.
            I needed to look thru several "big docs" to more or less realize
            how it works. To be frank, I still do not :-)
            Actually, some five years ago I created an app using spring-security 
            framework, but it was completely xml-configured and, whats'more,
            so long ago...
            To logout please don't forget to click logout link
            in upper-left corner. This will clear HttpSession and
            Authentication attributes.
            So, enjoy or curse :-)
        </p>
        <button onclick="window.location.href='login'">Login</button>
        <button onclick="window.location.href='registration'">Registration</button>
    </body>
</html>
