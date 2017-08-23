package com.casumo.orders.events.entity;

import java.time.Instant;

public class CoffeeBrewStarted extends CoffeeEvent {

    private final OrderInfo orderInfo;

    public CoffeeBrewStarted(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public CoffeeBrewStarted(OrderInfo orderInfo, Instant instant) {
        super(instant);
        this.orderInfo = orderInfo;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

}
