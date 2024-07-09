/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

/**
 *
 * @author This PC
 */
import model.Category;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CategoryDAO {
    private static final String stm = "SELECT id, name, parentId, createAt FROM [Category]";

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        try (Connection connection = DatabaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(stm)) {
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int parentId = rs.getInt("parentId");
                LocalDate createAt = rs.getDate("createAt").toLocalDate();
                categories.add(new Category(id, name, parentId, createAt));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categories;
    }
}

