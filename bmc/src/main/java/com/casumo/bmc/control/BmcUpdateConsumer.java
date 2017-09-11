package com.casumo.bmc.control;

import com.casumo.bet.configuration.CommonProperties;
import com.casumo.bet.control.OffsetTracker;
import com.casumo.bet.control.UpdateConsumerFactoryMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Component;


@Component
public class BmcUpdateConsumer extends UpdateConsumerFactoryMethod {

    @Autowired
    public BmcUpdateConsumer(ApplicationEventPublisher publisher, CommonProperties commonProperties, OffsetTracker offsetTracker, TaskExecutor taskExecutor) {
        super(publisher, commonProperties, offsetTracker, taskExecutor);
    }

    public String getTopic() {
        return "bmc";
    }

}
