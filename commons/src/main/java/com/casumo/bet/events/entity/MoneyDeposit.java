package com.casumo.bet.events.entity;

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

}
