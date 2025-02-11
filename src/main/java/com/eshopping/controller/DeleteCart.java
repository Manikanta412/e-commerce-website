package com.eshopping.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshopping.DAO.CartDAO;
import com.eshopping.DAO.CartDAOImpl;
import com.eshopping.model.Cart;

@WebServlet("/deletecart")
public class DeleteCart extends HttpServlet {
    CartDAO cartDAO = new CartDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int customerId = Integer.parseInt(request.getParameter("customerid"));
        int productId = Integer.parseInt(request.getParameter("productid"));

        // Remove the product from the cart
        cartDAO.deleteCart(productId, customerId);

        // Check if the cart is now empty
        List<Cart> cartDetails = cartDAO.getCartDetails(customerId);
        if (cartDetails.isEmpty()) {
            // Set an attribute to indicate the cart is empty
            request.setAttribute("isCartEmpty", true);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("Cart.jsp");
        requestDispatcher.forward(request, response);
    }
}
