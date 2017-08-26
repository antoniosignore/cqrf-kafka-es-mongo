package com.casumo.wallet.events.entity;

public class BetStarted extends AbstractEvent {

    private BetInfo betInfo;

    public BetStarted() {
    }

    public BetStarted(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }
}
