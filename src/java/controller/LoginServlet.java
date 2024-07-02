
package controller;

import dao.DatabaseConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import util.Hash;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = Hash.doHash(request.getParameter("password"));

        // Check username va password co null khong
        if (username == null || password == null || username.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("errorMessage", "Vui lòng nhập tên đăng nhập và mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
            return;
        }

        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            //Lay thong tin tu data
            connection = DatabaseConnection.getConnection();

            
            String sql = "SELECT * FROM [User] WHERE [username] = ? AND [password] = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            // Chay lai database neu no chua duoc khoi chay
            resultSet = preparedStatement.executeQuery();

            // Neu user ton tai, chuyen huong toi home.jsp
            if (resultSet.next()) {
                // Session su dung de ghi nho user da tung dang nhap
                request.getSession().setAttribute("loggedInUser", username);
                response.sendRedirect("home.jsp");
            } else {
                // Neu user khong ton tai, in ra tbao loi
                request.setAttribute("errorMessage", "Wrong username or password!");
                request.getRequestDispatcher("login.jsp").forward(request, response);
            }

        } catch (SQLException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            request.setAttribute("errorMessage", "Have error when login. Try again!");
            request.getRequestDispatcher("login.jsp").forward(request, response);

        } finally {
            // Hoan thanh lay du lieu tu data, ket thuc
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException ex) {
                Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
