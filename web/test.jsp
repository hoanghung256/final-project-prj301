<%-- 
    Document   : test
    Created on : Jun 2, 2024, 2:17:38 PM
    Author     : hoang hung 
--%>

<%@ include file="layout/main-header.jsp" %>

<section>
    <h1>Hello</h1>
    <c:forEach var="v" items="${data}">
        <h3>${v}</h3>
    </c:forEach>
</section>

<%@ include file="layout/main-footer.jsp" %>

