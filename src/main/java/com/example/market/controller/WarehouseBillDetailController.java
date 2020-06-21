package com.example.market.controller;

import com.example.market.model.WarehouseBillDetail;
import com.example.market.service.warehouseBillDetail.IWarehouseBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/warehouse-bill-details")
@CrossOrigin("*")
public class WarehouseBillDetailController {
    @Autowired
    private IWarehouseBillDetailService warehouseBillDetailService;

    @GetMapping
    public ResponseEntity<Iterable<WarehouseBillDetail>> getAllWarehouseBillDetail() {
        return new ResponseEntity<>(warehouseBillDetailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<WarehouseBillDetail> findById(@PathVariable Long id) {
        Optional<WarehouseBillDetail> warehouseBillDetailOptional = warehouseBillDetailService.findById(id);
        return warehouseBillDetailOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<WarehouseBillDetail> createWarehouseBillDetail(@RequestBody WarehouseBillDetail warehouseBillDetail) {
        return new ResponseEntity<>(warehouseBillDetailService.save(warehouseBillDetail), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehouseBillDetail> editWarehouseBillDetail(@PathVariable Long id, @RequestBody WarehouseBillDetail warehouseBillDetail) {
        Optional<WarehouseBillDetail> warehouseBillDetailOptional = warehouseBillDetailService.findById(id);
        if (!warehouseBillDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehouseBillDetail.setId(id);
        return new ResponseEntity<>(warehouseBillDetailService.save(warehouseBillDetail), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WarehouseBillDetail> deleteWarehouseBillDetail(@PathVariable Long id) {
        Optional<WarehouseBillDetail> warehouseBillDetailOptional = warehouseBillDetailService.findById(id);
        if (!warehouseBillDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehouseBillDetailService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
