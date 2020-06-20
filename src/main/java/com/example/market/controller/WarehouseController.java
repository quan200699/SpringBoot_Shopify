package com.example.market.controller;

import com.example.market.model.Warehouse;
import com.example.market.service.warehouse.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/warehouses")
public class WarehouseController {

    @Autowired
    private IWarehouseService warehouseService;

    @GetMapping
    public ResponseEntity<Iterable<Warehouse>> getAllWarehouse(){
        return new ResponseEntity<>(warehouseService.findAll(), HttpStatus.OK) ;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Warehouse> findById(@PathVariable Long id){
        Optional<Warehouse> warehouse = warehouseService.findById(id);
        return warehouse.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Warehouse> createWarehouse(@RequestBody Warehouse warehouse){
        return new ResponseEntity<>(warehouseService.save(warehouse), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Warehouse> editWarehouse(@PathVariable Long id, @RequestBody Warehouse warehouse){
        Optional<Warehouse> warehouseOptional = warehouseService.findById(id);
        if (!warehouseOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehouse.setId(id);
        return new ResponseEntity<>(warehouseService.save(warehouse), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Warehouse> deleteWarehouse(@PathVariable Long id){
        Optional<Warehouse> warehouse = warehouseService.findById(id);
        if (!warehouse.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehouseService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
