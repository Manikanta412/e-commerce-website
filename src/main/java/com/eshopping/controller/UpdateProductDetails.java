package com.eshopping.controller;

import java.io.IOException;
import java.io.Writer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.eshopping.DAO.ProductDAOImpl;

@WebServlet("/UpdateProductDetails")
public class UpdateProductDetails extends HttpServlet {
 @Override
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String productid=request.getParameter("productid");
	double price=Double.parseDouble(request.getParameter("price"));
	int quantity=Integer.parseInt(request.getParameter("quantity"));
	String discount=request.getParameter("discount");
	ProductDAOImpl productDao=new ProductDAOImpl();
	if(productDao.updateProduct(Integer.parseInt(productid),price,quantity,discount)>0){
		RequestDispatcher dispatcher = request.getRequestDispatcher("AllProduct.jsp");
        dispatcher.forward(request, response);
	}
}
}
