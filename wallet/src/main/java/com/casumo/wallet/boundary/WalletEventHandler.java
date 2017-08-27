package com.casumo.wallet.boundary;

import com.casumo.bet.events.entity.BetPlaced;
import com.casumo.bet.events.entity.BetStarted;
import com.casumo.wallet.configuration.CommonProperties;
import com.casumo.wallet.control.EventConsumer;
import com.casumo.wallet.control.OffsetTracker;
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

    final CommonProperties conf;

    final OffsetTracker offsetTracker;

    final WalletCommandService walletCommandService;

    private EventConsumer eventConsumer;

    final TaskExecutor executor;

    @Autowired
    public WalletEventHandler(ApplicationEventPublisher publisher, CommonProperties conf, OffsetTracker offsetTracker, WalletCommandService walletCommandService, TaskExecutor executor) {
        this.publisher = publisher;
        this.conf = conf;
        this.offsetTracker = offsetTracker;
        this.walletCommandService = walletCommandService;
        this.executor = executor;
    }

    @Async
    @EventListener
    public void handleBetPlaced(BetPlaced event) {

        System.out.println("WalletEventHandler.handleBetPlaced");

        walletCommandService.validateFunds(
                event.getBetInfo().getUsername(),
                event.getBetInfo().getId());
    }

    @Async
    @EventListener
    public void handleBetStarted(BetStarted event) {

        System.out.println("WalletEventHandler.handleBetStarted");

        walletCommandService.withdrawnFunds(
                event.getBetInfo().getUsername(),
                event.getBetInfo().getStake());
    }

    @PostConstruct
    private void initConsumer() {

        Properties properties = conf.properties();
        properties.put("group.id", "wallets-handler");

        eventConsumer = new EventConsumer(properties, ev -> {
            System.out.println("event received from BET or BMC. Firing =>  " + ev);
            publisher.publishEvent(ev);
        }, offsetTracker, "bmc", "bet");

        this.executor.execute(eventConsumer);
    }

    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

}
