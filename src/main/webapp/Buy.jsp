<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<style>
    /* General Body Styling */
    body {
        font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        background: linear-gradient(120deg, #e0f7fa, #e8f5e9);
        color: #333;
        margin: 0;
        padding: 0;
    }

    /* Navigation Bar */
    nav {
        background-color: #26a69a; /* Teal */
        padding: 10px 20px;
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    nav .logo {
        font-size: 24px;
        font-weight: bold;
        color: white;
    }

    nav .nav-links {
        list-style: none;
        display: flex;
        gap: 15px;
    }

    nav .nav-links a {
        color: white;
        text-decoration: none;
        padding: 8px 15px;
        border-radius: 5px;
        transition: background-color 0.3s ease;
    }

    nav .nav-links a:hover {
        background-color: #00796b; /* Darker Teal */
    }

    /* Container Styling */
    .container {
        max-width: 450px;
        margin: 50px auto;
        padding: 20px;
        background-color: #ffffff;
        border-radius: 8px;
        box-shadow: 0 4px 10px rgba(0, 0, 0, 0.2);
    }

    .container h1 {
        text-align: center;
        color: #00796b; /* Dark Teal */
        margin-bottom: 20px;
    }

    /* Form Group Styling */
    .form-group {
        margin-bottom: 15px;
    }

    .form-group label {
        display: block;
        font-weight: bold;
        margin-bottom: 5px;
        color: #333;
    }

    .form-group input {
        width: 100%;
        padding: 10px;
        font-size: 14px;
        border: 1px solid #ccc;
        border-radius: 5px;
        box-sizing: border-box;
    }

    .form-group input:read-only {
        background-color: #f1f1f1;
    }

    /* Buttons Container Styling */
    .btn-container {
        display: flex;
        justify-content: space-between;
        align-items: center;
    }

    .btn-container input[type="submit"] {
        background-color: #26a69a; /* Teal */
        color: white;
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    .btn-container input[type="submit"]:hover {
        background-color: #00796b; /* Darker Teal */
    }

    #cancel {
        background-color: #ff7043; /* Coral */
        color: white;
        padding: 10px 20px;
        font-size: 16px;
        border: none;
        border-radius: 5px;
        cursor: pointer;
        transition: background-color 0.3s ease;
    }

    #cancel:hover {
        background-color: #d84315; /* Dark Coral */
    }

    /* Inputs for Quantity and Total Amount */
    .form-group input[type="number"] {
        -moz-appearance: textfield;
    }

    .form-group input[type="number"]::-webkit-inner-spin-button,
    .form-group input[type="number"]::-webkit-outer-spin-button {
        -webkit-appearance: none;
    }

    .form-group input[type="text"] {
        background-color: #f9f9f9;
    }

    /* Responsive Design */
    @media (max-width: 500px) {
        .container {
            padding: 15px;
        }

        nav .logo {
            font-size: 20px;
        }

        nav .nav-links {
            flex-direction: column;
            gap: 10px;
        }
    }
</style>
</head>
<body>
   
    <div class="container">
        <h1>Quick Shop - Buy</h1>
        <form action="">
            <div class="form-group">
                <label>Product Name</label>
                <input value="<%=request.getParameter("productname")%>" readonly>
            </div>
            <div class="form-group">
                <label>Product Brand</label>
                <input value="<%=request.getParameter("productbrand")%>" readonly>
            </div>
            <div class="form-group">
                <label>Price</label>
                <input value="<%=request.getParameter("price")%>" readonly id="price">
            </div>
            <div class="form-group">
                <label>Discount (%)</label>
                <input value="<%=request.getParameter("discount")%>" readonly id="discount">
            </div>
            <div class="form-group">
                <label>Quantity</label>
                <input placeholder="Enter Quantity" id="quantity" type="number" min="1">
            </div>
            <div class="form-group">
                <label>Total Amount</label>
                <input type="text" placeholder="Calculated Total" id="totalamount" readonly>
            </div>
            <div class="btn-container" style="float: left">
                <a href="Payment.html" target="_self">Buy</a>
            </div>
        </form>
        <button onclick="history.back()" id="cancel" style="margin-left: 200px">Cancel</button>
    </div>

    <script>
        function calculateTotalAmount() {
            const originalPrice = parseFloat(document.getElementById('price').value);
            const discountPercentage = parseFloat(document.getElementById('discount').value);
            const quantity = parseInt(document.getElementById('quantity').value);

            if (!isNaN(originalPrice) && !isNaN(discountPercentage) && !isNaN(quantity) && quantity > 0) {
                const discountAmountPerItem = (originalPrice * discountPercentage) / 100;
                const finalPricePerItem = originalPrice - discountAmountPerItem;
                const totalAmount = finalPricePerItem * quantity;

                document.getElementById('totalamount').value = totalAmount.toFixed(2);
            } else {
                document.getElementById('totalamount').value = '';
            }
        }

        document.getElementById('quantity').addEventListener('input', calculateTotalAmount);
    </script>
</body>
</html>
