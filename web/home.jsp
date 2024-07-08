<%-- 
    Document   : home-page
    Created on : Jul 1, 2024, 5:15:12 PM
    Author     : This PC
--%>

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
                background-color: lightgrey;
                margin-bottom: 20px;
            }
            .product-image img {
                width: 100%;
                height: 100%;
                object-fit: contain;
            }


            .product-card {
                margin-bottom: 20px;
            }

        </style>
    </head>
    <body>
        <%@include file="../layout/customer-navbar.jsp" %>
        <div class="container">
            <div class="banner">
                <!-- Banner content here -->
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
                            <a href="product-detail?command=LOAD&id=${product.id}">
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
