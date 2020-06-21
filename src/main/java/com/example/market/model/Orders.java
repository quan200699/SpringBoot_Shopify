package com.example.market.model;

import com.example.market.model.auth.User;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
}
