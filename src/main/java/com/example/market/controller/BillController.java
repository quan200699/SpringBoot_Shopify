package com.example.market.controller;

import com.example.market.model.Bill;
import com.example.market.service.bill.IBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/bills")
public class BillController {
    @Autowired
    private IBillService billService;

    @GetMapping
    public ResponseEntity<Iterable<Bill>> getAllBill() {
        return new ResponseEntity<>(billService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Bill> createNewBill(@RequestBody Bill bill) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        bill.setCreateDate(date);
        return new ResponseEntity<>(billService.save(bill), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bill> getBill(@PathVariable Long id) {
        Optional<Bill> billOptional = billService.findById(id);
        return billOptional.map(bill -> new ResponseEntity<>(bill, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bill> updateBill(@PathVariable Long id, @RequestBody Bill bill) {
        Optional<Bill> billOptional = billService.findById(id);
        return billOptional.map(bill1 -> {
            bill.setId(bill1.getId());
            return new ResponseEntity<>(billService.save(bill), HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Bill> deleteBill(@PathVariable Long id) {
        Optional<Bill> billOptional = billService.findById(id);
        return billOptional.map(bill -> {
            billService.remove(id);
            return new ResponseEntity<>(bill, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
