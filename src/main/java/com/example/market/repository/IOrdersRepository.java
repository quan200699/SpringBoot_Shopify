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

    @Query("SELECT DISTINCT od.product FROM Orders o left join OrdersDetail od on o.id = od.orders.id" +
            " WHERE o.user = ?1 and o.status = true")
    Iterable<Product> findAllProductUserBought(User user);

    @Query(value = "select sum(amount * price) " +
            "from market.orders " +
            "left join market.orders_detail od on orders.id = od.orders_id " +
            "left join market.product p on od.product_id = p.id " +
            "where month(create_date) = ?1 and year(create_date) = ?2", nativeQuery = true)
    Long sumTotalPriceInput(Integer month, Integer year);
}
