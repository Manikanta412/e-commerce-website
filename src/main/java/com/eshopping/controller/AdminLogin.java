package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.AdminDAO;
import com.eshopping.DAO.AdminDAOImpl;
@WebServlet("/adminlogin")
public class AdminLogin extends HttpServlet{
 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String emailid=request.getParameter("emailid");
	String pass=request.getParameter("password");
	AdminDAO adminDAO=new AdminDAOImpl();
	PrintWriter writer=response.getWriter();
    response.setContentType("text/html");
	if(adminDAO.getAdminDetails(emailid, pass)) {
		RequestDispatcher dispatcher=request.getRequestDispatcher("AdminOperation.html");
		dispatcher.forward(request, response);
	}else {
		RequestDispatcher dispatcher=request.getRequestDispatcher("AdminLogin.html");
		dispatcher.include(request, response);
		writer.print("<center><h2>Invalid Creditionals</h2></center>");
		
	}
}
}
