package com.example.market.controller;


import com.example.market.model.WareHouseBill;
import com.example.market.service.warehousingbill.IWarehousingBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/warehousebills")
public class WarehouseBillController {

    @Autowired
    private IWarehousingBillService warehousingBillService;

    @PostMapping
    public ResponseEntity<WareHouseBill> createWarehousingBill(@RequestBody WareHouseBill wareHouseBill){
        long milis = System.currentTimeMillis();
        Date date =  new Date(milis);
        wareHouseBill.setCreateDate(date);
        return new ResponseEntity<>(warehousingBillService.save(wareHouseBill), HttpStatus.OK) ;
    }

    @GetMapping
    public ResponseEntity<Iterable<WareHouseBill>> getAllWarehousingBill(){
        return new ResponseEntity<>(warehousingBillService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WareHouseBill> editWarehousingBill(@PathVariable Long id, @RequestBody WareHouseBill wareHouseBill){
        Optional<WareHouseBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        wareHouseBill.setId(id);
        return new ResponseEntity<>(warehousingBillService.save(wareHouseBill), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WareHouseBill> deleteWarehousingBill(@PathVariable Long id){
        Optional<WareHouseBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehousingBillService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WareHouseBill> getWarehousingBill(@PathVariable Long id){
        Optional<WareHouseBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(warehousingBillOptional.get(), HttpStatus.OK);
    }
}
