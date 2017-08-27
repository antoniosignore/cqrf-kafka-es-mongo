package com.casumo.bet.control;


import com.casumo.bet.configuration.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.UUID;

@Component
public class BetUpdateConsumer {

    private final ApplicationEventPublisher publisher;
    final CommonProperties commonProperties;
    final OffsetTracker offsetTracker;
    private EventConsumer eventConsumer;
    private final TaskExecutor taskExecutor;

    @Autowired
    public BetUpdateConsumer(ApplicationEventPublisher publisher, CommonProperties commonProperties, OffsetTracker offsetTracker, TaskExecutor taskExecutor) {
        this.publisher = publisher;
        this.commonProperties = commonProperties;
        this.offsetTracker = offsetTracker;
        this.taskExecutor = taskExecutor;
    }

    @PostConstruct
    private void init() {

        Properties properties = commonProperties.properties();
        properties.put("group.id", "bets-consumer-" + UUID.randomUUID());

        eventConsumer = new EventConsumer(properties, ev -> {
            System.out.println("\n EVENT from BET. Firing event -> " + ev.toString());
            publisher.publishEvent(ev);
        }, offsetTracker, "bet");

        taskExecutor.execute(eventConsumer);
    }

    @PreDestroy
    public void close() {

        eventConsumer.stop();
    }

}
