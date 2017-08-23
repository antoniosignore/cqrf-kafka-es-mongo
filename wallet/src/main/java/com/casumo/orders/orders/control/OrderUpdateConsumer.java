package com.casumo.orders.orders.control;

import com.casumo.orders.KafkaConfigurer;
import com.casumo.orders.events.control.EventConsumer;
import com.casumo.orders.events.control.OffsetTracker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;
import java.util.UUID;

@Component
public class OrderUpdateConsumer {

    private final ApplicationEventPublisher publisher;
    @Autowired
    KafkaConfigurer kafkaProperties;

    @Autowired
    OffsetTracker offsetTracker;
    private EventConsumer eventConsumer;
    @Autowired
    private TaskExecutor taskExecutor;
    private ApplicationContext context;

    @Autowired
    public OrderUpdateConsumer(ApplicationEventPublisher publisher, ApplicationContext context) {
        this.publisher = publisher;
        this.context = context;
    }

    @PostConstruct
    private void init() {

        Properties properties = new Properties();
        properties.put("group-offset", 1);
        properties.put("bootstrap.servers", "localhost:9092");
        properties.put("orders.topic", "order");
        properties.put("beans.topic", "beans");
        properties.put("barista.topic", "barista");

        properties.put("enable.auto.commit", "false");
        properties.put("auto.offset.reset", "earliest");
        properties.put("key.deserializer", "org.apache.kafka.common.serialization.StringDeserializer");
        properties.put("value.deserializer", "com.casumo.orders.events.control.EventDeserializer");

        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 0);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.casumo.orders.events.control.EventDeSerializer");

        properties.put("group.id", "order-consumer-" + UUID.randomUUID());
        String orders = properties.getProperty("orders.topic");

        eventConsumer = new EventConsumer(properties, publisher::publishEvent, offsetTracker, orders);

        taskExecutor.execute(eventConsumer);
    }

    @PreDestroy
    public void close() {
        eventConsumer.stop();
    }

}
