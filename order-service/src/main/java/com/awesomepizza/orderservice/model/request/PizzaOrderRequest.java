package com.awesomepizza.orderservice.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
public class PizzaOrderRequest {
    @JsonProperty("id")
    private Long id;
    @NotNull(message = "Customer name is required")
    private String customerName;
    @NotEmpty(message = "Pizzas are required")
    private List<PizzaDto> pizzas;
}