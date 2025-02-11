<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Product</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f9;
            margin: 0;
            padding: 0;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }
        .update-form {
            background-color: #ffffff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            width: 400px;
        }
        .update-form h2 {
            text-align: center;
            margin-bottom: 20px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 4px;
        }
        .form-group button {
            width: 100%;
            padding: 10px;
            background-color: #007bff;
            color: #ffffff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            font-size: 16px;
        }
        .form-group button:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
<% String productid=request.getParameter("productId");
%>
    <div class="update-form">
        <h2>Update Product</h2>
        <form action="UpdateProductDetails" method="get">
            <div class="form-group">
                <input type="hidden"  value="<%=productid %>" name="productid" required placeholder="Enter Product ID">
            </div>
            <div class="form-group">
                <label for="price">Price:</label>
                <input type="text" id="price" name="price" required placeholder="Enter New Price">
            </div>
            <div class="form-group">
                <label for="quantity">Quantity:</label>
                <input type="text" id="quantity" name="quantity" required placeholder="Enter New Quantity">
            </div>
            <div class="form-group">
                <label for="discount">Discount:</label>
                <input type="text" id="discount" name="discount" required placeholder="Enter Discount (Optional)">
            </div>
            <div class="form-group">
                <button type="submit">Update Product</button>
            </div>
        </form>
    </div>
</body>
</html>
