<%-- 
    Document   : product-details
    Created on : Jul 1, 2024, 4:39:01?PM
    Author     : ASUS
--%>
<%@ include file="../layout/customer-navbar.jsp" %>

            <style>
/*           body {
    font-family: Arial, sans-serif;
    margin: 0;
    padding: 0;
    background-color: #f4f4f4;
}*/

/*.container {
    max-width: 1200px;
    margin: 20px auto;
    padding: 20px;
    background-color: white;
    border-radius: 8px;
    box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
}*/

.product {
    display: flex;
    margin-bottom: 20px;
}

.product-image img {
    width: 500px;
    height: auto;
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
        </style>
<section>
        <div>
           <div class="product">
               <div class="product-image">
                   <img src="${product.avatarUrl}" alt="Product Image">
               </div>
               <div class="product-details">
                   <h2>${product.productName}</h2>
                   <div class="ratings">
                       <div class="stars">
                           <span>&#9733;</span>
                           <span>&#9733;</span>
                           <span>&#9733;</span>
                           <span>&#9733;</span>
                           <span>&#9734;</span> 
                       </div>
                       <span class="review-count">4.0 (200 Feedback)</span>
                       <span class="sold-count">| Sold: ${product.sold}</span>
                   </div>
                    <p class="price">Price: <span id="price">${product.price}</span> </p>
                    <p class="short-description">${product.description}</p>
                    <button class="buy-now">Buy Now</button>
                    <button class="add-to-cart">Add to cart</button>
                   <div class="share">
                       <span>Share:</span>
                       <a href="Facebook.com">Facebook</a>
                       <a href="#">Instagram</a>
                   </div>
               </div>
           </div>
       </div>
</section>
<%@ include file="../layout/customer-footer.jsp" %>
