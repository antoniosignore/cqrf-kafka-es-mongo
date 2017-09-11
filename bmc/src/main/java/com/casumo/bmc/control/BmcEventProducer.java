package com.casumo.bmc.control;

import com.casumo.bet.configuration.CommonProperties;
import com.casumo.bet.control.EventProducerFactoryMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BmcEventProducer extends EventProducerFactoryMethod {

    @Autowired
    public BmcEventProducer(CommonProperties conf) {
        super(conf);
    }

    public String getTopic() {
        return "bmc";
    }
}
