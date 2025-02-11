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

@WebServlet("/addcart")
public class AddToCart extends HttpServlet{

	CartDAO cartDAO=new CartDAOImpl();
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int productId=Integer.parseInt(request.getParameter("productid"));
		int customerId=Integer.parseInt(request.getParameter("customerid"));
		boolean present=false;
		List<Cart>  list=cartDAO.getCartDetails(customerId);
		int count=list.size();
		HttpSession httpSession=request.getSession();
		for(Cart cartDetails:list) {
			if(cartDetails.getProductid()==productId)
				present=true;
		}
		if(present) {
		    httpSession.setAttribute("count", count);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("AllProductDetailsForCustomer.jsp");
			requestDispatcher.forward(request, response);
		}
		else {
			if(cartDAO.insertCartDetails(productId, customerId)>0) {
			httpSession.setAttribute("count", count+1);
			RequestDispatcher requestDispatcher=request.getRequestDispatcher("AllProductDetailsForCustomer.jsp");
			requestDispatcher.forward(request, response);
		   }
			else {
				httpSession.setAttribute("count", count);
				RequestDispatcher requestDispatcher=request.getRequestDispatcher("AllProductDetailsforCustomer.jsp");
				requestDispatcher.forward(request, response);
			}
		}
	}
}
