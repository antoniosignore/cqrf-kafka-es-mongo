package com.casumo.wallet.events.entity;

import java.time.Instant;

public class MoneyDeposit extends AbstractEvent {

    private final String username;
    private final Double amount;

    public MoneyDeposit(final String username, final Double amount) {
        this.username = username;
        this.amount = amount;
    }

    public MoneyDeposit(final String username, final Double amount, final Instant instant) {
        super(instant);
        this.username = username;
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MoneyDeposit that = (MoneyDeposit) o;

        if (!username.equals(that.username)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }
}
