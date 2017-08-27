package com.casumo.bet.events.entity;

public final class BetAccepted extends AbstractEvent {

    private BetInfo betInfo;

    public BetAccepted() {
    }

    public void setBetInfo(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }

    @Override
    public String toString() {
        return "BetAccepted{" +
                "betInfo=" + betInfo +
                '}';
    }
}
