
package controller;

import dao.DatabaseConnection;
import dao.UserDAO;
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
import model.User;
import util.Hash;

@WebServlet(name="LoginController", urlPatterns={"/login"})
public class LoginController extends HttpServlet {
    private UserDAO dbContext = new UserDAO();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = Hash.doHash(request.getParameter("password"));

        User user = dbContext.getUserByUsernameAndPassword(username, password);
        
        if (user == null) {
            request.setAttribute("errorMessage", "Vui lòng nhập tên đăng nhập và mật khẩu!");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
            request.getSession().setAttribute("userInfo", user);
            request.getSession().setMaxInactiveInterval(0);
            response.sendRedirect("home");
        }
    }
}