package com.awesomepizza.orderservice;

import com.awesomepizza.orderservice.controller.PizzaOrderController;
import com.awesomepizza.orderservice.enumeration.OrderStatus;
import com.awesomepizza.orderservice.model.request.PizzaDto;
import com.awesomepizza.orderservice.model.request.PizzaOrderRequest;
import com.awesomepizza.orderservice.model.response.PizzaOrderResponse;
import com.awesomepizza.orderservice.service.IPizzaOrderService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@WebMvcTest(PizzaOrderController.class)
public class PizzaOrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IPizzaOrderService pizzaOrderService;

    @Test
    public void testCreateOrder() throws Exception {
        PizzaOrderRequest request = new PizzaOrderRequest();
        request.setCustomerName("Alex");
        List<PizzaDto> pizzas = new ArrayList<>();
        PizzaDto pizzaDto = new PizzaDto();
        pizzaDto.setPizzaName("Margherita");
        pizzaDto.setQuantity(1);
        pizzas.add(pizzaDto);
        request.setPizzas(pizzas);

        // Mock the behavior of the pizzaOrderService.createOrder method
        Long orderId = 1L;
        when(pizzaOrderService.createOrder(request)).thenReturn(orderId);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/orders")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(String.valueOf(orderId)));

        verify(pizzaOrderService).createOrder(request);
    }

    @Test
    public void testGetPizzaOrderState() throws Exception {
        Long orderId = 1L;
        PizzaOrderResponse response = new PizzaOrderResponse();
        response.setId(1L);
        response.setCustomerName("Alex");
        response.setStatus(OrderStatus.PENDING);

        when(pizzaOrderService.getPizzaOrderState(orderId)).thenReturn(response);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/orders/status/{pizzaOrderId}", orderId))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(orderId));

        verify(pizzaOrderService).getPizzaOrderState(orderId);
    }
}
