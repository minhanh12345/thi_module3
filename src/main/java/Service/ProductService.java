package Service;

import DAO.SelectProduct;
import Model.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public  class ProductService {
    public static ArrayList<Product> listProduct;

    static {
        try {
            listProduct = SelectProduct.select();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void create(Product product) {
        listProduct.add(product);
    }

    public static void edit(Product product, int index) {
        listProduct.set(index, product);
    }

    public static void delete(int index) {
        listProduct.remove(index);
    }

}
