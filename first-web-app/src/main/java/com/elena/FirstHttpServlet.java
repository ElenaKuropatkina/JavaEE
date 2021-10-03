package com.elena;

import com.elena.persist.Product;
import com.elena.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/http_servlet")
public class FirstHttpServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>First Http Servlet</h1>");
        resp.getWriter().println("<h2>Products</h2>");

        for (Product p : productRepository.getAllProducts()
        ) {
            resp.getWriter().println("<a>" + p.getName() + "</a> <br>");
        }
    }
}
