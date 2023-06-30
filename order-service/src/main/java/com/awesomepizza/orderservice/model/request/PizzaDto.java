package com.awesomepizza.orderservice.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class PizzaDto {
    @NotNull(message = "Pizza name is required")
    private String pizzaName;
    @NotNull(message = "Quantity is required")
    private int quantity;
}
