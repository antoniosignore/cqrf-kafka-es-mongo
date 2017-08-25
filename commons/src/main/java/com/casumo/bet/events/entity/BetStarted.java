package com.casumo.bet.events.entity;

import java.time.Instant;
import java.util.UUID;

public class BetStarted extends AbstractEvent {

    private final UUID id;

    public BetStarted(final UUID id) {
        this.id = id;
    }

    public BetStarted(final UUID id, final Instant instant) {
        super(instant);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
