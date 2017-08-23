package com.asignore.coffeeshop;

import com.asignore.coffeeshop.events.control.EventDeserializer;
import com.asignore.coffeeshop.events.entity.CoffeeEvent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.nio.charset.StandardCharsets;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class EventDeserializerTest {

    private EventDeserializer cut = new EventDeserializer();

    @Test
    public void test() {
        String data = "{\"@class\":\"com.asignore.coffeeshop.events.entity.OrderPlaced\",\"instant\":1503440057.005000000,\"orderInfo\":{\"orderId\":\"57d4d147-0cce-44e5-b309-23bb6f8d2a11\",\"type\":\"ESPRESSO\",\"beanOrigin\":\"pippo\"}}";
        CoffeeEvent actual = cut.deserialize(null, data.getBytes(StandardCharsets.UTF_8));
    }

    private void assertEventEquals(final CoffeeEvent actual, final CoffeeEvent expected) {
        assertEquals(expected, actual);
    }


}
