package com.asignore.coffeeshop.orders.boundary;

import com.asignore.coffeeshop.events.control.EventProducer;
import com.asignore.coffeeshop.events.entity.*;
import com.asignore.coffeeshop.orders.control.CoffeeOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderCommandService {

    @Autowired
    EventProducer eventProducer;

    @Autowired
    CoffeeOrders coffeeOrders;

    public void placeOrder(final OrderInfo orderInfo) {
        eventProducer.publish(new OrderPlaced(orderInfo));
    }

    void acceptOrder(final UUID orderId) {
        final OrderInfo orderInfo = coffeeOrders.get(orderId).getOrderInfo();
        eventProducer.publish(new OrderAccepted(orderInfo));
    }

    void cancelOrder(final UUID orderId, final String reason) {
        eventProducer.publish(new OrderCancelled(orderId, reason));
    }

    void startOrder(final UUID orderId) {
        eventProducer.publish(new OrderStarted(orderId));
    }

    void finishOrder(final UUID orderId) {
        eventProducer.publish(new OrderFinished(orderId));
    }

    void deliverOrder(final UUID orderId) {
        eventProducer.publish(new OrderDelivered(orderId));
    }

}
