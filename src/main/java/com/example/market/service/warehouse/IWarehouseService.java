package com.example.market.service.warehouse;

import com.example.market.model.Warehouse;
import com.example.market.model.query.IProductWarehouse;
import com.example.market.service.IGeneralService;

public interface IWarehouseService extends IGeneralService<Warehouse> {
    Iterable<IProductWarehouse> getProductInventory(Long warehouseId);
}
