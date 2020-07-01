package com.example.market.controller;

import com.example.market.model.Orders;
import com.example.market.model.OrdersDetail;
import com.example.market.model.auth.User;
import com.example.market.service.order.IOrdersService;
import com.example.market.service.orderDetail.IOrdersDetailService;
import com.example.market.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
@CrossOrigin("*")
public class OrdersController {
    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private IOrdersDetailService ordersDetailService;

    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<Iterable<Orders>> getAllOrders(@RequestParam Boolean status) {
        if (status == null) {
            return new ResponseEntity<>(ordersService.findAll(), HttpStatus.OK);
        }
        return new ResponseEntity<>(ordersService.findAllByStatus(status), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Orders> findById(@PathVariable Long id) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        return ordersOptional.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Orders> createOrders(@RequestBody Orders orders) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        orders.setCreateDate(date);
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

    @GetMapping("/users/{id}")
    public ResponseEntity<Iterable<Orders>> getAllOrderByUser(@PathVariable Long id, @RequestParam Boolean status) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(ordersService.findAllByUserAndStatus(user, status),
                HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/{id}/order-details")
    public ResponseEntity<Iterable<OrdersDetail>> findAllOrderDetailByOrder(@PathVariable Long id) {
        Optional<Orders> ordersOptional = ordersService.findById(id);
        return ordersOptional.map(orders -> new ResponseEntity<>(ordersDetailService.findAllByOrders(orders), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
