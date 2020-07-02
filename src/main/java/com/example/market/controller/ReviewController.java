package com.example.market.controller;

import com.example.market.model.Product;
import com.example.market.model.Review;
import com.example.market.model.auth.User;
import com.example.market.service.product.IProductService;
import com.example.market.service.review.IReviewService;
import com.example.market.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private IReviewService reviewService;

    @Autowired
    private IUserService userService;

    @Autowired
    private IProductService productService;

    @GetMapping
    public ResponseEntity<Iterable<Review>> getAllReview() {
        return new ResponseEntity<>(reviewService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        Optional<Review> reviewOptional = reviewService.findById(id);
        return reviewOptional.map(review -> new ResponseEntity<>(review, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Review> createReview(@RequestBody Review review) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        review.setCreateDate(date);
        return new ResponseEntity<>(reviewService.save(review), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Review> updateReview(@PathVariable Long id, @RequestBody Review review) {
        Optional<Review> reviewOptional = reviewService.findById(id);
        return reviewOptional.map(review1 -> {
            review.setId(review1.getId());
            reviewService.save(review);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Review> deleteReview(@PathVariable Long id) {
        Optional<Review> reviewOptional = reviewService.findById(id);
        return reviewOptional.map(review -> {
            reviewService.remove(id);
            return new ResponseEntity<>(review, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping("/users/{userId}/products/{productId}")
    public ResponseEntity<Review> getReview(@PathVariable Long userId, @PathVariable Long productId) {
        Optional<User> userOptional = userService.findById(userId);
        return userOptional.map(user -> {
            Optional<Product> productOptional = productService.findById(productId);
            return productOptional.map(product ->
                    new ResponseEntity<>(reviewService.findByUserAndProduct(user, product), HttpStatus.OK)).
                    orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
