package com.example.market.service.orderDetail;

import com.example.market.model.OrdersDetail;
import com.example.market.service.IGeneralService;

public interface IOrdersDetailService extends IGeneralService<OrdersDetail> {
    Integer sumProductAmount(Long id);
}
