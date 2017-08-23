package com.asignore.coffeeshop.events.entity;

import java.time.Instant;
import java.util.UUID;

public class OrderBeansValidated extends CoffeeEvent {

    private final UUID orderId;

    public OrderBeansValidated(final UUID orderId) {
        this.orderId = orderId;
    }

    public OrderBeansValidated(final UUID orderId, final Instant instant) {
        super(instant);
        this.orderId = orderId;
    }


    public UUID getOrderId() {
        return orderId;
    }

}
