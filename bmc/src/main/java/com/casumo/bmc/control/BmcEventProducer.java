package com.casumo.bmc.control;

import com.casumo.wallet.events.entity.AbstractEvent;
import com.casumo.bmc.configuration.CommonProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class BmcEventProducer {

    private Producer<String, AbstractEvent> producer;
    private String topic;
    private final CommonProperties commonProperties;

    @Autowired
    public BmcEventProducer(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    @PostConstruct
    private void init() {
        producer = new KafkaProducer<>(commonProperties.properties());
        topic = commonProperties.topicBmc;
    }

    public void publish(AbstractEvent event) {
        final ProducerRecord<String, AbstractEvent> record = new ProducerRecord<>(topic, event);
        producer.send(record);
        producer.flush();
    }
}