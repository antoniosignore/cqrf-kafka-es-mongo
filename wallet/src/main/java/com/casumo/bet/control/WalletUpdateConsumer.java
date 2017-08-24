package com.casumo.bet.control;

import com.casumo.CommonProperties;
import com.casumo.bet.events.control.EventConsumer;

import com.casumo.bet.events.control.OffsetTracker;
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

    private final ApplicationEventPublisher publisher;

    @Autowired
    CommonProperties commonProperties;

    @Autowired
    OffsetTracker offsetTracker;

    private EventConsumer eventConsumer;

    @Autowired
    private TaskExecutor taskExecutor;

    public WalletUpdateConsumer(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @PostConstruct
    private void init() {

        Properties properties = commonProperties.properties();
        properties.put("group.id", "wallets-consumer-" + UUID.randomUUID());

        eventConsumer = new EventConsumer(
                properties, publisher::publishEvent, offsetTracker, commonProperties.topicWallet);
        taskExecutor.execute(eventConsumer);

    }

    @PreDestroy
    public void close() {
        eventConsumer.stop();
    }

}
