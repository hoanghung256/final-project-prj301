package model;

import java.time.LocalDateTime;

public class Order {
    private int id;
    private int userId;
    private LocalDateTime orderAt;
    private boolean isPay;
    private String paymentType;

    public Order() {
    }

    public Order(int id, int userId, LocalDateTime orderAt, boolean isPay, String paymentType) {
        this.id = id;
        this.userId = userId;
        this.orderAt = orderAt;
        this.isPay = isPay;
        this.paymentType = paymentType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public LocalDateTime getOrderAt() {
        return orderAt;
    }

    public void setOrderAt(LocalDateTime orderAt) {
        this.orderAt = orderAt;
    }

    public boolean isPay() {
        return isPay;
    }

    public void setPay(boolean pay) {
        isPay = pay;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
}
