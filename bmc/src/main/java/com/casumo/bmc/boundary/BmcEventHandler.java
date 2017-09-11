package com.casumo.bmc.boundary;

import com.casumo.bet.configuration.CommonProperties;
import com.casumo.bet.control.EventConsumer;
import com.casumo.bet.control.OffsetTracker;
import com.casumo.bet.events.entity.BetAccepted;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Properties;

@Component
public class BmcEventHandler {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BmcCommandService.class);

    private final ApplicationEventPublisher publisher;
    final CommonProperties conf;
    final OffsetTracker offsetTracker;
    final BmcCommandService bmcCommandService;
    private EventConsumer eventConsumer;
    final TaskExecutor executor;

    @Autowired
    public BmcEventHandler(ApplicationEventPublisher publisher,
                           CommonProperties conf,
                           OffsetTracker offsetTracker,
                           BmcCommandService bmcCommandService,
                           TaskExecutor executor) {
        this.publisher = publisher;
        this.conf = conf;
        this.offsetTracker = offsetTracker;
        this.bmcCommandService = bmcCommandService;
        this.executor = executor;
    }

    @EventListener
    public void handleBetAccepted(BetAccepted event) {

        System.out.println("BmcEventHandler.handleBetAccepted");
        System.out.println("event = " + event);

        bmcCommandService.makeCoffee(event.getBetInfo());
    }

    @PostConstruct
    private void initConsumer() {
        Properties properties = conf.properties();
        properties.put("group.id", "bmc-handler");
        eventConsumer = new EventConsumer(properties, ev -> {
            System.out.println("\n consuming Bet event. Firing  --> " + ev);
            publisher.publishEvent(ev);
        }, offsetTracker, "bet");

        this.executor.execute(eventConsumer);
    }

}
