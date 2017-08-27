package com.casumo.wallet.control;

import com.casumo.wallet.configuration.CommonProperties;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.UUID;


@Component
public class WalletUpdateConsumer {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WalletUpdateConsumer.class);

    final ApplicationEventPublisher publisher;

    final CommonProperties commonProperties;

    final OffsetTracker offsetTracker;
    final TaskExecutor taskExecutor;
    EventConsumer eventConsumer;

    @Autowired
    public WalletUpdateConsumer(ApplicationEventPublisher publisher, CommonProperties commonProperties, OffsetTracker offsetTracker, TaskExecutor taskExecutor) {
        this.publisher = publisher;
        this.commonProperties = commonProperties;
        this.offsetTracker = offsetTracker;
        this.taskExecutor = taskExecutor;
    }

    @PostConstruct
    private void init() {

        Properties properties = commonProperties.properties();
        properties.put("group.id", "wallets-consumer-" + UUID.randomUUID());

        eventConsumer = new EventConsumer(properties, ev -> {
            System.out.println("event received from WALLET. Firing =>  " + ev);
            publisher.publishEvent(ev);
        }, offsetTracker, "wallet");

        taskExecutor.execute(eventConsumer);

    }

    @PreDestroy
    public void close() {
        eventConsumer.stop();
    }

}
