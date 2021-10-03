package com.elena;

import com.elena.persist.Product;
import com.elena.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/products")
public class ProductListServlet extends HttpServlet {
    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        for (Product p : productRepository.getAllProducts()
        ) {
            resp.getWriter().println("<a href='" + getServletContext().getContextPath() + "/product/" + p.getId() + "'>" + p.getName() + "</a><br>");
        }
    }
}
