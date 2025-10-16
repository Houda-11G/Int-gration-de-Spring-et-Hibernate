package web;

import entities.Product;
import metier.ProductDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

// Définir l'URL pour accéder au servlet
@WebServlet("/products")
public class ProductServlet extends HttpServlet {

    private ProductDaoImpl productDao;

    @Override
    public void init() throws ServletException {
        super.init();
        // Initialiser le DAO via Spring
        productDao = (ProductDaoImpl) getServletContext()
                .getAttribute("productDao");
        if (productDao == null) {
            throw new ServletException("ProductDaoImpl non trouvé !");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) 
            throws ServletException, IOException {

        List<Product> products = productDao.findAll();

        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        out.println("<h1>Liste des produits</h1>");
        for (Product p : products) {
            out.println(p.getName() + " - " + p.getPrice() + "<br>");
        }
    }
}
