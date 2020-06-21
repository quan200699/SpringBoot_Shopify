package com.example.market.repository;

import com.example.market.model.WareHouseBill;
import com.example.market.model.WarehouseBillDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseBillDetailRepository extends JpaRepository<WarehouseBillDetail, Long> {
    Iterable<WarehouseBillDetail> findAllByWareHouseBill(WareHouseBill warehouseBill);

    @Query("SELECT SUM(d.amount) From WarehouseBillDetail d left join Product p on d.product.id = p.id where d.product.id = ?1 group by d.product.id")
    int sumAllProduct(Long productId);
}
