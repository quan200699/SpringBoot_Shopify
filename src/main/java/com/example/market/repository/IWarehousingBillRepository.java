package com.example.market.repository;

import com.example.market.model.Warehouse;
import com.example.market.model.WarehousingBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWarehousingBillRepository extends JpaRepository<WarehousingBill, Long> {
}
