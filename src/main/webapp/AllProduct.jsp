<%@page import="java.util.ArrayList"%>
<%@page import="com.eshopping.DAO.ProductDAOImpl"%>
<%@page import="com.eshopping.model.Product_Details"%>
<%@page import="com.eshopping.DAO.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page import="java.io.PrintWriter"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Product Details</title>
<style>
    /* General Body Styling */
    body {
        font-family: Arial, sans-serif;
        margin: 0;
        padding: 0;
        background-color: #f0f4f8; /* Single background color */
        color: #333;
    }

    /* Centered Content */
    center {
        margin: 30px auto;
        max-width: 90%;
    }

    /* Header Styling */
    h1 {
        font-size: 2.5rem;
        color: #ffffff;
        background: #4CAF50;
        padding: 10px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    /* Table Styling */
    table {
        width: 100%;
        border-collapse: collapse;
        margin-top: 20px;
        background-color: #ffffff;
        box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
        border-radius: 10px;
        overflow: hidden;
    }

    th, td {
        padding: 12px 15px;
        border: 1px solid #ddd;
        text-align: center;
    }

    th {
        background-color: #007BFF;
        color: #ffffff;
        font-size: 1rem;
    }

    tr:nth-child(even) {
        background-color: #f9f9f9;
    }

    tr:nth-child(odd) {
        background-color: #e9f3ff;
    }

    tr:hover {
        background-color: #f1f1f1;
        cursor: pointer;
    }

    /* Exit Button Styling */
    input[type="submit"] {
        padding: 12px 20px;
        border: none;
        border-radius: 10px;
        background-color: #dc3545;
        color: #fff;
        font-size: 16px;
        font-weight: bold;
        cursor: pointer;
        transition: all 0.3s ease;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
    }

    input[type="submit"]:hover {
        background-color: #c82333;
        transform: translateY(-2px);
    }

    /* Action Button Styling */
    .action-btn {
        padding: 6px 12px;
        border: none;
        border-radius: 5px;
        font-size: 14px;
        cursor: pointer;
        transition: all 0.3s ease;
    }

    .update-btn {
        background-color: #4CAF50;
        color: white;
    }

    .update-btn:hover {
        background-color: #45a049;
    }

    .delete-btn {
        background-color: #dc3545;
        color: white;
    }

    .delete-btn:hover {
        background-color: #c82333;
    }

</style>
</head>
<body>
<%!ProductDAO productDAO = new ProductDAOImpl();
List<Product_Details> list;
%>
<%
    PrintWriter printWriter = response.getWriter();
    String name = request.getParameter("search");
    list = productDAO.getAllProductDetails();
    List<Product_Details> list1 = new ArrayList<Product_Details>();

    if (name != null && !name.trim().isEmpty()) {
        for (Product_Details product : list) {
            if (product.getBrand().equalsIgnoreCase(name) ||
                product.getCategory().equalsIgnoreCase(name) ||
                product.getName().equalsIgnoreCase(name)) {
                list1.add(product);
            }
        }
    }
    if (!list1.isEmpty()) {
        list = list1;
    }
%>

<center>
    <h1>All Product Details</h1>
    <form action="AllProduct.jsp"><input style="color:red;border-radius: 12px;height: 30px" name="search" placeholder="brand/name/category">
    <input type="submit">
    </form>
     <br>
     <br>
    <form action="AddProducts.html">
        <input style="background-color: #45a049;" type="submit" value="ADD PRODUCTS">
    </form>
    
    
    <table>
        <tr>
            <th>Product Id</th>
            <th>Product Name</th>
            <th>Product Brand</th>
            <th>Product Price</th>
            <th>Product Discount</th>
            <th>Product Quantity</th>
            <th>Product Category</th>
            <th>Product Update</th> <!-- Update column -->
            <th>Product Delete</th> <!-- Delete column -->
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
            
            <!-- Product Update Column -->
            <td>
                <form action="UpdateProductDetails.jsp"  style="display:inline;">
                    <input type="hidden" name="productId" value="<%= productDetails.getProductid() %>">
                    <button type="submit" class="action-btn update-btn">Update</button>
                </form>
            </td>
            
            <!-- Product Delete Column -->
            <td>
    <form action="DeleteProduct" method="POST" style="display:inline;">
        <input type="hidden" name="productId" value="<%= productDetails.getProductid() %>">
        <button type="submit" class="action-btn delete-btn" onclick="return confirm('Are you sure you want to delete this product?')">Delete</button>
    </form>
</td>
        </tr>
        <% } %>
    </table>
    <br>
    <form action="AdminOperation.html">
        <input type="submit" value="Exit">
    </form>
</center>


</body>
</html>
