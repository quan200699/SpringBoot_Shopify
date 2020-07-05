package com.example.market.service.warehousingbill;

import com.example.market.model.WareHouseBill;
import com.example.market.service.IGeneralService;

public interface IWarehousingBillService extends IGeneralService<WareHouseBill> {
    Long sumTotalPriceHaveBought(Integer month, Integer year);
}
