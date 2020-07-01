package com.example.market.repository;

import com.example.market.model.Orders;
import com.example.market.model.Product;
import com.example.market.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {
    Iterable<Orders> findAllByUserAndStatus(User user, Boolean status);

    Iterable<Orders> findAllByStatus(Boolean status);

    @Query("SELECT od.product FROM Orders o left join OrdersDetail od on o.id = od.orders.id" +
            " WHERE o.user = ?1")
    Iterable<Product> findAllProductUserBought(User user);
}
