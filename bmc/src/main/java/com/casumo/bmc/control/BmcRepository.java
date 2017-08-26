package com.casumo.bmc.control;

import com.casumo.wallet.events.entity.BetDelivered;
import com.casumo.wallet.events.entity.BetFinished;
import com.casumo.wallet.events.entity.BetStarted;
import org.springframework.context.event.EventListener;
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

    @EventListener
    public void apply(BetStarted event) {
        unfinishedBets.add(event.getBetInfo().getId());
    }

    @EventListener
    public void apply(BetFinished event) {
        final Iterator<UUID> iterator = unfinishedBets.iterator();
        while (iterator.hasNext()) {
            final UUID orderId = iterator.next();
            if (orderId.equals(event.getId())) {
                iterator.remove();
                undeliveredBets.add(orderId);
            }
        }
    }

    @EventListener
    public void apply(BetDelivered event) {
        undeliveredBets.removeIf(i -> i.equals(event.getOrderId()));
    }

}
