package com.eshopping.DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.eshopping.model.CustomerDetails;

public class CustomerDAOImpl implements CustomerDAO{

  private static final String url="jdbc:mysql://localhost:3306/e_shopping?user=root&password=12345";
  
  private static final String insert="insert into customer_details (name, mobile, emailid, gender, address, password) values(?,?,?,?,?,?)";
  private static final String select_all="select * from customer_details";
  private static final String customerlogin="select * from customer_details where emailid=? and password=?";
  @Override
  public int insertCustomerDetails(CustomerDetails customerDetails) {
    try {
      Class.forName("com.mysql.cj.jdbc.Driver");
      Connection connection =  DriverManager.getConnection(url);
      PreparedStatement ps=connection.prepareStatement(insert);
      ps.setString(1, customerDetails.getName());
      ps.setLong(2, customerDetails.getMobilenumber());
      ps.setString(3, customerDetails.getEmailid());
      ps.setString(4, customerDetails.getGender());
      ps.setString(5, customerDetails.getAddress());
      ps.setString(6, customerDetails.getPassword());
      return ps.executeUpdate();
    } catch (ClassNotFoundException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 0;
    } catch (SQLException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
      return 0;
    }
    }
    @Override
    public List<CustomerDetails> getAllCustomerDetails(){
    	try {
    		Class.forName("com.mysql.cj.jdbc.Driver");
    	    Connection connection =  DriverManager.getConnection(url);
    	    PreparedStatement ps=connection.prepareStatement(select_all);
    	    ResultSet resultSet=ps.executeQuery(); 
    	    List<CustomerDetails>list=new ArrayList<CustomerDetails>();
    	    if (resultSet.isBeforeFirst()) {
				while (resultSet.next()) {
					CustomerDetails cusDetails=new CustomerDetails();
					cusDetails.setId(resultSet.getInt("customer_id"));
					cusDetails.setEmailid(resultSet.getString("Emailid"));
					cusDetails.setMobilenumber(resultSet.getLong("mobile"));
					cusDetails.setPassword(resultSet.getString("password"));
					list.add(cusDetails);
				}
				return list;
			}else {
				return null;
			}
    	} catch (ClassNotFoundException e) {
    	      // TODO Auto-generated catch block
    	      e.printStackTrace();
    	      return null;
    	    } catch (SQLException e) {
    	      // TODO Auto-generated catch block
    	      e.printStackTrace();
    	      return null;
    	    }
    }
    @Override
    public  CustomerDetails getCustomerDetails(String emailid,String password)  {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection	connection = DriverManager.getConnection(url);
			PreparedStatement ps=connection.prepareStatement(customerlogin);
			ps.setString(1, emailid);
			ps.setString(2, password);
		    ResultSet  resultSet=ps.executeQuery();
	    	if(resultSet.next()) {
	    		CustomerDetails customerDetails=new CustomerDetails();
	    		customerDetails.setId(resultSet.getInt("customer_id"));
	    		customerDetails.setEmailid(resultSet.getString("emailid"));
	    		customerDetails.setPassword(resultSet.getString("password"));
	    	   return customerDetails;
	    	}
	    	
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			
		}
		
		return null;
    	
    }
    
  }

 