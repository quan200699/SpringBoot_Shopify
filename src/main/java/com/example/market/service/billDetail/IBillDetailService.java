package com.example.market.service.billDetail;

import com.example.market.model.Bill;
import com.example.market.model.BillDetail;
import com.example.market.service.IGeneralService;

public interface IBillDetailService extends IGeneralService<BillDetail> {
    Iterable<BillDetail> findAllByBill(Bill bill);
}
