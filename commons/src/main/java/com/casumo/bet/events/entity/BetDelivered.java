package com.casumo.bet.events.entity;

import java.time.Instant;
import java.util.UUID;

public class BetDelivered extends AbstractEvent {

    private final UUID orderId;

    public BetDelivered(final UUID orderId) {
        this.orderId = orderId;
    }

    public BetDelivered(final UUID orderId, Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
