package com.example.market.service.order;

import com.example.market.model.Orders;
import com.example.market.model.Product;
import com.example.market.model.auth.User;
import com.example.market.service.IGeneralService;

public interface IOrdersService extends IGeneralService<Orders> {
    Iterable<Orders> findAllByUserAndStatus(User user, Boolean status);

    Iterable<Orders> findAllByStatus(Boolean status);

    Iterable<Product> findAllProductUserBought(User user);
}
