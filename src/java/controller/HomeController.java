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
@WebServlet(name = "HomeController", urlPatterns = {"/home-page"})
public class HomeController extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(HomeController.class.getName());

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
                case "SEARCH":
                    searchProductsByName(request, response);
                    break;
                default:
                    listProducts(request, response);
            }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    private void searchProductsByName(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String searchTerm = request.getParameter("searchTerm");
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            listProducts(request, response);
            return;
        }

        ProductDAO productDAO = new ProductDAO();
        List<Product> list = productDAO.searchProductsByName(searchTerm.trim());
        request.setAttribute("products", list);
        request.setAttribute("currentPage", 1);
        request.setAttribute("totalPages", 1);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/home/home-page.jsp");
        dispatcher.forward(request, response);
    }

    private void listProducts(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ProductDAO productDAO = new ProductDAO();
        int page = 1; // default page number
        int pageSize = 40; // number of products per page

        if (request.getParameter("page") != null) {
            try {
                page = Integer.parseInt(request.getParameter("page"));
            } catch (NumberFormatException e) {
                LOGGER.log(Level.WARNING, "Invalid page number, defaulting to 1", e);
            }
        }

        List<Product> list = productDAO.getProducts(page, pageSize);
        request.setAttribute("products", list);

        int totalProducts = productDAO.countProducts();
        int totalPages = (int) Math.ceil((double) totalProducts / pageSize);

        request.setAttribute("currentPage", page);
        request.setAttribute("totalPages", totalPages);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/home/home-page.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}
