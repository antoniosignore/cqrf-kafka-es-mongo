package com.casumo.wallet.repository;

import com.casumo.bet.events.entity.MoneyDeposit;
import com.casumo.bet.events.entity.MoneyWithdraw;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WalletRepository {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WalletRepository.class);

    private Map<String, Double> balances = new ConcurrentHashMap<>();

    public Map<String, Double> getBalances() {
        return Collections.synchronizedMap(balances);
    }

    public Double getRemainingAmount(final String username) {
        return balances.getOrDefault(username, 0D);
    }

    @Async
    @EventListener
    public void apply(MoneyDeposit moneyDeposit) {
        System.out.println("deposit : " + moneyDeposit.getAmount());
        Double currentValue = balances.get(moneyDeposit.getUsername());
        balances.put(moneyDeposit.getUsername(), currentValue + moneyDeposit.getAmount());
    }

    @Async
    @EventListener
    public void apply(MoneyWithdraw withdraw) {

        System.out.println("withdraw : " + withdraw.getAmount());

        Double currentValue = balances.get(withdraw.getUsername());
        balances.put(withdraw.getUsername(), currentValue - withdraw.getAmount());
    }

}
