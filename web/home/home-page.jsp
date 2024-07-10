<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        .banner {
            width: 100%;
            height: 300px;
            background: url('your-banner-image.jpg') no-repeat center center;
            background-size: cover;
            display: flex;
            align-items: center;
            justify-content: center;
            text-align: center;
            color: white;
            position: relative;
            margin-bottom: 20px;
        }

        .banner::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background: rgba(0, 0, 0, 0.5);
        }

        .banner-content {
            z-index: 1;
        }

        .banner h1 {
            font-size: 48px;
            font-weight: bold;
        }

        .banner button {
            margin-top: 20px;
            padding: 10px 20px;
            font-size: 18px;
            border: none;
            background-color: #007bff;
            color: white;
            border-radius: 25px;
            transition: background-color 0.3s ease;
        }

        .banner button:hover {
            background-color: #0056b3;
        }

        .product-image img {
            width: 100%;
            height: 100%;
            object-fit: contain;
            transition: transform 0.3s ease;
        }

        .product-image:hover img {
            transform: scale(1.05);
        }

        .product-card {
            margin-bottom: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            transition: transform 0.3s ease, box-shadow 0.3s ease;
        }

        .product-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        }

        .product-card .card-body {
            padding: 15px;
        }

        .product-card .card-title a {
            color: #333;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .product-card .card-title a:hover {
            color: #007bff;
        }

        .pagination .page-item.active .page-link {
            background-color: #007bff;
            border-color: #007bff;
            color: white;
        }

        .pagination .page-link {
            color: #007bff;
        }

        .pagination .page-link:hover {
            background-color: #e9ecef;
            border-color: #dee2e6;
        }
    </style>
</head>
<body>
    <%@include file="../layout/customer-navbar.jsp" %>
    <div class="container col-12">
        <div class="banner">
            <div class="banner-content">
                <h1>Welcome to Our Store</h1>
                <button class="btn btn-primary">Shop Now</button>
            </div>
        </div>

        <div class="row">
            <div class="col-12">
                <h3>Suggest Today</h3>
            </div>
        </div>

        <div class="row">
            <c:forEach var="product" items="${products}">
                <div class="col-md-3 product-card">
                    <div class="card">
                        <a href="ProductDetailsController?command=LOAD&id=${product.id}">
                            <div class="product-image img-fluid">
                                <img src="${product.avatarUrl}" alt="${product.productName}" class="img-fluid">
                            </div>
                        </a>
                        <div class="card-body">
                            <h5 class="card-title">
                                <a href="ProductDetailsController?command=LOAD&id=${product.id}" style="text-decoration: none;">
                                    ${product.productName}
                                </a>
                            </h5>
                            <p class="card-text">On sale</p>
                            <div class="row">
                                <div class="col-6">
                                    <p class="card-text">Price: ${product.price}</p>
                                </div>
                                <div class="col-6">
                                    <p class="card-text">Sold: ${product.sold}</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <c:if test="${currentPage > 1}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value='home-page'><c:param name='command' value='LIST'/><c:param name='page' value='${currentPage - 1}'/></c:url>" tabindex="-1">Previous</a>
                </li>
            </c:if>
            <c:forEach var="i" begin="1" end="${totalPages}">
                <li class="page-item ${i == currentPage ? 'active' : ''}">
                    <a class="page-link" href="<c:url value='home-page'><c:param name='command' value='LIST'/><c:param name='page' value='${i}'/></c:url>">${i}</a>
                </li>
            </c:forEach>
            <c:if test="${currentPage < totalPages}">
                <li class="page-item">
                    <a class="page-link" href="<c:url value='home-page'><c:param name='command' value='LIST'/><c:param name='page' value='${currentPage + 1}'/></c:url>">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>
    <%@include file="../layout/customer-footer.jsp" %>

    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.11.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
