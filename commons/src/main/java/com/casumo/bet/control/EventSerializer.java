package com.casumo.bet.control;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Serializer;

import java.io.Serializable;
import java.util.Map;

public class EventSerializer implements Serializer<Serializable> {

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public byte[] serialize(final String topic, final Serializable event) {
        if (event == null)
            return null;

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.findAndRegisterModules();

        objectMapper.enableDefaultTyping(
                ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);

        String jsonInString = null;
        try {
            jsonInString = objectMapper.writeValueAsString(event);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonInString.getBytes();
    }

    @Override
    public void close() {
        // nothing to do
    }

}
