package com.example.market.controller;

import com.example.market.model.ShoppingCart;
import com.example.market.service.shoppingCart.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/carts")
public class ShoppingCartController {
    @Autowired
    private IShoppingCartService shoppingCartService;

    @GetMapping
    public ResponseEntity<Iterable<ShoppingCart>> getAllShoppingCart() {
        return new ResponseEntity<>(shoppingCartService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ShoppingCart> getShoppingCart(@PathVariable Long id) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findById(id);
        return shoppingCartOptional.map(shoppingCart -> new ResponseEntity<>(shoppingCart, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> createShoppingCart(@RequestBody ShoppingCart shoppingCart) {
        return new ResponseEntity<>(shoppingCartService.save(shoppingCart), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ShoppingCart> updateShoppingCart(@PathVariable Long id, @RequestBody ShoppingCart shoppingCart) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findById(id);
        return shoppingCartOptional.map(shoppingCart1 -> {
            shoppingCart.setId(shoppingCart1.getId());
            shoppingCartService.save(shoppingCart);
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ShoppingCart> deleteShoppingCart(@PathVariable Long id) {
        Optional<ShoppingCart> shoppingCartOptional = shoppingCartService.findById(id);
        return shoppingCartOptional.map(shoppingCart -> {
            shoppingCartService.remove(id);
            return new ResponseEntity<>(shoppingCart, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
