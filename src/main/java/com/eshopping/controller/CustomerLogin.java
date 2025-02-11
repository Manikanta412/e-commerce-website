package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.model.CustomerDetails;
@WebServlet("/customerlogin")
public class CustomerLogin extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String emailid = request.getParameter("emailid"); // Updated to match the form field
        String password = request.getParameter("password");
        
        CustomerDAO customerDAO = new CustomerDAOImpl();
        CustomerDetails customerDetails = customerDAO.getCustomerDetails(emailid, password);
        
        PrintWriter writer = response.getWriter();
        response.setContentType("text/html");
        HttpSession session = request.getSession();
        
        if (customerDetails != null) {
            session.setAttribute("logincustomerdetails", customerDetails);
            session.setAttribute("cutomername", customerDetails.getName());
			session.setAttribute("customerid", customerDetails.getId());
            RequestDispatcher dispatcher = request.getRequestDispatcher("AllProductDetailsForCustomer.jsp");
            dispatcher.forward(request, response);
        } else {
            // Include error message on the login page
            RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerLogin.html");
            dispatcher.include(request, response);
            writer.println("<center><h3>Invalid Email or Password</h3></center>");
        }
    }
}
