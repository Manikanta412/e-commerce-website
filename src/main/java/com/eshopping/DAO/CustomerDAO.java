package com.eshopping.DAO;

import java.sql.SQLException;
import java.util.List;

import com.eshopping.model.CustomerDetails;

public interface CustomerDAO {

  int insertCustomerDetails(CustomerDetails customerDetails);
  List<CustomerDetails> getAllCustomerDetails();
  CustomerDetails getCustomerDetails(String emailid,String password);
}