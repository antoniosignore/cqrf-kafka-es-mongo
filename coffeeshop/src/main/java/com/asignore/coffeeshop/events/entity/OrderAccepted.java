package com.asignore.coffeeshop.events.entity;

import java.time.Instant;

public class OrderAccepted extends CoffeeEvent {

    private final OrderInfo orderInfo;

    public OrderAccepted(final OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderAccepted(final OrderInfo orderInfo, Instant instant) {
        super(instant);
        this.orderInfo = orderInfo;
    }


    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

}
