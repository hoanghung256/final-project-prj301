<%-- 
    Document   : customer-navbar
    Created on : Jun 27, 2024, 9:56:36 PM
    Author     : hoang hung 
--%>

<%@ include file="./main-header.jsp" %>

<style>
    .navbar .form-control {
        width: 50rem;
        height: 2.5rem;
    }
    
    .btn {
        border-color: black;
    }
    
    main {
        background-color: rgba(241, 241, 241, 1);
    }
    
    .body-content {
        margin-left: 5rem;
        margin-right: 5rem;
        width: 90rem;
        min-height: 40rem;
    }
</style>

<header class="pb-5">
    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light py-3" style="background-color: #e3f2fd">
        <div class="container">
            <a class="navbar-brand" href="#">LOGO</a>
            <div class="mx-auto">
                <form class="d-flex" action="#" method="GET">
                    <input class="form-control me-2 p-2" type="search" aria-label="Search">
                    <button class="btn" type="submit">Search</button>
                </form>
            </div>
        </div>
    </nav>
</header>

<main>
    <div class="body-content mt-4">
        