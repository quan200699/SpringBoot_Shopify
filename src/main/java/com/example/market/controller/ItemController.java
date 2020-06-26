package com.example.market.controller;

import com.example.market.model.Item;
import com.example.market.service.item.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/items")
public class ItemController {
    @Autowired
    private IItemService itemService;

    @GetMapping
    public ResponseEntity<Iterable<Item>> getAllItem() {
        return new ResponseEntity<>(itemService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Item> getItem(@PathVariable Long id) {
        Optional<Item> itemOptional = itemService.findById(id);
        return itemOptional.map(item -> new ResponseEntity<>(item, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Item> createItem(@RequestBody Item item) {
        return new ResponseEntity<>(itemService.save(item), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Item> updateItem(@PathVariable Long id, @RequestBody Item item) {
        Optional<Item> itemOptional = itemService.findById(id);
        return itemOptional.map(item1 -> {
            item.setId(item1.getId());
            itemService.save(item);
            return new ResponseEntity<>(item, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Item> deleteItem(@PathVariable Long id) {
        Optional<Item> itemOptional = itemService.findById(id);
        return itemOptional.map(item -> {
            itemService.remove(id);
            return new ResponseEntity<>(item, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
