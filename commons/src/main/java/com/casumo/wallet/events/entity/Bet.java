package com.casumo.wallet.events.entity;


public class Bet {

    private BetStatus state;
    private BetInfo betInfo;

    public void place(final BetInfo betInfo) {
        System.out.println("###########################--> PLACED");
        state = BetStatus.PLACED;
        this.betInfo = betInfo;
    }

    public void accept() {
        System.out.println("###########################--> ACCEPTED");
        state = BetStatus.ACCEPTED;
    }

    public void cancel() {
        System.out.println("###########################--> CANCELLED");
        state = BetStatus.CANCELLED;
    }

    public void start() {
        System.out.println("###########################--> STARTED");
        state = BetStatus.STARTED;
    }

    public void finish() {
        state = BetStatus.FINISHED;
    }

    public void deliver() {
        System.out.println("###########################--> DELIVERED");
        state = BetStatus.DELIVERED;
    }

    public BetStatus getState() {
        return state;
    }

    public BetInfo getBetInfo() {
        return betInfo;
    }


}
