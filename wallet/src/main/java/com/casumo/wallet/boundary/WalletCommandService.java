package com.casumo.wallet.boundary;

import com.casumo.wallet.control.*;
import com.casumo.wallet.events.entity.BetRejectedFundsNotAvailable;
import com.casumo.wallet.events.entity.BetWalletValidated;
import com.casumo.wallet.events.entity.MoneyWithdraw;
import com.casumo.wallet.events.entity.MoneyDeposit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletCommandService {

    WalletEventProducer eventProducerFactoryMethod;
    WalletRepository walletRepository;

    @Autowired
    public WalletCommandService(WalletEventProducer eventProducerFactoryMethod, WalletRepository walletRepository) {
        this.eventProducerFactoryMethod = eventProducerFactoryMethod;
        this.walletRepository = walletRepository;
    }

    public void addFunds(final String username, final Double amount) {
        eventProducerFactoryMethod.publish(new MoneyDeposit(username, amount));
    }

    void validateFunds(final String username, final UUID orderId) {
        if (walletRepository.getRemainingAmount(username) > 0)
            eventProducerFactoryMethod.publish(new BetWalletValidated(orderId));
        else
            eventProducerFactoryMethod.publish(new BetRejectedFundsNotAvailable(orderId));
    }

    void withdrawnFunds(final String username, Double amount) {
        eventProducerFactoryMethod.publish(new MoneyWithdraw(username, amount));
    }

}
