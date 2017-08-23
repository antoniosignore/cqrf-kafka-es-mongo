package com.casumo.orders.events.entity;

import java.time.Instant;
import java.util.UUID;

public class OrderDelivered extends CoffeeEvent {

    private final UUID orderId;

    public OrderDelivered(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderDelivered(final UUID orderId, Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
