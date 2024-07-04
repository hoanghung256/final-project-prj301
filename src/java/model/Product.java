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
    private int quantity;
    private int price;
    private String description;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;
    private int totalBuy;
    private String avatarUrl;

    public Product() {
    }
    
    public Product(int id, int price) {
        this.id = id;
        this.price = price;
    }

    public Product(int id, String productName, int price, String avatarUrl) {
        this.id = id;
        this.productName = productName;
        this.price = price;
        this.avatarUrl = avatarUrl;
    }

    public Product(int id, String productName, int sellBy, int categoryId, int quantity, int price, String description, LocalDateTime createAt, LocalDateTime updateAt, int totalBuy, String avatarUrl) {
        this.id = id;
        this.productName = productName;
        this.sellBy = sellBy;
        this.categoryId = categoryId;
        this.quantity = quantity;
        this.price = price;
        this.description = description;
        this.createAt = createAt;
        this.updateAt = updateAt;
        this.totalBuy = totalBuy;
        this.avatarUrl = avatarUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id= id;
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

    public int getcategoryId() {
        return categoryId;
    }

    public void setcategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        return "Product{" + "productName=" + productName + ", sellBy=" + sellBy + ", categoryId=" + categoryId + ", quantity=" + quantity + ", price=" + price + ", description=" + description + ", createAt=" + createAt + ", updateAt=" + updateAt + ", totalBuy=" + totalBuy + ", avatarUrl=" + avatarUrl + '}';
    }
}
