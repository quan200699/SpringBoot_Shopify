package com.example.market.controller;

import com.example.market.model.Review;
import com.example.market.service.review.IReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/reviews")
public class ReviewController {
    @Autowired
    private IReviewService reviewService;

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
}
