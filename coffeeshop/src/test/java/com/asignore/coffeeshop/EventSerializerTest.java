package com.asignore.coffeeshop;

import com.asignore.coffeeshop.events.control.EventDeserializer;
import com.asignore.coffeeshop.events.control.EventSerializer;
import com.asignore.coffeeshop.events.entity.CoffeeEvent;
import com.asignore.coffeeshop.events.entity.CoffeeType;
import com.asignore.coffeeshop.events.entity.OrderInfo;
import com.asignore.coffeeshop.events.entity.OrderPlaced;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class EventSerializerTest {

    OrderPlaced event = new OrderPlaced(new OrderInfo(UUID.randomUUID(), CoffeeType.ESPRESSO, "pippo"));
    private EventSerializer cut = new EventSerializer();
    private EventDeserializer dut = new EventDeserializer();

    @Test
    public void test() {
        final String actual = new String(cut.serialize(null, event), StandardCharsets.UTF_8);

        System.out.println("actual = " + actual);

        String data = "{\"@class\":\"com.asignore.coffeeshop.events.entity.OrderPlaced\",\"instant\":1503440057.005000000,\"orderInfo\":{\"orderId\":\"57d4d147-0cce-44e5-b309-23bb6f8d2a11\",\"type\":\"ESPRESSO\",\"beanOrigin\":\"pippo\"}}";
        CoffeeEvent event = dut.deserialize(null, data.getBytes(StandardCharsets.UTF_8));

        System.out.println("event.getInstant() = " + event.getInstant());
        System.out.println("event.getInstant() = " + event.getInstant());
        System.out.println("event.getInstant() = " + event.getInstant());
    }


}
