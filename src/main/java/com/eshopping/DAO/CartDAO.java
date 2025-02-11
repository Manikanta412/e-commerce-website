package com.eshopping.DAO;

import java.util.List;

import com.eshopping.model.Cart;

public interface CartDAO {
	int insertCartDetails(int productId,int customerId);
	List<Cart> getCartDetails(int customerId);
	int deleteCart(int productId,int customerId);
}
