package com.elena;

import com.elena.persist.Product;
import com.elena.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;
    private static final Pattern pathParam = Pattern.compile("\\/(\\d*)$");

    @Override
    public void init() throws ServletException {
        this.productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("<h1>Product</h1>");

        Matcher matcher = pathParam.matcher(req.getPathInfo());
        if (matcher.matches()) {
            long id;
            try {
                id = Long.parseLong(matcher.group(1));
            } catch (NumberFormatException ex) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            Product product = productRepository.getById(id).orElseThrow(RuntimeException::new);
            resp.getWriter().println("<p>Id: " + product.getId() + "</p>");
            resp.getWriter().println("<p>Name: " + product.getName() + "</p>");
            resp.getWriter().println("<p>Name: " + product.getPrice() + "</p>");
            return;
        }
    }

}
