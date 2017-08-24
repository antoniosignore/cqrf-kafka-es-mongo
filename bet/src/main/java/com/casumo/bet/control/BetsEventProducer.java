package com.casumo.bet.control;

import com.casumo.CommonProperties;
import com.casumo.bet.events.control.EventProducerFactoryMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BetsEventProducer extends EventProducerFactoryMethod {

    @Autowired
    CommonProperties properties;

    @Override
    protected String getTopic() {

        return properties.topicBet;
    }
}
