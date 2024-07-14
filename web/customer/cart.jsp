<%-- 
    Document   : cart
    Created on : Jun 29, 2024, 3:58:25 PM
    Author     : hoang hung 
--%>

<%@ include file="../layout/customer-navbar.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<style>
    .order-estimate {
        min-height: 20rem;
        border-radius: 0.2rem;
        box-shadow: 0 0.5rem 1rem rgba(0, 0, 0, 0.15);
    }

    .img-thumbnail {
        width: 100px;
        height: 100px;
        object-fit: cover;
        border-radius: 0.2rem;
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
        font-size: 0.9rem;
        font-weight: 500;
    }

    .return-display {
        color: orangered;
        border: 0.5px solid orangered;
        border-radius: 0.1rem;
        padding: 0.1rem 0.5rem;
        font-size: 0.7rem;
    }

    .quantity-icon {
        cursor: pointer;
    }

    .table th, .table td {
        vertical-align: middle;
    }

    .table thead th {
        font-weight: 600;
        background-color: #f8f9fa;
        border-top: none;
        border-bottom: 1px solid #dee2e6;
        text-transform: uppercase;
    }

    .table tbody tr:hover {
        background-color: #f8f9fa;
    }

    .total-price {
        font-size: 1.1rem;
        font-weight: bold;
    }

    .btn-checkout {
        background-color: #28a745;
        border-color: #28a745;
        transition: all 0.3s ease;
    }

    .btn-checkout:hover {
        background-color: #218838;
        border-color: #1e7e34;
    }

    .text-danger {
        font-size: 0.9rem;
        font-weight: 500;
    }
</style>

<section>
    <div class="container-fluid">
        <div class="row">
            <div class="col-8 p-3">
                <table class="table">
                    <thead>
                        <tr>
                            <th scope="col" colspan="3" class="text-center p-3" style="width: 3rem;">Product</th>
                            <th scope="col" class="p-3">Price</th>
                            <th scope="col" class="p-3" style="width: 0.5rem;">Quantity</th>
                            <th scope="col" class="p-3">Total</th>
                            <th scope="col" class="p-3">Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${cartItems}">
                            <tr>
                                <th style="width: 5rem;">
                                    <input type="checkbox" onchange="handleChange(this)" id="checkbox-${item.id}">
                                </th>
                                <td style="width: 7rem;">
                                    <img id="avatar-${item.id}" src="${item.product.avatarUrl}" class="img-thumbnail">
                                </td>
                                <td class="ms-3" style="width: 15rem;">
                                    <div>
                                        <div class="product-name" id="product-name-${item.id}">${item.product.productName}</div>
                                        <div class="mt-2">
                                            <span class="return-display">Free return in 15 days</span>
                                        </div>
                                    </div>
                                </td>
                                <td id="price">${item.product.price}</td>
                                <td>
                                    <input type="number" id="quantity-${item.id}" value="${item.quantity}" class="border-0 w-50">
                                </td>
                                <td id="price" class="total-price text-info fs-5">${item.totalPrice}</td>
                                <td>
                                    <a href="cart?method=DELETE&itemId=${item.id}" onclick="return confirm('Are you sure you want to delete this item?');" class="text-decoration-none">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>       
                </table>
            </div>
            <div class="half-side col-4 p-3">
                <div class="order-estimate bg-white">
                    <div class="p-4">
                        <h4 class="text-center">Order Estimate</h4>
                        <div class="ms-3 mt-3">
                            <b>Subtotal:</b> <span class="subtotal" id="price">${subtotal}</span>
                        </div>
                        <div class="ms-3 mt-3">
                            <b>Shipping: </b> <span class="shipping" id="price">20000</span>
                        </div>
                        <hr class="py-3">
                        <div>
                            <p class="fs-4">Estimate Total: <span class="total fs-4 text-primary" id="price">${estimateTotal}</span></p>
                        </div>
                        <div class="d-grid mx-auto mt-5">
                            <button class="btn btn-success">
                                <a href="order?type=from-cart" id="submit-button" class="text-decoration-none text-white">Checkout</a>
                            </button>
                            <p class="text-danger">${error}</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<script type="text/javascript">
    document.querySelectorAll('input[type="checkbox"]').forEach(function (checkbox) {
        checkbox.addEventListener('change', function () {
            if (this.checked) {
                let submitButton = document.getElementById('submit-button');
                let itemId = this.id.slice(9);
                let quantityElement = document.getElementById("quantity-" + itemId);
                let quantity = quantityElement ? quantityElement.value : 0;

                submitButton.href = submitButton.href + "itemId=" + itemId + "&quantity=" + quantity + "&";

                console.log(submitButton.href + "itemId=" + this.id.slice(9) + "&quantity=" + (document.getElementById("quantity-" + this.id.slice(9)).textContent) + "&");
            } else {
                console.log('Checkbox ' + this.id + ' is unchecked');
            }
        });
    });

    function getSubTotalOfAllItems() {
        let total = 0;
        let rows = document.querySelectorAll("tbody tr");

        rows.forEach(row => {
            let price = parseFloat(row.querySelector(".total-price").textContent);

            total += price;
        });

        let subtotal = document.querySelector(".subtotal");
        subtotal.textContent = formatCurrency(total);

        let estimateTotal = document.querySelector(".total");
        let shipping = parseFloat(document.querySelector(".shipping").textContent);
        estimateTotal.textContent = formatCurrency((total + shipping));
    }

    document.querySelectorAll('input[type="checkbox"]').forEach(function(checkbox) {
    checkbox.addEventListener('change', function() {
        handleChange();
    });
});

document.querySelectorAll('input[type="number"]').forEach(function(input) {
    input.addEventListener('input', function() {
        handleChange();
    });
});

function handleChange() {
    let total = 0;
    let rows = document.querySelectorAll("tbody tr");

    rows.forEach(row => {
        let checkbox = row.querySelector("input[type='checkbox']");
        if (checkbox.checked) {
            let quantity = parseFloat(row.querySelector("input[type='number']").value);
            let price = parseFloat(row.querySelector(".total-price").textContent);
            total += quantity * price;
        }
    });

    let subtotal = document.querySelector(".subtotal");
    subtotal.textContent = formatCurrency(total);

    let shipping = parseFloat(document.querySelector(".shipping").textContent);
    let estimateTotal = total + shipping;
    let totalElement = document.querySelector(".total");
    totalElement.textContent = formatCurrency(estimateTotal);

    // C?p nh?t l?i ???ng d?n cho nút Checkout
    updateCheckoutLink();

    console.log("Updated subtotal and total: " + subtotal.textContent + ", " + totalElement.textContent);
}

function updateCheckoutLink() {
    let submitButton = document.getElementById('submit-button');
    let selectedItems = document.querySelectorAll('input[type="checkbox"]:checked');
    let queryParams = [];

    selectedItems.forEach(item => {
        let itemId = item.id.slice(9);
        let quantityElement = document.getElementById("quantity-" + itemId);
        let quantity = quantityElement ? quantityElement.value : 0;
        queryParams.push(`itemId=${itemId}&quantity=${quantity}`);
    });

    let checkoutLink = "order?type=from-cart";
    if (queryParams.length > 0) {
        checkoutLink += "&" + queryParams.join("&");
    }

    submitButton.href = checkoutLink;
}

function getSubTotalOfAllItems() {
    let total = 0;
    let rows = document.querySelectorAll("tbody tr");

    rows.forEach(row => {
        let quantity = parseFloat(row.querySelector("input[type='number']").value);
        let price = parseFloat(row.querySelector(".total-price").textContent);
        total += quantity * price;
    });

    let subtotal = document.querySelector(".subtotal");
    subtotal.textContent = formatCurrency(total);

    let shipping = parseFloat(document.querySelector(".shipping").textContent);
    let estimateTotal = total + shipping;
    let totalElement = document.querySelector(".total");
    totalElement.textContent = formatCurrency(estimateTotal);
}

function formatCurrency(value) {
    return new Intl.NumberFormat('en-US', {
        style: 'currency',
        currency: 'VND'
    }).format(value);
}

getSubTotalOfAllItems();


</script>

<%@ include file="../layout/customer-footer.jsp" %>