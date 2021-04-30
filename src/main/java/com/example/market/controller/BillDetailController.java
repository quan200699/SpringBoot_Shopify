package com.example.market.controller;

import com.example.market.model.BillDetail;
import com.example.market.service.billDetail.IBillDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/bill-details")
public class BillDetailController {
    @Autowired
    private IBillDetailService billDetailService;

    @GetMapping
    public ResponseEntity<Iterable<BillDetail>> getAllBillDetail() {
        return new ResponseEntity<>(billDetailService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BillDetail> createNewBillDetail(@RequestBody BillDetail billDetail) {
        return new ResponseEntity<>(billDetailService.save(billDetail), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BillDetail> getBillDetail(@PathVariable Long id) {
        Optional<BillDetail> billDetailOptional = billDetailService.findById(id);
        return billDetailOptional.map(billDetail -> new ResponseEntity<>(billDetail, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<BillDetail> updateBillDetail(@PathVariable Long id, @RequestBody BillDetail billDetail) {
        Optional<BillDetail> billOptional = billDetailService.findById(id);
        return billOptional.map(billDetail1 -> {
            billDetail.setId(billDetail1.getId());
            return new ResponseEntity<>(billDetailService.save(billDetail), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<BillDetail> deleteBillDetail(@PathVariable Long id) {
        Optional<BillDetail> billDetailOptional = billDetailService.findById(id);
        return billDetailOptional.map(billDetail -> {
            billDetailService.remove(id);
            return new ResponseEntity<>(billDetail, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
