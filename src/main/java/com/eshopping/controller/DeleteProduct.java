package com.eshopping.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.ProductDAOImpl;
@WebServlet("/DeleteProduct")
public class DeleteProduct extends HttpServlet{
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		String productid=request.getParameter("productId");
		ProductDAOImpl productDAO = new ProductDAOImpl();
		//PrintWriter writer = response.getWriter();
		if (productDAO.deleteProduct(Integer.parseInt(productid))) {
           
                RequestDispatcher dispatcher = request.getRequestDispatcher("AllProduct.jsp");
                try {
					dispatcher.forward(request, response);
				} catch (ServletException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                
	
	}
	}
}
