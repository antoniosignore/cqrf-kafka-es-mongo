package com.asignore.coffeeshop.events.entity;

import java.time.Instant;
import java.util.UUID;

public class OrderStarted extends CoffeeEvent {

    private final UUID orderId;

    public OrderStarted(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderStarted(final UUID orderId, final Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
