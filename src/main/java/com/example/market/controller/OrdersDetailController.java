package com.example.market.controller;

import com.example.market.model.OrdersDetail;
import com.example.market.service.orderDetail.IOrdersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order-details")
@CrossOrigin("*")
public class OrdersDetailController {
    @Autowired
    private IOrdersDetailService ordersDetailService;

    @GetMapping
    public ResponseEntity<Iterable<OrdersDetail>> getAllOrdersDetail() {
        return new ResponseEntity<>(ordersDetailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDetail> findById(@PathVariable Long id) {
        Optional<OrdersDetail> ordersDetailOptional = ordersDetailService.findById(id);
        return ordersDetailOptional.map(ordersDetail -> new ResponseEntity<>(ordersDetail, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<OrdersDetail> createOrdersDetail(@RequestBody OrdersDetail ordersDetail) {
        return new ResponseEntity<>(ordersDetailService.save(ordersDetail), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrdersDetail> editOrdersDetail(@PathVariable Long id, @RequestBody OrdersDetail ordersDetail) {
        Optional<OrdersDetail> ordersDetailOptional = ordersDetailService.findById(id);
        if (!ordersDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersDetail.setId(id);
        return new ResponseEntity<>(ordersDetailService.save(ordersDetail), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<OrdersDetail> deleteOrdersDetail(@PathVariable Long id) {
        Optional<OrdersDetail> ordersDetailOptional = ordersDetailService.findById(id);
        if (!ordersDetailOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersDetailService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/sum")
    public ResponseEntity<Integer> sumAmount(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersDetailService.sumProductAmount(id), HttpStatus.OK);
    }
}
