package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.Cart;

public class CartDAOImpl implements CartDAO{

	private static final String url="jdbc:mysql://localhost:3306/e_shopping?user=root&password=12345";
	private static final String insert=
			"insert into cart(Customer_Id, Product_Id) values(?,?)";
    private static final String selectAll="select * from cart where Customer_Id=?";
    private static final String delete="delete from cart where product_Id=? and customer_Id=?";
	public int insertCartDetails(int productId, int customerId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=	DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(insert);
			preparedStatement.setInt(1, customerId);
			preparedStatement.setInt(2, productId);
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		return 0;
	}

	@Override
	public List<Cart> getCartDetails(int customerId) {
		List<Cart> list=new ArrayList<Cart>();
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=	DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(selectAll);
			preparedStatement.setInt(1, customerId);
			 ResultSet resultSet= preparedStatement.executeQuery();
			 if(resultSet.isBeforeFirst()) {
				 while(resultSet.next()) {
					 Cart cartDetails=new Cart();
					 cartDetails.setCart_id(resultSet.getInt("cart_id"));
					 cartDetails.setProductid(resultSet.getInt("Product_Id"));
					 list.add(cartDetails);
				 }
				 return list;
			 }
			
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int deleteCart(int productId, int customerId) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=	DriverManager.getConnection(url);
			PreparedStatement preparedStatement=connection.prepareStatement(delete);
			preparedStatement.setInt(1, productId);
			preparedStatement.setInt(2, customerId);
			return preparedStatement.executeUpdate();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
	
}
