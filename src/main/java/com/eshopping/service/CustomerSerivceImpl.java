package com.eshopping.service;

import java.util.List;

import com.eshopping.DAO.CustomerDAO;
import com.eshopping.DAO.CustomerDAOImpl;
import com.eshopping.model.CustomerDetails;

import CExceptions.CustomerException;

public class CustomerSerivceImpl implements CustomerService{
    CustomerDAO customerDAO=new CustomerDAOImpl();
    @Override
    public boolean customerRegistraion(CustomerDetails customerDetails) {
    	List<CustomerDetails> allCustomerDetails=customerDAO.getAllCustomerDetails();
    boolean emailimatch=allCustomerDetails.stream().
    		anyMatch((customer-> customer.getEmailid().equalsIgnoreCase(customerDetails.getEmailid())));
    if (emailimatch) 
		throw new CustomerException("Emailid Already Existed");
    boolean mobilenumbermatch=allCustomerDetails.stream().
    		anyMatch((customer-> customer.getMobilenumber()==customerDetails.getMobilenumber()));
    if (mobilenumbermatch) 
		throw new CustomerException("mobile Already Existed");
    boolean passwordmatch=allCustomerDetails.stream().
    		anyMatch((customer-> customer.getPassword().equals(customerDetails.getPassword())));
    if (passwordmatch) 
		throw new CustomerException("Password Already Existed");
  return customerDAO.insertCustomerDetails(customerDetails)>0;
   
}
}
