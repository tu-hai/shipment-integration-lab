package com.shipmentintegration.integration.consumer;

import com.shipmentintegration.integration.dto.ShipmentRequest;
import com.shipmentintegration.integration.event.OrderCreatedEvent;
import com.shipmentintegration.integration.service.ShipmentOrchestrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class OrderCreatedConsumer {

    private static final Logger log = LoggerFactory.getLogger(OrderCreatedConsumer.class);
    private final ShipmentOrchestrationService orchestrationService;

    public OrderCreatedConsumer(ShipmentOrchestrationService orchestrationService) {
        this.orchestrationService = orchestrationService;
    }

    @RabbitListener(queues = "${app.rabbitmq.queue:shipment.integration.order.created.q}")
    public void consumeOrderCreated(OrderCreatedEvent event) {
        log.info("Received OrderCreatedEvent from RabbitMQ: orderId={}, customerId={}, destination={}",
                event.getOrderId(), event.getCustomerId(), event.getDestinationCity());

        ShipmentRequest request = new ShipmentRequest(event.getOrderId(), "STANDARD_EXPRESS");
        orchestrationService.processShipment(request);
    }
}
