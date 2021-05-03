package com.example.market.controller;

import com.example.market.model.Notification;
import com.example.market.model.ShoppingCart;
import com.example.market.model.auth.JwtResponse;
import com.example.market.model.auth.User;
import com.example.market.model.query.IUserChat;
import com.example.market.service.JwtService;
import com.example.market.service.notification.INotificationService;
import com.example.market.service.shoppingCart.IShoppingCartService;
import com.example.market.service.user.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@CrossOrigin("*")
@RestController
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private IUserService userService;

    @Autowired
    private INotificationService notificationService;

    @Autowired
    private IShoppingCartService shoppingCartService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtService.generateTokenLogin(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User currentUser = userService.findByEmail(user.getEmail()).get();
        return ResponseEntity.ok(new JwtResponse(jwt, currentUser.getId(), userDetails.getUsername(), currentUser.getFullName(), userDetails.getAuthorities()));
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody User user) {
        Iterable<User> users = userService.findAll();
        for (User currentUser : users) {
            if (currentUser.getEmail().equals(user.getEmail())) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        userService.save(user);
        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(user);
        shoppingCartService.save(shoppingCart);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/users/{id}/notifications")
    public ResponseEntity<Iterable<Notification>> getAllNotificationByUser(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(notificationService.findAllByStatusIsFalseAndUser(user), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users/{id}/notifications-desc")
    public ResponseEntity<Iterable<Notification>> getAllNotificationByUserDateDesc(@PathVariable Long id) {
        Optional<User> userOptional = userService.findById(id);
        return userOptional.map(user -> new ResponseEntity<>(notificationService.findAllDateDesc(id), HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<IUserChat>> getAllUser() {
        return new ResponseEntity<>(userService.getAllUserHasRoleUser(), HttpStatus.OK);
    }
}
