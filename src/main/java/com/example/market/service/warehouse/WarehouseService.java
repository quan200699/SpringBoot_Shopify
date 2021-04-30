package com.example.market.service.warehouse;

import com.example.market.model.Warehouse;
import com.example.market.model.query.IProductWarehouse;
import com.example.market.repository.IWarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class WarehouseService implements IWarehouseService {
    @Autowired
    private IWarehouseRepository warehouseRepository;

    @Override
    public Iterable<Warehouse> findAll() {
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> findById(Long id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public Warehouse save(Warehouse warehouse) {
        return warehouseRepository.save(warehouse);
    }

    @Override
    public void remove(Long id) {
        warehouseRepository.deleteById(id);
    }

    @Override
    public Iterable<IProductWarehouse> getProductInventory(Long warehouseId) {
        return warehouseRepository.getProductInventory(warehouseId);
    }
}
