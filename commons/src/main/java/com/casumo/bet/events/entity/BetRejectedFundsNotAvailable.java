package com.casumo.bet.events.entity;

import java.util.UUID;

public class BetRejectedFundsNotAvailable extends AbstractEvent {

    private UUID id;

    public BetRejectedFundsNotAvailable() {
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BetRejectedFundsNotAvailable{" +
                "id=" + id +
                '}';
    }

    public UUID getId() {
        return id;
    }

}
