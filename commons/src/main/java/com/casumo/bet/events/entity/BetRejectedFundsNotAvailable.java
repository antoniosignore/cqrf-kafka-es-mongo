package com.casumo.bet.events.entity;

import java.util.UUID;

public class BetRejectedFundsNotAvailable extends AbstractEvent {

    private UUID id;

    public BetRejectedFundsNotAvailable() {
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

    public void setId(UUID id) {
        this.id = id;
    }

}
