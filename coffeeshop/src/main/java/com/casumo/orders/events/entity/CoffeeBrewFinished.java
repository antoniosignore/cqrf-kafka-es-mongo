package com.casumo.orders.events.entity;

import java.time.Instant;
import java.util.UUID;

public class CoffeeBrewFinished extends CoffeeEvent {

    private final UUID orderId;

    public CoffeeBrewFinished(final UUID orderId) {
        this.orderId = orderId;
    }

    public CoffeeBrewFinished(final UUID orderId, Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
