<%-- 
    Document   : main-header.jsp
    Created on : Jun 2, 2024, 5:38:21 PM
    Author     : hoang hung 
--%>

<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${pageTitle}</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
    <style>
        .navbar-custom {
            background-color: #007bff;
        }
        .navbar-brand {
            font-size: 1.8rem;
            font-weight: bold;
            color: white;
        }
        .navbar-nav .nav-link {
            color: white !important;
            font-size: 1.1rem;
        }
        .navbar-nav .nav-link:hover {
            color: #ffcc00 !important;
        }
        .navbar-icons a {
            margin-left: 15px;
            color: white;
        }
        .navbar-icons a:hover {
            color: #ffcc00;
        }
        .navbar-icons .icon {
            width: 1.5rem;
            height: 1.5rem;
        }
    </style>
</head>
<body>
<header>
    <nav class="navbar navbar-expand-lg navbar-custom">
        <div class="container">
            <a class="navbar-brand" href="#">LOGO</a>
            <div class="mx-auto">
                <form class="d-flex" action="search" method="GET">
                    <input class="form-control me-2" type="search" name="query" placeholder="Search" aria-label="Search">
                    <button class="btn btn-light" type="submit">Search</button>
                </form>
            </div>
            <div class="navbar-icons d-flex align-items-center">
                <a href="cart" title="Cart">
                    <i class="fas fa-shopping-cart"></i>
                </a>
                <a href="customer-account" title="User Profile">
                    <i class="fas fa-user"></i>
                </a>
            </div>
        </div>
    </nav>
</header>
