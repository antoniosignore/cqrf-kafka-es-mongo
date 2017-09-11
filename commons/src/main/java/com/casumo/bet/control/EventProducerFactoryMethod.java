package com.casumo.bet.control;

import com.casumo.bet.configuration.CommonProperties;
import com.casumo.bet.events.entity.AbstractEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;

public abstract class EventProducerFactoryMethod {

    private final CommonProperties conf;
    private Producer<String, AbstractEvent> producer;
    private String topic;

    @Autowired
    public EventProducerFactoryMethod(CommonProperties conf) {
        this.conf = conf;
    }

    public abstract String getTopic();

    @PostConstruct
    private void init() {
        producer = new KafkaProducer<>(conf.properties());
        //topic = "bet";
        topic = getTopic();
    }

    public void publish(AbstractEvent event) {

        System.out.println("EventProducerFactoryMethod.publish");
        System.out.println("PUBLISH ON BET topic --> event = " + event);

        final ProducerRecord<String, AbstractEvent> record = new ProducerRecord<>(topic, event);
        producer.send(record);
        producer.flush();
    }
}
