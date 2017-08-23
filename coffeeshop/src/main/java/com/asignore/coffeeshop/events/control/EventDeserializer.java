package com.asignore.coffeeshop.events.control;

import com.asignore.coffeeshop.events.entity.CoffeeEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.serialization.Deserializer;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Logger;

public class EventDeserializer implements Deserializer<CoffeeEvent> {

    private static final Logger logger = Logger.getLogger(EventDeserializer.class.getName());

    @Override
    public void configure(final Map<String, ?> configs, final boolean isKey) {
        // nothing to configure
    }

    @Override
    public CoffeeEvent deserialize(final String topic, final byte[] data) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.findAndRegisterModules();

        String jsonInString = new String(data);
        CoffeeEvent user1;
        try {
            user1 = mapper.readValue(jsonInString, CoffeeEvent.class);
            return user1;
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
