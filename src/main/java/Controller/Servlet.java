package Controller;

import DAO.CRUD_SQL;
import Model.Product;
import Service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet(urlPatterns = {"/product"})
public class Servlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                resp.sendRedirect("Views/CreateProduct.jsp");
                break;
            case "edit":
                int indexEdit = Integer.parseInt(req.getParameter("index"));
                req.setAttribute("product", ProductService.listProduct.get(indexEdit));
                requestDispatcher = req.getRequestDispatcher("Views/EditProduct.jsp");
                requestDispatcher.forward(req, resp);
                break;
            case "delete":
                int indexDelete = Integer.parseInt(req.getParameter("index"));
                ProductService.delete(indexDelete);
                req.setAttribute("listProduct", ProductService.listProduct);
                requestDispatcher = req.getRequestDispatcher("/Views/ShowProduct.jsp");
                requestDispatcher.forward(req, resp);
            default:
                req.setAttribute("listProduct", ProductService.listProduct);
                requestDispatcher = req.getRequestDispatcher("/Views/ShowProduct.jsp");
                requestDispatcher.forward(req, resp);

        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        RequestDispatcher requestDispatcher;

        switch (action) {
            case "create":
                String name = req.getParameter("name");
                int price = Integer.parseInt(req.getParameter("price"));
                int number = Integer.parseInt(req.getParameter("number"));
                String color = req.getParameter("color");
                String description = req.getParameter("description");
                int idCategory = Integer.parseInt(req.getParameter("idCategory"));
                ProductService.create(new Product(name, price, number, color, description, idCategory));

                req.setAttribute("listProduct", ProductService.listProduct);
                requestDispatcher = req.getRequestDispatcher("Views/ShowProduct.jsp");
                requestDispatcher.forward(req, resp);
                break;
            case "edit":
                String nameEdit = req.getParameter("name");
                int priceEdit = Integer.parseInt(req.getParameter("price"));
                int numberEdit = Integer.parseInt(req.getParameter("number"));
                String colorEdit = req.getParameter("color");
                String descriptionEdit = req.getParameter("description");
                Integer idCategoryEdit = Integer.valueOf(req.getParameter("idCategory"));

                int index = Integer.parseInt(req.getParameter("index"));


                ProductService.edit(new Product(nameEdit, priceEdit, numberEdit, colorEdit, descriptionEdit, idCategoryEdit), index);

                req.setAttribute("listProduct", ProductService.listProduct);
                requestDispatcher = req.getRequestDispatcher("Views/ShowProduct.jsp");
                requestDispatcher.forward(req, resp);

                break;
            case"find":
                ArrayList<Product> listFind=new ArrayList<>();
                String nameFind=req.getParameter("nameFind");
                for(int i=0;i<ProductService.listProduct.size();i++){
                    if(ProductService.listProduct.get(i).getName().contains(nameFind)){
                        listFind.add(ProductService.listProduct.get(i));
                    }
                }
                req.setAttribute("listProduct", listFind);
                requestDispatcher = req.getRequestDispatcher("Views/ShowProduct.jsp");
                requestDispatcher.forward(req, resp);
        }
    }
}
