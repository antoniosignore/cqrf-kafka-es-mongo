package com.asignore.coffeeshop.orders.boundary;

import com.asignore.coffeeshop.events.entity.CoffeeType;
import com.asignore.coffeeshop.events.entity.OrderInfo;
import com.asignore.coffeeshop.orders.entity.CoffeeOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class OrdersResource {

    private static final String template = "Hello, %s!";
    final
    OrderCommandService commandService;
    final
    OrderQueryService queryService;
    private final AtomicLong counter = new AtomicLong();

    @Autowired
    public OrdersResource(OrderCommandService commandService, OrderQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(),
                String.format(template, name));
    }

    @RequestMapping(value = "/pippo", method = RequestMethod.POST)
    public ResponseEntity responseEntityCoffee(Greeting order) {

        System.out.println("order = " + order);

        return new ResponseEntity(
                HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/orders", method = RequestMethod.POST)
    public ResponseEntity orderCoffee(OrderInfo order, HttpServletRequest request) {

        System.out.println("OrdersResource.orderCoffee");

        if (order == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        final CoffeeType coffeeType = order.getType();

        final UUID orderId = UUID.randomUUID();
        commandService.placeOrder(new OrderInfo(orderId, coffeeType, order.getBeanOrigin()));

        URI location =
                ServletUriComponentsBuilder.fromServletMapping(request).path("/orders/{id}").build()
                        .expand(orderId).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity(headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/orders/{id}", method = RequestMethod.GET)
    public CoffeeOrder getOrder(@PathVariable("id") UUID orderId)
            throws ChangeSetPersister.NotFoundException {
        final CoffeeOrder order = queryService.getOrder(orderId);

        if (order == null)
            throw new ChangeSetPersister.NotFoundException();

        return order;
    }

}
