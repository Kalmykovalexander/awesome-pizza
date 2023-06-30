package com.awesomepizza.orderservice.controller;

import com.awesomepizza.orderservice.model.request.PizzaOrderRequest;
import com.awesomepizza.orderservice.model.response.PizzaOrderResponse;
import com.awesomepizza.orderservice.service.IPizzaOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("api/orders")
public class PizzaOrderController {
    private final IPizzaOrderService pizzaOrderService;

    @Autowired
    public PizzaOrderController(IPizzaOrderService pizzaOrderService){
        this.pizzaOrderService = pizzaOrderService;
    }

    @PostMapping
    public Long createOrder(@RequestBody @Valid PizzaOrderRequest request) {
        return pizzaOrderService.createOrder(request);
    }

    @GetMapping("/status/{pizzaOrderId}")
    public PizzaOrderResponse getPizzaOrderState(@PathVariable Long pizzaOrderId) {
        return pizzaOrderService.getPizzaOrderState(pizzaOrderId);
    }
}
