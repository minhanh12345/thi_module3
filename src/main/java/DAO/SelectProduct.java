package DAO;

import Model.Product;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SelectProduct {
    static String  select = "select product_name,product_price,product_number,product_color,product_description,product_category_id from product";

    public static ArrayList<Product> select() throws SQLException, ClassNotFoundException {
        ArrayList<Product> list = new ArrayList<>();
        Connection connection = ConnectMySQL.getConnect();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(select);
        while (resultSet.next()) {
            String name = resultSet.getString("product_name");
            int price = resultSet.getInt("product_price");
            int number = resultSet.getInt("product_number");
            String color = resultSet.getString("product_color");
            String description = resultSet.getString("product_description");
            int idCategory = resultSet.getInt("product_category_id");
            list.add(new Product(name, price, number, color, description, idCategory));
        }
        return list;
    }
}
