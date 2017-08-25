package com.casumo.bet.events.entity;

import java.time.Instant;
import java.util.UUID;

public class BetWalletValidated extends AbstractEvent {

    private final UUID id;

    public BetWalletValidated(final UUID id) {
        this.id = id;
    }

    public BetWalletValidated(final UUID id, final Instant instant) {
        super(instant);
        this.id = id;
    }


    public UUID getId() {
        return id;
    }

}
