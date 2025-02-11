<%@page import="com.eshopping.model.CustomerDetails"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.stream.Collectors"%>
<%@page import="com.eshopping.DAO.ProductDAOImpl"%>
<%@page import="com.eshopping.DAO.ProductDAO"%>
<%@page import="com.eshopping.model.Product_Details"%>
<%@page import="com.eshopping.DAO.CartDAOImpl"%>
<%@page import="com.eshopping.DAO.CartDAO"%>
<%@page import="com.eshopping.model.Cart"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Cart</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        nav {
            background-color: #333;
            overflow: hidden;
            padding: 10px;
            color: white;
            display: flex;
            align-items: center;
        }
        nav .logo {
            font-size: 20px;
            font-weight: bold;
            margin-right: 20px;
        }
        nav a {
            color: white;
            text-decoration: none;
            padding: 10px 15px;
            margin-right: 10px;
        }
        nav a:hover {
            background-color: #575757;
            border-radius: 5px;
        }
        h1 {
            color: #333;
            margin-top: 20px;
        }
        table {
            width: 90%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: white;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f2f2f2;
            color: #333;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        center {
            width: 90%;
            margin: 0 auto;
        }
        p {
            font-size: 18px;
            color: #555;
        }
    </style>
</head>
<body>
<%
    CustomerDetails customerDetails = (CustomerDetails) session.getAttribute("logincustomerdetails");
    ProductDAO productDAO = new ProductDAOImpl();
    CartDAO cartDAO = new CartDAOImpl();

    int customer_id = (Integer) session.getAttribute("customerid");
    List<Product_Details> list = productDAO.getAllProductDetails();
    List<Cart> cartDetails = cartDAO.getCartDetails(customerDetails.getId());
    List<Product_Details> cartList1 = new ArrayList<>();

    for (Product_Details productDetails : list) {
        if (cartDetails.stream().anyMatch(cart -> cart.getProductid() == productDetails.getProductid())) {
            cartList1.add(productDetails);
        }
    }

    String name = request.getParameter("search");
    if (name != null && !name.trim().isEmpty()) {
        list = list.stream()
            .filter(product -> product.getBrand().equalsIgnoreCase(name) ||
                    product.getCategory().equalsIgnoreCase(name) ||
                    product.getName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }

    if (!cartList1.isEmpty()) {
        list = cartList1;
    }
%>

<nav>
    <div class="logo">e_shopping</div>
    <div>
        <a href="App.html">Home</a>
        <a href="OrderDetails.jsp">Order Details</a>
        <a href="Logout.jsp">Log Out</a>
    </div>
</nav>

<center>
    <h1>Your Cart</h1>
    <% if (cartDetails == null || cartDetails.isEmpty()) { %>
        <p>Your cart is empty.</p>
        <a href="AllProductDetailsForCustomer.jsp" style="text-decoration: none; color: white;">
            <button style="padding: 10px 20px; background-color: #4CAF50; border: none; color: white; border-radius: 5px;">Shop Now</button>
        </a>
    <% } else { %>
        <table>
            <tr>
                <th>Product Id</th>
                <th>Product Name</th>
                <th>Product Brand</th>
                <th>Product Price</th>
                <th>Product Discount</th>
                <th>Product Category</th>
                <th>Remove</th>
                <th>Buy</th>
            </tr>
            <% for (Product_Details productDetails : cartList1) { %>
            <tr>
                <td><%= productDetails.getProductid() %></td>
                <td><%= productDetails.getName() %></td>
                <td><%= productDetails.getBrand() %></td>
                <td>&#8377;<%= productDetails.getPrice() %></td>
                <td><%= productDetails.getDiscount() %></td>
                <td><%= productDetails.getCategory() %></td>
                <td>
                    <form action="deletecart">
                        <input type="hidden" name="productid" value="<%= productDetails.getProductid() %>">
                        <input type="hidden" name="customerid" value="<%= customer_id %>">
                        <input type="submit" value="Remove">
                    </form>
                </td>
                <td>
                    <form action="Buy.jsp" style="display:inline;">
                        <input type="hidden" name="productname" value="<%= productDetails.getName() %>">
                        <input type="hidden" name="productbrand" value="<%= productDetails.getBrand() %>">
                        <input type="hidden" name="price" value="<%= productDetails.getPrice() %>">
                        <input type="hidden" name="discount" value="<%= productDetails.getDiscount() %>">
                        <button type="submit">Buy</button>
                    </form>
                </td>
            </tr>
            <% } %>
        </table>
    <% } %>
</center>
</body>
</html>
