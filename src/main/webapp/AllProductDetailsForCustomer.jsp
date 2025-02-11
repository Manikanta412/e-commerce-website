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
    <title>All Product Details</title>
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

        form {
            margin-bottom: 20px;
        }

        input[type="text"], input[type="submit"], button {
            padding: 8px 15px;
            margin: 5px;
            border: 1px solid #ccc;
            border-radius: 5px;
            font-size: 16px;
        }

        input[type="text"] {
            width: 300px;
        }

        input[type="submit"] {
            background-color: #4CAF50;
            color: white;
            cursor: pointer;
        }

        input[type="submit"]:hover {
            background-color: #45a049;
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

        td button {
            width: 100%;
            padding: 10px;
            font-size: 14px;
        }

        center {
            width: 90%;
            margin: 0 auto;
        }

        p {
            font-size: 18px;
            color: #555;
        }

        input[type="text"]:focus {
            border-color: #4CAF50;
            outline: none;
        }
    </style>
</head>
<body>
<%
    CustomerDetails customerDetails = (CustomerDetails) session.getAttribute("logincustomerdetails");
    ProductDAO productDAO = new ProductDAOImpl();
    CartDAO cartDAO = new CartDAOImpl();
    List<Product_Details> list = productDAO.getAllProductDetails();
    List<Cart> cartList = new ArrayList<>();
    int cartCount = 0;

    if (customerDetails.getId() != 0) {
        cartList = cartDAO.getCartDetails(customerDetails.getId());
        cartCount = cartList.size();
    }

    String name = request.getParameter("search");
    if (name != null && !name.trim().isEmpty()) {
        list = list.stream()
            .filter(product -> product.getBrand().equalsIgnoreCase(name) ||
                    product.getCategory().equalsIgnoreCase(name) ||
                    product.getName().equalsIgnoreCase(name))
            .collect(Collectors.toList());
    }
%>

<nav>
    <div class="logo">e_shopping</div>
    <div>
        <a href="App.html">Home</a>
        <a href="Cart.jsp">Cart (<%= cartCount %>)</a>
        <a href="OrderDetails.jsp">Order Details</a>
        <a href="Logout.jsp">Log Out</a>
    </div>
</nav>

<center>
    <h1>All Product Details</h1>
    <form action="AllProduct.jsp">
        <input style="color:red;border-radius: 12px;height: 30px" name="search" placeholder="brand/name/category">
        <input type="submit">
    </form>
    <br>

    <table>
        <tr>
            <th>Product Id</th>
            <th>Product Name</th>
            <th>Product Brand</th>
            <th>Product Price</th>
            <th>Product Discount</th>
            <th>Product Quantity</th>
            <th>Product Category</th>
            <th>ADD TO CART</th>
            <th>BUY</th>
        </tr>
        <% for (Product_Details productDetails : list) { %>
        <tr>
            <td><%= productDetails.getProductid() %></td>
            <td><%= productDetails.getName() %></td>
            <td><%= productDetails.getBrand() %></td>
            <td>&#8377;<%= productDetails.getPrice() %></td>
            <td><%= productDetails.getDiscount() %></td>
            <td><%= productDetails.getQuantity() %></td>
            <td><%= productDetails.getCategory() %></td>
            <td>
                <form action="addcart" style="display:inline;">
                    <input type="hidden" name="productid" value="<%= productDetails.getProductid() %>">
                    <input type="hidden" name="customerid" value="<%= customerDetails.getId() %>">
                    <button type="submit">Add to Cart</button>
                </form>
            </td>
            <td>
                <form action="Buy.jsp" style="display:inline;">
                    <input type="hidden" name="productname" value="<%= productDetails.getName() %>">
                    <input type="hidden" name="productbrand" value="<%= productDetails.getBrand() %>">
                    <input type="hidden" name="price" value="<%= productDetails.getPrice() %>">
                    <input type="hidden" name="discount" value="<%= productDetails.getDiscount() %>">
                    <button type="submit">BUY</button>
                </form>
            </td>
        </tr>
        <% } %>
    </table>
    <br>
    <form action="AdminOperation.html">
        <input type="submit" value="Exit" />
    </form>
</center>
</body>
</html>
