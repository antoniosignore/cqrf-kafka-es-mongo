package com.casumo.bet.events.entity;

import java.util.UUID;

public final class BmcBetDelivered extends AbstractEvent {

    private UUID orderId;

    public BmcBetDelivered() {
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
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
                ", orderId=" + orderId +
                '}';
    }

}
