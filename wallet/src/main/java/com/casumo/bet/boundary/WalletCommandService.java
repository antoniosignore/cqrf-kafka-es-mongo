package com.casumo.bet.boundary;

import com.casumo.bet.control.WalletStorage;
import com.casumo.bet.events.control.EventProducerFactoryMethod;
import com.casumo.bet.events.entity.BetRejectedFundsNotAvailable;
import com.casumo.bet.events.entity.BetWalletValidated;
import com.casumo.bet.events.entity.MoneyBalance;
import com.casumo.bet.events.entity.MoneyDeposit;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class WalletCommandService {

    EventProducerFactoryMethod eventProducerFactoryMethod;
    WalletStorage walletStorage;

    @Autowired
    public WalletCommandService(EventProducerFactoryMethod eventProducerFactoryMethod, WalletStorage walletStorage) {
        this.eventProducerFactoryMethod = eventProducerFactoryMethod;
        this.walletStorage = walletStorage;
    }

    public void addFunds(final String username, final Double amount) {
        eventProducerFactoryMethod.publish(new MoneyDeposit(username, amount));
    }

    void validateBeans(final String username, final UUID orderId) {
        if (walletStorage.getRemainingAmount(username) > 0)
            eventProducerFactoryMethod.publish(new BetWalletValidated(orderId));
        else
            eventProducerFactoryMethod.publish(new BetRejectedFundsNotAvailable(orderId));
    }

    void fetchBeans(final String username) {

        eventProducerFactoryMethod.publish(new MoneyBalance(username));
    }

}
