package com.casumo.orders.orders.boundary;


import com.casumo.orders.orders.control.CoffeeOrders;
import com.casumo.orders.orders.entity.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderQueryService {

    final
    CoffeeOrders coffeeOrders;

    @Autowired
    public OrderQueryService(CoffeeOrders coffeeOrders) {
        this.coffeeOrders = coffeeOrders;
    }

    public CoffeeOrder getOrder(final UUID orderId) {
        return coffeeOrders.get(orderId);
    }

}
