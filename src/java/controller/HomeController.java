/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package controller;

import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Product;

/**
 *
 * @author This PC
 */
@WebServlet(name="HomeController", urlPatterns={"/home-page"})
public class HomeController extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
//            /* TODO output your page here. You may use following sample code. */
//            out.println("<!DOCTYPE html>");
//            out.println("<html>");
//            out.println("<head>");
//            out.println("<title>Servlet HomeController</title>");  
//            out.println("</head>");
//            out.println("<body>");
//            out.println("<h1>Servlet HomeController at " + request.getContextPath () + "</h1>");
//            out.println("</body>");
//            out.println("</html>");
        }
    } 
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                theCommand = "LIST";
            }
            switch (theCommand) {
                case "LIST":
                    listProducts(request, response);
                    break;
                default:
                    listProducts(request, response);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    } 
    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception{
        ProductDAO productDAO = new ProductDAO();
        int page = 1; //default is 1
        int pageSize = 40; //number of products each page
        
        if(request.getParameter("page") != null){
            page = Integer.parseInt(request.getParameter("page"));
        }
        
        List<Product> list = productDAO.getProducts(page, pageSize);
        request.setAttribute("products", list);
        
        int totalProducts = productDAO.countProducts();
        int totalPages = (int) Math.ceilDiv(totalProducts, pageSize);//divide num of product to page size (40) and ceil to get the number of pages
        
        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("/home/home-page.jsp");
        dispatcher.forward(request, response);
        System.out.println(totalPages);
        System.out.println(list);
    }
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
}
