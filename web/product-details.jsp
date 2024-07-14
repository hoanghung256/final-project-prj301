<%-- 
    Document   : product-details
    Created on : Jul 1, 2024, 4:39:01?PM
    Author     : ASUS
--%>
<%@ include file="../layout/customer-navbar.jsp" %>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<style>
    .product {
        display: flex;
        margin-bottom: 20px;
    }

    .product-image img {
        width: 100%;
        height: 400px;
        object-fit: cover;
        margin-bottom: 10px;
    }

    .product-details {
        margin-left: 20px;
    }

    .product-details h2 {
        font-size: 28px;
        margin-bottom: 10px;
    }

    .ratings {
        display: flex;
        align-items: center;
        margin-bottom: 10px;
    }

    .stars span {
        color: #ff6600;
        font-size: 20px;
        margin-right: 5px;
    }

    .review-count, .sold-count {
        font-size: 14px;
        color: #757575;
        margin-left: 10px;
    }

    .product-details .price {
        font-size: 24px;
        color: #e53935;
        margin-bottom: 10px;
    }

    .product-details .short-description {
        margin-bottom: 20px;
    }

    .product-details button {
        background-color: #ff6600;
        color: white;
        padding: 10px 20px;
        border: none;
        cursor: pointer;
        font-size: 16px;
        margin-right: 10px;
        border-radius: 4px;
    }

    .product-details button:hover {
        background-color: #e55d00;
    }

    .review {
        background-color: #f9f9f9;
        padding: 10px;
        margin-bottom: 10px;
        border-radius: 4px;
    }

    .review strong {
        display: block;
        margin-bottom: 5px;
    }

    .share {
        margin-top: 20px;
    }

    .share span {
        font-weight: bold;
    }

    .share a {
        color: #ff6600;
        text-decoration: none;
        margin-right: 10px;
    }

    .share a:hover {
        text-decoration: underline;
    }

    .product-image-detail {
        display: grid;
        grid-template-columns: repeat(4, 1fr);
        gap: 10px;
    }

    .product-image-detail img {
        width: 100%;
        height: 100px;
        object-fit: cover;
        cursor: pointer; /* Changes cursor to pointer */
    }

    .short-description {
        margin-top: 10px;
    }
</style>
<section>
    <div class="container">
        <div class="product row">
            <div class="col-6">
                <div class="product-image img-fluid">
                    <img id="mainImage" src="${product.avatarUrl}" alt="Main Product Image">
                </div>

                <div class="product-image-detail">
                    <img src="https://images.unsplash.com/photo-1595950653106-6c9ebd614d3a?q=80&w=1887&auto=format&fit=crop&ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D" alt="Product Thumbnail 1" onclick="changeImage(this)">
                    <img src="${product.avatarUrl}" onclick="changeImage(this)">
                    <img src="${product.avatarUrl}" onclick="changeImage(this)">
                    <img src="${product.avatarUrl}" onclick="changeImage(this)">
                </div>
            </div>

            <div class="product-details col-4">
                <div class="mt-2">                
                    <h2>${product.productName}</h2>
                </div>
                <div class="ratings">
                    <div class="stars">
                        <span>&#9733;</span>
                        <span>&#9733;</span>
                        <span>&#9733;</span>
                        <span>&#9733;</span>
                        <span>&#9734;</span>
                    </div>
                    <span class="review-count">4.0 (140 Feedback)</span>
                    <span class="sold-count">| Sold: ${product.sold}</span>
                </div>
                <p class="price" >Price: <span id="price">${product.price}</span> </p>
                <div class="d-flex">
                    <form action="order" method="POST">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="quantity" value="1">
                        <input type="hidden" name="type" value="buy-now">
                        <button type="submit" class="buy-now">Buy Now</button>
                    </form>
                    <form action="add-cart" method="GET">
                        <input type="hidden" name="productId" value="${product.id}">
                        <input type="hidden" name="productPrice" value="${product.price}">
                        <input type="hidden" name="quantity" value="1">
                        <button type="submit" class="add-to-cart">Add to cart</button>
                        <p style="color: green;">${message}</p>
                    </form>
                </div>
                <div class="share">
                    <span>Share:</span>
                    <a href="https://facebook.com">Facebook</a>
                    <a href="https://instagram.com">Instagram</a>
                </div>
                <p class="short-description">${product.description}</p>
            </div>
        </div>
    </div>
</section>
<script>
    function changeImage(element) {
        var mainImage = document.getElementById("mainImage");
        mainImage.src = element.src;
    }
</script>
<%@ include file="../layout/customer-footer.jsp" %>