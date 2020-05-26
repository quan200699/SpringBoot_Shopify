package com.example.market.repository;

import com.example.market.model.Category;
import com.example.market.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IProductRepository extends JpaRepository<Product, Long> {
    Iterable<Product> findAllByCategory(Category category);

    @Query("select p from Product p where p.saleOff>0")
    Iterable<Product> findAllBySaleOffGreaterThanZero();
}
