package com.example.market.service.shoppingCart;

import com.example.market.model.ShoppingCart;
import com.example.market.model.auth.User;
import com.example.market.service.IGeneralService;

import java.util.Optional;

public interface IShoppingCartService extends IGeneralService<ShoppingCart> {
    Optional<ShoppingCart> findByUser(User user);
}
