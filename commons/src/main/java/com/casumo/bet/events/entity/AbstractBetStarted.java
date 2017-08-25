package com.casumo.bet.events.entity;

import java.time.Instant;

public class AbstractBetStarted extends AbstractEvent {

    private final BetInfo betInfo;

    public AbstractBetStarted(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public AbstractBetStarted(BetInfo betInfo, Instant instant) {
        super(instant);
        this.betInfo = betInfo;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }

}
