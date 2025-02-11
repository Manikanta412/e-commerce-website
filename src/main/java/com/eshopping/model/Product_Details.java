package com.eshopping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product_Details {

	private int productid;
	private String name;
	private String brand;
	private double price;
	private String discount;
	private String category;
	private int quantity;
}
