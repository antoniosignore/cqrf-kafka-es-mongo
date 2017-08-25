package com.casumo.bet.events.entity;

import java.io.Serializable;
import java.util.UUID;

public class BetInfo implements Serializable {

    private UUID id;
    private String username;

    public BetInfo() {
    }

    public BetInfo(final UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
