package com.awesomepizza.orderservice.entity;

import com.awesomepizza.orderservice.enumeration.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@Table(name = "pizza_order")
public class PizzaOrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "customer_name", nullable = false)
    private String customerName;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "pizza_order_id")
    private List<PizzaEntity> pizzas;

    @Column(name = "status")
    private OrderStatus status;

    @Column(name = "pizza_order_created")
    private LocalDateTime pizzaOrderCreated;

    @Column(name = "pizza_order_completed")
    private LocalDateTime pizzaOrderCompleted;
}
