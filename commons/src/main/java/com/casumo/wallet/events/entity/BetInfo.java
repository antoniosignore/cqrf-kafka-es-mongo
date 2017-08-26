package com.casumo.wallet.events.entity;

import java.io.Serializable;
import java.util.UUID;

public class BetInfo implements Serializable {

    private UUID id;
    private String username;
    private Double amount;

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

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
