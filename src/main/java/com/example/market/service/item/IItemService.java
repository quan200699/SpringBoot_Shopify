package com.example.market.service.item;

import com.example.market.model.Item;
import com.example.market.model.ShoppingCart;
import com.example.market.service.IGeneralService;

public interface IItemService extends IGeneralService<Item> {
    Iterable<Item> findAllByShoppingCart(ShoppingCart shoppingCart);
}
