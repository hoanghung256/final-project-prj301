/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import enums.OrderStatus;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author ASUS
 */
public class OrderDAO {
    private final Connection conn ;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public OrderDAO() {
        this.conn = DatabaseConnection.getConnection();
    }
    
    public List<OrderDetail> getOrderDetailsNotYetDelivered() {
        List<OrderDetail> list = new ArrayList<>();
        String query =  "select OrderDetail.*,  Product.productName, Product.avatarUrl, Product.price, (OrderDetail.quantity * Product.price) AS totalPrice\n" +
                        "from OrderDetail\n" +
                        "join Product on OrderDetail.productId = Product.id\n" +
                        "where status IN ('DELIVERED', 'CONFIRMATION_WAITING', 'PREPARING')";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new OrderDetail(rs.getInt("id"),
                        rs.getInt("productId"),
                        rs.getInt("orderId"),
                        rs.getInt("quantity"),
                        rs.getInt("price"),
                        rs.getInt("totalPrice"),
                        OrderStatus.valueOf(rs.getString("status")),
                        rs.getString("deliveryAddress"),
                        rs.getString("productName"),
                        rs.getString("avatarUrl")));
            }
        } catch (Exception e) {
        }
        return list;
    }
}