package com.casumo.orders.events.entity;

import java.time.Instant;
import java.util.UUID;

public class CoffeeDelivered extends CoffeeEvent {

    private final UUID orderId;

    public CoffeeDelivered(final UUID orderId) {
        this.orderId = orderId;
    }

    public CoffeeDelivered(final UUID orderId, Instant instant) {
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

        final CoffeeDelivered that = (CoffeeDelivered) o;

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
        return "CoffeeDelivered{" +
                "instant=" + getInstant() +
                ", orderId=" + orderId +
                '}';
    }

}
