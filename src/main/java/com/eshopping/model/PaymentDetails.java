package com.eshopping.model;

import lombok.Data;

@Data
public class PaymentDetails {
private double amount;
private String paymentdate;
private String paymenttime;
private String paymenttype;
private int customerid;
private int productid;
}
