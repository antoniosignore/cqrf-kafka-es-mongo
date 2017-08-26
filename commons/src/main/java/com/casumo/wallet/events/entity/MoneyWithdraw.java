package com.casumo.wallet.events.entity;

import java.time.Instant;

public class MoneyWithdraw extends AbstractEvent {

    private final String username;
    private final Double amount;

    public MoneyWithdraw(final String username, Double amount) {
        this.username = username;
        this.amount = amount;
    }

    public MoneyWithdraw(final String username, final Instant instant, Double amount) {
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
