package com.casumo.bet.boundary;


import com.casumo.bet.configuration.CommonProperties;
import com.casumo.bet.control.EventConsumer;
import com.casumo.bet.control.OffsetTracker;
import com.casumo.bet.events.entity.*;
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
    public void handleBetWalletValidated(BetWalletValidated event) {
        System.out.println("!!! BetEventHandler.handleBetWalletValidated");
        System.out.println("event = " + event);
        betCommandService.acceptBet(event.getId());
    }

    @Async
    @EventListener
    public void handleBetRejectedFundsNotAvailable(BetRejectedFundsNotAvailable event) {
        System.out.println("BetEventHandler.handleBetRejectedFundsNotAvailable");
        System.out.println("event = " + event);
        betCommandService.cancelBet(event.getId(), "No funds available");
    }

    @Async
    @EventListener
    public void handleBmcBetStarted(BmcBetStarted event) {
        System.out.println("BetEventHandler.handleBmcBetStarted");
        System.out.println("event = " + event);
        betCommandService.startBet(event.getBetInfo().getId());
    }

    @Async
    @EventListener
    public void handleBmcBetFinished(BmcBetFinished event) {
        System.out.println("BetEventHandler.handleBmcBetFinished");
        System.out.println("event = " + event);
        betCommandService.finishBet(event.getUuid());
    }

    @Async
    @EventListener
    public void handleBmcBetDelivered(BmcBetDelivered event) {
        System.out.println("BetEventHandler.handleBmcBetDelivered");
        System.out.println("event = " + event);
        betCommandService.deliverOrder(event.getOrderId());
    }

    @PostConstruct
    private void initConsumer() {

        Properties properties = commonProperties.properties();
        properties.put("group.id", "bet-handler");

        eventConsumer = new EventConsumer(properties, ev -> {
            System.out.println("\n EVENT from BMC or WALLET . Firing event -> " + ev.toString());
            publisher.publishEvent(ev);
        }, offsetTracker, "bmc", "wallet");

        this.executor.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

}
