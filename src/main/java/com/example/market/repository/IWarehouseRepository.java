package com.example.market.repository;

import com.example.market.model.Warehouse;
import com.example.market.model.query.IProductWarehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IWarehouseRepository extends JpaRepository<Warehouse, Long> {
    @Query(value = "call market.getProductInventory(?1)", nativeQuery = true)
    Iterable<IProductWarehouse> getProductInventory(Long warehouseId);
}
