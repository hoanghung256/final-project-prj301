/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.ProductDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.Part;
import java.util.Collection;
import java.util.List;
import model.Product;
import model.ProductImage;
import util.ImageHandler;

/**
 *
 * @author This PC
 */
@WebServlet(name = "AddNewProductController", urlPatterns = {"/add-new-product"})
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
        maxFileSize = 1024 * 1024 * 50, // 50 MB
        maxRequestSize = 1024 * 1024 * 100 // 100 MB
)
public class AddNewProductController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/add-new-product/add-new-product.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String theCommand = request.getParameter("command");
            if (theCommand == null) {
                theCommand = "ADD";
            }
            switch (theCommand) {
                case "ADD":
                    addProduct(request, response);
                    break;
                default:
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid command");
                // Handle default case
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    private void addProduct(HttpServletRequest request, HttpServletResponse response) throws Exception {
        System.out.println("called1");
        // Get the product details from the request
        String productName = request.getParameter("productName");
        String productDescription = request.getParameter("productDescription");
        int categoryId = Integer.parseInt(request.getParameter("category"));
        int price = Integer.parseInt(request.getParameter("price"));
        int quantity = Integer.parseInt(request.getParameter("quantity"));

        // Handle the cover photo upload
//        Part coverPhotoPart = request.getPart("coverPhotos");
//        String coverPhotoUrl = "";
//        if (coverPhotoPart != null && coverPhotoPart.getSubmittedFileName() != null && !coverPhotoPart.getSubmittedFileName().isEmpty()) {
//            // Handle the upload of cover photo
//            ProductImage coverPhoto = ImageHandler.handleSingleUploadImage(coverPhotoPart, getServletContext());
//            if (coverPhoto != null) {
//                coverPhotoUrl = "upload-images/" + coverPhoto.getImageName();
//            }
//        }

        // Handle the product photos upload
        Collection<Part> productPhotoParts = request.getParts();
        List<ProductImage> productPhotoList = ImageHandler.handleUploadImages(productPhotoParts, getServletContext());

        // Create the product object and save to the database
        Product newProduct = new Product();
        newProduct.setProductName(productName);
        newProduct.setDescription(productDescription);
        newProduct.setCategoryId(categoryId);
        newProduct.setPrice(price);
        newProduct.setQuantity(quantity);
        newProduct.setAvatarUrl(coverPhotoUrl);
        System.out.println(newProduct);
        ProductDAO productDAO = new ProductDAO();
        productDAO.createProduct(newProduct.getProductName(), 0, newProduct.getCategoryId(), newProduct.getDescription(), newProduct.getPrice(), newProduct.getQuantity(), newProduct.getAvatarUrl());

        // Redirect or forward to a confirmation page or back to the product list
        response.sendRedirect("home-page");
    }

    @Override
    public String getServletInfo() {
        return "Servlet to add new products";
    }
}
