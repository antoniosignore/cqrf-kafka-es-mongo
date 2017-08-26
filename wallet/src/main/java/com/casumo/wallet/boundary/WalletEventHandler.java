package com.casumo.wallet.boundary;

import com.casumo.wallet.configuration.CommonProperties;
import com.casumo.wallet.control.EventConsumer;
import com.casumo.wallet.control.OffsetTracker;
import com.casumo.wallet.events.entity.BetPlaced;
import com.casumo.wallet.events.entity.BetStarted;
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
public class WalletEventHandler {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WalletEventHandler.class);

    private final ApplicationEventPublisher publisher;

    final CommonProperties commonProperties;

    final OffsetTracker offsetTracker;

    final WalletCommandService walletCommandService;

    private EventConsumer eventConsumer;

    final TaskExecutor executor;

    @Autowired
    public WalletEventHandler(ApplicationEventPublisher publisher, CommonProperties commonProperties, OffsetTracker offsetTracker, WalletCommandService walletCommandService, TaskExecutor executor) {
        this.publisher = publisher;
        this.commonProperties = commonProperties;
        this.offsetTracker = offsetTracker;
        this.walletCommandService = walletCommandService;
        this.executor = executor;
    }

    @Async
    @EventListener
    public void handle(BetPlaced event) {
        walletCommandService.validateFunds(
                event.getBetInfo().getUsername(),
                event.getBetInfo().getId());
    }

    @Async
    @EventListener
    public void handle(BetStarted event) {
        walletCommandService.withdrawnFunds(
                event.getBetInfo().getUsername(),
                event.getBetInfo().getAmount());
    }

    @PostConstruct
    private void initConsumer() {
        Properties properties = commonProperties.properties();
        properties.put("group.id", "wallets-handler");
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
