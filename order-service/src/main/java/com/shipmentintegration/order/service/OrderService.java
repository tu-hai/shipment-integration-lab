package com.shipmentintegration.order.service;

import com.shipmentintegration.order.dto.OrderRequest;
import com.shipmentintegration.order.dto.OrderResponse;
import com.shipmentintegration.order.event.OrderCreatedEvent;
import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderService.class);
    private final Map<String, OrderResponse> orderRepository = new ConcurrentHashMap<>();
    private final RabbitTemplate rabbitTemplate;
    private final Counter orderCreatedCounter;

    @Value("${app.rabbitmq.exchange:shipment.integration.exchange}")
    private String exchangeName;

    @Value("${app.rabbitmq.routing-key:order.created}")
    private String routingKey;

    public OrderService(RabbitTemplate rabbitTemplate, MeterRegistry meterRegistry) {
        this.rabbitTemplate = rabbitTemplate;
        this.orderCreatedCounter = Counter.builder("order_created_total")
                .description("Total number of orders created")
                .register(meterRegistry);
    }

    public OrderResponse createOrder(OrderRequest request) {
        String orderId = (request.getOrderId() != null && !request.getOrderId().isBlank())
                ? request.getOrderId()
                : "ORD-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();

        String now = Instant.now().toString();
        OrderResponse response = new OrderResponse(orderId, "CREATED", request.getCustomerId(), now);
        orderRepository.put(orderId, response);

        orderCreatedCounter.increment();
        log.info("Order created successfully. orderId={}, customerId={}", orderId, request.getCustomerId());

        // Publish event to RabbitMQ
        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.getCustomerId(),
                request.getDestination() != null ? request.getDestination().getCountry() : "VN",
                request.getDestination() != null ? request.getDestination().getCity() : "Hanoi",
                now
        );

        try {
            rabbitTemplate.convertAndSend(exchangeName, routingKey, event);
            log.info("Published OrderCreatedEvent to RabbitMQ exchange '{}' with routing key '{}'. orderId={}", exchangeName, routingKey, orderId);
        } catch (Exception e) {
            log.warn("Failed to publish OrderCreatedEvent to RabbitMQ (Broker might be offline). orderId={}. Error: {}", orderId, e.getMessage());
        }

        return response;
    }

    public Optional<OrderResponse> getOrder(String orderId) {
        return Optional.ofNullable(orderRepository.get(orderId));
    }
}
