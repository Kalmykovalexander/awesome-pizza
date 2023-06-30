package com.awesomepizza.orderservice.model.response;

import com.awesomepizza.orderservice.enumeration.OrderStatus;
import lombok.Data;

@Data
public class PizzaOrderResponse {
    private Long id;
    private String customerName;
    private OrderStatus status;
}
