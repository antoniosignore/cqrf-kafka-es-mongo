package com.casumo.wallet.events.entity;


public class BetPlaced extends AbstractEvent {

    private BetInfo betInfo;

    public BetPlaced() {
    }

    public BetPlaced(final BetInfo betInfo) {
        this.betInfo = betInfo;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }

    public void setBetInfo(BetInfo betInfo) {
        this.betInfo = betInfo;
    }

}
