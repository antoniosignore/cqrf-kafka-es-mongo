package com.casumo.orders;

import com.casumo.bet.events.entity.BetPlaced;
import com.casumo.bet.events.entity.BetInfo;
import org.junit.Test;

import java.nio.charset.StandardCharsets;
import java.util.UUID;

public class EventSerializerTest {

    BetPlaced event = new BetPlaced(new BetInfo(UUID.randomUUID(), CoffeeType.ESPRESSO, "pippo"));
    private EventSerializer cut = new EventSerializer();
    private EventDeserializer dut = new EventDeserializer();

    @Test
    public void test() {
        final String actual = new String(cut.serialize(null, event), StandardCharsets.UTF_8);

        System.out.println("actual = " + actual);

        String data = "{\"@class\":\"com.casumo.bet.events.entity.BetPlaced\",\"instant\":1503440057.005000000,\"orderInfo\":{\"orderId\":\"57d4d147-0cce-44e5-b309-23bb6f8d2a11\",\"type\":\"ESPRESSO\",\"beanOrigin\":\"pippo\"}}";
        CoffeeEvent event = dut.deserialize(null, data.getBytes(StandardCharsets.UTF_8));

        System.out.println("event.getInstant() = " + event.getInstant());
        System.out.println("event.getInstant() = " + event.getInstant());
        System.out.println("event.getInstant() = " + event.getInstant());
    }


}
