package com.casumo.orders.events.entity;

import java.time.Instant;
import java.util.UUID;

public class OrderCancelled extends CoffeeEvent {

    private final UUID orderId;
    private final String reason;

    public OrderCancelled(final UUID orderId, final String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }

    public OrderCancelled(final UUID orderId, final String reason, Instant instant) {
        super(instant);
        this.orderId = orderId;
        this.reason = reason;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

}
