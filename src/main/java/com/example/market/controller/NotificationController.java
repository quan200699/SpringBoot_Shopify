package com.example.market.controller;

import com.example.market.model.Notification;
import com.example.market.service.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.Optional;

@RestController
@CrossOrigin("*")
@RequestMapping("/notifications")
public class NotificationController {
    @Autowired
    private INotificationService notificationService;

    @GetMapping
    public ResponseEntity<Iterable<Notification>> getAllNotification() {
        return new ResponseEntity<>(notificationService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Notification> getNotification(@PathVariable Long id) {
        Optional<Notification> notificationOptional = notificationService.findById(id);
        return notificationOptional.map(notification -> new ResponseEntity<>(notification, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Notification> createNotification(@RequestBody Notification notification) {
        long milis = System.currentTimeMillis();
        Date date = new Date(milis);
        notification.setCreateDate(date);
        return new ResponseEntity<>(notificationService.save(notification), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Notification> updateNotification(@PathVariable Long id, @RequestBody Notification notification) {
        Optional<Notification> notificationOptional = notificationService.findById(id);
        return notificationOptional.map(notification1 -> {
            notification.setId(notification1.getId());
            notificationService.save(notification);
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Notification> deleteNotification(@PathVariable Long id) {
        Optional<Notification> notificationOptional = notificationService.findById(id);
        return notificationOptional.map(notification -> {
            notificationService.remove(id);
            return new ResponseEntity<>(notification, HttpStatus.OK);
        }).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
