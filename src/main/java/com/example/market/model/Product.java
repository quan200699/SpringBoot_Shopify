package com.example.market.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private boolean status;

    @Column(nullable = false)
    private String preservation;

    @Column(nullable = false)
    private String ingredient;

    @Column(nullable = false)
    private String instructional;

    @Column(nullable = false)
    private int mass;

    @Column(nullable = false)
    private String description;

    @ManyToOne
    private Category category;
}
