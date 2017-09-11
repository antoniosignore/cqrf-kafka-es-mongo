package com.casumo.bet.events.entity;

import java.util.UUID;

public final class BetWalletValidated extends AbstractEvent {

    private UUID id;

    public BetWalletValidated() {
    }

    @Override
    public String toString() {
        return "BetWalletValidated{" +
                "id=" + id +
                '}';
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

}
