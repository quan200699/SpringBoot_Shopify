package com.example.market.repository;

import com.example.market.model.Orders;
import com.example.market.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    Iterable<Orders> findAllByUser(User user);

    Iterable<Orders> findAllByStatus(Boolean status);
}
