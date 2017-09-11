package com.casumo.wallet.control;

import com.casumo.bet.configuration.CommonProperties;
import com.casumo.bet.control.EventProducerFactoryMethod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WalletEventProducer extends EventProducerFactoryMethod {

    @Autowired
    public WalletEventProducer(CommonProperties conf) {
        super(conf);
    }

    public String getTopic() {
        return "wallet";
    }
}
