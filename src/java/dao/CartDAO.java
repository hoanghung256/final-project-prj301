/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.CartItem;
import model.Product;

/**
 *
 * @author hoang hung
 */
public class CartDAO {
    
    private Connection conn;
    private final String GET_CART_ITEMS_BY_USER_ID = "SELECT " +
                                                    "	CartItem.id, " +
                                                    "	Product.id AS 'productId', " +
                                                    "	Product.productName, " +
                                                    "	Product.price, " +
                                                    "	Product.avatarUrl, " +
                                                    "	CartItem.quantity, " +
                                                    "	CartItem.quantity * Product.price AS 'totalPrice' " +
                                                    "FROM Cart " +
                                                    "		INNER JOIN CartItem ON Cart.id = CartItem.cartId " +
                                                    "		INNER JOIN Product ON CartItem.productId = Product.id " +
                                                    "WHERE Cart.userId=?";
    private final String GET_CART_ITEMS_BY_ITEMS_ID = "SELECT " +
                                                    "	CartItem.id, " +
                                                    "	Product.id AS 'productId', " +
                                                    "	Product.productName, " +
                                                    "	Product.price, " +
                                                    "	Product.avatarUrl, " +
                                                    "	CartItem.quantity, " +
                                                    "	CartItem.quantity * Product.price AS 'totalPrice' " +
                                                    "FROM Cart " +
                                                    "		INNER JOIN CartItem ON Cart.id = CartItem.cartId " +
                                                    "		INNER JOIN Product ON CartItem.productId = Product.id " +
                                                    "WHERE CartItem.id IN";
    private final String ADD_ITEM_INTO_CART_BY_USER_ID = "INSERT INTO CartItem(cartId, productId, quantity) " +
                                                        "VALUES (" +
                                                        "	(SELECT cartId FROM Cart WHERE userId=?), " +
                                                        "	?, " +
                                                        "	? " +
                                                        ")";
    private final String DELETE_ITEM_BY_ITEM_ID = "DELETE FROM CartItem WHERE id=?";
//    private final String UPDATE_ITEM_QUANTITY_BY_ID = "UPDATE FROM CartItem SET quantity=? WHERE id=?";

    public CartDAO() {
        this.conn = DatabaseConnection.getConnection();
    }
    
    public List<CartItem> getOrderItemsByUserId(int userId) {
        List<CartItem> items = new ArrayList<>();
        
        try {
            PreparedStatement stm = conn.prepareStatement(GET_CART_ITEMS_BY_USER_ID);
            stm.setInt(1, userId);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                items.add(
                        new CartItem(
                                rs.getInt("id"),
                                new Product(
                                        rs.getInt("productId"),
                                        rs.getNString("productName"),
                                        rs.getInt("price"),
                                        rs.getString("avatarUrl")
                                ),
                                rs.getInt("quantity"),
                                rs.getInt("totalPrice")
                        )
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return items;
    }
    
    public List<CartItem> getCartItemByItemIds(String[] ids) {
        List<CartItem> items = new ArrayList<>();
        
        try {
            String query = insertIdIntoQuery(GET_CART_ITEMS_BY_ITEMS_ID, ids);
            PreparedStatement stm = conn.prepareStatement(query);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                items.add(
                        new CartItem(
                                rs.getInt("id"),
                                new Product(
                                        rs.getInt("productId"),
                                        rs.getNString("productName"),
                                        rs.getInt("price"),
                                        rs.getString("avatarUrl")
                                ),
                                rs.getInt("quantity"),
                                rs.getInt("totalPrice")
                        )
                );
            }
        } catch (SQLException e) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return items;
    }
    
    public boolean addItemIntoCartByUserId(CartItem item, int userId) {
        boolean isAdded = false;
        
        try {
            PreparedStatement stm = conn.prepareStatement(ADD_ITEM_INTO_CART_BY_USER_ID);
            stm.setInt(1, userId);
            stm.setInt(2, item.getProduct().getId());
            stm.setInt(2, item.getQuantity());
            
            int affected = stm.executeUpdate();
            isAdded = !Boolean.parseBoolean(String.valueOf(affected));
        } catch (SQLException e) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return isAdded;
    }
    
    public void deleteItemByCartItem(List<CartItem> items) {
        for (CartItem item : items) {
            deleteItemById(item.getId());
        }
    }
    
    public boolean deleteItemById(int itemId) {
        boolean isDeleted = false;
        
        try {
            PreparedStatement stm = conn.prepareStatement(DELETE_ITEM_BY_ITEM_ID);
            stm.setInt(1, itemId);
            
            int affected = stm.executeUpdate();
            isDeleted = !Boolean.parseBoolean(String.valueOf(affected));
        } catch (SQLException e) {
            Logger.getLogger(CartDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return isDeleted;
    }
    
    private String insertIdIntoQuery(String query, String[] ids) {
        StringBuilder queryBuilder = new StringBuilder(query);
        
        queryBuilder.append("(");
        for (int i = 0; i < ids.length; i++) {
            queryBuilder.append(ids[i]);
            if (i == ids.length - 1) {
                break;
            }
            queryBuilder.append(",");
        }
        queryBuilder.append(")");
        
        return queryBuilder.toString();
    }
}
