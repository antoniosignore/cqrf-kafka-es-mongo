package com.casumo.bmc.control;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;

import java.util.Properties;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Consumer;

import static java.util.Arrays.asList;

public class EventConsumer implements Runnable {

    private final KafkaConsumer<String, AbstractEvent> consumer;
    private final OffsetTracker offsetTracker;
    private final Consumer<AbstractEvent> eventConsumer;
    private final AtomicBoolean closed = new AtomicBoolean();

    public EventConsumer(Properties kafkaProperties, Consumer<AbstractEvent> eventConsumer, OffsetTracker offsetTracker, String... topics) {
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

        System.out.println("########### EventConsumer.consume");

        ConsumerRecords<String, AbstractEvent> records = consumer.poll(Long.MAX_VALUE);
        for (ConsumerRecord<String, AbstractEvent> record : records) {
            eventConsumer.accept(record.value());
            offsetTracker.trackOffset(record.topic(), record.partition(), record.offset() + 1);
        }
    }

    public void stop() {
        closed.set(true);
        consumer.wakeup();
    }

}
