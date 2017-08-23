package com.casumo.orders.events.entity;

import java.time.Instant;

public class BeansFetched extends CoffeeEvent {

    private final String beanOrigin;

    public BeansFetched(final String beanOrigin) {
        this.beanOrigin = beanOrigin;
    }

    public BeansFetched(final String beanOrigin, final Instant instant) {
        super(instant);
        this.beanOrigin = beanOrigin;
    }

    public String getBeanOrigin() {
        return beanOrigin;
    }

}
