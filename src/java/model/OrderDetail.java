package model;

import enums.OrderStatus;

public class OrderDetail {
    private int detailId;
    private int productId;
    private int orderId;
    private int quantity;
    private int totalPrice;
    private OrderStatus status;
    private String deliveryAddress;

    public OrderDetail() {
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

    @Override
    public String toString() {
        return "OrderDetail{" + "detailId=" + detailId + ", productId=" + productId + ", orderId=" + orderId + ", quantity="
                + quantity + ", totalPrice=" + totalPrice + ", status=" + status + ", deliveryAddress="
                + deliveryAddress + "}";
    }
}
