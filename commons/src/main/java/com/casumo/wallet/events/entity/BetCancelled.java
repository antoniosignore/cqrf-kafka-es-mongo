package com.casumo.wallet.events.entity;

import java.time.Instant;
import java.util.UUID;

public class BetCancelled extends AbstractEvent {

    private final UUID orderId;
    private final String reason;

    public BetCancelled(final UUID orderId, final String reason) {
        this.orderId = orderId;
        this.reason = reason;
    }

    public BetCancelled(final UUID orderId, final String reason, Instant instant) {
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
