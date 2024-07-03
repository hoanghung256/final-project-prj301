package controller;

import dao.UserDAO;
import enums.Gender;
import enums.Role;
import model.User;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import util.Hash;

@WebServlet(name = "RegisterPageController", urlPatterns = "/register")
public class RegisterPageController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UserDAO userDAO = new UserDAO();

        String username = request.getParameter("username");
        String password = Hash.doHash(request.getParameter("password"));
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String genderStr = request.getParameter("gender");
        Gender gender = Gender.valueOf(genderStr.toUpperCase());
        String dobStr = request.getParameter("dob");
        LocalDate dob = LocalDate.parse(dobStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        String address = request.getParameter("address");

        boolean usernameExists = userDAO.isUsernameExists(username);
        boolean emailExists = userDAO.isEmailExists(email);

        if (usernameExists || emailExists ) {
            request.setAttribute("error", "This account already exists");            
            request.getRequestDispatcher("register.jsp").forward(request, response);
            
        }       

        User user = new User(username, password, email, phone, gender, dob, address);
        try {
            userDAO.saveUser(user);
            
            request.setAttribute("success", "Your account has been created successfully.");
            
            request.getRequestDispatcher("register.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("error", "An error occurred while registering. Please try again.");
        }
//        request.getRequestDispatcher("register-success.jsp").forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
