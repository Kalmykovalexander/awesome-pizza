package com.awesomepizza.orderservice.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "pizza")
public class PizzaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "pizza_name")
    private String pizzaName;

    @Column(name = "quantity")
    private int quantity;
}
