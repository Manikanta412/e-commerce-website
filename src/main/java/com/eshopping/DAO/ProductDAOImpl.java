package com.eshopping.DAO;

import com.eshopping.model.Product_Details;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAOImpl implements ProductDAO {
    private static final String url = "jdbc:mysql://localhost:3306/e_shopping?user=root&password=12345";
    private static final String insert = "INSERT INTO product_details(name, brand, price, discount, category, quantity) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String select = "SELECT * FROM product_details";
    private static final String deleteQuery = "DELETE FROM product_details WHERE product_id = ?";
    private static final String updateprodetails="update product_details set price=? ,discount=?,quantity=quantity+? where product_id=?";
    @Override
    public boolean addDetails(Product_Details product) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement ps = connection.prepareStatement(insert);
            ps.setString(1, product.getName());
            ps.setString(2, product.getBrand());
            ps.setDouble(3, product.getPrice());
            ps.setString(4, product.getDiscount());
            ps.setString(5, product.getCategory());
            ps.setInt(6, product.getQuantity());

            // Execute the query
            int result = ps.executeUpdate();
            return result > 0; // Return true if insertion is successful

        } catch (SQLException e) {
            System.err.println("Database error: " + e.getMessage());
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Product_Details> getAllProductDetails() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement ps = connection.prepareStatement(select);
            ResultSet rs = ps.executeQuery();

            List<Product_Details> list = new ArrayList<>();
            if (rs.isBeforeFirst()) {
                while (rs.next()) {
                    Product_Details product = new Product_Details();
                    // product_id, name, brand, price, discount, category, quantity
                    product.setProductid(rs.getInt("product_id"));
                    product.setName(rs.getString("name"));
                    product.setBrand(rs.getString("brand"));
                    product.setPrice(rs.getDouble("price"));
                    product.setDiscount(rs.getString("discount"));
                    product.setCategory(rs.getString("category"));
                    product.setQuantity(rs.getInt("quantity"));
                    list.add(product);
                }
                return list;
            } else {
                return null;
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean deleteProduct(int productId) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection(url);
            PreparedStatement ps = connection.prepareStatement(deleteQuery);
            ps.setInt(1, productId);
            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0; 
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    @Override
    public int updateProduct(int productId, double price,int quantity,String discount) {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection=DriverManager.getConnection(url);
		    PreparedStatement ps=connection.prepareStatement(updateprodetails);
                    ps.setDouble(1, price);
                    ps.setString(2, discount);
                    ps.setInt(3, quantity);
                    ps.setInt(4, productId);
                    return ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
    	
    	
    }
    
}
