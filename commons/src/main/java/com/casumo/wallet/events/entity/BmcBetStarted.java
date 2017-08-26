package com.casumo.wallet.events.entity;

import java.time.Instant;

public class BmcBetStarted extends AbstractEvent {

    private final BetInfo betInfo;

    public BmcBetStarted(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public BmcBetStarted(BetInfo betInfo, Instant instant) {
        super(instant);
        this.betInfo = betInfo;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }

}
