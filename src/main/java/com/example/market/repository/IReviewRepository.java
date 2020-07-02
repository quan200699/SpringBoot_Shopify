package com.example.market.repository;

import com.example.market.model.Product;
import com.example.market.model.Review;
import com.example.market.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IReviewRepository extends JpaRepository<Review, Long> {
    Review findByUserAndProduct(User user, Product product);
}
