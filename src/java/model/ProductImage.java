/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.time.LocalDate;

/**
 *
 * @author ADMIN
 */
public class ProductImage {
    
    private int id;
    private int productId;
    private String imageName;
    private String url;
    private LocalDate uploadedDate;   

    public ProductImage() {
    }

    public ProductImage(String url) {
        this.url = url;
    }

    public ProductImage(int id, int productId, String imageName, String url, LocalDate uploadedDate) {
        this.id = id;
        this.productId = productId;
        this.imageName = imageName;
        this.url = url;
        this.uploadedDate = uploadedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LocalDate getUploadedDate() {
        return uploadedDate;
    }

    public void setUploadedDate(LocalDate uploadedDate) {
        this.uploadedDate = uploadedDate;
    }

    @Override
    public String toString() {
        return "ProductImage{" + "id=" + id + ", productId=" + productId + ", imageName=" + imageName + ", url=" + url + ", uploadedDate=" + uploadedDate + '}';
    }
}
