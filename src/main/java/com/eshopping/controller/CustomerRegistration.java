package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.model.CustomerDetails;
import com.eshopping.service.CustomerSerivceImpl;
import com.eshopping.service.CustomerService;

import CExceptions.CustomerException;

@WebServlet("/customerregistration")

public class CustomerRegistration extends HttpServlet{
  
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    String name=request.getParameter("name");
    long mobile=Long.parseLong(request.getParameter("mobilenumber"));
    String emailid=request.getParameter("emailid");
    String gender=request.getParameter("gender");
    String address=request.getParameter("address");
    String password=request.getParameter("password");
    CustomerService customerService=new CustomerSerivceImpl();
    CustomerDetails customerDetails=new CustomerDetails();
    customerDetails.setName(name);
    customerDetails.setMobilenumber(mobile);
    customerDetails.setEmailid(emailid);
    customerDetails.setGender(gender);
    customerDetails.setAddress(address);
    customerDetails.setPassword(password);
    
    PrintWriter printWriter=response.getWriter();
    response.setContentType("text/html");
    try {
     if(customerService.customerRegistraion(customerDetails)) {
      RequestDispatcher requestDispatcher=request.getRequestDispatcher("CustomerLogin.html");
      requestDispatcher.forward(request, response);
     }
     else {
      RequestDispatcher requestDispatcher=request.getRequestDispatcher("CustomerRegistration.html");
      requestDispatcher.include(request, response);
      printWriter.println("<center><h5>Invalid Data</h5></center>");
     }
    }
    catch(CustomerException e) {
     RequestDispatcher requestDispatcher =request.getRequestDispatcher("CustomerRegistration.html");
     requestDispatcher.include(request, response);
     printWriter.println("<center><h5>"+e.getMsg()+"</h5></center>");
    }
    
   }
  }