package com.example.market.repository;

import com.example.market.model.WareHouseBill;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IWarehousingBillRepository extends JpaRepository<WareHouseBill, Long> {
}
