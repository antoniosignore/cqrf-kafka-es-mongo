package com.casumo.bmc.boundary;

import com.casumo.wallet.events.entity.BetInfo;
import com.casumo.wallet.events.entity.BetStarted;
import com.casumo.wallet.events.entity.BmcBetDelivered;
import com.casumo.wallet.events.entity.BmcBetFinished;
import com.casumo.bmc.control.BmcRepository;

import com.casumo.bmc.control.BmcEventProducer;
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

    void makeCoffee(final BetInfo orderInfo) {
        bmcEventProducer.publish(new BetStarted(orderInfo));
    }

    void checkBets() {
        final Collection<UUID> unfinishedBets = repo.getUnfinishedBets();
        log.info("checking " + unfinishedBets.size() + " unfinished bets");

        unfinishedBets.forEach(i -> {
            if (new Random().nextBoolean())
                bmcEventProducer.publish(new BmcBetFinished(i));
        });
    }

    void checkBetDelivery() {

        final Collection<UUID> undeliveredBets = repo.getUndeliveredBets();
        log.info("checking " + undeliveredBets.size() + " un-served bets");

        undeliveredBets.forEach(i -> {
            if (new Random().nextBoolean())
                bmcEventProducer.publish(new BmcBetDelivered(i));
        });

    }

}
