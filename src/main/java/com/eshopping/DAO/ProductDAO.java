package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.Product_Details;

public interface ProductDAO {
 boolean addDetails(Product_Details product);
 
 List<Product_Details> getAllProductDetails();
 boolean deleteProduct(int productId);
 int updateProduct(int productId, double price,int quantity,String discount);
}
