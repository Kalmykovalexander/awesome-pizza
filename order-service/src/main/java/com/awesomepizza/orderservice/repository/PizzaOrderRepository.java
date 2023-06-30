package com.awesomepizza.orderservice.repository;

import com.awesomepizza.orderservice.entity.PizzaOrderEntity;
import com.awesomepizza.orderservice.enumeration.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PizzaOrderRepository extends JpaRepository<PizzaOrderEntity, Long> {
    Optional<PizzaOrderEntity> findFirstByStatusOrderByPizzaOrderCreatedAsc(OrderStatus status);
}
