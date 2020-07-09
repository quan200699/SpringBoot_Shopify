package com.example.market.model;

import com.example.market.model.auth.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private boolean status;

    @ManyToOne
    private User user;
}
