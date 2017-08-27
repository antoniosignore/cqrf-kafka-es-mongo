package com.casumo.bet.events.entity;

public class BmcBetStarted extends AbstractEvent {

    private BetInfo betInfo;

    public BmcBetStarted() {
    }

    public void setBetInfo(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }

    @Override
    public String toString() {
        return "BmcBetStarted{" +
                "betInfo=" + betInfo +
                '}';
    }
}
