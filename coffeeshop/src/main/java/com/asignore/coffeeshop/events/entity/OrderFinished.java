package com.asignore.coffeeshop.events.entity;

import java.time.Instant;
import java.util.UUID;

public class OrderFinished extends CoffeeEvent {

    private final UUID orderId;

    public OrderFinished(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderFinished(final UUID orderId, Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
