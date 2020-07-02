package com.example.market.model;

import com.example.market.model.auth.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int evaluate;

    private String comment;

    private Date createDate;

    @ManyToOne
    private User user;

    @ManyToOne
    private Product product;
}
