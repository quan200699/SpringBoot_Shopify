package com.example.market.service.order;

import com.example.market.model.Orders;
import com.example.market.model.auth.User;
import com.example.market.service.IGeneralService;

public interface IOrdersService extends IGeneralService<Orders> {
    Iterable<Orders> findAllByUser(User user);

    Iterable<Orders> findAllByStatus(Boolean status);
}
