package com.casumo.bet.boundary;

import com.casumo.bet.control.BetsEventProducer;
import com.casumo.bet.events.entity.*;
import com.casumo.bet.control.BetsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BetCommandService {

    final
    BetsEventProducer eventProducer;

    final
    BetsRepository betsRepository;

    @Autowired
    public BetCommandService(BetsEventProducer eventProducer, BetsRepository betsRepository) {
        this.eventProducer = eventProducer;
        this.betsRepository = betsRepository;
    }

    public void placeBet(final BetInfo betInfo) {
        System.out.println("BetCommandService.placeBet");
        System.out.println("betInfo = " + betInfo);
        eventProducer.publish(new BetPlaced(betInfo));
    }

    void acceptBet(final UUID id) {
        System.out.println("################# :BetCommandService.acceptBet");
        final BetInfo betInfo = betsRepository.get(id).getBetInfo();
        eventProducer.publish(new BetAccepted(betInfo));
    }

    void cancelBet(final UUID id, final String reason) {
        System.out.println("BetCommandService.cancelBet");
        eventProducer.publish(new BetCancelled(id, reason));
    }

    void startBet(final UUID id) {
        System.out.println("BetCommandService.startBet");
        eventProducer.publish(new BetStarted(id));
    }

    void finishBet(final UUID id) {
        System.out.println("BetCommandService.finishBet");
        eventProducer.publish(new BetFinished(id));
    }

    void deliverOrder(final UUID id) {
        System.out.println("BetCommandService.deliverOrder");
        eventProducer.publish(new BetDelivered(id));
    }

}
