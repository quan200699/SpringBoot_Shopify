package com.example.market.repository;

import com.example.market.model.WarehouseBillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseBillDetailRepository extends JpaRepository<WarehouseBillDetail, Long> {
}
