package com.example.market.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Bill {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "VARCHAR(50)", unique = true, nullable = false)
    private String code;

    private Date createDate;

    @ManyToOne
    private Warehouse warehouse;
}
