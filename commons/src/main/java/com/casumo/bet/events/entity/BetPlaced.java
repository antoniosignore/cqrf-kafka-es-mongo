package com.casumo.bet.events.entity;


public final class BetPlaced extends AbstractEvent {

    private BetInfo betInfo;

    public BetPlaced() {
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }

    public void setBetInfo(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    @Override
    public String toString() {
        return "BetPlaced{" +
                "betInfo=" + betInfo +
                '}';
    }
}
