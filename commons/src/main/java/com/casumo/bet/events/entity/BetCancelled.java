package com.casumo.bet.events.entity;

import java.util.UUID;

public final class BetCancelled extends AbstractEvent {

    private UUID orderId;
    private String reason;

    public BetCancelled() {
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public String getReason() {
        return reason;
    }

    @Override
    public String toString() {
        return "BetCancelled{" +
                "orderId=" + orderId +
                ", reason='" + reason + '\'' +
                '}';
    }
}
