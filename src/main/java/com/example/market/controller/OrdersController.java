package com.example.market.controller;

import com.example.market.model.Orders;
import com.example.market.service.order.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @GetMapping
    public ResponseEntity<Iterable<Orders>> getAllOrders() {
        return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findById(@PathVariable Long id) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        return ordersOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Orders> editOrders(@PathVariable Long id, @RequestBody Orders orders) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        orders.setId(id);
        return new ResponseEntity<>(ordersService.save(orders), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Orders> deleteOrders(@PathVariable Long id) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        if (!ordersOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersService.remove(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/{id}/sum")
    public ResponseEntity<Integer> sumAmount(@PathVariable Long id) {
        if (id == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(ordersService.sumProductAmount(id), HttpStatus.OK);
    }
}
