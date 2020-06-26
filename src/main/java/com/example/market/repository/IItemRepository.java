package com.example.market.repository;

import com.example.market.model.Item;
import com.example.market.model.ShoppingCart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IItemRepository extends JpaRepository<Item, Long> {
    Iterable<Item> findAllByShoppingCart(ShoppingCart shoppingCart);
}
