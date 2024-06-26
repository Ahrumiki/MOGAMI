package DAO;

import Database.JDBC_Util;
import Model.Product;
import com.mysql.cj.xdevapi.PreparableStatement;

import java.sql.*;
import java.util.ArrayList;

public class Product_DAO implements DAO_Interface<Product, String>{
    @Override
    public int insert(Product entity) {
        //product_id, name, price, color, size, quantity, description, type_id
        String sql = "INSERT INTO products (product_id, name, price, color, size, quantity, description, type_id) VALUES (?,?,?,?,?,?,?,?)";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getProduct_id());
            statement.setString(2, entity.getName());
            statement.setInt(3, entity.getPrice());
            statement.setString(4, entity.getColor());
            statement.setString(5, entity.getSize());
            statement.setInt(6, entity.getQuantity());
            statement.setString(7, entity.getDescription());
            statement.setInt(8, entity.getType_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e){
            e.printStackTrace();
        }


        return  0;
    }

    @Override
    public int update(Product entity) {
        String sql = "UPDATE products SET name = ?, price = ?, color = ?, size = ?, quantity = ?, description = ?, type_id = ? WHERE product_id = ?";

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, entity.getName());
            statement.setInt(2, entity.getPrice());
            statement.setString(3, entity.getColor());
            statement.setString(4, entity.getSize());
            statement.setInt(5, entity.getQuantity());
            statement.setString(6, entity.getDescription());
            statement.setInt(7, entity.getType_id());
            statement.setInt(8, entity.getProduct_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public int delete(Product entity) {
        String sql = "DELETE FROM products WHERE product_id = ?";
        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, entity.getProduct_id());

            int result = statement.executeUpdate();
            System.out.println("Số dòng bị ảnh hưởng: " + result);

            JDBC_Util.closeConnection(connection);

        }catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public ArrayList<Product> findAll() {
        ArrayList<Product> listProduct = new ArrayList<Product>();
        String sql = "SELECT * FROM products";

        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setColor(resultSet.getString("color"));
                product.setSize(resultSet.getString("size"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setType_id(resultSet.getInt("type_id"));

                listProduct.add(product);
            }
            JDBC_Util.closeConnection(connection);
            return listProduct;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Product findById(String s) {
        Product product = new Product();
        String sql = "SELECT * FROM products WHERE product_id = ?";
        try( Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setInt(1, Integer.parseInt(s));
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setColor(resultSet.getString("color"));
                product.setSize(resultSet.getString("size"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setType_id(resultSet.getInt("type_id"));
            }
            JDBC_Util.closeConnection(connection);
            return product;

        }catch (SQLException e){
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public ArrayList<Product> findByCondition(String condition) {
        ArrayList<Product> listProduct = new ArrayList<Product>();
        String sql = "SELECT * FROM products WHERE " + condition;

        try(Connection connection = JDBC_Util.getConnection(); PreparedStatement statement = connection.prepareStatement(sql)){
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()){
                Product product = new Product();
                product.setProduct_id(resultSet.getInt("product_id"));
                product.setName(resultSet.getString("name"));
                product.setPrice(resultSet.getInt("price"));
                product.setColor(resultSet.getString("color"));
                product.setSize(resultSet.getString("size"));
                product.setQuantity(resultSet.getInt("quantity"));
                product.setDescription(resultSet.getString("description"));
                product.setType_id(resultSet.getInt("type_id"));

                listProduct.add(product);
            }
            JDBC_Util.closeConnection(connection);
            return listProduct;

        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    public static Product_DAO getInstance(){
        return new Product_DAO();
    }
}
