package com.casumo.bet.events.control;

import com.casumo.CommonProperties;
import com.casumo.bet.events.entity.AbstractEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public abstract class EventProducerFactoryMethod {

    private Producer<String, AbstractEvent> producer;
    private String topic;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private CommonProperties commonProperties;

    protected abstract String getTopic();

    @PostConstruct
    private void init() {
        producer = new KafkaProducer<>(commonProperties.properties());
        topic = getTopic();
    }

    public void publish(AbstractEvent event) {

        System.out.println("############EventProducerFactoryMethod.publish");
        System.out.println("event.getInstant() = " + event.getInstant());
        System.out.println("SENDING event.getClass().toGenericString() = " + event.getClass().toGenericString());

        final ProducerRecord<String, AbstractEvent> record = new ProducerRecord<>(topic, event);
        producer.send(record);
        producer.flush();
    }

    @PreDestroy
    public void close() {
        producer.close();
    }

}

