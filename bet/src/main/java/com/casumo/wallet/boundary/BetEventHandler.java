package com.casumo.wallet.boundary;


import com.casumo.wallet.configuration.CommonProperties;
import com.casumo.wallet.control.EventConsumer;
import com.casumo.wallet.control.OffsetTracker;
import com.casumo.wallet.events.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;


@Component
public class BetEventHandler {

    private static final Logger log = LoggerFactory.getLogger(BetEventHandler.class);

    private final ApplicationEventPublisher publisher;
    final CommonProperties commonProperties;
    final OffsetTracker offsetTracker;
    final BetCommandService betCommandService;
    private EventConsumer eventConsumer;
    final TaskExecutor executor;
    
    @Autowired
    public BetEventHandler(ApplicationEventPublisher publisher, CommonProperties commonProperties, OffsetTracker offsetTracker, BetCommandService betCommandService, TaskExecutor executor) {
        this.publisher = publisher;
        this.commonProperties = commonProperties;
        this.offsetTracker = offsetTracker;
        this.betCommandService = betCommandService;
        this.executor = executor;
    }

    @Async
    @EventListener
    public void handle(BetWalletValidated event) {
        log.debug("######## VALIDATED = " + event);
        log.debug("event = " + event.getClass().toString());
        betCommandService.acceptBet(event.getId());
    }

    @Async
    @EventListener
    public void handle(BetRejectedFundsNotAvailable event) {
        log.debug("######## FAILED NO MONEY AVAILABLE = " + event);
        log.debug("event = " + event.getClass().toString());
        betCommandService.cancelBet(event.getId(), "No beans of the origin were available");
    }

    @Async
    @EventListener
    public void handle(BmcBetStarted event) {
        log.debug("######## BREW STARTED = " + event);
        log.debug("event = " + event.getClass().toString());
        betCommandService.startBet(event.getBetInfo().getId());
    }

    @Async
    @EventListener
    public void handle(BmcBetFinished event) {
        log.debug("######## BREW FINISHED = " + event);
        log.debug("event = " + event.getClass().toString());
        betCommandService.finishBet(event.getOrderId());
    }

    @Async
    @EventListener
    public void handle(BmcBetDelivered event) {
        log.debug("event = " + event.getClass().toString());
        betCommandService.deliverOrder(event.getOrderId());
    }

    @PostConstruct
    private void initConsumer() {
        Properties properties = commonProperties.properties();
        properties.put("group.id", "wallet-handler");
        eventConsumer = new EventConsumer(properties,
                publisher::publishEvent, offsetTracker,
                commonProperties.topicBmc,
                commonProperties.topicWallet);

        this.executor.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

}
