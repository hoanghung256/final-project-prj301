<%-- 
    Document   : account
    Created on : Jul 8, 2024, 9:24:48 AM
    Author     : TRINHHUY
--%>

<%-- 
    Document   : account
    Created on : Jul 8, 2024, 9:24:48 AM
    Author     : TRINHHUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <title>Information account</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
        <style>
            .user-info {
                margin-top: 20px;
            }
            .center-content {
                height: 100vh;
            }
            .card {
                border: 2px solid #007bff;
                padding: 40px;
                border-radius: 10px;
            }
            .card-text {
                font-size: 20px;
                margin-bottom: 30px;
            }
            .card-text strong {
                font-size: 24px; /* Increase font size for labels */
            }
            .home-button {
                display: flex;
                justify-content: right;
                color:  #C1F2C0;
                padding-right: 90px;
            }
            .home-button a {
                color: #007bff;
            }
            .user-info h1 {
                display: flex;
                justify-content: center;
                color: #007bff;
            }
        </style>
    </head>
    <body>
        <%@include file="../layout/customer-navbar.jsp" %>

        <div class="container user-info">
            <h1>Information Account</h1>

            <c:if test="${not empty user}">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-text"><strong>UserName:</strong> <c:out value="${user.username}"/> </h4>
                        <h4 class="card-text"><strong>Email:</strong> <c:out value="${user.email}"/></h4>
                        <h4 class="card-text"><strong>Phone:</strong> <c:out value="${user.phone}"/></h4>
                        <h4 class="card-text"><strong>Gender:</strong> <c:out value="${user.gender}"/></h4>
                        <h4 class="card-text"><strong>Dob:</strong> <c:out value="${user.dob}"/></h4>
                        <h4 class="card-text"><strong>Address:</strong> <c:out value="${user.address}"/></h4>
                    </div>
                </div>
            </c:if>

            <c:if test="${empty user}">
                <p>Can't find.</p>
            </c:if>
        </div>
        <div class="home-button">
            <a href="home.jsp">Home</a>
        </div>

        <%@include file="../layout/customer-footer.jsp" %>

        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    </body>
</html>



