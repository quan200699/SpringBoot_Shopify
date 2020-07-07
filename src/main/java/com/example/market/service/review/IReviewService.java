package com.example.market.service.review;

import com.example.market.model.Product;
import com.example.market.model.Review;
import com.example.market.model.auth.User;
import com.example.market.service.IGeneralService;

public interface IReviewService extends IGeneralService<Review> {
    Review findByUserAndProduct(User user, Product product);
    Iterable<Review> findAllByProduct(Product product);
}
