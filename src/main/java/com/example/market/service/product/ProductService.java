package com.example.market.service.product;

import com.example.market.model.Category;
import com.example.market.model.Product;
import com.example.market.model.query.IProductImage;
import com.example.market.model.query.IProductWarehouse;
import com.example.market.repository.IProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
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
    public Iterable<Product> findAllByCategory(Category category, int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAllByCategory(category, pageRequest);
        return products.getContent();
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
    public Iterable<IProductImage> findAllProductOrderByDate() {
        List<IProductImage> productImages = new ArrayList<>();
        List<IProductImage> productImageLatest = (List<IProductImage>) productRepository.findAllProductOrderByDate();
        for (IProductImage productImage : productImageLatest) {
            if (productImages.isEmpty()) {
                productImages.add(productImage);
            } else {
                for (IProductImage productImage1 : productImages) {
                    if (!productImage1.getId().equals(productImage.getId())) {
                        productImages.add(productImage);
                        break;
                    }
                }
            }
        }
        return productImages;
    }

    @Override
    public Iterable<Product> findAllByNameContaining(String name) {
        return productRepository.findAllByNameContaining(name);
    }

    @Override
    public Iterable<Product> findAll(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageRequest);
        return products.getContent();
    }

    @Override
    public Iterable<IProductWarehouse> findAllProductInventoryAmount() {
        return productRepository.findAllProductInventoryAmount();
    }

    @Override
    public Iterable<IProductImage> getAllProductBestSell() {
        return productRepository.getAllProductBestSell();
    }

    @Override
    public Iterable<IProductImage> getAllProductMostLiked() {
        List<IProductImage> productImages = new ArrayList<>();
        List<IProductImage> productImageMostLiked = (List<IProductImage>) productRepository.getAllProductMostLiked();
        for (IProductImage productImage : productImageMostLiked) {
            if (productImages.isEmpty()) {
                productImages.add(productImage);
            } else {
                for (IProductImage productImage1 : productImages) {
                    if (!productImage1.getId().equals(productImage.getId())) {
                        productImages.add(productImage);
                        break;
                    }
                }
            }
        }
        return productImages;
    }
}
