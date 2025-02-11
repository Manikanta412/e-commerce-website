package com.eshopping.controller;

import com.eshopping.DAO.ProductDAO;
import com.eshopping.DAO.ProductDAOImpl;
import com.eshopping.model.Product_Details;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/addProduct")
public class AddProdutDetails extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get form data
        String name = request.getParameter("name");
        String brand = request.getParameter("brand");
        double price = Double.parseDouble(request.getParameter("price"));
        String discount=request.getParameter("discount");
        String category = request.getParameter("category");
        int quantity = Integer.parseInt(request.getParameter("quantity"));
        // Create Product_Details object
        Product_Details product = new Product_Details();
        product.setName(name);
        product.setBrand(brand);
        product.setPrice(price);
        product.setDiscount(discount);
        product.setCategory(category);
        product.setQuantity(quantity);
        // Call DAO to insert product
        ProductDAO productDAO = new ProductDAOImpl();
        boolean isAdded = productDAO.addDetails(product);
        // Send response
        PrintWriter writer = response.getWriter();
        RequestDispatcher dispatcher = request.getRequestDispatcher("AddProducts.html");
        if (isAdded) {
            dispatcher.include(request, response);
            writer.println("<center><h2>Product added successfully!</h2></center>");
        } else {
            dispatcher.include(request, response);
            writer.println("<center><h2>Failed to add product. Please try again.</h2></center>");
        }
    }
}
