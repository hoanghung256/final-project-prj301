package model;

import enums.OrderStatus;

public class OrderDetail {
    private int detailId;
    private int productId;
    private int orderId;
    private int quantity;
    private int price;
    private int totalPrice;
    private String productName;
    private String avatarUrl;
    private OrderStatus status;
    private String deliveryAddress;

    public OrderDetail(int detailId, int productId, int orderId, int quantity, int price, int totalPrice, OrderStatus status,
            String deliveryAddress, String productName, String avatarUrl) {
        this.detailId = detailId;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.price = price;
        this.totalPrice = totalPrice;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
        this.productName = productName;
        this.avatarUrl = avatarUrl;
        
    }

    public OrderDetail(int detailId, int productId, int orderId, int quantity, int totalPrice, OrderStatus status,
            String deliveryAddress) {
        this.detailId = detailId;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.status = status;
        this.deliveryAddress = deliveryAddress;
    }

    public int getDetailId() {
        return detailId;
    }

    public void setDetailId(int detailId) {
        this.detailId = detailId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
    
    

    @Override
    public String toString() {
        return "OrderDetail{" + "detailId=" + detailId + ", productId=" + productId + ", orderId=" + orderId + ", quantity="
                + quantity + ", totalPrice=" + totalPrice + ", status=" + status + ", deliveryAddress="
                + deliveryAddress + "}";
    }
}
