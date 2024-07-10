<%-- 
    Document   : login
    Created on : Jul 2, 2024, 8:44:14 AM
    Author     : TRINHHUY
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Đăng nhập</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <!-- Your custom CSS -->
    
</head>
<body>
    <div class="container-fluid">
        <div class="row">
            <div class="col-md-6 login-section-wrapper">
                <!-- Khu vực đăng nhập -->
                <div class="login-wrapper my-auto">
                     <br> <br> <br>
                    <h2>Login</h2>
                     <br> <br> <br>
                    <form action="login" method="post">
                        <label for="username">Username:</label>
                        <input type="text" id="username" name="username" class="form-control" required>
                        <br>
                        <label for="password">Password:</label>
                        <input type="password" id="password" name="password" class="form-control" required>
                        <br>
                        <button type="submit" class="btn btn-primary btn-block">Đăng nhập</button>
                    </form>
                    <br>
                    <c:if test="${not empty errorMessage}">
                        <div style="color:red;">${errorMessage}</div>
                    </c:if>
                    <p>Does not have account, <a href="register.jsp">register now</a></p>
                </div>
            </div>
        </div>
    </div>

    <!-- Bootstrap JS and dependencies -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>