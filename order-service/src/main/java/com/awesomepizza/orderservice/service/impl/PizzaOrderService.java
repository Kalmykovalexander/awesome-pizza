package com.awesomepizza.orderservice.service.impl;

import com.awesomepizza.orderservice.enumeration.OrderStatus;
import com.awesomepizza.orderservice.mapper.OrderMapper;
import com.awesomepizza.orderservice.entity.PizzaOrderEntity;
import com.awesomepizza.orderservice.model.request.PizzaOrderRequest;
import com.awesomepizza.orderservice.model.response.PizzaOrderResponse;
import com.awesomepizza.orderservice.repository.PizzaOrderRepository;
import com.awesomepizza.orderservice.service.IPizzaOrderService;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PizzaOrderService implements IPizzaOrderService {
    private static final Logger log = LoggerFactory.getLogger(PizzaOrderService.class);

    private final PizzaOrderRepository pizzaOrderRepository;
    private final OrderMapper mapper;
    private static Boolean isPizzaioloMakingPizza = false;

    @Override
    @Transactional
    public Long createOrder(PizzaOrderRequest request) {
        log.info("[START] PizzaOrderService::createOrder");
        PizzaOrderEntity order = mapper.fromRequestToEntity(request);
        PizzaOrderEntity entity = pizzaOrderRepository.save(order);
        log.info("[END] PizzaOrderService::createOrder");
        return Objects.isNull(entity) ? null : entity.getId();
    }

    @Override
    public PizzaOrderResponse getPizzaOrderState(Long pizzaOrderId) {
        Optional<PizzaOrderEntity> pizzaOrder = pizzaOrderRepository.findById(pizzaOrderId);
        return mapper.fromEntityToDto(pizzaOrder);
    }

    @Scheduled(fixedRate = 20000)
    public void processOrders() throws InterruptedException {
        log.info("[START] PizzaOrderService::processOrders");
        Optional<PizzaOrderEntity> firstOrder = pizzaOrderRepository.findFirstByStatusOrderByPizzaOrderCreatedAsc(OrderStatus.PENDING);
        if (firstOrder.isPresent() && !isPizzaioloMakingPizza) {
            isPizzaioloMakingPizza = true;
            PizzaOrderEntity order = firstOrder.get();

            // update order's status IN_PROGRESS
            log.info("Order " + order.getId() + " in progress");
            order.setStatus(OrderStatus.IN_PROGRESS);
            pizzaOrderRepository.save(order);

            // waiting for the cooking time
            // Thread.sleep(3 * 60 * 1000); // 3 minutes
            Thread.sleep(30000); // 30 seconds

            // update order's status READY
            log.info("Order " + order.getId() + " is ready");
            order.setStatus(OrderStatus.READY);
            pizzaOrderRepository.save(order);
            isPizzaioloMakingPizza = false;
        }
        log.info("[END] PizzaOrderService::processOrders");
    }

}
