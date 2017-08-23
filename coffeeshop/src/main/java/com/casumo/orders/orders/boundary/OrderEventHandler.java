package com.casumo.orders.orders.boundary;

import com.casumo.orders.KafkaConfigurer;
import com.casumo.orders.events.control.EventConsumer;
import com.casumo.orders.events.control.OffsetTracker;
import com.casumo.orders.events.entity.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;


@Component
public class OrderEventHandler {

    private static final Logger log = LoggerFactory.getLogger(OrderEventHandler.class);
    private final ApplicationEventPublisher publisher;
    @Autowired
    KafkaConfigurer kafkaProperties;
    @Autowired
    OffsetTracker offsetTracker;
    @Autowired
    OrderCommandService orderService;
    private EventConsumer eventConsumer;
    private ApplicationContext context;
    @Autowired
    private TaskExecutor taskExecutor;

    @Autowired
    public OrderEventHandler(ApplicationEventPublisher publisher, ApplicationContext context) {
        this.publisher = publisher;
        this.context = context;
    }

    @EventListener
    public void handle(OrderBeansValidated event) {

        System.out.println("######## VALIDATED = " + event);
        System.out.println("event = " + event.getClass().toString());

        orderService.acceptOrder(event.getOrderId());
    }

    @EventListener
    public void handle(OrderFailedBeansNotAvailable event) {

        System.out.println("######## FAILED NO MONEY AVAILABLE = " + event);
        System.out.println("event = " + event.getClass().toString());

        orderService.cancelOrder(event.getOrderId(), "No beans of the origin were available");
    }

    @EventListener
    public void handle(CoffeeBrewStarted event) {

        System.out.println("######## BREW STARTED = " + event);

        System.out.println("event = " + event.getClass().toString());
        orderService.startOrder(event.getOrderInfo().getOrderId());
    }

    @EventListener
    public void handle(CoffeeBrewFinished event) {
        System.out.println("######## BREW FINISHED = " + event);
        System.out.println("event = " + event.getClass().toString());
        orderService.finishOrder(event.getOrderId());
    }

    @EventListener
    public void handle(CoffeeDelivered event) {

        System.out.println("event = " + event.getClass().toString());

        orderService.deliverOrder(event.getOrderId());
    }

    @PostConstruct
    private void initConsumer() {

        System.out.println("\n\n\n\n##################################### POST CONSTRUCT");

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

        properties.put("group.id", "order-handler");
        String barista = properties.getProperty("barista.topic");
        String beans = properties.getProperty("beans.topic");

        eventConsumer = new EventConsumer(properties, ev -> {
            publisher.publishEvent(ev);
        }, offsetTracker, barista, beans);

        this.taskExecutor.execute(eventConsumer);
    }


    @PreDestroy
    public void closeConsumer() {
        eventConsumer.stop();
    }

}
