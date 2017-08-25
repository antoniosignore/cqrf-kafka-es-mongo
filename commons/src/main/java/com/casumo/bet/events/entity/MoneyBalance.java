package com.casumo.bet.events.entity;

import java.time.Instant;

public class MoneyBalance extends AbstractEvent {

    private final String username;

    public MoneyBalance(final String username) {
        this.username = username;
    }

    public MoneyBalance(final String username, final Instant instant) {
        super(instant);
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

}
