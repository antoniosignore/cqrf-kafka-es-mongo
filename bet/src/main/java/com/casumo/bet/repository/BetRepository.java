package com.casumo.bet.repository;


import com.casumo.bet.boundary.BetRestController;
import com.casumo.bet.events.entity.*;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class BetRepository {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BetRestController.class);

    private Map<UUID, Bet> acceptedBets = new ConcurrentHashMap<>();

    public Bet get(final UUID orderId) {
        return acceptedBets.get(orderId);
    }

    public Map<UUID, Bet> getAcceptedBets() {
        return acceptedBets;
    }

    @Async
    @EventListener
    public void apply(BetPlaced event) {
        log.debug("\n\n\n\n\n\nBET POSTED --> event : " + event.toString());
        acceptedBets.putIfAbsent(event.getBetInfo().getId(), new Bet());
        applyFor(event.getBetInfo().getId(), o -> o.place(event.getBetInfo()));
    }

    @Async
    @EventListener
    public void apply(BetCancelled event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getOrderId(), Bet::cancel);
    }

    @Async
    @EventListener
    public void apply(BetAccepted event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getBetInfo().getId(), Bet::accept);
    }

    @Async
    @EventListener
    public void apply(BetStarted event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getBetInfo().getId(), Bet::start);
    }

    @Async
    @EventListener
    public void apply(BetFinished event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getId(), Bet::finish);
    }

    @Async
    @EventListener
    public void apply(BetDelivered event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getOrderId(), Bet::deliver);
    }

    private void applyFor(final UUID orderId, final Consumer<Bet> consumer) {
        final Bet bet = acceptedBets.get(orderId);
        if (bet != null)
            consumer.accept(bet);
    }

}
