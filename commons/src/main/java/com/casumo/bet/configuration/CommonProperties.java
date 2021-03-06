package com.casumo.bet.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class CommonProperties {

    @Value("${group.offset}")
    public Integer groupOffset;

    @Value("${bootstrap.servers}")
    public String bootstrapServer;

    @Value("${enable.auto.commit}")
    public Boolean enableAutoCommit;

    @Value("${auto.offset.reset}")
    public String autoOffsetReset;

    @Value("${acks}")
    public String acks;

    @Value("${retries}")
    public Integer retries;

  /*  @Value("${batch.size}")
    public Long batchSize;*/

    @Value("${linger.ms}")
    public Long lingerMs;

    @Value("${buffer.memory}")
    public Long bufferMemory;

    @Value("${key.serializer}")
    public String keySerializer;

    @Value("${key.deserializer}")
    public String keyDeserializer;

    @Value("${value.serializer}")
    public String valueSerializer;

    @Value("${value.deserializer}")
    public String valueDeserializer;

    @Bean
    public Properties properties() {

        Properties properties = new Properties();
        properties.put("group-offset", groupOffset);
        properties.put("bootstrap.servers", bootstrapServer);
        properties.put("topic.bet", "bet");
        properties.put("topic.wallet", "wallet");
        properties.put("topic.bmc", "bmc");

        properties.put("enable.auto.commit", enableAutoCommit);
        properties.put("auto.offset.reset", autoOffsetReset);

        properties.put("key.serializer", keySerializer);
        properties.put("key.deserializer", keyDeserializer);

        properties.put("value.serializer", valueSerializer );
        properties.put("value.deserializer", valueDeserializer );

        properties.put("acks", acks);
        properties.put("retries", retries);
        properties.put("linger.ms", lingerMs);
        properties.put("buffer.memory", bufferMemory);

        return properties;

    }
}
