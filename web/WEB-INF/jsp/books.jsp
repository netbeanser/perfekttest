<%-- 
    Document   : books
    Created on : Aug 21, 2017, 5:04:27 AM
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
        <link rel="stylesheet" type="text/css" href="<c:url value='/resources/css/perfekt.css' />" >       
        <script  src='<c:url value="/resources/js/perfekt.js" />'>        
        </script> 
              
        <title>Book List</title>
    </head>
    <body onload="booksPageLoaded(<c:out value='${totalRows}' />)" 
          onunload="booksPageUnloaded()">
        <a href="<c:url value="/logout" />">Logout</a>
        <h3>Что,<c:out value="${username}"/>, тебя читать научили? И когда успели!</h3>
        <table>
            <tr>  
                <th>Id</th>
                <th>Author</th>
                <th>Title</th>
                <th>Icon</th>
                <th>Fav</th>
            </tr>
            <c:forEach items="${bookList}" var="book" varStatus="theCount" >
               <c:choose>
                    <c:when test="${(theCount.index % 2 eq 0)}">
                        <tr class="oddrow">
                    </c:when>        
                    <c:otherwise>
                        <tr class="evenrow">    
                    </c:otherwise>
                </c:choose> 
                <td>${book.book_id}</td>
                <td>${book.author}</td>
                <td>${book.title}</td>
                <td><img src='<c:url value="${book.imgpath}"/>'> </td>
                <td>
                    <c:choose>
                        <c:when test="${(book.user_id eq 0) ||
                                (book.user_id ne logged_in_user_id )}">
                            <input type="checkbox"
                                onclick='favChanged(this,"<c:out value="${username}"/>");'
                                id="${book.book_id}" >                                
                        </c:when>
                        <c:otherwise> 
                            <input type="checkbox" 
                                id="${book.book_id}"
                                onclick='favChanged(this,"<c:out value="${username}"/>");'
                                checked >                           
                        </c:otherwise>
                    </c:choose>
                </td>
                        </tr>
            </c:forEach>
        </table> 
        
        <button id="prev" onclick="prevNext(-10,
                <c:out value='${totalRows}' />);">&lt;&lt;</button>        
        <button id="next" onclick="prevNext(10,
                <c:out value='${totalRows}' />);">&gt;&gt;</button>

        
        <c:choose>
            <c:when test="${fav eq null}">
                <button onclick='window.location.href="<c:url value='/books/fav?offset=0'/>"'>Show your favorites</button>
            </c:when>
            <c:otherwise>                
                <button onclick='window.location.href="<c:url value='/books/all?offset=0'/>"'>Show entire book list</button>
            </c:otherwise>
        </c:choose>                        
        <div>
            <p style="font-weight: bold;">WebSocket console</p>
            <div id="console" />
        </div>

                <button onclick="history.back()">&lt;&lt;Back</button>
    </body>
</html>
