package com.example.market.controller;

import com.example.market.model.Image;
import com.example.market.model.Product;
import com.example.market.model.Review;
import com.example.market.model.query.IProductWarehouse;
import com.example.market.service.image.IImageService;
import com.example.market.service.product.IProductService;
import com.example.market.service.review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @Autowired
    private IImageService imageService;

    @Autowired
    private IReviewService reviewService;

    @GetMapping
    public ResponseEntity<Iterable<Product>> getAllProduct() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/inventory")
    public ResponseEntity<Iterable<IProductWarehouse>> getAllProductInventory() {
        return new ResponseEntity<>(productService.findAllProductInventoryAmount(), HttpStatus.OK);
    }

    @GetMapping("/pagination")
    public ResponseEntity<Iterable<Product>> getAllProductUsingPagination(@RequestParam int page, @RequestParam int size) {
        Iterable<Product> products = productService.findAll(page, size);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        return productOptional.map(product -> new ResponseEntity<>(product, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        return new ResponseEntity<>(productService.save(product), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product product) {
        Optional<Product> productOptional = productService.findById(id);
        return productOptional.map(product1 -> {
            product.setId(product1.getId());
            productService.save(product);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Product> deleteProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        return productOptional.map(product -> {
            productService.remove(id);
            return new ResponseEntity<>(product, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/images")
    public ResponseEntity<Iterable<Image>> getAllImageByProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        return productOptional.map(product -> new ResponseEntity<>(imageService.findAllByProduct(product), HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/sale-off")
    public ResponseEntity<Iterable<Product>> getAllProductWithSaleOffGreaterThan() {
        return new ResponseEntity<>(productService.findAllBySaleOffGreaterThanZero(), HttpStatus.OK);
    }

    @GetMapping("/price")
    public ResponseEntity<Iterable<Product>> getAllProductByPriceCondition(@RequestParam("min") int minValue, @RequestParam("max") int maxValue) {
        return new ResponseEntity<>(productService.findAllByPriceCondition(minValue, maxValue), HttpStatus.OK);
    }

    @GetMapping("/latest")
    public ResponseEntity<Iterable<Product>> getAllProductLatest() {
        return new ResponseEntity<>(productService.findAllProductOrderByDate(), HttpStatus.OK);
    }

    @GetMapping("/search")
    public ResponseEntity<Iterable<Product>> getAllProductByName(@RequestParam(name = "name") String name) {
        return new ResponseEntity<>(productService.findAllByNameContaining(name), HttpStatus.OK);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<Iterable<Review>> getAllReviewByProduct(@PathVariable Long id) {
        Optional<Product> productOptional = productService.findById(id);
        return productOptional.map(product -> new ResponseEntity<>(reviewService.findAllByProduct(product), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
