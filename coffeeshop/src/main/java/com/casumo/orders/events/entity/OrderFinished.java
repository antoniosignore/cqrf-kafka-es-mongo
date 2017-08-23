package com.casumo.orders.events.entity;

import java.time.Instant;
import java.util.UUID;

public class OrderFinished extends CoffeeEvent {

    private UUID orderId;

    public OrderFinished() {
    }

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
