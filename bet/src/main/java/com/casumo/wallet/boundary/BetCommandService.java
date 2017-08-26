package com.casumo.wallet.boundary;


import com.casumo.wallet.control.BetEventProducer;
import com.casumo.wallet.events.entity.*;
import com.casumo.wallet.control.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BetCommandService {

    final
    BetEventProducer eventProducer;

    final
    BetRepository betRepository;

    @Autowired
    public BetCommandService(BetEventProducer eventProducer, BetRepository betRepository) {
        this.eventProducer = eventProducer;
        this.betRepository = betRepository;
    }

    public void placeBet(final BetInfo betInfo) {
        System.out.println("BetCommandService.placeBet");
        System.out.println("betInfo = " + betInfo);
        eventProducer.publish(new BetPlaced(betInfo));
    }

    void acceptBet(final UUID id) {
        System.out.println("################# :BetCommandService.acceptBet");
        final BetInfo betInfo = betRepository.get(id).getBetInfo();
        eventProducer.publish(new BetAccepted(betInfo));
    }

    void cancelBet(final UUID id, final String reason) {
        System.out.println("BetCommandService.cancelBet");
        eventProducer.publish(new BetCancelled(id, reason));
    }

    void startBet(final UUID id) {
        System.out.println("BetCommandService.startBet");
        final BetInfo betInfo = betRepository.get(id).getBetInfo();
        eventProducer.publish(new BetStarted(betInfo));
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
