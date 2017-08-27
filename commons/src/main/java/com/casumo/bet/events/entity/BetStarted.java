package com.casumo.bet.events.entity;

public class BetStarted extends AbstractEvent {

    private BetInfo betInfo;

    public BetStarted() {
    }

    public void setBetInfo(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }

    @Override
    public String toString() {
        return "BetStarted{" +
                "betInfo=" + betInfo +
                '}';
    }
}
