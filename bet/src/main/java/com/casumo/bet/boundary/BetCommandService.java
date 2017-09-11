package com.casumo.bet.boundary;


import com.casumo.bet.control.BetEventProducer;
import com.casumo.bet.events.entity.*;
import com.casumo.bet.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
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
        BetPlaced event = new BetPlaced();
        event.setBetInfo(betInfo);
        eventProducer.publish(event);
    }

    void acceptBet(final UUID id) {
        System.out.println("################# :BetCommandService.acceptBet");
        System.out.println("id = " + id);
        Bet bet = betRepository.get(id);
        final BetInfo betInfo = bet.getBetInfo();
        BetAccepted event = new BetAccepted();
        event.setBetInfo(betInfo);
        eventProducer.publish(event);
    }

    void cancelBet(final UUID id, final String reason) {
        System.out.println("BetCommandService.cancelBet");
        BetCancelled event = new BetCancelled();
        event.setOrderId(id);
        event.setReason(reason);
        eventProducer.publish(event);
    }

    void startBet(final UUID id) {
        System.out.println("BetCommandService.startBet");
        final BetInfo betInfo = betRepository.get(id).getBetInfo();
        BetStarted event = new BetStarted();
        event.setBetInfo(betInfo);
        eventProducer.publish(event);
    }

    void finishBet(final UUID id) {
        System.out.println("BetCommandService.finishBet");
        BetFinished event = new BetFinished();
        event.setId(id);
        eventProducer.publish(event);
    }

    void deliverOrder(final UUID id) {
        System.out.println("BetCommandService.deliverOrder");
        BetDelivered event = new BetDelivered();
        event.setOrderId(id);
        eventProducer.publish(event);
    }

}
