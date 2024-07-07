package dao;

import java.sql.*;
import model.User;
import enums.Role;
import java.time.LocalDate;

/**
 *
 * @author hoang hung
 */
public class UserDAO {

    private final Connection conn;

    public UserDAO() {
        this.conn = DatabaseConnection.getConnection();
    }

    public boolean isUsernameExists(String username) {
        String query = "SELECT COUNT(*) FROM [User] WHERE username = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Kiểm tra xem email đã tồn tại chưa
    public boolean isEmailExists(String email) {
        String query = "SELECT COUNT(*) FROM [User] WHERE email = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void saveUser(User user) {
        String sql = "INSERT INTO [User](username, password, email, phone, gender, dob, address) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = conn.prepareStatement(sql)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getGender().toString());
            statement.setDate(6, java.sql.Date.valueOf(user.getDob()));
            statement.setNString(7, user.getAddress());
            int a = statement.executeUpdate();
            System.out.println(a);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
