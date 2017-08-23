package com.casumo.orders.orders.control;


import com.casumo.orders.events.entity.*;
import com.casumo.orders.orders.entity.CoffeeOrder;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Consumer;

@Component
public class CoffeeOrders {

    private Map<UUID, CoffeeOrder> coffeeOrders = new ConcurrentHashMap<>();

    public CoffeeOrder get(final UUID orderId) {
        return coffeeOrders.get(orderId);
    }

    public void apply(OrderPlaced event) {
        System.out.println("\n\n\n\n\n\nORDER POSTED --> event : " + event.toString());

        coffeeOrders.putIfAbsent(event.getOrderInfo().getOrderId(), new CoffeeOrder());
        applyFor(event.getOrderInfo().getOrderId(), o -> o.place(event.getOrderInfo()));
    }

    public void apply(OrderCancelled event) {
        System.out.println("--> event : " + event.toString());
        applyFor(event.getOrderId(), CoffeeOrder::cancel);
    }

    public void apply(OrderAccepted event) {
        System.out.println("--> event : " + event.toString());

        applyFor(event.getOrderInfo().getOrderId(), CoffeeOrder::accept);
    }

    public void apply(OrderStarted event) {

        System.out.println("--> event : " + event.toString());

        applyFor(event.getOrderId(), CoffeeOrder::start);
    }

    public void apply(OrderFinished event) {

        System.out.println("--> event : " + event.toString());

        applyFor(event.getOrderId(), CoffeeOrder::finish);
    }

    public void apply(OrderDelivered event) {

        System.out.println("--> event : " + event.toString());

        applyFor(event.getOrderId(), CoffeeOrder::deliver);
    }

    private void applyFor(final UUID orderId, final Consumer<CoffeeOrder> consumer) {
        final CoffeeOrder coffeeOrder = coffeeOrders.get(orderId);
        if (coffeeOrder != null)
            consumer.accept(coffeeOrder);
    }

}
