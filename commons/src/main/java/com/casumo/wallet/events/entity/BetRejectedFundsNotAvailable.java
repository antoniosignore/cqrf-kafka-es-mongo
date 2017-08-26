package com.casumo.wallet.events.entity;

import java.time.Instant;
import java.util.UUID;

public class BetRejectedFundsNotAvailable extends AbstractEvent {

    private final UUID id;

    public BetRejectedFundsNotAvailable(final UUID id) {
        this.id = id;
    }

    public BetRejectedFundsNotAvailable(final UUID id, final Instant instant) {
        super(instant);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

}
