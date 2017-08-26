package com.casumo.wallet.events.entity;

import java.time.Instant;
import java.util.UUID;

public class BmcBetDelivered extends AbstractEvent {

    private final UUID orderId;

    public BmcBetDelivered(final UUID orderId) {
        this.orderId = orderId;
    }

    public BmcBetDelivered(final UUID orderId, Instant instant) {
        super(instant);
        this.orderId = orderId;
    }

    public UUID getOrderId() {
        return orderId;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        final BmcBetDelivered that = (BmcBetDelivered) o;

        return orderId != null ? orderId.equals(that.orderId) : that.orderId == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (orderId != null ? orderId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "BmcBetDelivered{" +
                "instant=" + getInstant() +
                ", orderId=" + orderId +
                '}';
    }

}
