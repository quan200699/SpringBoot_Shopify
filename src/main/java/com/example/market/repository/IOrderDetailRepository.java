package com.example.market.repository;

import com.example.market.model.Orders;
import com.example.market.model.OrdersDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrderDetailRepository extends JpaRepository<OrdersDetail, Long> {
    @Query("select sum(o.amount) from Product p left join OrdersDetail o on p.id = o.product.id where p.id = ?1 group by p.id")
    Integer sumProductAmount(Long id);

    Iterable<OrdersDetail> findAllByOrders(Orders orders);
}
