package com.casumo.bet.control;

import com.casumo.bet.configuration.CommonProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BetEventProducer extends EventProducerFactoryMethod {

    @Autowired
    public BetEventProducer(CommonProperties conf) {
        super(conf);
    }

    public String getTopic() {
        return "bet";
    }

}
