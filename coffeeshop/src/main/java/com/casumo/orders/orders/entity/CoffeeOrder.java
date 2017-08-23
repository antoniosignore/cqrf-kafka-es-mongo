package com.casumo.orders.orders.entity;


import com.casumo.orders.events.entity.OrderInfo;

public class CoffeeOrder {

    private CoffeeOrderState state;
    private OrderInfo orderInfo;

    public void place(final OrderInfo orderInfo) {
        System.out.println("###########################--> PLACED");
        state = CoffeeOrderState.PLACED;
        this.orderInfo = orderInfo;
    }

    public void accept() {

        System.out.println("###########################--> ACCEPTED");

        state = CoffeeOrderState.ACCEPTED;
    }

    public void cancel() {

        System.out.println("###########################--> CANCELLED");

        state = CoffeeOrderState.CANCELLED;
    }

    public void start() {

        System.out.println("###########################--> STARTED");

        state = CoffeeOrderState.STARTED;
    }

    public void finish() {
        state = CoffeeOrderState.FINISHED;
    }

    public void deliver() {

        System.out.println("###########################--> DELIVERED");

        state = CoffeeOrderState.DELIVERED;
    }

    public CoffeeOrderState getState() {
        return state;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }

    public enum CoffeeOrderState {
        PLACED,
        ACCEPTED,
        STARTED,
        FINISHED,
        DELIVERED,
        CANCELLED
    }

}
