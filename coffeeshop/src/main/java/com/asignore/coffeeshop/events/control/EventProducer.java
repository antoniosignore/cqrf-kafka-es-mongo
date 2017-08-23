package com.asignore.coffeeshop.events.control;

import com.asignore.coffeeshop.KafkaConfigurer;
import com.asignore.coffeeshop.events.entity.CoffeeEvent;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Properties;

@Component
public class EventProducer {

    @Autowired
    KafkaConfigurer kafkaProperties;
    private Producer<String, CoffeeEvent> producer;
    private String topic;
    @Autowired
    private ApplicationContext context;

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
        properties.put("value.deserializer", "com.asignore.coffeeshop.events.control.EventDeserializer");

        properties.put("acks", "all");
        properties.put("retries", 0);
        properties.put("batch.size", 16384);
        properties.put("linger.ms", 0);
        properties.put("buffer.memory", 33554432);
        properties.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        properties.put("value.serializer", "com.asignore.coffeeshop.events.control.EventSerializer");

        producer = new KafkaProducer<>(properties);
        topic = properties.getProperty("orders.topic");
    }

    public void publish(CoffeeEvent event) {

        System.out.println("############EventProducer.publish");
        System.out.println("event.getInstant() = " + event.getInstant());

        final ProducerRecord<String, CoffeeEvent> record = new ProducerRecord<>(topic, event);
        producer.send(record);
        producer.flush();
    }

    @PreDestroy
    public void close() {
        producer.close();
    }

}

