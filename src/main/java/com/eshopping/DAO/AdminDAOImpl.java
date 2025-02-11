package com.eshopping.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;

public class AdminDAOImpl implements AdminDAO {
	  private static final String url="jdbc:mysql://localhost:3306/e_shopping?user=root&password=12345";
      private static final String  select="select*from admin where admin_emailid=? and admin_password=?";
	@Override
	public boolean getAdminDetails(String emailid, String password) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(select);
			ps.setString(1, emailid);
			ps.setString(2, password);
		    ResultSet  resultSet=ps.executeQuery();
		    return resultSet.isBeforeFirst();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
  
}
