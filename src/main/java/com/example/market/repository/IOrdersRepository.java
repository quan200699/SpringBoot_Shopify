package com.example.market.repository;

import com.example.market.model.Orders;
import com.example.market.model.auth.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IOrdersRepository extends JpaRepository<Orders, Long> {
//    @Query("select sum(o.amount) from Product p left join Orders o on p.id = o.product.id where p.id = ?1 group by p.id")
//    Integer sumProductAmount(Long id);

    Iterable<Orders> findAllByUser(User user);

    Iterable<Orders> findAllByStatus(Boolean status);
}
