package com.casumo.bmc.repository;

import com.casumo.bet.events.entity.BetDelivered;
import com.casumo.bet.events.entity.BetFinished;
import com.casumo.bet.events.entity.BetStarted;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

import static java.util.Collections.unmodifiableCollection;

@Component
public class BmcRepository {

    private final Set<UUID> unfinishedBets = new ConcurrentSkipListSet<>();
    private final Set<UUID> undeliveredBets = new ConcurrentSkipListSet<>();

    public Collection<UUID> getUnfinishedBets() {
        return unmodifiableCollection(unfinishedBets);
    }

    public Collection<UUID> getUndeliveredBets() {
        return unmodifiableCollection(undeliveredBets);
    }

    @Async
    @EventListener
    public void apply(BetStarted event) {
        unfinishedBets.add(event.getBetInfo().getId());
    }

    @Async
    @EventListener
    public void apply(BetFinished event) {

        System.out.println("BET FINISHED BmcRepository.apply");

        final Iterator<UUID> iterator = unfinishedBets.iterator();
        while (iterator.hasNext()) {
            final UUID orderId = iterator.next();
            if (orderId.equals(event.getId())) {
                iterator.remove();
                undeliveredBets.add(orderId);
            }
        }
    }

    @Async
    @EventListener
    public void apply(BetDelivered event) {
        undeliveredBets.removeIf(i -> i.equals(event.getOrderId()));
    }

}
