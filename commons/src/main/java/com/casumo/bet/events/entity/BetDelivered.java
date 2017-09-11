package com.casumo.bet.events.entity;

import java.util.UUID;

public final class BetDelivered extends AbstractEvent {

    private UUID orderId;

    public BetDelivered() {
    }

    @Override
    public String toString() {
        return "BetDelivered{" +
                "orderId=" + orderId +
                '}';
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

}
