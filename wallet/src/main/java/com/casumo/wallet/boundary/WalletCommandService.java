package com.casumo.wallet.boundary;

import com.casumo.bet.events.entity.BetRejectedFundsNotAvailable;
import com.casumo.bet.events.entity.BetWalletValidated;
import com.casumo.bet.events.entity.MoneyDeposit;
import com.casumo.bet.events.entity.MoneyWithdraw;
import com.casumo.wallet.control.WalletEventProducer;
import com.casumo.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class WalletCommandService {

    WalletEventProducer producer;
    WalletRepository walletRepository;

    @Autowired
    public WalletCommandService(WalletEventProducer producer, WalletRepository walletRepository) {
        this.producer = producer;
        this.walletRepository = walletRepository;
    }

    void addFunds(final String username, final Double amount) {
        MoneyDeposit event = new MoneyDeposit();
        event.setAmount(amount);
        event.setUsername(username);
        producer.publish(event);
    }

    void validateFunds(final String username, final UUID uuid) {

        System.out.println("WalletCommandService.validateFunds");
        System.out.println("username = " + username);
        if (walletRepository.getRemainingAmount(username) > 0) {
            BetWalletValidated event = new BetWalletValidated();
            event.setId(uuid);
            producer.publish(event);
        } else {
            BetRejectedFundsNotAvailable event = new BetRejectedFundsNotAvailable();
            event.setId(uuid);
            producer.publish(event);
        }
    }

    void withdrawnFunds(final String username, Double amount) {
        MoneyWithdraw event = new MoneyWithdraw();
        event.setAmount(amount);
        event.setUsername(username);
        producer.publish(event);
    }

}
