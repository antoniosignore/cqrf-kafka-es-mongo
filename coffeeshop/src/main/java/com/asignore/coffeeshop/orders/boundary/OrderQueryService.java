package com.asignore.coffeeshop.orders.boundary;


import com.asignore.coffeeshop.orders.control.CoffeeOrders;
import com.asignore.coffeeshop.orders.entity.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderQueryService {

    @Autowired
    CoffeeOrders coffeeOrders;

    public CoffeeOrder getOrder(final UUID orderId) {
        return coffeeOrders.get(orderId);
    }

}
