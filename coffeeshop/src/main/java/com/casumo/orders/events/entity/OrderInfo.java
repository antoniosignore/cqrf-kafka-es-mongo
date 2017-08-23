package com.casumo.orders.events.entity;

import java.io.Serializable;
import java.util.UUID;

public class OrderInfo implements Serializable {

    private UUID orderId;
    private CoffeeType type;
    private String beanOrigin;

    public OrderInfo() {
    }

    public OrderInfo(final UUID orderId, final CoffeeType type, final String beanOrigin) {
        this.orderId = orderId;
        this.type = type;
        this.beanOrigin = beanOrigin;
    }

    public UUID getOrderId() {
        return orderId;
    }

    public void setOrderId(UUID orderId) {
        this.orderId = orderId;
    }

    public CoffeeType getType() {
        return type;
    }

    public void setType(CoffeeType type) {
        this.type = type;
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

    public void setBeanOrigin(String beanOrigin) {
        this.beanOrigin = beanOrigin;
    }
}
