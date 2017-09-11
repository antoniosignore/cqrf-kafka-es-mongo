package com.casumo.bet.events.entity;

import java.util.UUID;

public final class BetFinished extends AbstractEvent {

    private UUID id;

    public BetFinished() {
    }

    @Override
    public String toString() {
        return "BetFinished{" +
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
