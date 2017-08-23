package com.casumo.orders.events.entity;

import com.fasterxml.jackson.annotation.JsonTypeInfo;

import java.time.Instant;
import java.util.Objects;

@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "@class")
public abstract class CoffeeEvent {

    private final Instant instant;

    protected CoffeeEvent() {
        instant = Instant.now();
    }

    protected CoffeeEvent(final Instant instant) {
        Objects.requireNonNull(instant);
        this.instant = instant;
    }

    public Instant getInstant() {
        return instant;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        final CoffeeEvent that = (CoffeeEvent) o;

        return instant.equals(that.instant);
    }

    @Override
    public int hashCode() {
        return instant.hashCode();
    }

}
