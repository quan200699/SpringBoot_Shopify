package com.example.market.controller;

import com.example.market.model.CustomerInfo;
import com.example.market.service.customerInfo.ICustomerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/customer-infos")
public class CustomerInfoController {
    @Autowired
    private ICustomerInfoService customerInfoService;

    @GetMapping
    public ResponseEntity<Iterable<CustomerInfo>> getAllCustomerInfo() {
        return new ResponseEntity<>(customerInfoService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerInfo> getCustomerInfo(@PathVariable Long id) {
        Optional<CustomerInfo> customerInfoOptional = customerInfoService.findById(id);
        return customerInfoOptional.map(customerInfo -> new ResponseEntity<>(customerInfo, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<CustomerInfo> createCustomerInfo(@RequestBody CustomerInfo customerInfo) {
        return new ResponseEntity<>(customerInfoService.save(customerInfo), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CustomerInfo> updateCustomerInfo(@PathVariable Long id, @RequestBody CustomerInfo customerInfo) {
        Optional<CustomerInfo> customerInfoOptional = customerInfoService.findById(id);
        return customerInfoOptional.map(customerInfo1 -> {
            customerInfo.setId(customerInfo1.getId());
            customerInfoService.save(customerInfo);
            return new ResponseEntity<>(customerInfo, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CustomerInfo> deleteCustomerInfo(@PathVariable Long id) {
        Optional<CustomerInfo> customerInfoOptional = customerInfoService.findById(id);
        return customerInfoOptional.map(customerInfo -> {
            customerInfoService.remove(id);
            return new ResponseEntity<>(customerInfo, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
