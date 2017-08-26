package com.casumo.wallet.events.entity;

import java.time.Instant;

public class BetAccepted extends AbstractEvent {

    private final BetInfo betInfo;

    public BetAccepted(final BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public BetAccepted(final BetInfo betInfo, Instant instant) {
        super(instant);
        this.betInfo = betInfo;
    }


    public BetInfo getBetInfo() {
        return betInfo;
    }

}
