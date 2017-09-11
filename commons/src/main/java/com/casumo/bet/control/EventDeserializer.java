package com.casumo.bet.control;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.logging.Logger;

public class EventDeserializer implements Deserializer<Serializable> {

    private static final Logger logger = Logger.getLogger(EventDeserializer.class.getName());

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public Serializable deserialize(final String topic, final byte[] data) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        String jsonInString = new String(data);
        Serializable event;
        try {
            event = mapper.readValue(jsonInString, Serializable.class);
            return event;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void close() {
        // nothing to do
    }

}
