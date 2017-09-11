package com.casumo.bet.control;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.io.Serializable;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class EventConsumer implements Runnable {

    private final KafkaConsumer<String, Serializable> consumer;
    private final OffsetTracker offsetTracker;
    private final Consumer<Serializable> eventConsumer;
    private final AtomicBoolean closed = new AtomicBoolean();

    public EventConsumer(Properties kafkaProperties, Consumer<Serializable> eventConsumer, OffsetTracker offsetTracker, String... topics) {

        System.out.println("EventConsumer.EventConsumer");
        System.out.println("topics = " + topics);

        List<String> strings = asList(topics);
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            System.out.println("TOPIC s = " + s);
        }
        this.eventConsumer = eventConsumer;
        this.offsetTracker = offsetTracker;
        consumer = new KafkaConsumer<>(kafkaProperties);
        consumer.subscribe(asList(topics), new OffsetTrackingRebalanceListener(consumer, offsetTracker));
    }

    @Override
    public void run() {
        try {
            while (!closed.get()) {
                consume();
            }
        } catch (WakeupException e) {
            // will wakeup for closing
        } finally {
            consumer.close();
        }
    }

    private void consume() {

        System.out.println("BET EventConsumer.consume");

        ConsumerRecords<String, Serializable> records = consumer.poll(Long.MAX_VALUE);
        for (ConsumerRecord<String, Serializable> record : records) {
            eventConsumer.accept(record.value());
            offsetTracker.trackOffset(record.topic(), record.partition(), record.offset() + 1);
        }
    }

    public void stop() {
        closed.set(true);
        consumer.wakeup();
    }

}
