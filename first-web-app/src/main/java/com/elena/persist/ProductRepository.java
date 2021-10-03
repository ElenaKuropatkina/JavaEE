package com.elena.persist;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

public class ProductRepository {

    private final Map<Long, Product> productMap = new HashMap<>();
    private AtomicLong identity = new AtomicLong();

    public List<Product> getAllProducts() {
        return new ArrayList<>(productMap.values());
    }

    public Optional<Product> getById(Long id) {
        return Optional.ofNullable(productMap.get(id));
    }

    public Product save(Product product) {
        if (product.getId() == null) {
            product.setId(identity.incrementAndGet());
        }
        return productMap.put(product.getId(), product);
    }

    public void delete(Long id) {
        productMap.remove(id);
    }

}
