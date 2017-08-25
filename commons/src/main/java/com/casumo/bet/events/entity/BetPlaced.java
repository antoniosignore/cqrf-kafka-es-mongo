package com.casumo.bet.events.entity;

import java.time.Instant;

public class BetPlaced extends AbstractEvent {

    private BetInfo betInfo;

    public BetPlaced() {
    }

    public BetPlaced(final BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public BetPlaced(final BetInfo betInfo, Instant instant) {
        super(instant);
        this.betInfo = betInfo;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }

    public void setBetInfo(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

}
