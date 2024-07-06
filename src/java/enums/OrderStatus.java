/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package enums;

/**
 *
 * @author hoang hung
 */
public enum OrderStatus {
    CONFIRMATION_WAITING("Confirmation waiting", "purpure"),
    PREPARING("Preparing", "yellow"),
    DELIVERING("Delivering", "blue"),
    DELIVERED("Delivered", "green");
    
    private String description;
    private String displayColor;

    private OrderStatus(String description, String displayColor) {
        this.description = description;
        this.displayColor = displayColor;
    }
    
    public String getDisplayColor() {
        return this.displayColor;
    }
    
    @Override
    public String toString() {
        return this.description;
    }
}
