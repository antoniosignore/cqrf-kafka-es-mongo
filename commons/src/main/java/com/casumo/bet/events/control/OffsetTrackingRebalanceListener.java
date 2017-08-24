package com.casumo.bet.events.control;

import com.casumo.bet.events.entity.AbstractEvent;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRebalanceListener;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.TopicPartition;

import java.util.Collection;

public class OffsetTrackingRebalanceListener implements ConsumerRebalanceListener {

    private final OffsetTracker offsetTracker;
    private final Consumer<String, AbstractEvent> consumer;

    public OffsetTrackingRebalanceListener(KafkaConsumer<String, AbstractEvent> consumer, OffsetTracker offsetTracker) {
        this.consumer = consumer;
        this.offsetTracker = offsetTracker;
    }

    public void onPartitionsRevoked(Collection<TopicPartition> topicPartitions) {
        for (TopicPartition topicPartition : topicPartitions) {
            offsetTracker.trackOffset(topicPartition.topic(), topicPartition.partition(), consumer.position(topicPartition));
        }
    }

    public void onPartitionsAssigned(Collection<TopicPartition> topicPartitions) {
        for (TopicPartition topicPartition : topicPartitions) {
            consumer.seek(topicPartition, offsetTracker.nextOffset(topicPartition.topic(), topicPartition.partition()));
        }
    }

}
