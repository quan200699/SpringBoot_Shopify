package com.example.market.controller;

import com.example.market.model.Image;
import com.example.market.service.image.IImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/images")
public class ImageController {
    @Autowired
    private IImageService imageService;

    @GetMapping
    public ResponseEntity<Iterable<Image>> getAllImage() {
        return new ResponseEntity<>(imageService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Image> createNewImage(@RequestBody Image image) {
        return new ResponseEntity<>(imageService.save(image), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Image> getImage(@PathVariable Long id) {
        Optional<Image> imageOptional = imageService.findById(id);
        return imageOptional.map(image -> new ResponseEntity<>(image, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
