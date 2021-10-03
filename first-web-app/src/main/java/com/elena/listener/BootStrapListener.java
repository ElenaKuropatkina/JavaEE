package com.elena.listener;


import com.elena.persist.Product;
import com.elena.persist.ProductRepository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.math.BigDecimal;

@WebListener
public class BootStrapListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ProductRepository productRepository = new ProductRepository();

        productRepository.save(new Product(null, "Product1", new BigDecimal(500)));
        productRepository.save(new Product(null, "Product2", new BigDecimal(100)));
        productRepository.save(new Product(null, "Product3", new BigDecimal(300)));
        productRepository.save(new Product(null, "Product4", new BigDecimal(400)));
        productRepository.save(new Product(null, "Product5", new BigDecimal(150)));
        productRepository.save(new Product(null, "Product6", new BigDecimal(500)));

        sce.getServletContext().setAttribute("productRepository", productRepository);
    }
}
