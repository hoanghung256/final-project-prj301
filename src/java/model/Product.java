/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDateTime;

/**
 *
 * @author MinhThang
 */
public class Product {
    private int id;
    private String productName;
    private int sellBy;
    private int categoryId;
    private String description;
    private int price;
    private int quantity;
    private int sold;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int totalBuy;
    private String avatarUrl;

    public Product() {
    }

    public Product(int id, String productName, int sellBy, int categoryId, String description, int price, int quantity, int sold, LocalDateTime createAt, LocalDateTime updateAt, int totalBuy, String avatarUrl) {
        this.id = id;
        this.productName = productName;
        this.sellBy = sellBy;
        this.categoryId = categoryId;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
        this.sold = sold;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.totalBuy = totalBuy;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getSellBy() {
        return sellBy;
    }

    public void setSellBy(int sellBy) {
        this.sellBy = sellBy;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getSold() {
        return sold;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    public int getTotalBuy() {
        return totalBuy;
    }

    public void setTotalBuy(int totalBuy) {
        this.totalBuy = totalBuy;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", productName=" + productName + ", sellBy=" + sellBy + ", categoryId=" + categoryId + ", description=" + description + ", price=" + price + ", quantity=" + quantity + ", sold=" + sold + ", createAt=" + createAt + ", updateAt=" + updateAt + ", totalBuy=" + totalBuy + ", avatarUrl=" + avatarUrl + '}';
    }
    
    

    
}
