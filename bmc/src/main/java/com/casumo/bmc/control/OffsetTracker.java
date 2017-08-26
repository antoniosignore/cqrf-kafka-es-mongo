package com.casumo.bmc.control;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class OffsetTracker {

    private final Map<String, Long> topicPartitionOffsets = new ConcurrentHashMap<>();

    public void trackOffset(String topic, int partition, long offset) {
        String key = calculateKey(topic, partition);
        topicPartitionOffsets.put(key, offset);
    }

    public long nextOffset(String topic, int partition) {
        String key = calculateKey(topic, partition);
        return topicPartitionOffsets.getOrDefault(key, 0L);
    }

    private String calculateKey(String topic, int partition) {
        return topic + partition;
    }

}
