package com.example.market.service.product;

import com.example.market.model.Category;
import com.example.market.model.Product;
import com.example.market.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class ProductService implements IProductService {
    @Autowired
    private IProductRepository productRepository;

    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public Product save(Product product) {
        long currentTime = System.currentTimeMillis();
        Date currentDate = new Date(currentTime);
        product.setCreatedDate(currentDate);
        return productRepository.save(product);
    }

    @Override
    public void remove(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public Iterable<Product> findAllByCategory(Category category) {
        return productRepository.findAllByCategory(category);
    }

    @Override
    public Iterable<Product> findAllBySaleOffGreaterThanZero() {
        return productRepository.findAllBySaleOffGreaterThanZero();
    }

    @Override
    public Iterable<Product> findAllByPriceCondition(double minValue, double maxValue) {
        return productRepository.findAllByPriceCondition(minValue, maxValue);
    }

    @Override
    public Iterable<Product> findAllProductOrderByDate() {
        return productRepository.findAllProductOrderByDate();
    }

    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }
}
