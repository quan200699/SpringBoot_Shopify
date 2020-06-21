package com.example.market.service.order;

import com.example.market.model.Orders;
import com.example.market.service.IGeneralService;

public interface IOrdersService extends IGeneralService<Orders> {
    Integer sumProductAmount(Long id);
}
