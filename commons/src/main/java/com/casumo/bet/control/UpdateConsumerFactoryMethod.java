package com.casumo.bet.control;


import com.casumo.bet.configuration.CommonProperties;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.UUID;


public abstract class UpdateConsumerFactoryMethod {

    final CommonProperties commonProperties;
    final OffsetTracker offsetTracker;
    private final ApplicationEventPublisher publisher;
    private final TaskExecutor taskExecutor;
    private EventConsumer eventConsumer;

    public UpdateConsumerFactoryMethod(ApplicationEventPublisher publisher, CommonProperties commonProperties, OffsetTracker offsetTracker, TaskExecutor taskExecutor) {
        this.publisher = publisher;
        this.commonProperties = commonProperties;
        this.offsetTracker = offsetTracker;
        this.taskExecutor = taskExecutor;
    }

    public abstract String getTopic();

    @PostConstruct
    private void init() {

        Properties properties = commonProperties.properties();
        properties.put("group.id", getTopic() + "-consumer-" + UUID.randomUUID());
/*
        eventConsumer = new EventConsumer(properties, ev -> {
            System.out.println("\n EVENT from BET. Firing event -> " + ev.toString());
            publisher.publishEvent(ev);
        }, offsetTracker, "bet");
        */

        eventConsumer = new EventConsumer(properties, ev -> {
            System.out.println("\n EVENT from " + getTopic() + ". Firing event -> " + ev.toString());
            publisher.publishEvent(ev);
        }, offsetTracker, getTopic());


        taskExecutor.execute(eventConsumer);
    }

    @PreDestroy
    public void close() {

        eventConsumer.stop();
    }

}
