<%-- 
    Document   : product-details
    Created on : Jul 1, 2024, 4:39:01 PM
    Author     : ASUS
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="../style/product-details.css"/>
        <title>JSP Page</title>
    </head>
    <body>
        <c:forEach items="${product}" var="x">
             <div class="container">
                <div class="product">
                    <div class="product-image">
                        <img src="${x.avatarUrl}" alt="Product Image">
                    </div>
                    <div class="product-details">
                        <h2>${x.productName}</h2>
                        <div class="ratings">
                            <div class="stars">
                                <span>&#9733;</span>
                                <span>&#9733;</span>
                                <span>&#9733;</span>
                                <span>&#9733;</span>
                                <span>&#9734;</span> 
                            </div>
                            <span class="review-count">4.0 (200 đánh giá)</span>
                            <span class="sold-count">| Sold: ${x.sold}</span>
                        </div>
                        <p class="price">Price: <span id="price">${x.price}</span> </p>
                        <p class="short-description">${x.description}</p>
                        <button class="buy-now">Mua ngay</button>
                        <button class="add-to-cart">Thêm vào giỏ hàng</button>
                        <div class="share">
                            <span>Share:</span>
                            <a href="Facebook.com">Facebook</a>
                            <a href="#">Instagram</a>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
   
    </body>
</html>
