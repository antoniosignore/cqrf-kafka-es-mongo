package com.casumo.orders.events.entity;

import java.time.Instant;
import java.util.UUID;

public class OrderFailedBeansNotAvailable extends CoffeeEvent {

    private final UUID orderId;

    public OrderFailedBeansNotAvailable(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderFailedBeansNotAvailable(final UUID orderId, final Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
