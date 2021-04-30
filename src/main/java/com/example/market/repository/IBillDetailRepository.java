package com.example.market.repository;

import com.example.market.model.Bill;
import com.example.market.model.BillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IBillDetailRepository extends JpaRepository<BillDetail, Long> {
    Iterable<BillDetail> findAllByBill(Bill bill);
}
