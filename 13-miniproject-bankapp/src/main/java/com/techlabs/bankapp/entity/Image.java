package com.techlabs.bankapp.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Data
@Table(name = "images")
public class Image {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name_image")
    private String name;

    @Column(name = "url_image")
    private String url;

    @ManyToOne
    @JoinColumn(name="customer_id",nullable = false)
    private Customer customer;
}
