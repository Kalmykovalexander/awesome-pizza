package com.awesomepizza.orderservice.service;

import com.awesomepizza.orderservice.model.request.PizzaOrderRequest;
import com.awesomepizza.orderservice.model.response.PizzaOrderResponse;

public interface IPizzaOrderService {
    Long createOrder(PizzaOrderRequest request);
    PizzaOrderResponse getPizzaOrderState(Long pizzaOrderId);
}
