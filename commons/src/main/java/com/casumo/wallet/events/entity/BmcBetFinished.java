package com.casumo.wallet.events.entity;

import java.time.Instant;
import java.util.UUID;

public class BmcBetFinished extends AbstractEvent {

    private final UUID orderId;

    public BmcBetFinished(final UUID orderId) {
        this.orderId = orderId;
    }

    public BmcBetFinished(final UUID orderId, Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

}
