package model;

public class OrderDetail {
    private int detailId;
    private int productId;
    private int orderId;
    private int quantity;
    private int totalPrice;
    private String deliveryStatus;
    private String deliveryAddress;

    public OrderDetail() {
    }

    public OrderDetail(int detailId, int productId, int orderId, int quantity, int totalPrice, String deliveryStatus, String deliveryAddress) {
        this.detailId = detailId;
        this.productId = productId;
        this.orderId = orderId;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
        this.deliveryStatus = deliveryStatus;
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

    public String getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(String deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }
}
