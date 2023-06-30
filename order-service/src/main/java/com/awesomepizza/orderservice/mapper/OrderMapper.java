package com.awesomepizza.orderservice.mapper;

import com.awesomepizza.orderservice.entity.PizzaEntity;
import com.awesomepizza.orderservice.enumeration.OrderStatus;
import com.awesomepizza.orderservice.entity.PizzaOrderEntity;
import com.awesomepizza.orderservice.model.request.PizzaOrderRequest;
import com.awesomepizza.orderservice.model.request.PizzaDto;
import com.awesomepizza.orderservice.model.response.PizzaOrderResponse;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class OrderMapper {
    public PizzaOrderEntity fromRequestToEntity(PizzaOrderRequest request) {
        PizzaOrderEntity pizzaOrderEntity = new PizzaOrderEntity();
        pizzaOrderEntity.setCustomerName(request.getCustomerName());

        List<PizzaEntity> pizzas = new ArrayList<>();
        for (PizzaDto pizzaDto : request.getPizzas()) {
            PizzaEntity pizzaEntity = new PizzaEntity();
            pizzaEntity.setPizzaName(pizzaDto.getPizzaName());
            pizzaEntity.setQuantity(pizzaDto.getQuantity());
            pizzas.add(pizzaEntity);
        }
        pizzaOrderEntity.setPizzas(pizzas);
        pizzaOrderEntity.setStatus(OrderStatus.PENDING);
        pizzaOrderEntity.setPizzaOrderCreated(LocalDateTime.now());
        return pizzaOrderEntity;
    }

    public PizzaOrderResponse fromEntityToDto(Optional<PizzaOrderEntity> optional) {
        PizzaOrderResponse response = new PizzaOrderResponse();
        if (!optional.isPresent()) {
            return response;
        }
        PizzaOrderEntity entity = optional.get();
        response.setId(entity.getId());
        response.setCustomerName(entity.getCustomerName());
        response.setStatus(entity.getStatus());
        return response;
    }
}
