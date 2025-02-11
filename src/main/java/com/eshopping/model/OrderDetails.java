package com.eshopping.model;

import lombok.Data;

@Data
public class OrderDetails {
private int customerid;
private int productid;
private int purchasequantity;
private String purchasedate;
private double totalprice;
}
