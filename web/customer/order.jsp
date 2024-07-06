<%-- 
    Document   : order
    Created on : Jul 4, 2024, 2:22:34 PM
    Author     : hoang hung 
--%>

<%@ include file="../layout/customer-navbar.jsp" %>
<%@ page import="enums.PaymentType" %>

<style>
    .order-estimate {
        min-height: 20rem;
        border-radius: 0.2rem;
    }
    
    .img-thumbnail {
        width: 100px;
        height: 100px;
        object-fit: cover;
    }
    
    .product-name {
        display: -webkit-box;
        -webkit-line-clamp: 2;
        -webkit-box-orient: vertical;
        overflow: hidden;
        text-overflow: ellipsis;
        max-width: 15rem;
        max-height: calc(2 * 1.2em);
        line-height: 1.2em;
    }
    
    .return-display {
        color: orangered;
        border: 0.5px solid orangered;
        border-radius: 0.1rem;
        padding: 0.1rem;
        font-size: 0.7rem;
    }
    
    .quantity-icon {
        cursor: pointer;
    }
    
    .order-status-modal {
        
    }
</style>

<section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-8 p-3">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col" colspan="2" class="p-3" style="width: 3rem;">Product</th>
                            <th scope="col" class="p-3">Price</th>
                            <th scope="col" class="p-3" style="width: 0.5rem;">Quantity</th>
                            <th scope="col" class="p-3">Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:set var="totalPrice" value="0" />
                        <c:forEach var="item" items="${sessionScope.orderItems}">
                            <tr>
                                <td style="width: 7rem;">
                                    <img src="${item.product.avatarUrl}" class="img-thumbnail">
                                </td>
                                <td class="ms-3" style="width: 15rem;">
                                    <div>
                                        <div class="product-name">${item.product.productName}</div>
                                        <div class="mt-2">
                                            <span class="return-display">Free return in 15 days</span>
                                        </div>
                                    </div>
                                </td>
                                <td id="price">${item.product.price}</td>
                                <td>${item.quantity}</td>
                                <td id="price" class="total-price fs-5">${item.totalPrice}</td>
                                <c:set var="totalPrice" value="${totalPrice + item.totalPrice}" />
                            </tr>
                        </c:forEach>
                    </tbody>       
                </table>
            </div>
            <div class="half-side col-4 p-3">
                <div class="p-4 bg-white">
                    <form action="order" method="POST" class="order-estimate">
                        <h3 class="text-center">Order Information</h3>
                        <table>
                            <tr>
                                <td>Address</td>
                                <td>
                                    <textarea type="text" style="width: 15rem;" name="address" value="${sessionScope.userInfo.address}"></textarea>
                                </td>
                            </tr>
                             <tr>
                                <td>Payment type</td>
                                <td>
                                    <select name="paymentType">
                                        <c:forEach var="type" items="${PaymentType.values()}">
                                            <option value="${type}">${type}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td>Subtotal</td>
                                <td>
                                    <span class="subtotal" id="price">${totalPrice}</span>
                                </td>
                            </tr>
                            <tr>
                                <td>Shipping</td>
                                <td>
                                    <span class="shipping" id="price">20000</span>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2" class="py-3">
                                    <hr>
                                </td>
                            </tr>
                            <tr>
                                <td class="fs-4">Total</td>
                                <td>
                                    <span class="total fs-4 text-primary" id="price">${20000 + totalPrice}</span>
                                </td>
                            </tr>
                        </table>
                        <div class="d-grid mx-auto mt-5 gap-3">
                            <button class="btn btn-success" type="submit">Place Order</button>
                            <button class="btn btn-danger">
                                <a href="order?method=DELETE" class="text-decoration-none text-white">Cancel</a>
                            </button>
                            <p class="text-danger">${error}</p>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="../layout/customer-footer.jsp" %>
