package com.asignore.coffeeshop.events.entity;

import java.time.Instant;

public class OrderPlaced extends CoffeeEvent {

    private OrderInfo orderInfo;

    public OrderPlaced() {
    }

    public OrderPlaced(final OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

    public OrderPlaced(final OrderInfo orderInfo, Instant instant) {
        super(instant);
        this.orderInfo = orderInfo;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public void setOrderInfo(OrderInfo orderInfo) {
        this.orderInfo = orderInfo;
    }

}
