/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CartDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.CartItem;
import model.Product;
import model.User;

/**
 *
 * @author hoang hung
 */
@WebServlet(name="CartController", urlPatterns={"/cart"})
public class CartController extends HttpServlet {
    
    private CartDAO dbContext = new CartDAO();
    
    private boolean processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String method = req.getParameter("method");
        if (method == null) return false;
        boolean isExecuted = false;
        
        switch (method) {
            case "DELETE" -> {
                doDelete(req, resp);
                isExecuted = true;
            }
            default -> {
                isExecuted = false;
            }
        }
        
        return isExecuted;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (processRequest(req, resp)) return;
        
        User user = (User) req.getSession().getAttribute("userInfo");
        System.out.println("user infor " + user);
        List<CartItem> cartItems = dbContext.getOrderItemsByUserId(1);
        
        req.setAttribute("cartItems", cartItems);
        req.getRequestDispatcher("customer/cart.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("userInfo");
        int productId = Integer.parseInt(req.getParameter("productId"));
        int productPrice = Integer.parseInt(req.getParameter("productPrice"));
        int quantity = Integer.parseInt(req.getParameter("quantity"));
        
        
        boolean isAdded = dbContext.addItemIntoCartByUserId(
                                new CartItem(
                                        new Product(productId, productPrice), 
                                        quantity
                                ),
                                user.getId()
                        );
        
        if (isAdded) {
            req.setAttribute("message", "Added product into cart");
        } else {
            req.setAttribute("message", "Add product into cart failed");
        }
        
        req.getRequestDispatcher("product-detail?id=" + productId).forward(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int itemId = Integer.parseInt(req.getParameter("itemId"));
        
        boolean isDeleted = dbContext.deleteItemById(itemId);
        
        if (isDeleted) {
            req.setAttribute("message", "Deleted");
        } else {
            req.setAttribute("message", "Delete failed");
        }
        
        resp.sendRedirect("cart");
    }
}
