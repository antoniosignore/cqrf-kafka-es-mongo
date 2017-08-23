package com.casumo.orders.events.entity;

import java.time.Instant;

public class BeansStored extends CoffeeEvent {

    private final String beanOrigin;
    private final int amount;

    public BeansStored(final String beanOrigin, final int amount) {
        this.beanOrigin = beanOrigin;
        this.amount = amount;
    }

    public BeansStored(final String beanOrigin, final int amount, final Instant instant) {
        super(instant);
        this.beanOrigin = beanOrigin;
        this.amount = amount;
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

    public int getAmount() {
        return amount;
    }

}
