/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.List;
import java.util.ArrayList;
import model.Product;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author This PC
 */
public class ProductDAO {

    private final Connection conn;
    private PreparedStatement ps = null;
    private ResultSet rs = null;

    public ProductDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    public List<Product> getProducts(int page, int pageSize) throws SQLException {
        if (pageSize <= 0) {
            throw new IllegalArgumentException("Page size must be greater than zero.");
        }
        List<Product> list = new ArrayList<>();
        String query = "SELECT * FROM Product ORDER BY id OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";
        try (PreparedStatement stm = conn.prepareStatement(query)) {
            stm.setInt(1, (page - 1) * pageSize);
            stm.setInt(2, pageSize);
            ResultSet rs = stm.executeQuery();
            while (rs.next()) {
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
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    public int countProducts() {
        int count = 0;
        String query = "SELECT COUNT(*) AS total FROM Product";
        try {
            ps = conn.prepareStatement(query);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt("total");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeResources();
        }
        return count;
    }

    private void closeResources() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
