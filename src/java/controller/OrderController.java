/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.CartDAO;
import dao.OrderDAO;
import enums.PaymentType;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import model.CartItem;
import model.User;


/**
 *
 * @author hoang hung
 */
@WebServlet(name="OrderController", urlPatterns="/order")
public class OrderController extends HttpServlet {
    
    private OrderDAO dbContext = new OrderDAO();
    
    private boolean processRequest(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        boolean isExecuted = false;
        String method = req.getParameter("method");
        
        if ("DELETE".equals(method)) {
            doDelete(req, resp);
            isExecuted = true;
        } else if ("UPDATE".equals(method)) {
            doDelete(req, resp);
            isExecuted = true;
        }
        
        return isExecuted;
    }
    
    /**
     * Get itemId and quantity parameters from the customer/cart.jsp page (the item that user have choosed)
     * Then forward to display at customer/order.jsp
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (processRequest(req, resp)) return;
        
        String[] ids = req.getParameterValues("itemId");
        String[] quantities = req.getParameterValues("quantity");
        
        if (ids == null || quantities == null) {
            req.setAttribute("error", "You have not choose any item!");
            req.getRequestDispatcher("cart").forward(req, resp);
        }
        
        List<CartItem> items = new CartDAO().getCartItemByItemIds(ids);
        processQuantity(items, ids, quantities);
        
        req.getSession().setAttribute("orderItems", items);
        
        req.getRequestDispatcher("customer/order.jsp").forward(req, resp);
    }

    /**
     * Create new order, then forward message back to display
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("userInfo");
        List<CartItem> items = (List<CartItem>) req.getSession().getAttribute("orderItems");
        String address = req.getParameter("address");
        PaymentType paymentType = PaymentType.valueOf(req.getParameter("paymentType"));
        
//        int insertOrderId = dbContext.createOrderAndGetId(user.getId(), paymentType);
        int insertOrderId = dbContext.createOrderAndGetId(1, paymentType);
//        System.out.println("orderID: " + insertOrderId);
        
        boolean isOrderCreated = dbContext.addOrderItem(insertOrderId, address, items);
        
        if (isOrderCreated) {
            req.setAttribute("message", "Create order successfully");
            // Delete the cart item that user just order succeed
            CartDAO cartDbContext = new CartDAO();
            cartDbContext.deleteItemByCartItem(items);
            
            req.getSession().removeAttribute("orderItems");
            
            // Redirect to order status page (Redirect to order status page is temporary)
            req.getRequestDispatcher("customer/order-status.jsp").forward(req, resp);
        } else {
            req.setAttribute("message", "Error occured, create order failed!");
            req.getRequestDispatcher("customer/order.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().removeAttribute("orderItems");
        
        resp.sendRedirect("cart");
    }
    
    private void processQuantity(List<CartItem> items, String[] ids, String[] quantities) {
        for (int i = 0; i < ids.length; i++) {
            for (CartItem item : items) {
                if (Integer.parseInt(ids[i]) == item.getId()) {
                    int quantity = Integer.parseInt(quantities[i]);
                    item.setQuantity(quantity);
                    item.setTotalPrice(item.getProduct().getPrice() * quantity);
                }
            }
        }
    }
}
