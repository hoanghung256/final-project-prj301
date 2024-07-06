/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.OrderDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import model.OrderDetail;

/**
 *
 * @author ASUS
 */
public class OrderDetailsController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet OrderDetailsController</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet OrderDetailsController at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
             String command =  request.getParameter("command");
             switch(command){
                 case "LOAD":
                     getOrderDetailsNotYetDelivered(request, response);
                     break;
                 case "ADD":
                     
                     break;
                 case "UPDATE":
                    
                     break;
                 case "DELETE":
                     
                     break;
             }
        } catch (Exception e) {
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void getOrderDetailsNotYetDelivered(HttpServletRequest request, HttpServletResponse response) throws Exception{
        OrderDAO dao = new OrderDAO();
        List<OrderDetail> list = dao.getOrderDetailsNotYetDelivered();
        request.setAttribute("list", list);
        request.getRequestDispatcher("customer/order-details.jsp").forward(request, response);
    }

}
