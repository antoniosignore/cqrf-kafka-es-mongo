package com.casumo.wallet.events.entity;

import java.time.Instant;
import java.util.UUID;

public class BetFinished extends AbstractEvent {

    private UUID id;

    public BetFinished() {
    }

    public BetFinished(final UUID id) {
        this.id = id;
    }

    public BetFinished(final UUID id, Instant instant) {
        super(instant);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
