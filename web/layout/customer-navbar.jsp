<%-- 
    Document   : customer-navbar
    Created on : Jun 27, 2024, 9:56:36 PM
    Author     : hoang hung 
--%>

<%@ include file="./main-header.jsp" %>

<style>
    .navbar-custom {
        background-color: #C1F2C0; 
        height: 70px;
        
    }

    .navbar .form-control {
        width: 35rem; 
        height: 2.5rem;
    }

    .btn {
        border-color: black;
        background-color: #f4f4f4;
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
  
    .logo {
        width: 2.5rem;
        height: 2.5rem;
        border-radius: 50%;
        margin-left: 5px; 
    }

    .navbar-custom .logo-account {
        margin-left: 15px; 
    }

    .navbar-custom .logo-extra {
        width: 3rem; 
        height: 3rem; 
    }
</style>


<header class="pb-5">
    <nav class="navbar fixed-top navbar-expand-lg navbar-custom py-3">
        <div class="container d-flex justify-content-between align-items-center">
            <a class="navbar-brand" href="#">LOGO</a>
            <div class="mx-auto d-flex align-items-center flex-grow-1">
                <form class="d-flex flex-grow-1" action="home-page" method="GET">
                    <input class="form-control me-2 p-2" type="search" name="searchTerm" placeholder="Search products..." aria-label="Search">
                    <button class="btn" type="submit">Search</button>
                    <input type="hidden" name="command" value="SEARCH">
                </form>
                <div class="d-flex align-items-center ml-3">
                    <a href="customer/cart.jsp"> <!-- Liên k?t ??n trang gi? hàng -->
                        <img src="https://png.pngtree.com/png-clipart/20230812/original/pngtree-trolley-symbol-buying-basket-vector-picture-image_10434634.png" alt="Cart Logo" class="logo logo-extra">
                    </a>
                    <a href="customer/account.jsp"> <!-- Liên k?t ??n trang tài kho?n -->
                        <img src="https://static.vecteezy.com/system/resources/previews/006/732/119/large_2x/account-icon-sign-symbol-logo-design-free-vector.jpg" alt="Account Logo" class="logo logo-account">
                    </a>
                </div>
            </div>
        </div>
    </nav>
</header>

<main>
    <div class="body-content mt-4">
