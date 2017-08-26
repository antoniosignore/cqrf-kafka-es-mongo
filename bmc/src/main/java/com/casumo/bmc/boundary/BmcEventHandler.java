package com.casumo.bmc.boundary;

import com.casumo.wallet.events.entity.BetAccepted;
import com.casumo.bmc.configuration.CommonProperties;
import com.casumo.bmc.control.EventConsumer;
import com.casumo.bmc.control.OffsetTracker;
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
    final CommonProperties commonProperties;
    final OffsetTracker offsetTracker;
    final BmcCommandService bmcCommandService;
    private EventConsumer eventConsumer;
    final TaskExecutor executor;

    @Autowired
    public BmcEventHandler(ApplicationEventPublisher publisher,
                           CommonProperties commonProperties,
                           OffsetTracker offsetTracker,
                           BmcCommandService bmcCommandService,
                           TaskExecutor executor) {
        this.publisher = publisher;
        this.commonProperties = commonProperties;
        this.offsetTracker = offsetTracker;
        this.bmcCommandService = bmcCommandService;
        this.executor = executor;
    }

    @EventListener
    public void handle(BetAccepted event) {
    bmcCommandService.makeCoffee(event.getBetInfo());
    }

    @PostConstruct
    private void initConsumer() {
        Properties properties = commonProperties.properties();
        properties.put("group.id", "bmc-handler");
        eventConsumer = new EventConsumer(properties,
                publisher::publishEvent, offsetTracker,
                commonProperties.topicBet);

        this.executor.execute(eventConsumer);
    }

}
