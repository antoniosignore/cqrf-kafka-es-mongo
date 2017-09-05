package com.casumo.bet.events.entity;


import com.casumo.bet.events.entity.player.BetInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public final class Bet {

    private UUID id;
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
