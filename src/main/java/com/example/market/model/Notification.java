package com.example.market.model;

import com.example.market.model.auth.User;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class Notification {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String message;

    private boolean status;

    private Date createDate;

    @ManyToOne
    private User user;
}
