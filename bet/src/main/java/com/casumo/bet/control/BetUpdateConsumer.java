package com.casumo.bet.control;


import com.casumo.bet.configuration.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;


@Component
public class BetUpdateConsumer extends UpdateConsumerFactoryMethod {

    @Autowired
    public BetUpdateConsumer(ApplicationEventPublisher publisher, CommonProperties commonProperties, OffsetTracker offsetTracker, TaskExecutor taskExecutor) {
        super(publisher, commonProperties, offsetTracker, taskExecutor);
    }

    public String getTopic() {
        return "bet";
    }


}
