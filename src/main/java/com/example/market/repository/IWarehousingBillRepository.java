package com.example.market.repository;

import com.example.market.model.WareHouseBill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface IWarehousingBillRepository extends JpaRepository<WareHouseBill, Long> {
    @Query(value = "select sum(amount * price)\n" +
            "from ware_house_bill left join warehouse_bill_detail wbd " +
            "on ware_house_bill.id = wbd.ware_house_bill_id " +
            "left join product p on wbd.product_id = p.id " +
            "where month(create_date) = ?1 and year(create_date) = ?2", nativeQuery = true)
    Long sumTotalPriceHaveBought(Integer month, Integer year);
}
