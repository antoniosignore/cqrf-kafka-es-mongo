package com.casumo.bet.events.entity;

import java.util.UUID;

public final class BmcBetFinished extends AbstractEvent {

    private UUID uuid;

    public BmcBetFinished() {
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    @Override
    public String toString() {
        return "BmcBetFinished{" +
                "uuid=" + uuid +
                '}';
    }
}
