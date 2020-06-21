package com.example.market.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class WareHouseBill {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Date createDate;

    @ManyToOne
    private Warehouse warehouse;
}
