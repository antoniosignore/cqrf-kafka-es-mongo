package com.casumo.bet.control;

import com.casumo.bet.configuration.CommonProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.Serializable;

@Component
public class BetEventProducer {

    private Producer<String, Serializable> producer;
    private String topic;
    private final CommonProperties conf;

    @Autowired
    public BetEventProducer(CommonProperties conf) {
        this.conf = conf;
    }

    @PostConstruct
    private void init() {
        producer = new KafkaProducer<>(conf.properties());
        topic = "bet";
    }

    public void publish(Serializable event) {

        System.out.println("BetEventProducer.publish");
        System.out.println("PUBLISH ON BET topic --> event = " + event);

        final ProducerRecord<String, Serializable> record = new ProducerRecord<>(topic, event);
        producer.send(record);
        producer.flush();
    }
}
