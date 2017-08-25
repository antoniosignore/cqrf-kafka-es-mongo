package com.casumo.bet.boundary;


import com.casumo.CommonProperties;
import com.casumo.bet.events.control.EventConsumer;
import com.casumo.bet.events.control.OffsetTracker;
import com.casumo.bet.events.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;


@Component
public class BetEventHandler {

    private static final Logger log = LoggerFactory.getLogger(BetEventHandler.class);

    private final ApplicationEventPublisher publisher;

    @Autowired
    CommonProperties commonProperties;

    @Autowired
    OffsetTracker offsetTracker;

    @Autowired
    BetCommandService betCommandService;

    private EventConsumer eventConsumer;
    
    @Autowired
    TaskExecutor executor;
    
    @Autowired
    public BetEventHandler(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @EventListener
    public void handle(BetWalletValidated event) {
        log.debug("######## VALIDATED = " + event);
        log.debug("event = " + event.getClass().toString());
        betCommandService.acceptBet(event.getId());
    }

    @EventListener
    public void handle(BetRejectedFundsNotAvailable event) {
        log.debug("######## FAILED NO MONEY AVAILABLE = " + event);
        log.debug("event = " + event.getClass().toString());
        betCommandService.cancelBet(event.getId(), "No beans of the origin were available");
    }

    @EventListener
    public void handle(AbstractBetStarted event) {
        log.debug("######## BREW STARTED = " + event);
        log.debug("event = " + event.getClass().toString());
        betCommandService.startBet(event.getBetInfo().getId());
    }

    @EventListener
    public void handle(AbstractBetFinished event) {
        log.debug("######## BREW FINISHED = " + event);
        log.debug("event = " + event.getClass().toString());
        betCommandService.finishBet(event.getOrderId());
    }

    @EventListener
    public void handle(AbstractDelivered event) {
        log.debug("event = " + event.getClass().toString());
        betCommandService.deliverOrder(event.getOrderId());
    }

    @PostConstruct
    private void initConsumer() {
        Properties properties = commonProperties.properties();
        properties.put("group.id", "bet-handler");
        eventConsumer = new EventConsumer(properties, 
                publisher::publishEvent, offsetTracker, commonProperties.topicBmc, commonProperties.topicWallet);

        this.executor.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

}
