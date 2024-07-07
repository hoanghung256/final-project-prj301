<%-- 
    Document   : order-details
    Created on : Jul 5, 2024, 10:05:35?PM
    Author     : ASUS
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="../layout/customer-navbar.jsp" %>
<style>
    .content {
        flex: 1;
        padding-left: 20px;
    }

    .content h2 {
        margin-top: 0;
        font-size: 24px;
        margin-bottom: 20px;
    }

    .order-list {
        margin-top: 20px;
    }

    .order {
        background-color: #f9f9f9;
        padding: 10px;
        border: 1px solid #ddd;
        margin-bottom: 20px;
        border-radius: 4px;
    }

    .order-header {
        display: flex;
        justify-content: space-between;
        margin-bottom: 10px;
        font-size: 16px;
        color: #333;
    }

    .order-item {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }

    .order-item img {
        width: 100px;
        height: auto;
        margin-right: 10px;
    }

    .item-details {
        display: flex;
        flex-direction: column;
    }

    .item-name{
        margin: 0;
    }

    .item-quantity {
        font-size: 12px;
    }

    .order-footer {
        display: flex;
        justify-content: flex-end;
    }

    .order-footer .item-price, .total {
        background-color: #ff6600;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
        font-size: 16px;
        border-radius: 4px;
        margin-left: 10px;
    }
</style>

<section>
    <div>
        <div class="content">
            <h2>Order Status</h2>
            <c:forEach items="${list}" var="x">
                <div class="order-list">
                <div class="order">
                    <div class="order-header">
                        <span class="order-id">Order ID: ${x.orderId}</span>
                    </div>
                    
                    <div class="order-item">
                        <img src="${x.avatarUrl}" alt="Product Image">
                        <div class="item-details">
                            <p class="item-name">Product Name: ${x.productName}</p>
                            <p class="item-quantity">Quantity: ${x.quantity}</p>
                            <p class="order-status">Status: ${x.status} </p>
                        </div>
                    </div>
                    
                    <div class="order-footer">
                        <button class="item-price">Price: <span id="price"> ${x.price}</button>
                        <button class="total">Total: <span id="price">${x.totalPrice}</button>
                    </div>
                </div>
            </div>
            </c:forEach>
            
        </div>
    </div>
</section>
<%@ include file="../layout/customer-footer.jsp" %>