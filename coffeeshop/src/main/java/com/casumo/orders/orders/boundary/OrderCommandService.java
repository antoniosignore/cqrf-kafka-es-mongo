package com.casumo.orders.orders.boundary;

import com.casumo.orders.events.control.EventProducer;
import com.casumo.orders.events.entity.*;
import com.casumo.orders.orders.control.CoffeeOrders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderCommandService {

    final
    EventProducer eventProducer;

    final
    CoffeeOrders coffeeOrders;

    @Autowired
    public OrderCommandService(EventProducer eventProducer, CoffeeOrders coffeeOrders) {
        this.eventProducer = eventProducer;
        this.coffeeOrders = coffeeOrders;
    }

    public void placeOrder(final OrderInfo orderInfo) {
        System.out.println("OrderCommandService.placeOrder");
        System.out.println("orderInfo = " + orderInfo);
        eventProducer.publish(new OrderPlaced(orderInfo));
    }

    void acceptOrder(final UUID orderId) {
        System.out.println("################# :OrderCommandService.acceptOrder");
        final OrderInfo orderInfo = coffeeOrders.get(orderId).getOrderInfo();
        eventProducer.publish(new OrderAccepted(orderInfo));
    }

    void cancelOrder(final UUID orderId, final String reason) {
        System.out.println("OrderCommandService.cancelOrder");
        System.out.println("OrderCommandService.cancelOrder");
        eventProducer.publish(new OrderCancelled(orderId, reason));
    }

    void startOrder(final UUID orderId) {
        System.out.println("OrderCommandService.startOrder");
        eventProducer.publish(new OrderStarted(orderId));
    }

    void finishOrder(final UUID orderId) {
        System.out.println("OrderCommandService.finishOrder");
        eventProducer.publish(new OrderFinished(orderId));
    }

    void deliverOrder(final UUID orderId) {
        System.out.println("OrderCommandService.deliverOrder");
        eventProducer.publish(new OrderDelivered(orderId));
    }

}
