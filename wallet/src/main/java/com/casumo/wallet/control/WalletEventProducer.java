package com.casumo.wallet.control;

import com.casumo.wallet.configuration.CommonProperties;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class WalletEventProducer {

    private Producer<String, AbstractEvent> producer;
    private String topic;
    private final CommonProperties commonProperties;

    @Autowired
    public WalletEventProducer(CommonProperties commonProperties) {
        this.commonProperties = commonProperties;
    }

    @PostConstruct
    private void init() {
        producer = new KafkaProducer<>(commonProperties.properties());
        topic = "wallet";
    }

    public void publish(AbstractEvent event) {
        System.out.println("WalletEventProducer.publish");
        System.out.println("PUBLISH event to WALLET= " + event);
        final ProducerRecord<String, AbstractEvent> record = new ProducerRecord<>(topic, event);
        producer.send(record);
        producer.flush();
    }
}
