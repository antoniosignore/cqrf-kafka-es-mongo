package com.casumo.bet.control;


import com.casumo.bet.boundary.BetController;
import com.casumo.bet.events.entity.*;
import com.casumo.bet.events.entity.Bet;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class BetsRepository {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BetController.class);

    private Map<UUID, Bet> coffeeOrders = new ConcurrentHashMap<>();

    public Bet get(final UUID orderId) {
        return coffeeOrders.get(orderId);
    }

    @EventListener
    public void apply(BetPlaced event) {
        log.debug("\n\n\n\n\n\nORDER POSTED --> event : " + event.toString());
        coffeeOrders.putIfAbsent(event.getBetInfo().getId(), new Bet());
        applyFor(event.getBetInfo().getId(), o -> o.place(event.getBetInfo()));
    }

    @EventListener
    public void apply(BetCancelled event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getOrderId(), Bet::cancel);
    }

    @EventListener
    public void apply(BetAccepted event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getBetInfo().getId(), Bet::accept);
    }

    @EventListener
    public void apply(BetStarted event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getId(), Bet::start);
    }

    @EventListener
    public void apply(BetFinished event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getId(), Bet::finish);
    }

    @EventListener
    public void apply(BetDelivered event) {
        log.debug("--> event : " + event.toString());
        applyFor(event.getOrderId(), Bet::deliver);
    }

    private void applyFor(final UUID orderId, final Consumer<Bet> consumer) {
        final Bet bet = coffeeOrders.get(orderId);
        if (bet != null)
            consumer.accept(bet);
    }

}
