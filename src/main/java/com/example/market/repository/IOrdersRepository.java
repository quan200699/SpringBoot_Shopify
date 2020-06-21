package com.example.market.repository;

import com.example.market.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {
}
