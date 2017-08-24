package com.casumo.bet.boundary;

import com.casumo.CommonProperties;
import com.casumo.bet.control.WalletStorage;
import com.casumo.bet.events.control.EventConsumer;
import com.casumo.bet.events.entity.BetPlaced;
import com.casumo.bet.events.entity.BetStarted;
import com.sebastian_daschner.scalable_coffee_shop.events.control.EventConsumer;
import com.sebastian_daschner.scalable_coffee_shop.events.control.OffsetTracker;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeBrewStarted;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.CoffeeEvent;
import com.sebastian_daschner.scalable_coffee_shop.events.entity.OrderPlaced;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.concurrent.ManagedExecutorService;
import javax.enterprise.event.Event;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import java.util.Properties;
import java.util.logging.Logger;

@Component
public class WalletEventHandler {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WalletEventHandler.class);

    private final ApplicationEventPublisher publisher;

    @Autowired
    CommonProperties commonProperties;

    @Autowired
    OffsetTracker offsetTracker;

    @Autowired
    WalletCommandService walletCommandService;

    private EventConsumer eventConsumer;

    @Autowired
    TaskExecutor executor;

    public WalletEventHandler(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @EventListener
    public void handle(BetPlaced event) {
        walletCommandService.validateBeans(
                event.getBetInfo().getUsername(),
                event.getBetInfo().getId());
    }

    @EventListener
    public void handle(BetStarted event) {
        walletCommandService.fetchBeans(event.getId(getOrderInfo().getBeanOrigin());
    }

    @PostConstruct
    private void initConsumer() {

        Properties properties = commonProperties.properties();
        properties.put("group.id", "wallets-handler");

        eventConsumer = new EventConsumer(properties,
                publisher::publishEvent, offsetTracker, commonProperties.topicBmc, commonProperties.topicWallet);

        this.executor.execute(eventConsumer);

        eventConsumer = new EventConsumer(kafkaProperties, ev -> {
            log.info("firing = " + ev);
            events.fire(ev);
        }, offsetTracker, commonProperties.topicBet, commonProperties.topicBmc);

        mes.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

}
