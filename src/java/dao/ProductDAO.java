/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Product;

/**
 *
 * @author ASUS
 */
public class ProductDAO {
    private final Connection conn ;

    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ProductDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    //READ
    public List<Product> getAllProduct(){
        List<Product> list = new ArrayList<>();
        String query = "select * from Product";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            while(rs.next()){
                list.add(new Product(rs.getInt("id"), 
                                     rs.getString("productName"),
                                     rs.getInt("sellBy"),
                                     rs.getInt("categoryID"),
                                     rs.getString("description"),
                                     rs.getInt("price"),
                                     rs.getInt("quantity"),
                                     rs.getInt("sold"),
                                     rs.getTimestamp("createAt").toLocalDateTime(),
                                     rs.getTimestamp("updateAt").toLocalDateTime(),
                                     rs.getInt("totalBuy"),
                                     rs.getString("avatarUrl")));  
            }
        } catch (Exception e) {
        }
        return list;
    }
    
    //CREATE
    public void createProduct(String productName, int sellBy, int categoryID, String description, int price, int quantity, String avatarUrl){
        String query =  "INSERT INTO [Product] ([productName], [sellBy], [categoryId], [description], [price], [quantity], [avatarUrl])\n" +
                        "VALUES\n" +
                        "(?, ?, ?, ?, ?, ?, ?)";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, productName);
            ps.setInt(2, sellBy);
            ps.setInt(3, categoryID);
            ps.setString(4, description);
            ps.setInt(5, price);
            ps.setInt(6, quantity);
            ps.setString(7, avatarUrl);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //DELETE
    public void deleteProduct(String id){
        String query =  "delete from Product \n" +
                        "where id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //UPDATE
    public void updateProduct(String id, String productName, int sellBy, int categoryId, String description, int price, int quantity, int sold, int totalBuy, String avatarUrl){
        String query =  "UPDATE [Product]\n" +
                        "SET\n" +
                        "    [productName] = ?,\n" +
                        "    [sellBy] = ?,\n" +
                        "    [categoryId] = ?,\n" +
                        "    [description] = ?,\n" +
                        "    [price] = ?,\n" +
                        "    [quantity] = ?,\n" +
                        "    [sold] = ?,\n" +
                        "    [updateAt] = GETDATE(),\n" +
                        "    [totalBuy] = ?,\n" +
                        "    [avatarUrl] = ?\n" +
                        "WHERE [id] = ?";
        
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, productName);
            ps.setInt(2, sellBy);
            ps.setInt(3, categoryId);
            ps.setString(4, description);
            ps.setInt(5, price);
            ps.setInt(6, quantity);
            ps.setInt(7, sold);
            ps.setInt(8, totalBuy);
            ps.setString(9, avatarUrl);
            ps.setString(10, id);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
    
    //search by id
    public Product searchById(String id){
        String query =  "select * from Product\n" +
                        "where id = ?";
        try {
            ps = conn.prepareStatement(query);
            ps.setString(1, id);
            rs = ps.executeQuery();
            while(rs.next()){
                return new Product(rs.getInt("id"), 
                                     rs.getString("productName"),
                                     rs.getInt("sellBy"),
                                     rs.getInt("categoryID"),
                                     rs.getString("description"),
                                     rs.getInt("price"),
                                     rs.getInt("quantity"),
                                     rs.getInt("sold"),
                                     rs.getTimestamp("createAt").toLocalDateTime(), 
                                     rs.getTimestamp("updateAt").toLocalDateTime(),
                                     rs.getInt("totalBuy"),
                                     rs.getString("avatarUrl"));
            }
        } catch (Exception e) {
        }
        return null;
    }
    
}
