package com.example.market.controller;


import com.example.market.model.WarehousingBill;
import com.example.market.service.warehousingbill.IWarehousingBillService;
import com.example.market.service.warehousingbill.WarehousingBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/warehousingbill")
public class WarehousingBillController {

    @Autowired
    private IWarehousingBillService warehousingBillService;

    @PostMapping
    public ResponseEntity<WarehousingBill> createWarehousingBill(@RequestBody WarehousingBill warehousingBill){
        long milis = System.currentTimeMillis();
        Date date =  new Date(milis);
        warehousingBill.setCreateDate(date);
        return new ResponseEntity<>(warehousingBillService.save(warehousingBill), HttpStatus.OK) ;
    }

    @GetMapping
    public ResponseEntity<Iterable<WarehousingBill>> getAllWarehousingBill(){
        return new ResponseEntity<>(warehousingBillService.findAll(), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<WarehousingBill> editWarehousingBill(@PathVariable Long id, @RequestBody WarehousingBill warehousingBill){
        Optional<WarehousingBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehousingBill.setId(id);
        return new ResponseEntity<>(warehousingBillService.save(warehousingBill), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<WarehousingBill> deleteWarehousingBill(@PathVariable Long id){
        Optional<WarehousingBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        warehousingBillService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<WarehousingBill> getWarehousingBill(@PathVariable Long id){
        Optional<WarehousingBill> warehousingBillOptional = warehousingBillService.findById(id);
        if (!warehousingBillOptional.isPresent()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(warehousingBillOptional.get(), HttpStatus.OK);
    }
}
