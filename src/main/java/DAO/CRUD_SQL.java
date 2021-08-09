package DAO;

import Model.Product;
import Service.ProductService;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class CRUD_SQL {
    public static void create(Product product) {
        Connection connection = null;
        try {
            connection = ConnectMySQL.getConnect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (
                SQLException throwables) {
            throwables.printStackTrace();
        }

        try {

            PreparedStatement statement1 = connection.prepareStatement("insert into product(product_name,product_price,product_number,product_color,product_description,product_category_id) value (?,?,?,?,?,?)");
            statement1.setString(1, product.getName());
            statement1.setInt(2, product.getPrice());
            statement1.setInt(3, product.getNumber());
            statement1.setString(4, product.getColor());
            statement1.setString(5, product.getDescription());
            statement1.setInt(6, product.getIdCategory());
            statement1.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void edit(int index,Product product){
String nameEdit= ProductService.listProduct.get(index).getName();
        Connection connection = null;
        try {
            connection = ConnectMySQL.getConnect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {

            PreparedStatement statement1 = connection.prepareStatement("update product set product_name=?,product_price=?,product_number=?,product_color=?,product_description=?,product_category_id=? where product_name=?");
            statement1.setString(1,product.getName());
            statement1.setInt(2,product.getPrice());
            statement1.setInt(3,product.getNumber());
            statement1.setString(4,product.getColor());
            statement1.setString(5,product.getDescription());
            statement1.setInt(6,product.getIdCategory());
            statement1.setString(7,nameEdit);
            statement1.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    public static void delete(int index){
        String nameEdit= ProductService.listProduct.get(index).getName();
        Connection connection = null;
        try {
            connection = ConnectMySQL.getConnect();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        Statement statement = null;
        try {
            statement = connection.createStatement();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        try {
            statement.executeUpdate("delete from product where name="+nameEdit);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
