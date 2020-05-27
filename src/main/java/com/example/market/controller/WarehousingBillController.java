package com.example.market.controller;


import com.example.market.model.WarehousingBill;
import com.example.market.service.warehousingbill.IWarehousingBillService;
import com.example.market.service.warehousingbill.WarehousingBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

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

}
