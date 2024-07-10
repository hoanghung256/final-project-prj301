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

    .navbar-brand {
        font-size: 1.5rem;
        font-weight: bold;
    }

    .navbar {
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }

    .navbar .form-control {
        border-radius: 25px;
        border: 2px solid #007bff;
        transition: border-color 0.3s ease;
    }

    .navbar .form-control:focus {
        border-color: #0056b3;
    }

    .navbar .btn {
        border-radius: 25px;
        background-color: #007bff;
        color: white;
        transition: background-color 0.3s ease, border-color 0.3s ease;
    }

    .navbar .btn:hover {
        background-color: #0056b3;
        border-color: #0056b3;
    }

    .navbar .navbar-icons a {
        margin-left: 15px;
        color: #333;
        transition: color 0.3s ease;
    }

    .navbar .navbar-icons a:hover {
        color: #007bff;
    }

    .navbar .navbar-icons .icon {
        width: 1.5rem;
        height: 1.5rem;
    }

    .navbar-brand img {
        max-height: 50px; /* Adjust the height as needed */
        height: auto;
        width: auto;
    }
</style>

<header class="pb-5">
    <nav class="navbar fixed-top navbar-expand-lg navbar-light bg-light py-3" style="background-color: #e3f2fd">
        <div class="container">
            <div class="d-flex justify-content-between w-100">
                <a class="navbar-brand" href="home">
                    <img src="image/logo2.png" alt="Logo">
                </a>
                <div class="mx-auto">
                    <form class="d-flex" action="#" method="GET">
                        <input class="form-control me-2 p-2" type="search" aria-label="Search">
                        <button class="btn" type="submit">Search</button>
                    </form>
                </div>
                <div class="navbar-icons d-flex align-items-center">
                    <a href="cart" title="Cart">
                        <img src="https://img.icons8.com/?size=100&id=85080&format=png&color=000000" alt="Cart" class="icon">
                    </a>
                    <a href="customer-account" title="User Profile">
                        <img src="https://img.icons8.com/?size=100&id=15263&format=png&color=000000" alt="User Profile" class="icon">
                    </a>
                </div>
            </div>
        </div>
    </nav>
</header>

<main>
    <div class="body-content mt-4">
