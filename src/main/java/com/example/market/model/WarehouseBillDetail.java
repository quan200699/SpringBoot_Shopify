package com.example.market.model;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class WarehouseBillDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private WareHouseBill wareHouseBill;

    @ManyToOne
    private Product product;

    private int amount;
}
