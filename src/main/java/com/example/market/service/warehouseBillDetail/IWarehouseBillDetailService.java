package com.example.market.service.warehouseBillDetail;

import com.example.market.model.WareHouseBill;
import com.example.market.model.WarehouseBillDetail;
import com.example.market.service.IGeneralService;

public interface IWarehouseBillDetailService extends IGeneralService<WarehouseBillDetail> {
    Iterable<WarehouseBillDetail> findAllByWareHouseBill(WareHouseBill warehouseBill);
}
