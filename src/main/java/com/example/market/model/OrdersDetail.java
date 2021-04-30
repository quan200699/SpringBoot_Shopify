package com.example.market.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class OrdersDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int amount;

    private Double price;

    @ManyToOne
    private Product product;

    @ManyToOne
    private Orders orders;
}
