/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import enums.PaymentType;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;

/**
 *
 * @author hoang hung
 */
public class OrderDAO {
    
    private Connection conn;
    private final String CREATE_ORDER = "BEGIN TRY " +
                                        "    BEGIN TRANSACTION; " +
                                        "		INSERT INTO [Order]([userId], [paymentTypeId]) " +
                                        "		VALUES (?, (SELECT id FROM PaymentType WHERE paymentName=?)) " +
                                        "		DECLARE @NewID INT; " +
                                        "		SET @NewID = SCOPE_IDENTITY(); " +
                                        "    COMMIT TRANSACTION; " +
                                        "    SELECT @NewID AS newOrderID; " +
                                        "END TRY " +
                                        "BEGIN CATCH " +
                                        "    ROLLBACK TRANSACTION; " +
                                        "    SELECT ERROR_MESSAGE() AS ErrorMessage; " +
                                        "END CATCH; ";
    private final String ADD_ORDER_ITEMS = "INSERT INTO [OrderDetail]([orderId], [productId], [quantity], [totalPrice], [deliveryAddress])" 
                                        +   "VALUES (?, ?, ?, ?, ?)";

    public OrderDAO() {
        this.conn = DatabaseConnection.getConnection();
    }
    
    public int createOrderAndGetId(int userId, PaymentType paymentType) {
        int insertOrderId = 0;
        
        try {
            PreparedStatement stm = conn.prepareStatement(CREATE_ORDER);
            stm.setInt(1, userId);
            stm.setNString(2, paymentType.toString());
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                insertOrderId = rs.getInt("newOrderID");
            }
        } catch (SQLException e) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return insertOrderId;
    }
    
    public boolean addOrderItem(int orderId, String address, List<CartItem> items) {
        boolean isAdded = false;
        
        try {
            PreparedStatement stm = conn.prepareStatement(ADD_ORDER_ITEMS);
            
            for (CartItem item : items) {
                stm.setInt(1, orderId);
                stm.setInt(2, item.getProduct().getId());
                stm.setInt(3, item.getQuantity());
                stm.setInt(4, item.getTotalPrice());
                stm.setNString(5, address);
                
                stm.addBatch();
            }
            
            stm.executeBatch();
            isAdded = true;
        } catch (SQLException e) {
            isAdded = false;
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return isAdded;
    }
}
