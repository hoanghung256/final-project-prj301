/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import model.CartItem;
import model.Product;
import model.User;
import util.Cookiez;

/**
 *
 * @author ASUS
 */
@WebServlet(name="AddToCartController", urlPatterns={"/add-cart"})
public class AddToCartController extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        User user = (User) Cookiez.get("userInfo", request);
        
        int productId = Integer.parseInt(request.getParameter("productId"));
        int productPrice = Integer.parseInt(request.getParameter("productPrice"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        CartDAO dao = new CartDAO();
        boolean isAdded = dao.addItemIntoCartByUserId(
                new CartItem(
                    new Product(productId, productPrice),quantity),
                user.getId()
        );

        if (isAdded) {
            request.setAttribute("message", "Added product into cart");
        } else {
            request.setAttribute("message", "Add product into cart failed");
        }

        request.getRequestDispatcher("product-detail?id=" + productId + "&command=LOAD").forward(request, response);
    }
}