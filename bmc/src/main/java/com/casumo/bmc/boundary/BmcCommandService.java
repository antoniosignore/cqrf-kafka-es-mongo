package com.casumo.bmc.boundary;

import com.casumo.bet.events.entity.BetInfo;
import com.casumo.bet.events.entity.BetStarted;
import com.casumo.bet.events.entity.BmcBetDelivered;
import com.casumo.bet.events.entity.BmcBetFinished;
import com.casumo.bmc.control.BmcEventProducer;
import com.casumo.bmc.repository.BmcRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.Random;
import java.util.UUID;

@Component
public class BmcCommandService {

    private static final Logger log = LoggerFactory.getLogger(BmcCommandService.class);
    final BmcEventProducer bmcEventProducer;
    final BmcRepository repo;

    @Autowired
    public BmcCommandService(BmcEventProducer bmcEventProducer, BmcRepository repo) {
        this.bmcEventProducer = bmcEventProducer;
        this.repo = repo;
    }

    void makeCoffee(final BetInfo betInfo) {
        BetStarted event = new BetStarted();
        event.setBetInfo(betInfo);
        bmcEventProducer.publish(event);
    }

    void checkBets() {
        final Collection<UUID> unfinishedBets = repo.getUnfinishedBets();
        // log.info("checking " + unfinishedBets.size() + " unfinished bets");

        unfinishedBets.forEach(i -> {
            if (new Random().nextBoolean()) {
                BmcBetFinished event = new BmcBetFinished();
                event.setUuid(i);
                bmcEventProducer.publish(event);
            }
        });
    }

    void checkBetDelivery() {

        final Collection<UUID> undeliveredBets = repo.getUndeliveredBets();
        //log.info("checking " + undeliveredBets.size() + " un-served bets");

        undeliveredBets.forEach(i -> {
            if (new Random().nextBoolean()) {
                BmcBetDelivered event = new BmcBetDelivered();
                event.setOrderId(i);
                bmcEventProducer.publish(event);
            }
        });

    }

}
