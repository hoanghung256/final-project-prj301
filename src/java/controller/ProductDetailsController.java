/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import dao.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import model.Product;


/**
 *
 * @author ASUS
 */

public class ProductDetailsController extends HttpServlet {
    
      protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ContactControlServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ContactControlServlet at " + request.getContextPath() + "</h1>");
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
                     loadProductDetails(request, response);
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
    
    //LOAD
    private void loadProductDetails(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ProductDAO dao = new ProductDAO();
        String id = request.getParameter("id");
        Product product = dao.searchById(id);
        request.setAttribute("product", product);
        request.getRequestDispatcher("product/product-details.jsp").forward(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
